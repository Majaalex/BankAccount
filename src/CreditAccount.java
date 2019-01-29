public class CreditAccount extends Account{
    public CreditAccount (String owner, long account){
        super(owner, account);
        setAccountType(3);
    }

    @Override
    public void addInterest() {
        double interest = 0.25;
        double oldBalance = getBalance();
        double newBalance = oldBalance + (oldBalance * interest);
        setBalance(newBalance);
    }

    @Override
    public double deposit(double amount) {
        double currentBalance = getBalance();
        if (currentBalance <= -amount){
            setBalance(getBalance() + amount);
            System.out.println("You have deposited " + amount + " into your account.");
            System.out.println("Your new balance is " + getBalance());
        } else {
            System.out.println("You cannot deposit more money than you currently owe.");
        }
        return 0;
    }

    @Override
    public double withdraw(double amount, double fee) {
        double upperLimit = -1000;
        double lowerLimit = 0;
        double possibleBalance = getBalance() - amount - fee;
        if (possibleBalance <= lowerLimit && possibleBalance >= upperLimit){
            setBalance(getBalance() - amount - fee);
            System.out.println("You have withdrawn " + amount + " from your account.");
            System.out.println("You currently have a debt of " + getBalance() + ".");
            return 0;
        } else {
            System.out.println("You will reach your credit limit with this withdrawal, and as such it will not be approved.");
            return 1;
        }
    }
}
