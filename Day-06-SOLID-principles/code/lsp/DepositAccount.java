package lsp;

public interface DepositAccount {
    void deposit(double amount);
    
    static void runDemo() {
        WithdrawableAccount mySavings = new SavingsAccount();
        mySavings.deposit(500);
        mySavings.withdraw(100);
        
        DepositAccount myFd = new FixedTermAccount();
        myFd.deposit(1000);
    }
}

interface WithdrawableAccount extends DepositAccount {
    void withdraw(double amount);
}

class SavingsAccount implements WithdrawableAccount {
    public void deposit(double amount) { System.out.println("LSP: Deposited $" + amount + " to Savings."); }
    public void withdraw(double amount) { System.out.println("LSP: Withdrew $" + amount + " from Savings."); }
}

class FixedTermAccount implements DepositAccount {
    public void deposit(double amount) { System.out.println("LSP: Deposited $" + amount + " to Fixed Deposit."); }
}