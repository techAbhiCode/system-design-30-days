# Day 03 - Inheritance & Polymorphism

## Topics Covered

- Inheritance in OOP
- Static Polymorphism (Method Overloading)
- Dynamic Polymorphism (Method Overriding)
- Virtual Functions Concept
- Real-world Applications
- Importance in Low-Level Design

---

# What is Inheritance?

Inheritance allows one class to acquire the properties and behaviors of another class.

It promotes:

- Code Reusability
- Extensibility
- Maintainability

## Real-World Example

Consider a Vehicle.

Common properties:

- brand
- speed
- start()

Specific vehicles:

- Car
- Bike
- Truck

Instead of rewriting common code, child classes inherit from Vehicle.

```java
class Vehicle {

    void start() {
        System.out.println("Vehicle Started");
    }
}

class Car extends Vehicle {

}

public class Main {
    public static void main(String[] args) {

        Car car = new Car();

        car.start();
    }
}
```

---

# Types of Inheritance in Java

- Single Inheritance
- Multilevel Inheritance
- Hierarchical Inheritance

Java does not support Multiple Inheritance through classes.

It supports multiple inheritance through interfaces.

---

# What is Polymorphism?

Polymorphism means:

"One interface, multiple forms."

The same method call can behave differently depending on the object.

There are two types:

1. Static Polymorphism
2. Dynamic Polymorphism

---

# Static Polymorphism (Method Overloading)

Method Overloading occurs when multiple methods have:

- Same Name
- Different Parameters

The compiler decides which method to call.

This is Compile-Time Polymorphism.

## Example

```java
class Calculator {

    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}
```

### Output

```text
add(2,3) -> 5
add(2,3,4) -> 9
```

---

# Dynamic Polymorphism (Method Overriding)

Method Overriding occurs when:

A child class provides its own implementation of a parent method.

The decision is made at Runtime.

This is Runtime Polymorphism.

## Example

```java
class Animal {

    void sound() {
        System.out.println("Animal Sound");
    }
}

class Dog extends Animal {

    @Override
    void sound() {
        System.out.println("Bark");
    }
}

public class Main {

    public static void main(String[] args) {

        Animal animal = new Dog();

        animal.sound();
    }
}
```

### Output

```text
Bark
```

Runtime decides which implementation to execute.

---

# Virtual Functions Concept

Java methods are virtual by default.

Meaning:

```java
Animal animal = new Dog();
animal.sound();
```

The JVM determines at runtime that the object is Dog and calls Dog's implementation.

This enables Runtime Polymorphism.

---

# Real-World Example of Dynamic Polymorphism

### Payment System

```java
interface Payment {

    void pay();
}

class UpiPayment implements Payment {

    public void pay() {
        System.out.println("Payment through UPI");
    }
}

class CreditCardPayment implements Payment {

    public void pay() {
        System.out.println("Payment through Credit Card");
    }
}
```

Application code:

```java
Payment payment = new UpiPayment();

payment.pay();
```

Tomorrow we can switch to:

```java
payment = new CreditCardPayment();
```

without changing client code.

---

# Why Inheritance Matters in LLD

Inheritance helps:

- Reduce duplicate code
- Promote reuse
- Create extensible systems

Used in:

- Employee Management Systems
- Vehicle Systems
- Payment Systems
- E-commerce Platforms

---

# Why Polymorphism Matters in LLD

Polymorphism helps:

- Follow Open/Closed Principle
- Reduce conditional statements
- Improve extensibility
- Build scalable architectures

Most Design Patterns rely heavily on Polymorphism.

Examples:

- Strategy Pattern
- Factory Pattern
- Observer Pattern
- Command Pattern

---

# Interview Takeaways

### Inheritance

- IS-A Relationship
- Code Reusability
- Parent-Child Relationship

### Static Polymorphism

- Method Overloading
- Compile-Time Binding

### Dynamic Polymorphism

- Method Overriding
- Runtime Binding

### Virtual Functions

- Enable Runtime Polymorphism
- Java methods are virtual by default

---

# Day 03 Summary

- Learned Inheritance and Code Reusability
- Learned Static Polymorphism (Overloading)
- Learned Dynamic Polymorphism (Overriding)
- Understood Virtual Functions
- Connected OOP Concepts with Low-Level Design