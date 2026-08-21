# 5 Essential Interview Questions: Day 10 (Singleton Design Pattern)

### 1. What is the Singleton Design Pattern and where is it typically used?
**Answer:** Singleton is a creational design pattern that ensures a class has only one instance and provides a global point of access to it. It is typically used for resources that are expensive to create or need to maintain a shared state across the application, such as Database Connection Pools, Configuration Managers, and Logging Systems.

### 2. How do you make a Singleton thread-safe in Java, and why is "Double-Checked Locking" preferred over a simple `synchronized` method?
**Answer:** Making the entire `getInstance()` method `synchronized` ensures thread safety, but it causes a massive performance bottleneck because every subsequent call is locked even after the instance is created. 
**Double-Checked Locking** solves this by first checking if the instance is `null` *without* locking. If it is `null`, it enters a `synchronized` block and checks for `null` *again* before creating the object. This ensures locking only happens once during the initial creation.

### 3. Why is the `volatile` keyword strictly required in Java when implementing Double-Checked Locking?
**Answer:** The `volatile` keyword prevents **Instruction Reordering** by the JVM and CPU. Without `volatile`, another thread might see a partially constructed object (where the memory is allocated but the constructor hasn't finished executing). `volatile` establishes a "happens-before" relationship, ensuring the object is fully initialized before the reference is made visible to other threads.

### 4. How does the Spring framework utilize the Singleton pattern?
**Answer:** In Spring Framework and Spring Boot, beans managed by the Spring IoC (Inversion of Control) container are **Singletons by default**. The container creates exactly one instance of the bean and caches it. Every time that specific bean is requested or injected into another class, Spring returns the exact same cached instance. 

### 5. Can a Singleton be "broken" in Java? If yes, how do you prevent it?
**Answer:** Yes, a standard Singleton can be broken in three main ways:
1.  **Reflection:** Can access the private constructor. (Fix: Throw an exception in the constructor if the instance already exists).
2.  **Serialization/Deserialization:** Deserializing a Singleton creates a new instance. (Fix: Implement the `readResolve()` method to return the existing instance).
3.  **Cloning:** If the class implements `Cloneable`. (Fix: Override `clone()` to throw a `CloneNotSupportedException`).
*Note: The most robust, foolproof way to create a Singleton in Java that naturally prevents all these issues is by using an **Enum**.*
