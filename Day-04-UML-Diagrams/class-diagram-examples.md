# Day 4 - Class Diagram Examples

## Introduction

Class Diagrams are one of the most important UML diagrams in Low-Level Design.

They represent:

- Classes
- Attributes
- Methods
- Relationships between classes

Before writing code, Class Diagrams help us visualize how objects are connected and how responsibilities are distributed.

---

# Example 1: Car System

## Java Code

```java
class Car {
    private String brand;
    private String model;
    private int engineCC;

    public void start() {}
    public void stop() {}
    public void accelerate() {}
}
```

## UML Diagram

```text
+----------------------+
|         Car          |
+----------------------+
| - brand : String     |
| - model : String     |
| - engineCC : int     |
+----------------------+
| + start()            |
| + stop()             |
| + accelerate()       |
+----------------------+
```

---

# Example 2: Inheritance

## Scenario

Animal is the parent class.

Dog and Cat inherit from Animal.

## Java Code

```java
class Animal {
    void eat() {}
}

class Dog extends Animal {
    void bark() {}
}

class Cat extends Animal {
    void meow() {}
}
```

## UML Diagram

```text
        Animal
           ▲
           │
    ┌──────┴──────┐
    │             │
   Dog           Cat
```

### Relationship

Inheritance = Is-A Relationship

Examples:

- Dog is an Animal
- Cat is an Animal

---

# Example 3: Association

## Scenario

A Person lives in a House.

## Java Code

```java
class House {

}

class Person {
    House house;
}
```

## UML Diagram

```text
Person -------- House
```

### Relationship

Association = Has-A Relationship

Person has a House.

---

# Example 4: Aggregation

## Scenario

A Room contains furniture.

Furniture can exist even if Room is removed.

## Java Code

```java
class Sofa {

}

class Bed {

}

class Chair {

}

class Room {
    Sofa sofa;
    Bed bed;
    Chair chair;
}
```

## UML Diagram

```text
         Sofa
           |
           |
Room ◇------+
           |
         Bed
           |
        Chair
```

### Key Point

Furniture survives even if Room is destroyed.

This is a weak Has-A relationship.

---

# Example 5: Composition

## Scenario

A Chair consists of multiple parts.

Without the Chair, these parts have no meaning.

## Java Code

```java
class Wheel {

}

class Leg {

}

class Seat {

}

class Chair {
    private Wheel wheel;
    private Leg leg;
    private Seat seat;
}
```

## UML Diagram

```text
Chair ◆------ Wheel
Chair ◆------ Leg
Chair ◆------ Seat
```

### Key Point

If Chair is destroyed, all parts are destroyed.

This is a strong Has-A relationship.

---

# Example 6: ATM System

## Classes

```java
class ATM {

}

class User {

}

class Account {

}

class Transaction {

}

class CashDispenser {

}
```

## UML Diagram

```text
User -------- ATM

ATM -------- Transaction

Transaction -------- Account

Transaction -------- CashDispenser
```

### Responsibilities

User:
- Uses ATM

ATM:
- Accepts requests

Transaction:
- Handles operations

Account:
- Validates balance

CashDispenser:
- Dispenses cash

---

# Example 7: E-Commerce System

## Classes

```java
class User {

}

class Product {

}

class Cart {

}

class Order {

}
```

## UML Diagram

```text
User -------- Cart

Cart -------- Product

User -------- Order

Order -------- Product
```

### Flow

User → Cart

Cart → Products

User places Order

Order contains Products

---

# Example 8: Library Management System

## Classes

```java
class Library {

}

class Book {

}

class Member {

}
```

## UML Diagram

```text
Library ◇------ Book

Member -------- Book
```

### Meaning

Library contains Books.

Member can borrow Books.

---

# Example 9: Food Delivery App

## Classes

```java
class Customer {

}

class Restaurant {

}

class Order {

}

class DeliveryPartner {

}
```

## UML Diagram

```text
Customer -------- Order

Order -------- Restaurant

Order -------- DeliveryPartner
```

### Meaning

Customer places Order.

Restaurant prepares Order.

Delivery Partner delivers Order.

---

# Example 10: Parking Lot

## Classes

```java
class ParkingLot {

}

class ParkingSpot {

}

class Vehicle {

}
```

## UML Diagram

```text
ParkingLot ◇------ ParkingSpot

Vehicle -------- ParkingSpot
```

### Meaning

ParkingLot contains multiple spots.

Vehicle occupies a spot.

---

# Common UML Symbols

| Symbol | Meaning |
|----------|----------|
| + | Public |
| - | Private |
| # | Protected |
| ▲ | Inheritance |
| ◇ | Aggregation |
| ◆ | Composition |
| ─ | Association |

---

# Quick Revision

## Inheritance

```text
Dog → Animal
```

Is-A Relationship

---

## Association

```text
Person → House
```

Has-A Relationship

---

## Aggregation

```text
Room ◇ Furniture
```

Weak Has-A Relationship

---

## Composition

```text
Chair ◆ Wheel
```

Strong Has-A Relationship

---

# Interview Tip

Whenever an interviewer asks you to design:

- ATM
- Parking Lot
- Splitwise
- Food Delivery App
- Notification System

Start with:

1. Identifying classes
2. Drawing a Class Diagram
3. Defining relationships
4. Then move to code

This approach makes LLD interviews much easier and more structured.