# SOLID Principles & Advanced LLD Interview Questions & Answers

A curated collection of the top 15 in-depth interview questions covering the **SOLID Principles**, with heavy emphasis on **Advanced Liskov Substitution Principle (LSP)**, **Interface Segregation Principle (ISP)**, and **Dependency Inversion Principle (DIP)**.

---

## Q1. What is the subtle difference between syntactic subtyping (compilation) and behavioral subtyping (LSP)?

**Answer:**
- **Syntactic Subtyping (Compile-Time):** Ensured by the compiler via type systems (e.g., `extends` or `implements` in Java). It verifies that method names, parameter types, and return types conform to the interface contract.
- **Behavioral Subtyping (Runtime / LSP):** Defined by Barbara Liskov and Jeannette Wing, it requires that a subtype must preserve the semantics, invariants, preconditions, and postconditions of the parent type.
- **Key Insight:** Code can compile cleanly while still breaking client expectations at runtime (e.g., throwing `UnsupportedOperationException`, breaking base class invariants, or strengthening preconditions).

---

## Q2. Explain the classic "Square extends Rectangle" problem. Which principle does it violate and why?

**Answer:**
It violates the **Liskov Substitution Principle (LSP)**.

### Why:
In geometry, a square is a rectangle. However, in OOP:
- A `Rectangle` has two independent properties: `width` and `height`. Setting the width does not alter the height.
- A `Square` couples `width` and `height` (`width == height`).

### Code Example:
```java
class Rectangle {
    protected int width, height;
    public void setWidth(int w) { this.width = w; }
    public void setHeight(int h) { this.height = h; }
    public int getArea() { return width * height; }
}

class Square extends Rectangle {
    @Override
    public void setWidth(int w) { this.width = w; this.height = w; }
    @Override
    public void setHeight(int h) { this.width = h; this.height = h; }
}
```

### Client Failure:
```java
void testArea(Rectangle r) {
    r.setWidth(5);
    r.setHeight(4);
    // Client assumes area is 5 * 4 = 20. For Square, it evaluates to 16.
    assert r.getArea() == 20 : "LSP Violation!";
}
```

---

## Q3. What are Preconditions and Postconditions in Design by Contract (DbC), and how do they apply to LSP?

**Answer:**
- **Preconditions:** Criteria that caller code must satisfy before invoking a method.
  - **LSP Rule:** A subclass **cannot strengthen** preconditions (it cannot demand more than the parent). It can weaken or keep them equal.
  - *Violation Example:* If `Parent.deposit(double amount)` requires `amount > 0`, a `Child` requiring `amount >= 1000` strengthens the precondition and breaks generic client code.
- **Postconditions:** Guarantees that the method promises upon completion.
  - **LSP Rule:** A subclass **cannot weaken** postconditions (it cannot guarantee less than the parent). It can strengthen or keep them equal.
  - *Violation Example:* If `Parent.clearCache()` guarantees the cache size is `0`, a `Child` running asynchronously and leaving cache clearing pending weakens the postcondition.

---

## Q4. What is the Class Invariant Rule and the History Constraint in LSP?

**Answer:**
1. **Class Invariant Rule:**
   - An invariant is a condition that remains true throughout the lifecycle of an object (e.g., `balance >= 0`).
   - A subclass must preserve all base-class invariants. If a subclass method allows the state to violate base invariants (e.g., overdrafting into negative balance without base support), LSP is violated.
2. **History Constraint:**
   - Subclasses cannot allow state changes that the superclass prohibited.
   - *Example:* If `ImmutablePoint` guarantees coordinates cannot change post-instantiation, an `EditablePoint` subclass adding `setX()` and `setY()` violates the history constraint because clients holding an `ImmutablePoint` reference expect perpetual immutability.

---

## Q5. How does Java handle the Method Argument Rule and Covariant Return Types under LSP?

