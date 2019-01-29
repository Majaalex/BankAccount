public class SavingsAccount extends Account {
    private double totalInterest;

    public SavingsAccount(String owner, int account){
        super(owner, account);
        setAccountType(2);
    }

    public double getTotalInterest(){
        return totalInterest;
    }

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
