# 5 Essential Interview Questions: Day 8 (Strategy Design Pattern)

### 1. What is the Strategy Design Pattern and what primary problem does it solve?
**Answer:** The Strategy Design Pattern is a behavioral design pattern that defines a family of algorithms, encapsulates each one into separate classes, and makes their objects interchangeable. It primarily solves the problem of rigid code by allowing an application to change its behavior or algorithms at runtime without altering the classes that use them. It encapsulates the part of the code that varies frequently.

### 2. Can you explain the principle "Favor Composition over Inheritance" using an example?
**Answer:** Inheritance creates a rigid, static "is-a" relationship, which can lead to a massive, complicated class hierarchy (Inheritance Hell) when dealing with multiple varying behaviors. Composition, on the other hand, creates a dynamic "has-a" relationship. 
**Example:** Instead of a `Robot` class inheriting from `FlyableRobot` or `WalkableRobot`, the `Robot` class *has a* `Flyable` interface and a `Walkable` interface. This allows us to inject different flying or walking behaviors (strategies) at runtime without creating deeply nested child classes.

### 3. How does the Strategy Design Pattern uphold the Open-Closed Principle (OCP)?
**Answer:** The Open-Closed Principle states that classes should be open for extension but closed for modification. In the Strategy Pattern, if we need to add a new behavior (e.g., adding `FlyWithJet` to a robot, or `CryptoPayment` to a payment system), we simply create a new class that implements the strategy interface. We do not need to touch or modify the existing client code (the `Robot` or `PaymentSystem` class), thereby perfectly following OCP.

### 4. What is "Inheritance Hell" and how does it occur?
**Answer:** "Inheritance Hell" (or class explosion) occurs when you try to solve a multi-dimensional feature requirement purely using inheritance. For example, if robots can either Walk or Not Walk, Talk or Not Talk, and Fly or Not Fly, trying to represent every combination using inheritance requires creating a massive number of subclasses (e.g., `WalkingTalkingFlyingRobot`, `WalkingNonTalkingFlyingRobot`, etc.). Adding just one new behavior doubles the number of required classes. 

### 5. Give a real-world use case of the Strategy Pattern in a backend system.
**Answer:** A very common real-world use case is a **Payment Processing System**. 
A checkout service (the client) has a `pay()` method. Instead of writing massive `if-else` or `switch` statements to handle different payment methods, we define a `PaymentStrategy` interface with an `executePayment()` method. We then create concrete strategies like `UPIPayment`, `CreditCardPayment`, and `NetBankingPayment`. The checkout service simply delegates the payment logic to the dynamically passed strategy object. Another standard Java example is the `Comparator` interface used in sorting collections.