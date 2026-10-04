# 5 Essential Interview Questions: Day 20 (Template Pattern)

### 1. What is the Template Design Pattern and when should you use it?
**Answer:** The Template Pattern is a behavioral design pattern that defines the skeleton or pipeline of an algorithm in a base class, but lets subclasses override specific steps of the algorithm. You should use it when you have a series of steps that must be executed in a strict, unchangeable order, but the implementation details of individual steps may vary.

### 2. In Java, why is the Template Method itself usually marked with the `final` keyword?
**Answer:** The Template Method defines the overarching structure or order of execution (the pipeline). By marking it `final`, we prevent subclasses from overriding it and maliciously or accidentally altering the required sequence of steps (e.g., preventing a developer from calling `save()` before `validate()`).

### 3. What is the difference between the Template Method Pattern and the Strategy Pattern?
**Answer:** 
*   **Strategy Pattern:** Deals with delegating the *entire* algorithm to a separate object. You swap the whole algorithm at runtime using composition (Has-a).
*   **Template Pattern:** Deals with modifying *parts* of an algorithm. The overall skeleton remains the same, and you use inheritance (Is-a) to override specific steps.

### 4. What are "Hooks" in the context of the Template Pattern?
**Answer:** A hook is an optional step declared in the abstract base class with an empty or default implementation. Subclasses can choose to override the hook if they want to add extra behavior at that specific point in the pipeline, but they are not forced to (unlike `abstract` methods, which mandate implementation).

### 5. Can you give a real-world example of the Template Pattern in standard Java or Spring Boot?
**Answer:** In Java, the `AbstractList` provides a skeletal implementation of the `List` interface using the Template pattern. In Spring Boot, the `JdbcTemplate` is a classic example. It executes the core workflow: opening a database connection, executing the statement, iterating through the `ResultSet`, catching `SQLExceptions`, and closing the connection. As developers, we only provide the specific SQL query and the `RowMapper` logic for that one specific step, while Spring handles the strict pipeline.
