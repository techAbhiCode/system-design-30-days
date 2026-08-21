# Day 10 - Singleton Design Pattern

## 1. Introduction to Singleton
The Singleton Design Pattern is one of the easiest patterns to implement but also one of the most widely used in real-world applications. 
**Definition:** A Singleton class is a class that allows only **one instance** (object) of itself to be created. If you try to create another instance, it simply returns the reference to the already created first instance.

### Why do we need it?
*   To restrict object creation and save memory.
*   To create a "Single Source of Truth" across the entire application.
*   To prevent redundant, expensive operations (like opening multiple database connections).

---

## 2. Under the Hood: Object Creation
When you write `A a = new A();`:
1.  **Heap Memory:** Space is reserved in the Heap for the new object (non-primitive data type).
2.  **Stack Memory:** A reference pointer `a` is created in the Stack to point to that Heap memory.
3.  **Constructor Call:** The default (or parameterized) constructor of class `A` is called to instantiate the object with default values.

To stop users from creating multiple objects using the `new` keyword, we must tackle the Constructor.

---

## 3. Implementing Singleton (Step-by-Step)

### Step 1: Private Constructor
If the constructor is public, anyone can call `new Singleton()`. By making the constructor **private**, we completely block object creation from outside the class.

### Step 2: Static `getInstance()` Method
Since we can't create an object, we need a way to get the instance. We provide a `public static` method (usually named `getInstance()`). It must be static so it can be called using the Class name (`Singleton.getInstance()`) without needing an object.

### Step 3: Static Instance Variable
We need a static variable inside the class to hold the single object reference. 
*   If this variable is `null`, `getInstance()` creates a new object, assigns it to the variable, and returns it.
*   If it is not `null`, `getInstance()` simply returns the existing object.

```java
// Basic Singleton Implementation (Not Thread-Safe)
class Singleton {
    // 3. Static variable to hold the one instance
    private static Singleton instance;

    // 1. Private Constructor
    private Singleton() {
        System.out.println("Singleton Instance Created!");
    }

    // 2. Public Static Method to get the instance
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

---

## 4. The Problem: Thread Safety
The basic implementation fails in a **Multi-threading Environment**.
If Thread 1 (T1) and Thread 2 (T2) call `getInstance()` exactly at the same time, both will see `instance == null` as true, and both will create a new object, violating the Singleton rule.

### Solution 1: Thread-Safe Singleton (Synchronized)
We can lock the `getInstance()` method so only one thread can enter at a time.
*   **Drawback:** Locking is an expensive operation. If the object is already created, locking every time just to return the instance wastes system resources and slows down the application.

### Solution 2: Double-Checked Locking (Optimized)
Instead of locking the whole method, we first check if the instance is null. If it is, *then* we apply a lock block. Inside the lock block, we check for null *again* (because another thread might have created it while this thread was waiting for the lock).

```java
// Thread-Safe Singleton with Double-Checked Locking
class Singleton {
    // 'volatile' ensures changes made by one thread are immediately visible to others in Java
    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        // Check 1: Avoid locking if instance already exists
        if (instance == null) {
            // Lock the critical section
            synchronized (Singleton.class) {
                // Check 2: Ensure another thread hasn't initialized it while waiting
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

### Solution 3: Eager Initialization
If the object is lightweight, we can simply initialize it at the time of class loading, completely avoiding the need for `if-null` checks and synchronization locks.
*   **Drawback:** If the object is memory-intensive and is never actually used in the application, it wastes memory since it's created upfront.

```java
// Eager Initialization
class EagerSingleton {
    // Created immediately when the class is loaded
    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {}

    public static EagerSingleton getInstance() {
        return instance; // No locks, no checks needed
    }
}
```

---

## 5. Real-World Use Cases for Singleton
1.  **Logging System:** You want the entire application to write logs to the same file using a single Logger instance. Creating multiple logger objects wastes memory and scrambles log outputs.
2.  **Database Connection Pool:** Establishing a DB connection is very expensive. A Singleton ensures that connection logic and pooling are handled centrally without creating hundreds of redundant connection objects per user request.
3.  **Configuration Manager:** To ensure a "Single Source of Truth" for properties like API keys or environment variables. All services fetch configs from one shared instance to prevent inconsistency.

## 6. Drawbacks / When NOT to use Singleton
*   When you genuinely need multiple instances (e.g., game characters/players where each needs its own state/session).
*   Singletons make Unit Testing difficult because they carry global state across tests.
*   Overusing Singletons can lead to tightly coupled code (anti-pattern) if used just as a replacement for global variables.
