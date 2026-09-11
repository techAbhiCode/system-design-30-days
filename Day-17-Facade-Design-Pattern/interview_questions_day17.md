# 5 Essential Interview Questions: Day 17 (Facade Pattern)

### 1. What is the primary intent of the Facade Design Pattern?
**Answer:** The primary intent is to provide a unified, simplified interface to a complex subsystem. It hides the underlying structural complexity from the client, reducing coupling and making the subsystem easier to use and maintain.

### 2. Explain the difference between the Facade Pattern and the Adapter Pattern.
**Answer:** The difference lies in their intent. 
*   **Adapter Pattern:** Modifies an existing interface so that it matches what a client expects. It makes two incompatible interfaces work together (e.g., translating XML to JSON).
*   **Facade Pattern:** Simplifies a complex subsystem containing multiple classes. It doesn't translate incompatible interfaces; rather, it provides a brand new, higher-level interface that is easier for the client to use.

### 3. How does the Facade pattern relate to the "Principle of Least Knowledge" (Law of Demeter)?
**Answer:** The Facade pattern is a direct implementation of the Principle of Least Knowledge. This principle states that a component should only "talk to its immediate friends" and not navigate through a deep chain of objects. By introducing a Facade, the client only talks to the Facade (its immediate friend) instead of talking to the dozens of underlying subsystem classes directly.

### 4. Does using a Facade prevent a client from accessing the underlying subsystem classes directly?
**Answer:** No, it does not. A Facade provides a convenient shortcut for the most common operations, but it does not encapsulate or hide the subsystem classes permanently. If advanced clients or developers need fine-grained control, they can bypass the Facade and interact directly with the subsystem classes.

### 5. Give an example of how you might use a Facade in a modern Spring Boot backend architecture.
**Answer:** In a Microservices or complex monolithic backend, you might have an endpoint for `checkoutOrder()`. Executing an order requires interacting with the `InventoryService`, `PaymentService`, `NotificationService`, and `ShippingService`. Instead of injecting all four services into the Web Controller, you create an `OrderCheckoutFacade`. The Controller calls `facade.processCheckout()`, and the Facade orchestrates the complex workflow between the four underlying services.
