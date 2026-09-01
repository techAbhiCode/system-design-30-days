# 5 Essential Interview Questions: Day 13 (Decorator Pattern)

### 1. What specific problem does the Decorator Pattern solve over standard Inheritance?
**Answer:** It solves the "Class Explosion" problem. If we use inheritance to add multiple, combinable features to a base class, we have to create a new subclass for every possible permutation (e.g., `CarWithSunroof`, `CarWithSunroofAndLeather`, `CarWithLeather`). The Decorator pattern allows us to dynamically attach these features at runtime using composition, keeping the class count minimal.

### 2. In the Decorator pattern's UML, why does the abstract Decorator class have both an "Is-a" and a "Has-a" relationship with the Component interface?
**Answer:** 
*   **Is-a:** It implements the Component interface so that the decorator can seamlessly substitute the original object. The client doesn't know it's interacting with a decorator; it just sees the Component interface.
*   **Has-a:** It holds a reference to a Component object so it can delegate the base method calls to the wrapped object, and then add its own extra behavior to the result.

### 3. How does the Decorator Pattern simulate recursive execution?
**Answer:** When decorators are stacked (e.g., `new DecoratorA(new DecoratorB(new BaseComponent()))`), calling a method on the outermost object (`DecoratorA`) triggers a chain reaction. `DecoratorA` calls the method on its wrapped object (`DecoratorB`), which calls the method on `BaseComponent`. The base object returns its result, and as the call stack unwinds, each decorator appends its own behavior to the result, acting similarly to a recursive function hitting a base case and returning.

### 4. What is a real-world example of the Decorator pattern in the Java Standard Library?
**Answer:** The Java I/O streams are the classic example. A `FileReader` reads raw bytes. You can decorate it to read whole lines and buffer the input for performance by wrapping it: `new BufferedReader(new FileReader("file.txt"))`. You are dynamically adding buffering behavior to the base reader.

### 5. Does the Decorator pattern follow the Open/Closed Principle (OCP)? How?
**Answer:** Yes, it perfectly adheres to OCP. The system is closed for modification because we don't need to change the base `Component` or existing decorators to add new features. It is open for extension because if we need a new feature (e.g., `FlyPowerDecorator`), we simply create a new concrete decorator class that wraps the existing ones.
