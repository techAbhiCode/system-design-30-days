# Day 5 - SOLID Principles (Part 1: SRP, OCP, LSP)

## What are SOLID Principles?

SOLID is a set of 5 foundational object-oriented design principles introduced by Robert C. Martin (Uncle Bob). They are the blueprint for writing enterprise-grade software that avoids "spaghetti code."

**The core goals of SOLID:**
*   **Maintainable:** Code is easy to read and update.
*   **Flexible:** Code adapts to new requirements without breaking.
*   **Scalable:** Codebases can grow massive without collapsing under technical debt.
*   **Testable:** Isolated logic makes unit testing straightforward.

| Principle | Full Form | Core Philosophy |
| :--- | :--- | :--- |
| **S** | Single Responsibility Principle | High Cohesion |
| **O** | Open Closed Principle | Extensibility |
| **L** | Liskov Substitution Principle | Reliable Inheritance |
| **I** | Interface Segregation Principle | Focused Interfaces |
| **D** | Dependency Inversion Principle | Loose Coupling |

---

## 1. Single Responsibility Principle (SRP)

> **Definition:** A class should have one, and only one, reason to change. 
> *(Uncle Bob's modern update: "A class should be responsible to one, and only one, actor.")*

### ❌ SRP Violation: The "God Class"
When one class handles business logic, UI formatting, and database operations, it becomes a nightmare to maintain.

```java
// VIOLATION: This class has 3 reasons to change (Logic, Presentation, Storage)
public class ShoppingCart {

    public void addProduct(Product product) {
        // 1. Cart Business Logic
    }

    public double calculateTotal() {
        // 1. Cart Business Logic
        return 0.0;
    }

    public void printInvoice() {
        // 2. Presentation/Formatting Logic (What if we want PDF instead of text?)
    }

    public void saveToDatabase() {
        // 3. Persistence Logic (What if we switch from SQL to MongoDB?)
    }
}
```

### ✅ SRP Followed: Separation of Concerns
We split the responsibilities into focused classes. This mimics the standard Controller-Service-Repository architecture.

```java
// 1. Core Business Logic
public class ShoppingCart {
    public double calculateTotal() { /* ... */ }
}

// 2. Presentation Logic
public class InvoicePrinter {
    public void print(ShoppingCart cart) { /* ... */ }
}

// 3. Persistence Logic
public class CartRepository {
    public void save(ShoppingCart cart) { /* ... */ }
}
```

**Benefits:** If the invoice format changes to a PDF, you only touch `InvoicePrinter`. The `ShoppingCart` remains perfectly safe and bug-free.

---

## 2. Open Closed Principle (OCP)

> **Definition:** Software entities (classes, modules, functions) should be **Open for Extension** but **Closed for Modification**.

New features should be added by writing *new* code, not by altering *existing, working* code. 

### ❌ OCP Violation: The `if-else` Trap
Modifying existing classes for every new requirement introduces regression bugs.

```java
public class CartRepository {
    public void save(ShoppingCart cart, String dbType) {
        // VIOLATION: Every time a new DB is added, this existing method must be modified.
        if (dbType.equals("SQL")) {
            System.out.println("Saving to MySQL...");
        } else if (dbType.equals("MONGO")) {
            System.out.println("Saving to MongoDB...");
        } else if (dbType.equals("FILE")) {
            System.out.println("Saving to local file...");
        }
    }
}
```

### ✅ OCP Followed: Abstraction & Polymorphism
We fix this using the **Strategy Pattern**. We define an interface and create separate implementations.

```java
// Abstraction (Closed for modification)
public interface Persistence {
    void save(ShoppingCart cart);
}

// Extensions (Open for extension)
public class SqlPersistence implements Persistence {
    @Override
    public void save(ShoppingCart cart) { /* SQL logic */ }
}

public class MongoPersistence implements Persistence {
    @Override
    public void save(ShoppingCart cart) { /* Mongo logic */ }
}
```

**Real-Life Analogy:** A wall socket. The socket (interface) doesn't change when you buy a new device. You just plug in a new charger (extension).

---

## 3. Liskov Substitution Principle (LSP)

> **Definition:** Objects of a superclass shall be replaceable with objects of its subclasses without breaking the application.

If it looks like a duck and quacks like a duck, but needs batteries—you probably have the wrong abstraction.

### ❌ LSP Violation: The Banking Example
A child class shouldn't implement a method it can't actually use.

```java
public interface BankAccount {
    void deposit(double amount);
    void withdraw(double amount);
}

// VIOLATION: Fixed Deposits don't allow standard withdrawals.
public class FixedDepositAccount implements BankAccount {
    public void deposit(double amount) { /* ... */ }

    public void withdraw(double amount) {
        // Breaking the contract! A generic BankAccount shouldn't throw this.
        throw new UnsupportedOperationException("Cannot withdraw from FD before maturity!");
    }
}
```
If a system loops through a list of `BankAccount` objects and calls `.withdraw()`, the application will suddenly crash when it hits the `FixedDepositAccount`.

### Liskov Substitution Principle (LSP)

## Definition

Objects of child classes should replace parent objects without breaking behavior.

If child cannot fully behave like parent

then inheritance is wrong.

---

## Classic LSP Violation

### Parent

```java
class Bird {
    void fly(){}
}
```

### Child

```java
class Penguin extends Bird {
    void fly(){
        throw new UnsupportedOperationException();
    }
}
```

Problem:

```java
Bird bird = new Penguin();
bird.fly();
```

Application crashes.

LSP violated.

---

# Banking Example

Accounts:

- Savings Account
- Current Account
- Fixed Deposit Account

---

## Wrong Design

```java
interface BankAccount {

    void deposit();

    void withdraw();
}
```

Savings Account:

```java
class SavingAccount implements BankAccount
```

Current Account:

```java
class CurrentAccount implements BankAccount
```

Fixed Deposit:

```java
class FixedDepositAccount implements BankAccount
```

Problem:

FD account does not allow withdrawal.

Developer writes:

```java
public void withdraw() {
    throw new UnsupportedOperationException();
}
```

Now:

```java
BankAccount account = new FixedDepositAccount();
account.withdraw();
```

Runtime failure.

LSP violated.

---

# Correct LSP Solution

Separate behavior.

```java
interface DepositOnlyAccount {
    void deposit();
}
```

```java
interface WithdrawableAccount
        extends DepositOnlyAccount {

    void withdraw();
}
```

---

## Savings Account

```java
class SavingAccount
        implements WithdrawableAccount
```

Supports:

- Deposit
- Withdraw

---

## Current Account

```java
class CurrentAccount
        implements WithdrawableAccount
```

Supports:

- Deposit
- Withdraw

---

## Fixed Deposit Account

```java
class FixedTermAccount
        implements DepositOnlyAccount
```

Supports:

- Deposit only

No unnecessary withdraw method.

LSP followed.

---

# Why LSP Matters

Without LSP:

- Runtime failures
- Unexpected behavior
- Tight coupling
- Difficult maintenance

With LSP:

- Reliable polymorphism
- Cleaner inheritance hierarchy
- Better extensibility

---
### ✅ LSP Followed: Contract-Driven Interfaces
Break the hierarchy down so classes only inherit behaviors they can actually fulfill.

```java
// Base level contract
public interface DepositAccount {
    void deposit(double amount);
}

// Extended contract
public interface WithdrawableAccount extends DepositAccount {
    void withdraw(double amount);
}

// Implementations
public class SavingsAccount implements WithdrawableAccount {
    public void deposit(double amount) { /* ... */ }
    public void withdraw(double amount) { /* ... */ }
}

public class FixedTermAccount implements DepositAccount {
    // Only implements what it can safely do. No withdraw method exists here!
    public void deposit(double amount) { /* ... */ }
}
```

---

## 💡 Technical Interview Cheat Sheet

*   **SRP:** "Gather together the things that change for the same reasons. Separate those things that change for different reasons."
*   **OCP:** "Achieved heavily through Interfaces and Abstract classes. The **Strategy Pattern** is the ultimate realization of OCP."
*   **LSP:** "Beware of `UnsupportedOperationException`. If a child class throws this, or if you have to do type-checking (`instanceof`) before calling a method, you are likely violating LSP."
*   **Bonus point to mention:** Bad design often works initially but collapses under its own weight around sprint 4 or 5. SOLID principles ensure code remains "Production Ready" as the project scales.