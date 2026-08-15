import dip.UrlStorage;
import isp.WebHosting;
import lsp.DepositAccount;
import lsp_rules.EcomOrder;
import ocp.Persistence;
import srp.ShoppingCart;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- 1. SRP Demo ---");
        ShoppingCart.runDemo();

        System.out.println("\n--- 2. OCP Demo ---");
        Persistence.runDemo();

        System.out.println("\n--- 3. LSP Demo ---");
        DepositAccount.runDemo();

        System.out.println("\n--- 4. LSP Rules Demo ---");
        EcomOrder.runDemo();

        System.out.println("\n--- 5. ISP Demo ---");
        WebHosting.runDemo();

        System.out.println("\n--- 6. DIP Demo ---");
        UrlStorage.runDemo();
    }
}