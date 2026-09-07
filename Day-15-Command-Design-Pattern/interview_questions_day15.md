# 5 Essential Interview Questions: Day 15 (Command Pattern)

### 1. What is the primary purpose of the Command Design Pattern?
**Answer:** The primary purpose is to decouple the object that invokes a request (the Invoker/Source) from the object that actually performs the action (the Receiver). It achieves this by encapsulating the request itself into a standalone "Command" object.

### 2. How does the Command Pattern enable the "Undo" functionality found in text editors?
**Answer:** Because every action is encapsulated into a Command object that has both an `execute()` and an `undo()` method, the application can maintain a Stack of executed commands. When a user hits `Ctrl+Z` (Undo), the system simply pops the most recent command object off the stack and calls its `undo()` method, reversing the exact action that was taken.

### 3. In the UML of the Command pattern, why does the Concrete Command have a "Has-a" relationship with the Receiver, rather than the Abstract Command Interface?
**Answer:** The abstract `ICommand` interface only defines an intention (that it can execute or undo *something*), but it doesn't know *what* it operates on. It is the responsibility of the Concrete Command (like `LightCommand`) to know exactly which specific Receiver (like a `Light`) it needs to communicate with. Linking the interface to a generic receiver would break the Liskov Substitution Principle because different receivers (Lights, ACs, Fans) have entirely different APIs and capabilities.

### 4. How does the Command pattern help in upholding the Open-Closed Principle (OCP)?
**Answer:** It completely closes the Invoker (e.g., the Remote Control) from modification. If we buy a new Smart Appliance, we do not need to rewrite the Remote Control class. We simply write a new Concrete Command for that appliance and inject it into the remote dynamically at runtime. The Remote is open for extension via new Commands but closed for core modification.

### 5. What is the difference between the Strategy Pattern and the Command Pattern?
**Answer:** 
*   The **Strategy Pattern** is about encapsulating an *algorithm* or a *how* (e.g., How should we sort this data? How should we process this payment?). The client cares about the result.
*   The **Command Pattern** is about encapsulating a *request* or a *what* (e.g., Turn on the light. Delete this text). The client simply fires the command and usually doesn't expect a returned result from it. Command is often used to delay, queue, or undo operations.
