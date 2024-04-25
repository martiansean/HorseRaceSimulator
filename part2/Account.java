package part2;

/**
 * Write a description of class Horse here.
 * 
 * @author Thanathorn Satayamana
 * @version 1
 */
public class Account {
    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void Adjust(int amount) {
        balance += amount;
        // System.out.println("Balance: " + balance);
    }
}
