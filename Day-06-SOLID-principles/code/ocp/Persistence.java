package ocp;
import srp.ShoppingCart;

public interface Persistence {
    void save(ShoppingCart cart);
    
    // Java 8+ interfaces mein static methods allow karta hai
    static void runDemo() {
        ShoppingCart cart = new ShoppingCart();
        Persistence mongoDb = new MongoPersistence();
        mongoDb.save(cart);
    }
}

class SqlPersistence implements Persistence {
    public void save(ShoppingCart cart) { System.out.println("OCP: Saving cart to MySQL..."); }
}

class MongoPersistence implements Persistence {
    public void save(ShoppingCart cart) { System.out.println("OCP: Saving cart to MongoDB..."); }
}