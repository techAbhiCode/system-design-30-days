# 5 Essential Interview Questions: Day 16 (Adapter Pattern)

### 1. What problem does the Adapter Design Pattern solve?
**Answer:** It solves the problem of incompatible interfaces. When a client expects to interact with an object through a specific interface, but the available object (e.g., a third-party library or legacy code) has a completely different interface, the Adapter acts as a middleman. It translates the client's requests into a format the existing object can understand.

### 2. How does the Adapter Pattern uphold the Open-Closed Principle (OCP) and Dependency Inversion Principle (DIP)?
**Answer:** 
*   **OCP:** You can introduce new adapters into the program to work with new third-party libraries without breaking or modifying the existing client code.
*   **DIP:** The client code depends only on a high-level abstraction (the Target Interface), not on the low-level, concrete third-party class.

### 3. What is the difference between an Object Adapter and a Class Adapter? Which one is preferred in Java?
**Answer:** 
*   An **Object Adapter** uses *composition* ("has-a" relationship). It implements the Target interface and holds an instance of the Adaptee class. 
*   A **Class Adapter** uses *multiple inheritance* ("is-a" relationship). It inherits from *both* the Target interface and the Adaptee class simultaneously.
*   **Preference:** Object Adapter is strongly preferred. First, Java does not support multiple inheritance of classes, making Class Adapters generally impossible without interfaces. Second, the fundamental OOP rule dictates "Favor Composition over Inheritance" for better flexibility.

### 4. Give a real-world example of the Adapter pattern in Java backend development.
**Answer:** A classic architectural use case is the **Anti-Corruption Layer**. If a modern Spring Boot application needs to fetch user data from a 20-year-old legacy SOAP web service, you shouldn't pollute your modern Domain/Service layer with SOAP parsing logic. Instead, you create a `LegacyUserAdapter` that implements your modern `UserRepository` interface. The adapter talks to the SOAP service, translates the XML into modern Java POJOs, and returns them to the service layer.

### 5. How does the Adapter pattern differ from the Decorator pattern?
**Answer:** While both patterns wrap an object (often called a wrapper), their intent is entirely different.
*   The **Adapter Pattern** is used to *change* the interface of an existing object so it can be used by an incompatible client. (e.g., XML to JSON).
*   The **Decorator Pattern** is used to *enhance or add responsibilities* to an object without changing its interface. (e.g., adding a Timestamp to a Notification).
