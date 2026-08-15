# Day 06 - Advanced LSP, ISP & DIP

## Overview

Today's focus was on completing the SOLID principles with a deep dive into the formal rules governing the **Liskov Substitution Principle (LSP)**, followed by the **Interface Segregation Principle (ISP)** and the **Dependency Inversion Principle (DIP)**.

**Topics Covered:**
- Formal Definition of LSP (Barbara Liskov & Jeannette Wing)
- **Signature Rules:** Contravariance of Arguments, Covariance of Return Types, Exception Constraints
- **Property Rules:** Invariant Preservation, History Constraint
- **Method Rules (Design by Contract):** Preconditions & Postconditions
- **Interface Segregation Principle (ISP):** Fat Interfaces vs. Role Interfaces
- **Dependency Inversion Principle (DIP):** Abstraction, Inversion of Control (IoC), and Constructor Injection
- Full SOLID Synthesis for Low-Level Design (LLD)

---

# 1. Advanced Liskov Substitution Principle (LSP)

## The Core Concept

Most surface-level definitions describe LSP as:
> *"Subtypes must be substitutable for their base types without altering program correctness."*

In practice, syntactic inheritance (`extends` in Java) only guarantees **type compatibility at compile time**. It does **not** guarantee **behavioral compatibility at runtime**. A child class can compile without errors while fundamentally breaking client expectations.

To ensure true behavioral subtyping, Barbara Liskov and Jeannette Wing defined rules across three categories:
1. **Signature Rules** (Syntactic Subtyping)
2. **Property Rules** (State & Invariant Subtyping)
3. **Method Rules** (Design by Contract Subtyping)

---

## 1.1 Signature Rules

Signature rules dictate how method parameters, return types, and exceptions must behave during inheritance.

### A. Method Argument Rule (Contravariance of Arguments)
> A subclass method must accept arguments that are as general as (or more general than) the parent method.

In strict type theory, argument types should be **contravariant**. In Java, overriding requires the exact same parameter type. If a subclass overloads a method with a narrower type, client polymorphism breaks.

#### ❌ Violation
```java
class Food {}
class Seed extends Food {}

class Bird {
    public void eat(Food food) {
        System.out.println("Eating general food...");
    }
}

class Sparrow extends Bird {
    // Overloading instead of overriding: accepts ONLY Seed
    public void eat(Seed seed) {
        System.out.println("Eating seeds...");
    }
}
```

**Why it fails client code:**
```java
public class Client {
    public static void feedBird(Bird bird, Food food) {
        // If bird is Sparrow, it defaults to Bird's eat(Food) 
        // because Sparrow does not properly substitute the eat behavior for all Food.
        bird.eat(food);
    }
}
```

#### ✅ Correct Approach
```java
class Sparrow extends Bird {
    @Override
    public void eat(Food food) {
        // Accepts everything the parent accepts
        System.out.println("Sparrow eating: " + food.getClass().getSimpleName());
    }
}
```

---

### B. Return Type Rule (Covariance of Return Types)
> A subclass method can return a subtype of the return type declared by the superclass.

Java natively supports **Covariant Return Types** since Java 5.

```java
class Animal {}
class Dog extends Animal {}

abstract class AnimalShelter {
    public abstract Animal adopt();
}

class DogShelter extends AnimalShelter {
    // Covariant Return: Dog is an Animal, so client expectations are preserved.
    @Override
    public Dog adopt() {
        return new Dog();
    }
}
```

**Client Code Guarantee:**
```java
AnimalShelter shelter = new DogShelter();
Animal pet = shelter.adopt(); // Client expects an Animal, Dog fulfills this guarantee.
```

---

### C. Exception Rule
> An overriding method cannot throw new or broader checked exceptions than those declared by the superclass method.

#### ❌ Violation
```java
import java.io.IOException;

class DataReader {
    public void read() throws IOException {
        // Reads raw bytes
    }
}

class CloudDataReader extends DataReader {
    // COMPILE ERROR in Java: Exception is broader than IOException.
    // Even if simulated via unchecked exceptions, throwing wider unchecked exceptions
    // breaks client catch blocks.
    @Override
    public void read() throws Exception {
        throw new Exception("General network crash");
    }
}
```

#### ✅ Correct Approach
A subclass method can:
- Throw the **exact same** exception.
- Throw a **subclass** of the declared exception (e.g., `FileNotFoundException` inside `IOException`).
- Throw **fewer** or **no** checked exceptions.

```java
import java.io.FileNotFoundException;
import java.io.IOException;

class LocalFileReader extends DataReader {
    @Override
    public void read() throws FileNotFoundException { 
        // Narrower checked exception: safe substitution
        System.out.println("Reading local file safely.");
    }
}
```

