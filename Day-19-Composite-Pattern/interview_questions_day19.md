# 5 Essential Interview Questions: Day 19 (Composite Pattern)

### 1. What specific problem does the Composite Design Pattern solve?
**Answer:** It solves the complexity of working with tree-like part-whole hierarchies. Without it, client code would need to constantly check the object type (using `instanceof` or `if-else` blocks) to determine if it is dealing with a single object (leaf) or a group of objects (composite). The pattern provides a uniform interface to treat both identically.

### 2. How does the Composite pattern leverage recursion and polymorphism?
**Answer:** The Composite class holds a list of the common Component interfaces. When an operation (like `getSize()`) is invoked on the Composite, it loops through this list and calls `getSize()` on each child. Thanks to **polymorphism**, if the child is a Leaf, it returns its value. If the child is another Composite, it **recursively** forwards the call to its own children. The client only makes one initial method call to trigger the entire tree traversal.

### 3. In the UML for both the Decorator and Composite patterns, the main class has both an "Is-a" and a "Has-a" relationship with the component interface. How are they different?
**Answer:** While structurally similar, their intents and cardinality are completely different. 
*   **Decorator:** Has a `1-to-1` "Has-a" relationship. Its intent is to dynamically *add responsibilities* to a single wrapped object.
*   **Composite:** Has a `1-to-many` (List/Collection) "Has-a" relationship. Its intent is to *structure* objects into a tree hierarchy so clients can perform aggregate operations uniformly.

### 4. What is a potential drawback of using the Composite Design Pattern?
**Answer:** A major drawback is that it can make the design overly general. Because both Leaf and Composite share the exact same interface, you might end up declaring methods in the interface that only make sense for the Composite (like `add()`, `remove()`, or `getChild()`). This forces the Leaf node to implement dummy methods or throw exceptions (e.g., `UnsupportedOperationException`), which violates the **Interface Segregation Principle (ISP)**.

### 5. Give a real-world example of where you would use the Composite Pattern in a Spring Boot application?
**Answer:** I would use it for a dynamic multi-level menu system or an Organization/Department structure. For example, if I need an API to calculate the total budget allocated to a specific department, I can model it using a Composite pattern. `Department` is the Composite, and `Employee` is the Leaf. Calling `getBudget()` on the Root Department would recursively aggregate the salaries of all sub-departments and direct employees automatically.
