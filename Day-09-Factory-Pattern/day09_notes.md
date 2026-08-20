# Day 9 - Factory Design Pattern (Creational)

## 1. Introduction: Why do we need a Factory?
In real-world applications, mixing **Business Logic** with **Object Creation Logic** (using the `new` keyword everywhere) makes the code tightly coupled, hard to read, and difficult to maintain. 
The Factory Design Pattern solves this by delegating the responsibility of object creation to a dedicated "Factory" class. The client simply asks the factory for an object, completely decoupling the client from the concrete implementations.

---

## 2. The Three Types of Factories

### A. Simple Factory (Programming Idiom)
Not officially a GoF design pattern, but heavily used in practice. It involves a single factory class that uses conditional logic (`if-else` or `switch`) to return different concrete objects based on an input parameter.

**Example: Burger Shop**
```java
// 1. The Product Interface
interface Burger { 
    void prepare(); 
}

// 2. Concrete Products
class BasicBurger implements Burger { 
    public void prepare() { System.out.println("Preparing Basic Burger"); } 
}
class PremiumBurger implements Burger { 
    public void prepare() { System.out.println("Preparing Premium Burger"); } 
}

// 3. The Simple Factory
class SimpleBurgerFactory {
    public Burger createBurger(String type) {
        if (type.equalsIgnoreCase("basic")) return new BasicBurger();
        if (type.equalsIgnoreCase("premium")) return new PremiumBurger();
        throw new IllegalArgumentException("Unknown burger type");
    }
}
```

### B. Factory Method Pattern
Defines an interface for creating an object, but allows subclasses to alter the type of objects that will be created. We move from a single monolithic factory to specialized factory subclasses.

**Example: Two different franchises (SinghBurger - Normal, KingBurger - Wheat)**
```java
// 1. Concrete Products (Normal vs Wheat)
class NormalBasicBurger implements Burger { 
    public void prepare() { System.out.println("Normal Basic Burger"); } 
}
class WheatBasicBurger implements Burger { 
    public void prepare() { System.out.println("Healthy Wheat Basic Burger"); } 
}

// 2. The Creator (Abstract Factory Class)
abstract class BurgerFactory {
    // The Factory Method
    abstract Burger createBurger(String type);
}

// 3. Concrete Creators (Subclasses decide which object to instantiate)
class SinghBurgerFactory extends BurgerFactory {
    @Override
    Burger createBurger(String type) {
        if (type.equalsIgnoreCase("basic")) return new NormalBasicBurger();
        return null; 
    }
}

class KingBurgerFactory extends BurgerFactory {
    @Override
    Burger createBurger(String type) {
        if (type.equalsIgnoreCase("basic")) return new WheatBasicBurger();
        return null;
    }
}
```

### C. Abstract Factory Pattern
Provides an interface for creating **families of related or dependent objects** without specifying their concrete classes. 

**Example: Creating a Full Meal (Burger + Garlic Bread)**
```java
// 1. Abstract Products
interface Burger { void prepare(); }
interface GarlicBread { void bake(); }

// Concrete Implementations (Omitted for brevity, e.g., NormalBurger, WheatBurger)

// 2. Abstract Factory (Creates a family of products)
interface MealFactory {
    Burger createBurger();
    GarlicBread createGarlicBread();
}

// 3. Concrete Factory 1: Normal Meal Family (SinghBurger)
class SinghMealFactory implements MealFactory {
    public Burger createBurger() { return new NormalBurger(); }
    public GarlicBread createGarlicBread() { return new NormalGarlicBread(); }
}

// 4. Concrete Factory 2: Healthy/Wheat Meal Family (KingBurger)
class KingMealFactory implements MealFactory {
    public Burger createBurger() { return new WheatBurger(); }
    public GarlicBread createGarlicBread() { return new WheatGarlicBread(); }
}
```

---

## 3. Real-World Application: Notification System
If building a Notification System (SMS, Email, Push), you might wonder whether to use Strategy or Factory:
*   **Factory Pattern:** Use this if you want to separate the *creation logic* of the notification objects from the core business logic. The client asks `NotificationFactory.create("SMS")`.
*   **Strategy Pattern:** Use this if you already have the objects created and want to dynamically switch the *behavior/algorithm* at runtime.

### Golden Rule:
*   Varying Algorithms at Runtime? ➡️ **Strategy Pattern**
*   Separating Object Creation Logic? ➡️ **Factory Pattern**