---

## 1.2 Property Rules (Invariants & History)

### A. Class Invariants
> Conditions that must always hold true for an object before and after any public method execution. Subclasses must preserve all base-class invariants.

#### ❌ Violation
```java
class BankAccount {
    protected double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance < 0) throw new IllegalArgumentException("Balance cannot be negative");
        this.balance = initialBalance;
    }

    // Invariant: balance >= 0
    public void withdraw(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        this.balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}

class OverdraftAccount extends BankAccount {
    public OverdraftAccount(double initialBalance) {
        super(initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        // Breaks base class invariant (balance >= 0)
        this.balance -= amount; 
    }
}
```

**Why it breaks clients:**
```java
public void processDailySettlement(BankAccount account) {
    account.withdraw(5000);
    // Client assumes the invariant: balance can never drop below 0
    assert account.getBalance() >= 0 : "System invariant violated!";
}
```

---

### B. History Constraint
> Subclasses must not introduce state transitions that would be impossible in the base class.

If a base class is designed to be **immutable**, a subclass must not introduce mutator methods that modify internal state.

#### ❌ Violation
```java
class ImmutableDocument {
    private final String content;

    public ImmutableDocument(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

class EditableDocument extends ImmutableDocument {
    private String mutableContent;

    public EditableDocument(String content) {
        super(content);
        this.mutableContent = content;
    }

    public void updateContent(String newContent) {
        this.mutableContent = newContent; // Violates the historical guarantee of immutability
    }

    @Override
    public String getContent() {
        return this.mutableContent;
    }
}
```

---

## 1.3 Method Rules (Design by Contract)

Bertrand Meyer introduced **Design by Contract (DbC)**:
- **Preconditions:** Requirements the caller must fulfill before invoking the method.
- **Postconditions:** Guarantees the method fulfills after successful execution.

### Rule Summary
| Rule | Base Class | Valid Subclass | Invalid Subclass |
|---|---|---|---|
| **Preconditions** | Cannot be strengthened | Can be weakened or kept equal | Strengthening (`amount > 0` → `amount > 1000`) |
| **Postconditions** | Cannot be weakened | Can be strengthened or kept equal | Weakening (Failing to deduct money or clear cache) |

---

### A. Precondition Rule (Do Not Strengthen Preconditions)

#### ❌ Violation
```java
class PaymentProcessor {
    // Precondition: amount > 0
    public void processPayment(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        System.out.println("Processing: $" + amount);
    }
}

class PremiumPaymentProcessor extends PaymentProcessor {
    // Strengthened Precondition: amount >= 1000
    @Override
    public void processPayment(double amount) {
        if (amount < 1000) {
            throw new IllegalArgumentException("Minimum premium processing is $1000");
        }
        System.out.println("Processing VIP: $" + amount);
    }
}
```

**Client Failure:**
```java
public void checkout(PaymentProcessor processor) {
    // Works for PaymentProcessor, fails unexpectedly for PremiumPaymentProcessor
    processor.processPayment(50.0); 
}
```

---

### B. Postcondition Rule (Do Not Weaken Postconditions)

#### ❌ Violation
```java
class SimpleOrderService {
    protected boolean isProcessed = false;

    // Postcondition: isProcessed == true AND inventory is decremented
    public void completeOrder() {
        decrementInventory();
        this.isProcessed = true;
    }

    protected void decrementInventory() {
        System.out.println("Inventory decremented.");
    }
}

class AsyncOrderService extends SimpleOrderService {
    @Override
    public void completeOrder() {
        // Weakens guarantee: does not complete processing immediately, leaves state undefined
        System.out.println("Queued in background. Inventory NOT yet deducted.");
        this.isProcessed = false; 
    }
}
```

---

# 2. Interface Segregation Principle (ISP)

> *"Clients should not be forced to depend on methods they do not use."*

Fat/polluted interfaces lead to bloated classes, unnecessary recompilations, and empty/unsupported method implementations (`throw new UnsupportedOperationException()`).

---

## ❌ Violation (Fat Interface)

```java
interface MultiFunctionPrinter {
    void print(String document);
    void scan(String document);
    void fax(String document);
    void staple(String document);
}

class BasicPrinter implements MultiFunctionPrinter {
    @Override
    public void print(String document) {
        System.out.println("Printing: " + document);
    }

    @Override
    public void scan(String document) {
        // Forced to implement unused method
        throw new UnsupportedOperationException("Scan not supported");
    }

    @Override
    public void fax(String document) {
        throw new UnsupportedOperationException("Fax not supported");
    }

    @Override
    public void staple(String document) {
        throw new UnsupportedOperationException("Staple not supported");
    }
}
```