**Answer:**
- **Argument Rule (Contravariance):** A subclass method should ideally accept more general arguments. In Java, parameter types must match exactly to count as method overriding; changing parameter types creates an **overload**, which can lead to unexpected polymorphic dispatch issues.
- **Return Type Rule (Covariance):** A subclass method can return a subtype of the return type declared in the parent class. Java supports covariant return types:
```java
class Producer {
    public Number produce() { return 10; }
}
class IntegerProducer extends Producer {
    @Override
    public Integer produce() { return 10; } // Integer is a subtype of Number (Valid LSP)
}
```

---

## Q6. Why is throwing `UnsupportedOperationException` in a subclass considered a major design code smell?

**Answer:**
Throwing `UnsupportedOperationException` in an overridden method (e.g., `ReadOnlyList.add()`) directly violates the **Liskov Substitution Principle (LSP)** and signals a violation of the **Interface Segregation Principle (ISP)**.

- **Consequence:** It violates the principle of least astonishment. Polymorphic client code expecting `List` functionality will crash at runtime.
- **Solution:** Segregate interfaces (e.g., `ReadableCollection` vs. `ModifiableCollection`) or replace inheritance with composition.

---

## Q7. What are the common symptoms and anti-patterns of ISP violations?

**Answer:**
1. **Fat / "Header-heavy" Interfaces:** Interfaces with 15–20+ methods spanning unrelated responsibilities.
2. **Empty or Dummy Implementations:** Subclasses providing empty bodies (`{}`) or throwing `UnsupportedOperationException` for methods they don't care about.
3. **High Recompilation / Redeployment Cascades:** When a change to a single method in an interface forces all implementing classes to recompile and redeploy, even if they don't use that method.
4. **Client Coupling:** Clients depending on methods they never call.

---

## Q8. How do you refactor a Fat Interface according to ISP?

**Answer:**
Break down the fat interface into granular, focused **Role Interfaces**, and let concrete classes implement only the interfaces they actually support.

### Example:
```java
// Anti-pattern: Fat interface
interface DocumentManager {
    void print();
    void scan();
    void email();
    void fax();
}

// Refactored: Role-based interfaces
interface Printable { void print(); }
interface Scannable { void scan(); }
interface Emaillable { void email(); }

// Concrete implementations bind only to needed capabilities
class BasicOfficePrinter implements Printable {
    public void print() { /* print implementation */ }
}

class ModernSmartCopier implements Printable, Scannable, Emaillable {
    public void print() { /* ... */ }
    public void scan() { /* ... */ }
    public void email() { /* ... */ }
}
```

---

## Q9. What is the core difference between Dependency Inversion Principle (DIP), Inversion of Control (IoC), and Dependency Injection (DI)?

**Answer:**
- **Dependency Inversion Principle (DIP):** A high-level architectural design principle stating that high-level modules should not depend on low-level modules; both should depend on abstractions.
- **Inversion of Control (IoC):** A broader architectural pattern where the control flow of a program is inverted (the framework/runtime controls lifecycle and execution instead of custom procedural code).
- **Dependency Injection (DI):** A specific design technique/pattern used to implement DIP and IoC, where dependencies are supplied ("injected") into a class via constructors, setters, or fields rather than being instantiated directly (`new`) inside the class.

---

## Q10. What is the difference between Constructor Injection and Setter/Field Injection, and which is preferred for DIP?

**Answer:**
- **Constructor Injection (Preferred):**
  - Enforces immutability (`final` dependencies).
  - Guarantees the object is never in a half-initialized or inconsistent state.
  - Simplifies unit testing by making dependencies explicit when instantiating mocks.
- **Setter Injection:**
  - Useful for optional or dynamically reconfigurable dependencies, but risks `NullPointerException` if accessed before setter execution.
- **Field Injection (e.g., `@Autowired` on private fields):**
  - Violates encapsulation and makes standalone unit tests harder without reflection or Spring test runners.

---

## Q11. "Abstractions should not depend on details. Details should depend on abstractions." What does this statement in DIP mean?

**Answer:**
- The interface (abstraction) contract must be designed around the **domain / high-level business requirement**, not dictated by the underlying technical implementation (detail).
- **Bad Design:** Designing an interface with `void saveToMySQLDatabase(String query)` leaks SQL database details into the abstraction.
- **Good Design:** Designing `void save(Order order)` in an `OrderRepository` interface allows MySQL, MongoDB, or DynamoDB implementations to conform to the business contract.

