package lsp_rules;

public class EcomOrder {
    public Number getDiscount() { return 10; }
    
    public static void runDemo() {
        FestiveOrder diwaliOrder = new FestiveOrder();
        System.out.println("LSP Rule (Covariance): Festive Discount is $" + diwaliOrder.getDiscount());
        
        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.processData();
    }
}

class FestiveOrder extends EcomOrder {
    @Override
    public Double getDiscount() { return 20.5; } // Valid return type change
}

class DatabaseHandler {
    protected boolean isConnected = true;
    public void processData() {
        try { System.out.println("LSP Rule (Post-Condition): Processing DB ops..."); } 
        finally {
            isConnected = false; 
            System.out.println("LSP Rule: Post-condition met. Connection safely closed.");
        }
    }
}