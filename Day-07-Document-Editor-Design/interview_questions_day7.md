# LLD Interview Questions: Document Editor & Design Principles (Day 7)

Based on the Document Editor Low-Level Design (LLD) and the theoretical concepts discussed, here are 8 high-quality interview questions you might face, along with their answers.

---

### 1. What is the difference between Top-Down and Bottom-Up approaches in Low-Level Design? Which one did we use for the Document Editor?
**Answer:** 
*   **Top-Down:** You start by designing the main, top-most object of the application first, and then figure out the smaller objects it needs to function.
*   **Bottom-Up:** You start by creating the smallest foundational objects and defining their relationships, eventually building up to the main application object. 
*   **Application:** In the Document Editor, we used the Bottom-Up approach, which is generally preferred in LLD interviews. We started with the `DocumentElement` and `Persistence`, and finally built the `DocumentEditor` and `Client` on top of them.

### 2. How did refactoring the monolithic `DocumentEditor` class help achieve the Single Responsibility Principle (SRP)?
**Answer:** The initial `DocumentEditor` class was responsible for holding data, rendering the UI, and saving files. By refactoring, we split these into dedicated classes:
*   `Document` is solely responsible for managing the collection of elements (CRUD operations).
*   `Persistence` is solely responsible for saving data.
*   `DocumentEditor` (or `Client`) acts as the orchestrator, delegating tasks rather than implementing the business logic for all of them.

### 3. Looking at the UML diagram, how does the `DocumentElement` hierarchy satisfy the Open-Closed Principle (OCP)?
**Answer:** The design is *open for extension but closed for modification*. If we want to add a new feature like a `TableElement` or `VideoElement`, we simply create a new class that inherits from the abstract `DocumentElement` and implements its own `render()` method. We do not need to touch or modify the existing `Document` or `DocumentEditor` classes to support this new element.

### 4. Explain how the Dependency Inversion Principle (DIP) is applied to the saving mechanism in this design.
**Answer:** DIP states that high-level modules should not depend on low-level modules; both should depend on abstractions. In our design, the `DocumentEditor` (high-level) does not depend on a concrete `SaveToFile` or `SaveToDB` class (low-level). Instead, it holds a reference to the abstract `Persistence` interface. This allows us to swap the database implementation at runtime without changing the editor's code.

### 5. Where can we see the Liskov Substitution Principle (LSP) in action within this architecture?
**Answer:** The `Document` class maintains a `List<DocumentElement>`. At runtime, this list contains objects of child classes like `TextElement` and `ImageElement`. When the `Document` iterates through this list and calls `.render()`, the child classes perfectly substitute their parent (`DocumentElement`) without breaking the application's expected behavior.

### 6. Architecture Debate: The `DocumentEditor` delegates `save()` and `render()`, but it still knows *about* these operations. Does this violate SRP?
**Answer:** This is a classic trade-off in System Design. 
*   **Argument for NO:** It doesn't violate SRP because the actual "reason to change" the saving logic or rendering logic has been moved to other classes. The Editor is just a mediator.
*   **Argument for YES:** The Editor still possesses the *knowledge* of these operations. If we remove rendering entirely from the system, we still have to modify the Editor to remove the delegation code. To strictly fix this, we can introduce a `Client` that independently coordinates a `Document`, a `DocumentRenderer`, and a `Persistence` layer.

### 7. What is the Principle of Least Knowledge (PLK) or the Law of Demeter?
**Answer:** It is a design guideline summarized as "Don't talk to strangers" or "Talk only to your immediate friends." An object should only invoke methods of itself, its parameters, objects it creates, or its direct components. It should not navigate through a chain of objects to reach a method (e.g., `a.getB().getC().doSomething()`).

### 8. According to the UML diagram, how does the `DocumentRenderer` violate the Principle of Least Knowledge?
**Answer:** The `DocumentRenderer` has a dependency on `Document`. To render the elements, it calls `doc.getElement()` to retrieve the list of elements, and then iterates through that list to call `.render()` on each item. Here, `DocumentRenderer` is talking to a "friend of a friend" (the elements inside the document). This creates tight coupling, as the Renderer now intimately knows the internal data structure of the `Document`.
