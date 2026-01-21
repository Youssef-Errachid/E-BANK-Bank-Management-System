/**
 * SavingsAccount class - Inherits from Account and represents a savings account with interest
 */
public class SavingAccount extends Account {
    private double interestRate;

    public SavingAccount(String accountNumber, double initialBalance, Client owner, double interestRate) {
        super(accountNumber, initialBalance, owner);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public double calculateInterest() {
        return getBalance() * (interestRate / 100);
    }

    public void applyInterest() {
        double interest = calculateInterest();
        deposit(interest);
        System.out.println("Interest applied: " + String.format("%.2f", interest));
    }


}