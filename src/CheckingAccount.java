public class CheckingAccount extends Account{

    public CheckingAccount(String owner, long account){
        super(owner, account);
        setAccountType(1);
    }
    @Override
    public void addInterest() {
        double interest = 0.01;
        double oldBalance = getBalance();
        double newBalance = oldBalance + (oldBalance * interest);
        setBalance(newBalance);
    }
}
