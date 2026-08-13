# 10 Essential SOLID Principles Interview Questions

Here are 10 high-frequency interview questions regarding SOLID principles, along with brief, effective answers.

---

### 1. What are the main benefits of applying SOLID principles?
*   **Answer:** They make code more maintainable, scalable, and testable. By reducing tight coupling, they allow developers to add new features or modify existing ones with minimal risk of breaking unrelated parts of the system.

### 2. Can you explain the difference between SRP and ISP?
*   **Answer:** SRP (Single Responsibility Principle) focuses on the **class level**, ensuring a class has only one reason to change. ISP (Interface Segregation Principle) focuses on the **interface level**, ensuring that a client is not forced to depend on methods it does not use.

### 3. How does the Open-Closed Principle (OCP) prevent regression bugs?
*   **Answer:** By encouraging the use of interfaces, abstract classes, and the Strategy Pattern, OCP ensures that when we add new functionality, we are creating **new code** rather than modifying existing, tested, and working code. This isolates changes and reduces the risk of side effects.

### 4. What is a "Liskov Substitution" violation? Give a quick example.
*   **Answer:** A violation occurs when a subclass cannot fulfill the contract defined by the parent class. A classic example is a `Bird` class with a `fly()` method, where a `Penguin` subclass is forced to implement `fly()` but throws an `UnsupportedOperationException`.

### 5. How would you explain Dependency Inversion Principle (DIP) to a junior dev?
*   **Answer:** High-level modules (business logic) should not depend on low-level modules (database/UI). Both should depend on abstractions (interfaces). It’s like swapping a lightbulb; the lamp (high-level) doesn't care about the specific brand of bulb (low-level), as long as it fits the socket (abstraction).

### 6. Can you over-engineer by following SOLID too strictly?
*   **Answer:** Yes. Applying SOLID everywhere can lead to "interface explosion" or too many small, fragmented classes. It’s important to balance architectural purity with YAGNI (You Ain't Gonna Need It) and simplicity.

### 7. How does SOLID help with Unit Testing?
*   **Answer:** SOLID principles naturally lead to decoupled code. When a class has one responsibility and depends on abstractions (interfaces), it becomes very easy to **mock** those dependencies, making it possible to write fast, isolated unit tests.

### 8. Which design patterns best support the Open-Closed Principle?
*   **Answer:** The **Strategy Pattern** (swapping algorithms), **Template Method Pattern** (defining skeleton logic), and **Decorator Pattern** (extending behavior without modifying classes) are common implementations of OCP.

### 9. What happens if you ignore the Interface Segregation Principle?
*   **Answer:** You end up with "Fat Interfaces." Clients implementing these interfaces are forced to implement empty or useless methods, leading to fragile code that breaks easily when requirements change.

### 10. Does Dependency Injection always mean Dependency Inversion?
*   **Answer:** Dependency Injection (DI) is a technique used to *implement* the Dependency Inversion Principle. While DIP is the *principle* (what to aim for), DI is the *mechanism* (how to achieve it, e.g., using constructor injection).

---

# Quick Revision

### SRP

One Class = One Responsibility

### OCP

Extend Existing Functionality
Don't Modify Existing Code

### LSP

Child Must Safely Replace Parent

### Real World Examples

SRP → ShoppingCart, Printer, Persistence

OCP → SQL, Mongo, File Persistence

LSP → Savings Account, Current Account, Fixed Deposit Account