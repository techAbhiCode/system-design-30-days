# Day 01 - Introduction to System Design ✅

##  Overview

Today I started my **30 Days of System Design Challenge** and learned the fundamentals of **Low-Level Design (LLD)**, along with how it relates to **Data Structures & Algorithms (DSA)** and **High-Level Design (HLD)**.

Understanding the difference between these concepts is important because they represent different layers of software engineering.

---

# What is Low-Level Design (LLD)?

Low-Level Design focuses on designing the internal structure of a software system.

It defines:

- Classes
- Objects
- Relationships
- Responsibilities
- Interactions between components

LLD helps transform business requirements into maintainable and extensible code.

### Example

If we are building a Parking Lot System:

LLD answers questions such as:

- What classes are required?
- How will ParkingSpot interact with Vehicle?
- How will Ticket generation work?
- Which Design Pattern should be used?

---

# DSA vs LLD

| DSA | LLD |
|-------|-------|
| Focuses on solving problems efficiently | Focuses on designing maintainable systems |
| Uses algorithms and data structures | Uses OOP and design patterns |
| Optimizes time and space complexity | Optimizes code structure and extensibility |
| Common in coding interviews | Common in design interviews |

## Example

### Uber Driver Matching

#### DSA Perspective

Find the nearest driver using:

- Graphs
- Priority Queue
- Dijkstra Algorithm

#### LLD Perspective

Design classes such as:

```java
Driver
Rider
Trip
Location
MatchingService
```

Define:

- Responsibilities
- Relationships
- Interactions

---

# High-Level Design (HLD)

High-Level Design focuses on the overall architecture of the system.

It answers questions like:

- How many services will exist?
- Which database should be used?
- How will services communicate?
- Where should caching be introduced?
- How will the system scale?

---

# Relationship Between DSA, LLD and HLD

```text
High-Level Design (HLD)
│
├── Service A
│
├── Service B
│
└── Service C
      │
      └── Low-Level Design (LLD)
              │
              ├── Classes
              ├── Objects
              ├── Interfaces
              └── Design Patterns
                       │
                       └── Data Structures & Algorithms (DSA)
```

---

# Key Learnings

1. LLD is about code organization.
2. DSA powers efficient operations inside classes.
3. HLD and LLD complement each other.
4. Good software is maintainable, reusable, and extensible.

---

# Interview Question

### Why is DSA important in Low-Level Design?

**Answer:**

Low-Level Design defines how classes and components interact, but those components still perform operations internally. Efficient Data Structures and Algorithms ensure those operations remain performant and scalable as the system grows.

---

# Day 01 Summary

- Learned the fundamentals of Low-Level Design
- Understood the difference between DSA and LLD
- Learned how HLD, LLD, and DSA fit together
- Built a foundation for future System Design topics

---

### Challenge

**#30DaysOfSystemDesign**

Day 01 ✅ Completed

**Next Up:** SOLID Principles & Object-Oriented Design Basics 🚀
