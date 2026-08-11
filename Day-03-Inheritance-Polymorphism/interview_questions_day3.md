# Day 03 - Interview Questions & Answers

## 1. What is Inheritance?

### Answer

Inheritance is an OOP concept where one class acquires the properties and behaviors of another class.

It promotes:

- Code Reusability
- Maintainability
- Extensibility

### Example

```java
class Vehicle {

    void start() {
        System.out.println("Vehicle Started");
    }
}

class Car extends Vehicle {

}
```

Here, Car inherits the start() method from Vehicle.

---

## 2. What are the advantages of Inheritance?

### Answer

Advantages:

- Code Reusability
- Reduced Duplication
- Better Maintainability
- Easier Extensibility
- Supports Hierarchical Relationships

---

## 3. What is an IS-A Relationship?

### Answer

Inheritance represents an IS-A relationship.

Examples:

```text
Car IS-A Vehicle
Dog IS-A Animal
Manager IS-A Employee
```

Whenever an IS-A relationship exists, inheritance can be considered.

---

## 4. What are the types of Inheritance in Java?

### Answer

Java supports:

- Single Inheritance
- Multilevel Inheritance
- Hierarchical Inheritance

### Example

```text
Vehicle
   ↑
  Car
   ↑
SportsCar
```

This is Multilevel Inheritance.

---

## 5. Why doesn't Java support Multiple Inheritance through classes?

### Answer

To avoid ambiguity caused by the Diamond Problem.

Example:

```text
      A
     / \
    B   C
     \ /
      D
```

If both B and C contain the same method, Java won't know which implementation D should inherit.

Therefore Java supports multiple inheritance using Interfaces.

---

## 6. What is Polymorphism?

### Answer

Polymorphism means:

"One Interface, Multiple Forms"

The same method call can behave differently depending on the object.

Example:

```java
Animal animal = new Dog();
animal.sound();
```

The behavior depends on the actual object.

---

## 7. What are the types of Polymorphism?

### Answer

1. Static Polymorphism (Compile-Time)
2. Dynamic Polymorphism (Run-Time)

---

## 8. What is Static Polymorphism?

### Answer

Static Polymorphism is achieved through Method Overloading.

The compiler determines which method to execute.

### Example

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

The method is selected during compilation.

---

## 9. What is Method Overloading?

### Answer

Method Overloading occurs when:

- Method Name is same
- Parameters are different

The difference can be:

- Number of Parameters
- Type of Parameters

### Example

```java
void print(int a)
void print(String a)
void print(int a, int b)
```

---

## 10. Can we overload methods by changing only the return type?

### Answer

No.

This is invalid:

```java
int add(int a, int b)
double add(int a, int b)
```

The compiler cannot determine which method should be called.

---

## 11. What is Dynamic Polymorphism?

### Answer

Dynamic Polymorphism is achieved through Method Overriding.

The decision is made at Runtime.

Example:

```java
Animal animal = new Dog();

animal.sound();
```

Runtime determines which implementation should execute.

---

## 12. What is Method Overriding?

### Answer

Method Overriding occurs when a child class provides its own implementation of a parent class method.

### Example

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
```

---

## 13. Difference Between Overloading and Overriding

### Answer

| Method Overloading | Method Overriding |
|-------------------|-------------------|
| Compile-Time Polymorphism | Runtime Polymorphism |
| Same Class | Parent-Child Classes |
| Parameters must differ | Signature remains same |
| Faster Resolution | Runtime Resolution |

---

## 14. What is Runtime Polymorphism?

### Answer

Runtime Polymorphism occurs when the method to execute is determined during program execution.

Example:

```java
Animal animal = new Dog();

animal.sound();
```

The JVM determines that the object is Dog and calls Dog's implementation.

---

## 15. What is Upcasting?

### Answer

Upcasting means assigning a child object reference to a parent reference.

### Example

```java
Animal animal = new Dog();
```

Benefits:

- Flexibility
- Runtime Polymorphism
- Loose Coupling

---

## 16. What is a Virtual Function?

### Answer

A Virtual Function is a function whose implementation is decided at runtime.

Java methods are virtual by default.

Example:

```java
Animal animal = new Dog();

animal.sound();
```

Runtime decides which implementation to execute.

---

## 17. How does Java achieve Runtime Polymorphism?

### Answer

Using:

- Method Overriding
- Upcasting
- Dynamic Method Dispatch

Example:

```java
Animal animal = new Cat();

animal.sound();
```

JVM executes Cat's implementation.

---

## 18. Real-World Example of Inheritance

### Answer

Vehicle System

Parent:

```java
Vehicle
```

Children:

```java
Car
Bike
Truck
```

All vehicles share common behavior such as:

- start()
- stop()

---

## 19. Real-World Example of Dynamic Polymorphism

### Answer

Payment Gateway

```java
Payment payment = new UpiPayment();
payment.pay();
```

Later:

```java
payment = new CreditCardPayment();
payment.pay();
```

Client code remains unchanged.

This demonstrates Runtime Polymorphism.

---

## 20. Why is Polymorphism important in Low-Level Design?

### Answer

Benefits:

- Loose Coupling
- Extensibility
- Maintainability
- Scalability

Most Design Patterns use Polymorphism extensively.

Examples:

- Strategy Pattern
- Factory Pattern
- Observer Pattern
- Command Pattern

---

## 21. Why is Inheritance important in Low-Level Design?

### Answer

Inheritance helps:

- Reuse Common Code
- Reduce Duplication
- Create Hierarchies
- Improve Maintainability

Example:

```java
Employee
   ↑
Manager
Developer
Tester
```

---

## 22. When should Inheritance be avoided?

### Answer

Avoid inheritance when:

- No true IS-A relationship exists
- The hierarchy becomes too deep
- Composition provides a better solution

Modern design often prefers:

```text
Composition over Inheritance
```

---

## 23. What is Dynamic Method Dispatch?

### Answer

Dynamic Method Dispatch is the mechanism through which Java resolves overridden methods at runtime.

Example:

```java
Animal animal = new Dog();

animal.sound();
```

The JVM dispatches the call to Dog's implementation.

---

## 24. Which is preferred in modern Low-Level Design: Inheritance or Composition?

### Answer

Composition is generally preferred.

Reason:

- Lower Coupling
- Better Flexibility
- Easier Testing
- Better Maintainability

Inheritance should only be used when a strong IS-A relationship exists.

---

# Quick Revision

### Inheritance

- IS-A Relationship
- Reusability
- Parent-Child Classes

### Static Polymorphism

- Method Overloading
- Compile-Time Binding

### Dynamic Polymorphism

- Method Overriding
- Runtime Binding

### Virtual Functions

- Enable Runtime Polymorphism

### Interview Favorite Questions

✔ Difference between Overloading and Overriding

✔ Why Java doesn't support Multiple Inheritance

✔ What is Runtime Polymorphism

✔ What is Dynamic Method Dispatch

✔ Composition vs Inheritance

✔ Real-world examples of Polymorphism

---

# Day 03 Takeaway

Inheritance helps reuse behavior.

Polymorphism helps extend behavior.

Together they form the foundation of scalable and maintainable Low-Level Design systems.