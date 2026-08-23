# Day 11 - Low-Level Design (LLD) of a Food Delivery App (Zomato/Swiggy Clone)

## 1. Interview Approach & Requirements Gathering
In an LLD interview, always start by clarifying the scope of the problem. Break down the requirements into two categories:
*   **Functional Requirements:** The core business features. (e.g., Users can search restaurants by location, add items to a cart, place an order (Delivery/Pickup), pay via multiple methods, and receive notifications).
*   **Non-Functional Requirements:** System behavior. (e.g., Scalability, thread-safe managers, loose coupling).

**Design Approach: Bottom-Up**
In most LLD interviews, a **Bottom-Up approach** is preferred. You design the smallest independent components first (like `MenuItem`, `User`), establish their relationships, and then build the larger orchestrating components (like `Cart`, `Order`, `Managers`).

---

## 2. Core Entities and Relationships
As depicted in the UML diagram (Screenshot 2026-08-22 213143.png), the system is composed of several interacting entities:

*   **Models (Data Containers):**
    *   `Restaurant`: Contains ID, Name, Location, and a list of `MenuItem` objects.
    *   `MenuItem`: Represents a dish (Code, Name, Price). *Relationship: Restaurant has a strict Composition with MenuItem.*
    *   `User`: Represents the customer.
    *   `Cart`: Holds the selected `Restaurant` and a list of `MenuItem`s. *Relationship: User has a strict Composition with Cart (1-to-1).*
*   **Order Hierarchy:**
    *   `Order` (Base Class): Holds User, Restaurant, Items, and Payment Strategy.
    *   `DeliveryOrder` (Subclass): Adds a delivery address.
    *   `PickUpOrder` (Subclass): Adds a restaurant pickup address.

---

## 3. Design Patterns Applied

### A. Singleton Pattern (The Managers)
*   **Components:** `RestaurantManager` and `OrderManager`.
*   **Why?** We only want one centralized list of restaurants and orders across the entire application to act as a "Single Source of Truth." Multiple instances would lead to fragmented and inconsistent data.

### B. Strategy Pattern (Payment)
*   **Component:** `PaymentStrategy` interface with concrete classes (`UPIPayment`, `CreditCardPayment`).
*   **Why?** Allows the application to switch payment algorithms at runtime. The `Order` object just calls `strategy.pay()` without knowing the complex underlying logic of the specific payment gateway.

### C. Factory Method Pattern (Order Creation)
*   **Components:** `IOrderFactory` interface with `NewOrderFactory` (Now) and `ScheduleOrderFactory`.
*   **Why?** Order creation is complex (requires checking cart, fetching user details, applying strategies). The factory separates this creation logic from the main application. It allows scalability: if tomorrow we introduce a `CateringOrder`, we just add a new Factory without breaking existing code.

### D. Facade Pattern (The Orchestrator)
*   **Component:** `FoodDeliveryApp` (or `Tomato` class).
*   **Why?** It acts as a single point of contact for the client (Front-end). It receives requests like `addToCart` or `checkout` and delegates them to the respective sub-systems (Cart, Managers, Factories).

---

## 4. Architecture Trade-offs & Interview Discussions
*   **The Orchestrator Problem:** The `FoodDeliveryApp` class violates the **Single Responsibility Principle (SRP)** and the **Principle of Least Knowledge (Law of Demeter)** because it knows too much about the internal workings of Cards, Users, and Factories.
*   **The Production Solution:** In a real-world Spring Boot backend, we replace this monolithic Orchestrator with a **Controller-Service Architecture**. 
    *   API requests hit specific Controllers (e.g., `RestaurantController`, `OrderController`).
    *   Controllers delegate logic to specific Services (`RestaurantService`, `OrderService`), ensuring a highly decoupled and modular design.
