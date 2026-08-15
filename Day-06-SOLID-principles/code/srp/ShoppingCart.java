package srp;

public class ShoppingCart {
    public double calculateTotal() { return 100.50; }
    
    // Yahan humne demo logic encapsulate kar diya
    public static void runDemo() {
        ShoppingCart cart = new ShoppingCart();
        InvoicePrinter printer = new InvoicePrinter();
        printer.print(cart);
    }
}

class InvoicePrinter {
    public void print(ShoppingCart cart) {
        System.out.println("SRP: Printing Invoice for total: $" + cart.calculateTotal());
    }
}