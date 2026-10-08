# 5 Essential Interview Questions: Day 21 (Proxy Pattern)

### 1. What is the Proxy Design Pattern and why is it used?
**Answer:** The Proxy pattern provides a surrogate or placeholder object that controls access to a target object (the Real Subject). It is used to intercept client requests before they reach the Real Subject, allowing you to implement lazy-loading (Virtual Proxy), access control (Protection Proxy), or network communication (Remote Proxy) without modifying the Real Subject's code.

### 2. How does a Virtual Proxy improve application performance?
**Answer:** A Virtual Proxy implements **Lazy Loading**. If a Real Subject is highly resource-intensive to create (e.g., loading a massive file from disk or establishing a heavy DB connection), creating it immediately might waste memory if the client ends up never actually using it. The Virtual Proxy delays the instantiation of the Real Subject until the exact moment one of its methods is invoked by the client, saving resources.

### 3. What is the structural difference between the Decorator Pattern and the Proxy Pattern?
**Answer:** Structurally, they look nearly identical—both implement a common interface and hold a reference to an object of that interface. The difference lies in their **intent** and how the reference is managed:
*   **Decorator:** Its intent is to *add behavior* dynamically at runtime. The client usually creates the Decorator and passes the base object into it (e.g., `new Decorator(new RealObject())`).
*   **Proxy:** Its intent is to *control access*. The client does not pass the Real Object in. The Proxy manages the lifecycle and instantiation of the Real Object internally (e.g., the Proxy creates the `new RealObject()` itself inside its `display()` method).

### 4. Can you give an example of a Protection Proxy in a modern backend system?
**Answer:** In a web application, an authentication or authorization middleware acts as a Protection Proxy. For example, if a user requests to hit an admin-only API endpoint to delete a database record, the Protection Proxy intercepts the request, checks the user's role or JWT token, and only forwards the request to the Database Controller if the user has the required privileges. If not, the Proxy returns an HTTP 403 Forbidden error, protecting the real resource.

### 5. How does the Spring Framework utilize the Proxy Pattern?
**Answer:** The Proxy pattern is the foundation of **Spring AOP (Aspect-Oriented Programming)**. Whenever you annotate a class or method with `@Transactional`, `@Async`, or `@Cacheable`, Spring dynamically generates a Proxy object around your bean at runtime. When another class calls your bean, it is actually calling the Spring Proxy. The Proxy executes its specific logic (like opening a DB transaction), delegates the call to your actual bean method, and then executes its clean-up logic (like committing the transaction).