---

## Q12. How does violating LSP often trigger violations of OCP (Open-Closed Principle)?

**Answer:**
When a subclass violates LSP, client code cannot treat all subclasses uniformly. As a workaround, developers introduce `instanceof` checks and `if-else` type inspections:

```java
public void processPayment(PaymentMethod method) {
    if (method instanceof CryptoPayment) {
        // Special workaround because CryptoPayment doesn't support the standard refund() flow
        ((CryptoPayment) method).cryptoSpecificRefund();
    } else {
        method.refund();
    }
}
```
Every time a new payment subtype is added, the client method must be modified, violating **OCP**.

---

## Q13. How do you distinguish when to use inheritance vs. composition in relation to LSP?

**Answer:**
- Use the **"IS-A" test accompanied by behavioral substitution:** If class `B` cannot fulfill every behavioral promise, contract, and invariant of class `A`, it is **not** a true subtype.
- Follow the principle **"Favor Composition over Inheritance"**:
  - If you only want code reuse without polymorphic interchangeability, use composition (`HAS-A`).
  - *Example:* A `Stack` should **not** extend `Vector` (a historical Java mistake that broke encapsulation by exposing indexed inserts `insertAt(i)` on a LIFO stack). A `Stack` should wrap a `Vector`/`List` internally.

---

## Q14. In modern microservices and API design, how does ISP apply?

**Answer:**
- **BFF Pattern (Backend for Frontend):** Creating tailored API endpoints or gateway layers for Mobile, Web, and Third-Party clients rather than a single massive, bloated API payload.
- **GraphQL / Sparse Fieldsets:** Allowing clients to query only the specific fields they require, avoiding over-fetching and tight coupling to database schemas.
- **Micro-interfaces / CQRS:** Separating Command APIs (writes) from Query APIs (reads) so clients that only need read access aren't coupled to write-model schemas.

---

## Q15. Walk through an end-to-end refactoring scenario applying LSP, ISP, and DIP together.

**Answer:**

### Initial Problematic Code (Violates LSP, ISP, and DIP):
```java
class SmartDeviceManager {
    // Violates DIP: Directly instantiates concrete Low-Level class
    private SamsungSmartLight light = new SamsungSmartLight();

    public void manage() {
        light.turnOn();
        light.adjustTemperature(22); // Not all smart devices support temperature
    }
}

// Violates ISP: Fat interface forced on all smart devices
interface SmartDevice {
    void turnOn();
    void turnOff();
    void adjustTemperature(int temp);
    void streamCamera();
}

// Violates LSP: Throws unsupported exceptions
class SmartBulb implements SmartDevice {
    public void turnOn() { /* ... */ }
    public void turnOff() { /* ... */ }
    public void adjustTemperature(int temp) { throw new UnsupportedOperationException(); }
    public void streamCamera() { throw new UnsupportedOperationException(); }
}
```

### Refactored Clean Architecture:
```java
// 1. ISP: Segregated Role Interfaces
interface Switchable {
    void turnOn();
    void turnOff();
}

interface TemperatureRegulatable {
    void adjustTemperature(int temp);
}

interface CameraStreamable {
    void streamCamera();
}

// 2. LSP Compliant: Implements only what it fully supports
class SmartBulb implements Switchable {
    public void turnOn() { System.out.println("Bulb lit."); }
    public void turnOff() { System.out.println("Bulb off."); }
}

class SmartThermostat implements Switchable, TemperatureRegulatable {
    public void turnOn() { System.out.println("Thermostat active."); }
    public void turnOff() { System.out.println("Thermostat off."); }
    public void adjustTemperature(int temp) { System.out.println("Temp set to " + temp); }
}

// 3. DIP: High-level service depends on abstraction, injected via constructor
class SmartAutomationService {
    private final List<Switchable> switchables;

    public SmartAutomationService(List<Switchable> switchables) {
        this.switchables = switchables;
    }

    public void shutdownAll() {
        switchables.forEach(Switchable::turnOff);
    }
}
```