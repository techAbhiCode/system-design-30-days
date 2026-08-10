# Day 02 - Interview Questions & Answers

## 1. What is Object-Oriented Programming (OOP)?

### Answer

Object-Oriented Programming (OOP) is a programming paradigm that organizes software around objects rather than functions.

An object contains:

- Data (Attributes)
- Behavior (Methods)

OOP helps in:

- Code Reusability
- Scalability
- Maintainability
- Modularity

### Example

```java
class Car {
    String brand;

    void start() {
        System.out.println("Car Started");
    }
}
```

---

## 2. What are the four pillars of OOP?

### Answer

The four pillars of OOP are:

1. Abstraction
2. Encapsulation
3. Inheritance
4. Polymorphism

These pillars help create maintainable and reusable software.

---

## 3. What is Abstraction?

### Answer

Abstraction means showing only the necessary details and hiding implementation details.

Users interact with the exposed functionality without knowing the internal working.

### Real-world Example

Driving a car:

Visible:

- Steering
- Brake
- Accelerator

Hidden:

- Engine
- Fuel Injection
- Gearbox Logic

### Java Example

```java
abstract class Vehicle {

    abstract void start();

    public void stop() {
        System.out.println("Vehicle Stopped");
    }
}
```

---

## 4. Why is Abstraction important?

### Answer

Benefits:

- Reduces complexity
- Improves maintainability
- Improves scalability
- Hides unnecessary implementation details

---

## 5. How is Abstraction achieved in Java?

### Answer

Java achieves abstraction using:

- Abstract Classes
- Interfaces

### Example

```java
interface Payment {
    void pay();
}

class CreditCardPayment implements Payment {

    @Override
    public void pay() {
        System.out.println("Payment via Credit Card");
    }
}
```

---

## 6. What is Encapsulation?

### Answer

Encapsulation means binding data and methods together and restricting direct access to data.

Data is protected using access modifiers.

### Example

```java
class BankAccount {

    private double balance;

    public void deposit(double amount) {
        balance += amount;
    }

    public double getBalance() {
        return balance;
    }
}
```

---

## 7. Why is Encapsulation important?

### Answer

Benefits:

- Data Security
- Better Control
- Reduced Coupling
- Easy Maintenance

Encapsulation prevents unauthorized modification of data.

---

## 8. How is Encapsulation achieved in Java?

### Answer

Using:

- Private variables
- Public getters
- Public setters

### Example

```java
class Student {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

---

## 9. Difference Between Abstraction and Encapsulation

### Answer

| Abstraction | Encapsulation |
|------------|---------------|
| Hides implementation details | Hides data |
| Focuses on behavior | Focuses on protection |
| What to do | How to protect |
| Achieved using abstract classes/interfaces | Achieved using access modifiers |

---

## 10. What is Procedural Programming?

### Answer

Procedural Programming organizes programs around functions and procedures.

Examples:

- C
- Pascal

### Example

```java
void withdrawMoney() {
    System.out.println("Money Withdrawn");
}
```

---

## 11. What are the limitations of Procedural Programming?

### Answer

- Difficult to scale
- Difficult to maintain
- Low reusability
- Data is often exposed
- High dependency between functions

---

## 12. Why was OOP introduced?

### Answer

OOP was introduced to solve the limitations of procedural programming.

Advantages:

- Better Code Organization
- Reusability
- Security
- Scalability
- Real-world Modeling

---

## 13. Difference Between Procedural Programming and OOP

### Answer

| Procedural Programming | OOP |
|-----------------------|-----|
| Function-Oriented | Object-Oriented |
| Top-down approach | Bottom-up approach |
| Less Reusable | Highly Reusable |
| Difficult Maintenance | Easier Maintenance |
| Data Exposed | Data Protected |

---

## 14. Can Encapsulation exist without Abstraction?

### Answer

Yes.

A class can hide its data using private variables without using abstract classes or interfaces.

Example:

```java
class Employee {

    private int salary;

    public int getSalary() {
        return salary;
    }
}
```

This demonstrates Encapsulation but not Abstraction.

---

## 15. Can Abstraction exist without Encapsulation?

### Answer

Practically, abstraction usually relies on encapsulation to hide internal details.

Both concepts often work together.

---

## 16. What is a Real-Life Example of Encapsulation?

### Answer

ATM Machine

User can:

- Withdraw Money
- Deposit Money
- Check Balance

User cannot directly access:

- Bank Database
- Internal Transactions

This is Encapsulation.

---

## 17. What is a Real-Life Example of Abstraction?

### Answer

Mobile Phone

User can:

- Make Calls
- Send Messages

User does not know:

- Signal Routing
- Hardware Communication
- Network Processing

This is Abstraction.

---

## 18. Why are OOP concepts important in Low-Level Design?

### Answer

LLD is built around:

- Classes
- Objects
- Relationships

Without OOP concepts:

- Design Patterns become difficult
- UML becomes difficult
- Maintainability decreases

OOP forms the foundation of Low-Level Design.

---

# Quick Revision

### Abstraction

- Hide Implementation
- Show Functionality
- Achieved using Abstract Classes and Interfaces

### Encapsulation

- Hide Data
- Protect State
- Achieved using Access Modifiers

### Procedural Programming

- Function-Based
- Less Maintainable

### OOP

- Object-Based
- Scalable
- Reusable
- Maintainable

---

# Day 02 Takeaway

Understanding Abstraction and Encapsulation is the first step toward writing maintainable and scalable software systems.

These concepts form the foundation for Design Patterns, SOLID Principles, and Low-Level Design.