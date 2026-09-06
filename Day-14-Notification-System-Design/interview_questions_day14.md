# 5 Essential Interview Questions: Day 14 (Notification System LLD)

### 1. In your Notification System LLD, how did you ensure that adding a new notification channel (like WhatsApp) wouldn't require modifying existing code?
**Answer:** I used the **Strategy Design Pattern**. The core `NotificationEngine` relies on an `INotificationStrategy` interface. To add WhatsApp, I simply create a new `WhatsAppStrategy` class implementing that interface and inject it into the engine. This perfectly adheres to the Open/Closed Principle (OCP) because the existing engine code remains untouched.

### 2. Why did you use the Decorator pattern for the notification content instead of simple inheritance?
**Answer:** Notifications often require dynamic, combinable additions like timestamps, company signatures, or HTML formatting. If I used inheritance, I would suffer from "Class Explosion" (e.g., creating a `NotificationWithTimestampAndSignature` class). The **Decorator Pattern** allows me to wrap the base notification with these features at runtime, keeping the design flexible and the class count minimal.

### 3. How does your system decouple the creation of a notification from the actual sending of it?
**Answer:** By utilizing the **Observer Design Pattern**. The `NotificationService` acts as the bridge. When a notification is created, it is handed to a `NotificationObservable`. The observable simply announces, "I have a new notification." Subscribed Observers—like the `Logger` and the `NotificationEngine`—react to this announcement, fetch the content, and process it independently. The creator of the notification has no idea how it gets logged or sent.

### 4. Why is the `NotificationService` implemented as a Singleton? What issue would arise if it weren't?
**Answer:** The `NotificationService` is a Singleton because it acts as the centralized manager and maintains the history of all notifications sent across the application. If it weren't a Singleton, different parts of the application might instantiate their own `NotificationService`, leading to fragmented histories and multiple disjointed Observables, completely breaking the publish-subscribe flow.

### 5. In your refactored code, you made the Observers (Logger/Engine) register themselves to the Observable inside their constructors. What is the benefit of this?
**Answer:** This significantly improves the **"Plug and Play"** nature of the system. Initially, the client code had to manually fetch the observable and wire it to the observers, violating the Principle of Least Knowledge. By moving `observable.addObserver(this)` into the observer's constructor, the internal wiring is encapsulated. The client simply instantiates the components, and the system automatically wires the Pub-Sub network together.
