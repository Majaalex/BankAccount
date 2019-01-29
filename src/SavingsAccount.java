public class SavingsAccount extends Account {
    private double totalInterest;

    SavingsAccount(String owner, int account) {
        super(owner, account);
        setAccountType(2);
    }

    double getTotalInterest() {
        return totalInterest;
    }

    // Adds interest and stores the total interest the account has accumulated
    @Override
    public void addInterest() {
        double interest = 0.1;
        double oldBalance = getBalance();
        double newBalance = oldBalance + (oldBalance * interest);
        setBalance(newBalance);
        double addedInterest = newBalance - oldBalance;
        totalInterest = totalInterest + addedInterest;
    }
}
