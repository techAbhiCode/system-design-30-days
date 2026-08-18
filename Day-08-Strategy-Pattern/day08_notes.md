# Day 8 - Strategy Design Pattern

## 1. Why Do We Need Design Patterns?
*   Applications are always evolving, and new features will constantly be integrated.
*   A flexible design ensures that integrating a new feature requires minimal code changes and minimal time.
*   Design patterns are proven solutions to common problems faced by developers.
*   The core philosophy of most design patterns is: **Identify the aspects of your application that vary and separate them from what remains the same**.

## 2. The Problem: "Inheritance Hell"
Imagine building a simulation with various types of Robots.
*   You start with a base `Robot` class containing `walk()`, `talk()`, and an abstract `projection()` method.
*   Child classes like `CompanionR` and `WorkerR` inherit from `Robot` and provide their own `projection()`.
*   **The Issue:** When you need to introduce flying robots (e.g., `SparrowRobot`), adding `fly()` to the base class makes *all* robots fly.
*   If you try to fix this by creating deeper inheritance trees (e.g., separating `Flyable` and `NonFlyable` classes), the design breaks down as more variations appear (e.g., a `JetRobot` that flies differently than a bird-like robot).
*   This leads to massive code duplication, violating the **DRY (Do Not Repeat Yourself)** principle, and creates a highly complicated inheritance tree of every possible permutation.

## 3. The Solution: Strategy Design Pattern
**Definition:** The Strategy Pattern defines a family of algorithms, encapsulates each one into separate classes, and makes them interchangeable at runtime.

### How we refactored the Robot application:
1.  **Extract the varying behaviors:** We removed `talk()`, `walk()`, and `fly()` from the main `Robot` class.
2.  **Create Interfaces (The Strategy):** We created `Talkable`, `Walkable`, and `Flyable` interfaces.
3.  **Create Concrete Implementations:** We created specific classes for each behavior, such as `NormalWalk`, `NoWalk`, `NormalFly`, and `NoFly`.
4.  **Favor Composition Over Inheritance:** The `Robot` class now holds a reference (a *has-a* relationship) to these interfaces. 
5.  **Delegation:** The `Robot` acts as a "dumb" client. When asked to fly, it simply delegates the task to its injected `Flyable` object (e.g., `f.fly()`).

## 4. Real-World Examples

### A. Payment Systems
*   A `PaymentSystem` class (the Client) has a `payNow()` method.
*   Instead of hardcoding payment logic, it holds a reference to a Strategy interface.
*   Concrete strategies include `UPI`, `Debit/Credit`, and `NetBank`.
*   The payment method can be swapped dynamically at runtime.

### B. Sorting Algorithms
*   A class needing to sort data can delegate the task to a sorting strategy.
*   The family of algorithms includes `QuickSort`, `MergeSort`, and `InsertionSort`.
*   These can further have sub-strategies (e.g., Normal QuickSort vs. Randomized QuickSort).

## 5. Key Takeaways & Conclusions
*   **Encapsulate what varies** and keep it separate from what remains the same.
*   **The solution to inheritance is not more inheritance**.
*   **Composition should be favored over inheritance**.
*   **Code to an interface, not to a concretion**.
*   **Do NOT Repeat Yourself (DRY)**.