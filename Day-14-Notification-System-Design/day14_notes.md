# Day 14 - LLD of a Notification System

## 1. Requirements Gathering
When designing a notification system, the goal is to create a robust, plug-and-play service that can be easily integrated into any existing application.

*   **Functional Requirements:**
    *   Send notifications via multiple channels (SMS, Email, Push/Popup).
    *   Store/Log all notifications for history and debugging.
*   **Non-Functional Requirements:**
    *   **Plug & Play:** Minimal code changes required for clients to integrate it.
    *   **Extensible (OCP):** Easy to add new notification channels (e.g., WhatsApp) later.
    *   **Dynamic Modification:** Ability to dynamically add headers, footers, or timestamps to the notification content at runtime.

---

## 2. Design Patterns Applied

### A. Decorator Pattern (Dynamic Modification)
*   **Problem:** We need to attach timestamps or signatures to a notification dynamically without creating static subclasses like `NotificationWithTimestampAndSignature`.
*   **Solution:** We create an `INotification` interface. Then, we create abstract `NotificationDecorator` classes (`TimestampDecorator`, `SignatureDecorator`) that wrap the base notification. 
*   **Result:** `new SignatureDecorator(new TimestampDecorator(new SimpleNotification("Order Shipped")));`

### B. Observer Pattern (The Core Flow)
*   **Problem:** We need to decouple the system that *creates* the notification from the systems that *send* or *log* it.
*   **Solution:**
    *   **Observable (Subject):** `NotificationObservable`. It holds the current notification and maintains a list of Observers. When a new notification is set, it calls `notifyObservers()`.
    *   **Observers:** `Logger` (logs to console/file) and `NotificationEngine` (handles dispatching). When notified, they fetch the new notification content and process it.

### C. Strategy Pattern (Extensible Dispatching)
*   **Problem:** The `NotificationEngine` needs to send messages via Email, SMS, or Popup, and we must be able to add new methods easily.
*   **Solution:** We define an `INotificationStrategy` interface with a `sendNotification(content)` method. We create concrete strategies: `EmailStrategy`, `SMSStrategy`, `PopupStrategy`.
*   *Twist:* Instead of the Engine having just *one* strategy, it holds a `List<INotificationStrategy>`. This allows a single notification to be blasted across multiple channels simultaneously.

### D. Singleton Pattern (The Facade/Manager)
*   **Problem:** We need a single point of contact for the client to interact with, and we need a single source of truth for the Notification History.
*   **Solution:** We create a `NotificationService` as a **Singleton**. It holds the `NotificationObservable` and the `List<Notification>` (for history). The client simply calls `NotificationService.getInstance().sendNotification(notification)`.

---

## 3. Architecture Overview & Improvements

**Initial Design Flaw:**
Initially, the client (the `main` method) had to manually fetch the Observable from the Service, create Observers, pass the Observable into the Observers, and then manually register the Observers back to the Observable (`observable.addObserver(logger)`). This violated the "Plug & Play" requirement, as the client had to know too much about the internal wiring.

**The Refactored "Plug & Play" Approach:**
We moved the wiring logic inside the Observers' default constructors.
When a `Logger` or `NotificationEngine` is instantiated:
1. It fetches the Singleton `NotificationService`.
2. It fetches the `Observable` from the service.
3. It automatically registers *itself* to the Observable using `observable.addObserver(this)`.

Now, the client only needs to instantiate the Observers and call `sendNotification()` on the Service. The internal pub-sub wiring happens automatically!
