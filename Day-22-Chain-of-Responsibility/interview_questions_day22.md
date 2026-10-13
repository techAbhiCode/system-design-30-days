# 5 Essential Interview Questions: Day 22 (Chain of Responsibility)

### 1. What problem does the Chain of Responsibility (CoR) pattern solve?
**Answer:** It solves the problem of tight coupling between the sender of a request and its receiver. By giving multiple objects a chance to handle the request, it avoids hardcoding which specific object should process which specific request. The request is simply passed down a chain until a handler processes it.

### 2. How is the Chain of Responsibility conceptually different from a standard Linked List?
**Answer:** While both link objects together via a `next` pointer, their intents are completely different. A Linked List is a data structure meant to store and traverse data, and its nodes are usually identical in behavior. CoR is a behavioral design pattern meant to process business logic, and its "nodes" are completely different concrete classes (e.g., `TeamLead`, `Manager`, `Director`) that encapsulate different algorithms but share a common handler interface.

### 3. Why shouldn't we just use a massive `if-else` or `switch` statement instead of creating so many handler classes in CoR?
**Answer:** A massive `if-else` block violates both the Single Responsibility Principle (SRP) and the Open/Closed Principle (OCP). If the logic for one step changes, or if we need to add a new handler (like a ₹50 note dispenser in an ATM), we have to modify the core `if-else` block, risking bugs. CoR allows us to add or remove handlers dynamically at runtime without touching existing code.

### 4. What happens if a request reaches the end of the chain and no handler has processed it?
**Answer:** This depends entirely on the business logic. It can fail silently, return a default response, or throw an exception. For example, in the ATM scenario, if the amount requested cannot be fulfilled completely by the end of the chain, the system throws an "Insufficient Funds" or "Invalid Denomination" error to the user.

### 5. Can you give a real-world example of the Chain of Responsibility pattern in modern backend frameworks?
**Answer:** Yes, it is heavily used in web request processing. In Java Spring Boot, the `FilterChain` used in Spring Security is a perfect example. An incoming HTTP request passes through multiple filters (`CorsFilter`, `JwtAuthenticationFilter`, `AuthorizationFilter`). Each filter decides whether to process the request, reject it (e.g., return a 401 Unauthorized), or pass it to the next filter in the chain using `chain.doFilter(request, response)`.
