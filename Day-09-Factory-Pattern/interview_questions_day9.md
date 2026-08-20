# 6 Essential Interview Questions: Day 9 (Factory Design Pattern)

### 1. What is the primary purpose of the Factory Design Pattern?
**Answer:** The primary purpose is to separate the object creation logic from the business logic. Instead of a client directly using the `new` keyword to instantiate an object (which creates tight coupling), the client delegates this responsibility to a Factory. This makes the code more modular, easier to test, and adaptable to changes.

### 2. Can you explain the difference between Simple Factory, Factory Method, and Abstract Factory?
**Answer:** 
*   **Simple Factory:** A single class with a method (usually containing an `if-else` or `switch` statement) that returns different concrete classes based on an input parameter. It is more of a programming idiom than a strict GoF pattern.
*   **Factory Method:** Defines an interface for creating an object, but delegates the exact instantiation logic to its subclasses. It uses inheritance to let subclasses decide which concrete product to create.
*   **Abstract Factory:** Provides an interface for creating *families* of related or dependent objects without specifying their concrete classes. It groups multiple Factory Methods together (e.g., a `MealFactory` that creates both `Burger` and `GarlicBread` of the same "Healthy" family).

### 3. You are designing a Notification System (SMS, Email, Push). When would you choose the Factory Pattern versus the Strategy Pattern?
**Answer:** Both can be used, but the intent is different. 
*   I would use the **Factory Pattern** if my primary goal is to abstract *how* these notification objects are created. The client simply says `factory.createNotification("SMS")` and gets a ready-to-use object.
*   I would use the **Strategy Pattern** if the objects are already created (perhaps injected via Spring) and my goal is to dynamically swap the *algorithm/behavior* of sending the notification at runtime based on user preference.

### 4. How does the Factory Method Pattern adhere to the Open/Closed Principle (OCP)?
**Answer:** It perfectly adheres to OCP (Open for Extension, Closed for Modification). If we need to add a new type of product (e.g., a `VeganBurger`), we do not need to modify the existing `BurgerFactory` or client code. We simply create a new `VeganBurger` class and a corresponding `VeganBurgerFactory` subclass to instantiate it. 

### 5. As a Java backend developer, can you name any standard Java libraries or frameworks that use the Factory Pattern?
**Answer:** Yes, it is heavily used in Java. 
*   In the JDK: `java.util.Calendar.getInstance()`, `java.text.NumberFormat.getInstance()`, and wrapper classes like `Integer.valueOf()`.
*   In Spring Boot: The `BeanFactory` and `ApplicationContext` are classic examples of the Factory Pattern (often combined with Singleton), responsible for instantiating and managing beans.

### 6. What is a potential drawback of using the Factory Pattern?
**Answer:** The main drawback is increased complexity and the potential for "Class Explosion." For every new concrete product, you might need to create a corresponding concrete factory class (especially in the Factory Method and Abstract Factory patterns), which can make the codebase unnecessarily large for simple applications.
