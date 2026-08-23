# 6 Essential Interview Questions: Day 11 (Food Delivery LLD)

### 1. In your LLD, why did you choose a Composition relationship between `User` and `Cart`, but an Aggregation relationship between `RestaurantManager` and `Restaurant`?
**Answer:** Composition implies a strict dependency where the child cannot exist without the parent. A `Cart` makes no sense and cannot exist independently without a `User`. However, a `Restaurant` can exist independently of the `RestaurantManager` (e.g., it could be managed by a different service or exist in a database before the manager is instantiated), making it an Aggregation (a weak "has-a" relationship).

### 2. How did you handle the varying types of Orders (Delivery vs. Pickup) and their creation logic?
**Answer:** I used inheritance to create `DeliveryOrder` and `PickUpOrder` from a base `Order` class, allowing them to hold specific data like `deliveryAddress`. For their creation, I implemented the **Factory Method Pattern** (`IOrderFactory`). This ensures the complex logic of assembling an order (fetching cart items, applying payment strategies) is decoupled from the main application flow.

### 3. Your `FoodDeliveryApp` orchestrator class seems to do everything. Doesn't this violate SOLID principles?
**Answer:** Yes, it violates the Single Responsibility Principle (SRP) and creates tight coupling. In a 1-hour interview setting, it acts as a quick **Facade** to simulate the client-backend interaction. In a real production environment (like Spring Boot), I would completely remove this class and introduce a **Controller-Service Layer architecture**, where individual controllers (`OrderController`, `CartController`) handle specific HTTP requests and delegate business logic to isolated services.

### 4. Why did you make `RestaurantManager` a Singleton, and what happens if your app runs in a multi-threaded environment?
**Answer:** It is a Singleton because we need a single, centralized registry of restaurants to prevent data inconsistency. In a multi-threaded environment (e.g., multiple users searching and adding restaurants concurrently), a basic Singleton will cause race conditions. I would ensure thread safety by using **Double-Checked Locking** with the `volatile` keyword in Java, or by relying on Spring's default Singleton bean management.

### 5. How did you integrate the Payment system, and why is your approach scalable?
**Answer:** I used the **Strategy Design Pattern**. The `Order` object holds a reference to a `PaymentStrategy` interface rather than a concrete implementation. When the user checks out, the specific strategy (e.g., `UPI` or `CreditCard`) is injected. This is highly scalable because adding a new payment method (like `CryptoWallet`) simply requires creating a new class that implements the interface, perfectly adhering to the Open/Closed Principle (OCP).

### 6. According to the Principle of Least Knowledge (Law of Demeter), what was wrong with passing only the `User` object to the `OrderFactory`?
**Answer:** If I passed only the `User` object, the `OrderFactory` would have to call `user.getCart().getItems()` and `user.getCart().getRestaurant()`. This forces the Factory to have deep knowledge of the internal relationships between User, Cart, and Restaurant, violating the Law of Demeter ("Don't talk to strangers"). To fix this, the orchestrator/service should extract these details first and pass them directly as arguments to the Factory.
