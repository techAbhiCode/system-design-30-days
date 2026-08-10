# Day 01 - Interview Questions

## 1. What is Low-Level Design (LLD)?

### Answer

Low-Level Design (LLD) focuses on the internal implementation of a software system.

It defines:

- Classes
- Objects
- Interfaces
- Relationships
- Responsibilities

The primary goal of LLD is to create maintainable, reusable, and extensible software.

---

## 2. What is the difference between LLD and High-Level Design (HLD)?

### Answer

| HLD | LLD |
|------|------|
| Focuses on system architecture | Focuses on class-level design |
| Defines services and components | Defines classes and objects |
| Concerned with scalability | Concerned with maintainability |
| Example: Designing YouTube architecture | Example: Designing Video Service classes |

---

## 3. What is the difference between DSA and LLD?

### Answer

DSA focuses on solving individual problems efficiently using algorithms and data structures.

LLD focuses on organizing code using OOP principles and design patterns.

Example:

- DSA → Finding shortest path using Dijkstra
- LLD → Designing classes for a Ride Sharing System

---

## 4. How are DSA and LLD related?

### Answer

LLD defines the structure of classes and components.

DSA is used inside those components to perform operations efficiently.

For example:

A ParkingLot class may use:

- HashMap for fast lookups
- PriorityQueue for allocation
- Queue for vehicle processing

---

## 5. Why is LLD important?

### Answer

Without proper LLD:

- Code becomes difficult to maintain
- Adding new features becomes harder
- Testing becomes difficult
- Reusability decreases

Good LLD makes software easier to extend and maintain.

---

## 6. What problems does LLD solve?

### Answer

LLD helps solve:

- Tight coupling
- Poor maintainability
- Code duplication
- Scalability at code level
- Difficulty in extending existing systems

---

## 7. What is meant by maintainable code?

### Answer

Maintainable code is code that:

- Is easy to understand
- Is easy to modify
- Has clear responsibilities
- Supports future changes with minimal impact

---

## 8. What is meant by reusable code?

### Answer

Reusable code can be used across multiple modules or applications without major changes.

Example:

A NotificationService can be reused by:

- Order Service
- Payment Service
- User Service

---

## 9. What is meant by extensible code?

### Answer

Extensible code allows new features to be added with minimal modification to existing code.

This is one of the key goals of System Design.

---

## 10. Explain the relationship between DSA, LLD, and HLD.

### Answer

```text
HLD
│
├── Defines Services
│
└── LLD
     │
     ├── Defines Classes
     ├── Defines Objects
     └── Uses DSA
```

- HLD defines the architecture.
- LLD defines the implementation.
- DSA optimizes operations within the implementation.

---

## 11. Can a system have good HLD but poor LLD?

### Answer

Yes.

Even if the architecture is scalable, poor class design can result in:

- Difficult maintenance
- High coupling
- Difficult testing
- Frequent bugs

A successful system requires both good HLD and good LLD.

---

## 12. What skills are required to learn LLD?

### Answer

Important prerequisites:

- Object-Oriented Programming (OOP)
- Java/C++/C#
- Design Patterns
- SOLID Principles
- Basic DSA

---

# Quick Revision

### DSA

- Problem Solving
- Optimization
- Algorithms
- Data Structures

### LLD

- Classes
- Objects
- Relationships
- Design Patterns

### HLD

- Architecture
- Databases
- APIs
- Scalability
- Distributed Systems

---

# Day 01 Takeaway

A great engineer doesn't just write efficient algorithms.

They also design software that is:

- Maintainable
- Reusable
- Extensible
- Scalable
