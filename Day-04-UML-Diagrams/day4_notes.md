# Day 4 - UML Diagrams Interview Questions & Answers

## 1. What is UML?

### Answer
UML (Unified Modeling Language) is a standardized visual language used to design, visualize, and document software systems.

It helps developers understand:

- System structure
- Object relationships
- System behavior
- Component interactions

Before writing code, UML provides a blueprint of the system.

---

## 2. Why is UML important in Low-Level Design?

### Answer

UML helps developers:

- Visualize system architecture
- Identify classes and relationships
- Reduce design mistakes
- Improve communication among team members
- Explain design during interviews

Without UML:
Requirement → Code

With UML:
Requirement → UML Design → Code

---

## 3. What are the two major categories of UML Diagrams?

### Answer

### Structural Diagrams
Describe system structure.

Examples:

- Class Diagram
- Object Diagram
- Component Diagram
- Deployment Diagram

### Behavioral Diagrams
Describe system behavior.

Examples:

- Sequence Diagram
- Activity Diagram
- State Diagram
- Use Case Diagram

---

## 4. What is a Class Diagram?

### Answer

A Class Diagram represents:

- Classes
- Attributes
- Methods
- Relationships between classes

It shows the static structure of the system.

Example:

Car

Attributes:
- brand
- model
- engineCC

Methods:
- start()
- stop()
- accelerate()

---

## 5. What are the three sections of a Class Diagram?

### Answer

A class diagram consists of:

### Class Name

Example:

Car

### Attributes

Example:

- brand : String
- model : String

### Methods

Example:

- start()
- stop()

---

## 6. What are UML Access Modifiers?

### Answer

| Symbol | Access Modifier |
|----------|----------------|
| + | Public |
| - | Private |
| # | Protected |

Example:

```text
Car
----------------
- brand : String
# engineCC : int
+ start() : void
```

---

## 7. Difference between Public, Private and Protected?

### Answer

| Modifier | Same Class | Child Class | Outside Class |
|-----------|------------|------------|--------------|
| Public | Yes | Yes | Yes |
| Protected | Yes | Yes | No |
| Private | Yes | No | No |

---

## 8. What is Association?

### Answer

Association represents a relationship between two classes.

Example:

Person ---- House

A person lives in a house.

This is a "has-a" relationship.

---

## 9. What is Aggregation?

### Answer

Aggregation is a weak "has-a" relationship.

Objects can exist independently.

Example:

Room contains:

- Sofa
- Bed
- Chair

Even if Room is removed,
Sofa, Bed and Chair still exist.

---

## 10. What is Composition?

### Answer

Composition is a strong "has-a" relationship.

Child objects cannot exist independently.

Example:

Chair contains:

- Wheels
- Legs
- Seat
- Arm Rest

If Chair is destroyed,
all parts are destroyed.

---

## 11. Difference between Aggregation and Composition?

### Answer

| Aggregation | Composition |
|-------------|-------------|
| Weak relationship | Strong relationship |
| Child survives | Child does not survive |
| Independent lifecycle | Dependent lifecycle |

Example:

Aggregation → Room & Furniture

Composition → Car & Engine

---

## 12. What is Inheritance in UML?

### Answer

Inheritance represents an "is-a" relationship.

Example:

Animal
├── Dog
├── Cat
└── Human

Dog is an Animal.

Human is an Animal.

---

## 13. What is a Sequence Diagram?

### Answer

A Sequence Diagram shows:

- Object interactions
- Message flow
- Execution order
- Runtime behavior

It represents how objects communicate over time.

---

## 14. What are Lifelines in a Sequence Diagram?

### Answer

Lifelines represent participating objects.

Example:

User
ATM
Transaction
Account
CashDispenser

Each object has a vertical dotted line called a Lifeline.

---

## 15. What is an Activation Bar?

### Answer

Activation Bar represents the period during which an object is actively executing a task.

It appears as a thin rectangle on the lifeline.

---

## 16. What are Synchronous Messages?

### Answer

Sender waits for receiver to complete processing.

Example:

ATM → Account

checkBalance()

ATM waits for response.

---

## 17. What are Asynchronous Messages?

### Answer

Sender does not wait for response.

Example:

Notification Service

sendEmail()

System continues processing.

---

## 18. What is a Create Message?

### Answer

Used when one object creates another object.

Example:

```java
Account account = new Account();
```

In UML:

A → <<create>> Account

---

## 19. What is a Destroy Message?

### Answer

Represents object destruction.

Example:

```java
account = null;
```

The object's lifeline ends with an X symbol.

---

## 20. Explain ATM Withdrawal Sequence Diagram.

### Answer

Flow:

1. User requests withdrawal
2. ATM sends request to Transaction
3. Transaction verifies Account
4. Account validates balance
5. CashDispenser dispenses cash
6. Success response returned

Objects involved:

- User
- ATM
- Transaction
- Account
- CashDispenser

---

## 21. Why are Sequence Diagrams useful in LLD Interviews?

### Answer

Sequence diagrams help interviewers evaluate:

- Object interaction understanding
- Communication flow
- Responsibility distribution
- Design thinking

Most LLD problems can be explained using sequence diagrams before coding.

---

## 22. Which UML Diagrams are most important for LLD Interviews?

### Answer

Most frequently asked:

1. Class Diagram
2. Sequence Diagram

These two diagrams are enough for:

- Parking Lot
- ATM
- Food Delivery App
- Splitwise
- Notification System
- Vending Machine

and most LLD interview questions.

---

# Quick Revision

### Class Diagram

Shows:
- Classes
- Attributes
- Methods
- Relationships

### Sequence Diagram

Shows:
- Runtime behavior
- Object communication
- Message flow
- Execution order

### Relationships

- Inheritance → Is-A
- Association → Has-A
- Aggregation → Weak Has-A
- Composition → Strong Has-A

### UML Access Symbols

+ Public
# Protected
- Private