# 5 Essential Interview Questions: Day 12 (Observer Pattern)

### 1. What problem does the Observer Design Pattern solve?
**Answer:** It solves the problem of needing to notify multiple objects about a state change in a single object without tightly coupling them. Instead of the dependents continuously checking for updates (polling), the subject actively notifies them when a change occurs (pushing).

### 2. Can you explain the difference between Polling and Pushing?
**Answer:** 
*   **Polling:** The client continuously asks the server/subject, "Is there an update?" at regular intervals. This wastes CPU cycles and network resources.
*   **Pushing:** The client registers once, and the server/subject actively sends a message to the client the moment an update is available. The Observer pattern relies on pushing.

### 3. How does the Observer Pattern seemingly violate the Single Responsibility Principle (SRP), and why is it acceptable?
**Answer:** A concrete Observable class (like a `YoutubeChannel`) often handles its core business logic (uploading videos) while simultaneously managing a list of observers and the logic to notify them. This violates SRP because the class has two reasons to change. However, it's an acceptable trade-off in standard OOP design because splitting these responsibilities often leads to unnecessary complexity and over-engineering.

### 4. What is the difference between the Observer Pattern and the standard Pub-Sub (Publisher-Subscriber) pattern?
**Answer:** 
*   In the **Observer pattern**, the Observable and Observer are aware of each other. The Observable maintains the list of Observers directly.
*   In the **Pub-Sub pattern**, there is a middleman (an Event Bus or Message Broker). Publishers and Subscribers do not know about each other, making the system completely decoupled. 

### 5. Give an example of how you might have unknowingly used the Observer pattern in Java or full-stack development?
**Answer:** Any event-driven programming heavily relies on it. For example, attaching an `addEventListener` to a button in frontend development is a pure Observer pattern. In a Spring Boot backend environment, using `ApplicationEventPublisher` to publish events and `@EventListener` to listen to them is a classic implementation of this pattern.