---

## ✅ Correct Approach (Segregated Role Interfaces)

Break the fat interface into granular, cohesive interfaces based on client roles.

```java
interface Printer {
    void print(String document);
}

interface Scanner {
    void scan(String document);
}

interface FaxMachine {
    void fax(String document);
}

// 1. Basic Device: implements only what it supports
class BasicInkjetPrinter implements Printer {
    @Override
    public void print(String document) {
        System.out.println("Printing: " + document);
    }
}

// 2. Enterprise Device: composes multiple role interfaces
class EnterpriseCopier implements Printer, Scanner, FaxMachine {
    @Override
    public void print(String document) {
        System.out.println("High-speed printing: " + document);
    }

    @Override
    public void scan(String document) {
        System.out.println("Scanning high-res PDF: " + document);
    }

    @Override
    public void fax(String document) {
        System.out.println("Faxing: " + document);
    }
}
```

---

# 3. Dependency Inversion Principle (DIP)

> **1.** High-level modules should not depend on low-level modules. Both should depend on abstractions.  
> **2.** Abstractions should not depend on details. Details should depend on abstractions.

- **High-Level Module:** Business logic / Core workflows (e.g., `OrderService`, `NotificationManager`).
- **Low-Level Module:** Implementation details / I/O mechanisms (e.g., `MySQLDatabase`, `SendGridEmailService`, `WiredKeyboard`).

---

## ❌ Violation (Tight Coupling)

```java
// Low-Level Module
class MySQLDatabase {
    public void save(String data) {
        System.out.println("Saving to MySQL: " + data);
    }
}

// High-Level Module directly instantiates and depends on a concrete low-level class
class OrderProcessor {
    private MySQLDatabase database;

    public OrderProcessor() {
        // Tightly coupled: Cannot switch to MongoDB or use MockDatabase for unit tests
        this.database = new MySQLDatabase(); 
    }

    public void processOrder(String orderId) {
        database.save("Order: " + orderId);
    }
}
```

---

## ✅ Correct Approach (Dependency Injection via Abstraction)

```java
// 1. Abstraction owned by high-level layer
interface OrderRepository {
    void save(String data);
}

// 2. Low-Level Module Implementations
class MySQLOrderRepository implements OrderRepository {
    @Override
    public void save(String data) {
        System.out.println("Saving to MySQL DB: " + data);
    }
}

class MongoOrderRepository implements OrderRepository {
    @Override
    public void save(String data) {
        System.out.println("Saving to MongoDB Collection: " + data);
    }
}

// 3. High-Level Module depends purely on abstraction
class OrderProcessor {
    private final OrderRepository repository;

    // Inversion of Control via Constructor Injection
    public OrderProcessor(OrderRepository repository) {
        this.repository = repository;
    }

    public void processOrder(String orderId) {
        repository.save("Order #" + orderId);
    }
}

// 4. Client Wire-up
public class Main {
    public static void main(String[] args) {
        OrderRepository sqlRepo = new MySQLOrderRepository();
        OrderProcessor processor = new OrderProcessor(sqlRepo);
        processor.processOrder("1001");

        // Seamlessly switch to Mongo without altering OrderProcessor
        OrderRepository mongoRepo = new MongoOrderRepository();
        OrderProcessor mongoProcessor = new OrderProcessor(mongoRepo);
        mongoProcessor.processOrder("1002");
    }
}
```

---

# SOLID Principles Summary Matrix

| Principle | Core Goal | Primary Anti-Pattern | Solution |
|---|---|---|---|
| **SRP** (Single Responsibility) | High cohesion | God Classes, mixed concerns | Break into single-purpose classes |
| **OCP** (Open/Closed) | Extensibility without regressions | Deep `if/else` or `switch` chains on type | Polymorphism, Strategy pattern |
| **LSP** (Liskov Substitution) | Safe polymorphism & predictability | Throwing `UnsupportedOperationException`, breaking base contracts | Preserve invariants, preconditions, and postconditions |
| **ISP** (Interface Segregation) | Lean contracts | Fat interfaces with empty method implementations | Segment into granular role interfaces |
| **DIP** (Dependency Inversion) | Decoupling layers | `new LowLevelClass()` inside high-level business services | Invert dependencies via Interfaces & Dependency Injection |

---

# Key Takeaways

1. **LSP goes beyond compiler rules:** Code that compiles cleanly can still break runtime semantics if invariants, preconditions, or history constraints are violated.
2. **ISP prevents interface bloat:** Favor multiple role-specific interfaces over large, all-in-one contracts.
3. **DIP decouples architecture:** High-level policy must never know the technical details of low-level infrastructure; dependencies should always point toward abstractions.