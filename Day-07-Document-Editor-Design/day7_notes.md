# Low-Level Design (LLD): Document Editor System

This document outlines the approach to designing a real-world document editor system (similar to Google Docs) as discussed in the video. It demonstrates the transition from a naive implementation to a robust architecture using Object-Oriented Principles (OOPs) and SOLID design principles.

## 1. Top-Down vs. Bottom-Up Approach
When approaching LLD problems, there are generally two strategies:

*   **Top-Down Approach:** Designing the top-most (main) object of the application first, followed by smaller, dependent objects.
*   **Bottom-Up Approach:** Building smaller objects and defining their dependencies first, before constructing the larger components. This is often the preferred approach in LLD interviews.

## 2. The Bad Design
The initial approach involved a monolithic `DocumentEditor` class that handled all responsibilities.

### Characteristics of the Bad Design:
*   **Data Storage:** Used a `vector<string>` to store both text and image paths.
*   **Methods:** Contained methods like `addText()`, `addImage()`, `renderDocument()`, and `saveToFile()`.
*   **Rendering Logic:** The `renderDocument()` method iterated through the vector, using hacky logic (e.g., checking if the string ends with `.jpg` or `.png`) to determine if an element was an image or text, and rendering them accordingly.

### Problems Identified:
*   **Single Responsibility Principle (SRP) Violation:** The class handled multiple distinct tasks: managing elements, rendering the document, and saving to a file. It had multiple reasons to change.
*   **Open/Closed Principle (OCP) Violation:** Adding support for new element types (like videos or tables) would require modifying the existing `DocumentEditor` class and its `renderDocument` logic.
*   **Lack of Other SOLID Principles:** It completely missed Liskov Substitution, Interface Segregation, and Dependency Inversion principles.

## 3. The Better Design (Applying SOLID Principles)
To improve the architecture, the monolithic class was broken down, and responsibilities were delegated.

### A. Document Elements (Abstraction & Polymorphism)
*   **Concept:** Extracted the elements into a separate hierarchy.
*   **Implementation:** Created an abstract class/interface `DocumentElement` with a `render()` method.
*   **Sub-classes:** Created `TextElement`, `ImageElement`, `NewLineElement`, `TabSpaceElement` which inherit from `DocumentElement` and implement their own specific `render()` logic.
*   **Benefits:** This adheres to **OCP** (easy to add new elements without modifying existing code) and **LSP** (sub-types can replace the parent type without breaking functionality).

### B. Document Class (Data Management)
*   **Concept:** Created a class specifically to manage the collection of elements.
*   **Implementation:** The `Document` class holds a `vector<DocumentElement*>`. It includes an `addElement()` method and a `render()` method.
*   **Rendering Strategy:** The `Document` class does not know *how* to render elements. It simply iterates through its list and calls the `render()` method on each `DocumentElement` (Delegation).

### C. Persistence Class (Data Storage)
*   **Concept:** Separated the responsibility of saving the document.
*   **Implementation:** Created an abstract `Persistence` class with a `save()` method. Sub-classes like `SaveToFile` and `SaveToDB` implement specific saving logic.
*   **Benefits:** Adheres to **SRP** and **Dependency Inversion Principle (DIP)**. High-level modules don't depend on low-level implementation details.

### D. Refactored Document Editor
*   **Role:** Acts primarily as an interface for the client.
*   **Implementation:** It holds references to a `Document` object and a `Persistence` object. Its methods (`addText`, `addImage`, `renderDocument`, `save`) simply delegate requests to these specialized objects.

## 4. Counter-Arguments and Trade-offs
Even the "Better Design" can be critiqued during an interview:

*   **SRP Debate:** One could argue that `DocumentEditor` still has knowledge of multiple responsibilities (rendering, saving), even if it delegates the actual work.

### The DocumentRenderer Proposal
*   To strictly separate rendering, a `DocumentRenderer` class could be introduced.
*   The `Document` class would then only be responsible for CRUD operations on elements.
*   A `Client` class would orchestrate interactions between the Editor, Renderer, and Persistence layers.

### Principle of Least Knowledge (Law of Demeter)
*   **Definition:** "Talk only to your immediate friends." A module should not navigate through a chain of objects to perform an action.
*   **The Conflict:** If a `DocumentRenderer` calls `document.getElements()` to retrieve the list and then iterates through it to call `render()` on each element, it is talking to a "friend of a friend." This increases coupling.
*   **Resolution:** To adhere to this principle, the `render()` logic might need to stay within the `Document` class, bringing back the earlier debate.

**Conclusion:**
There is no absolute "perfect" design in LLD. It is always a matter of trade-offs and reaching an agreement on design choices based on the specific constraints and requirements of the problem.