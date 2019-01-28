public class CheckingAccount extends Account{

    public CheckingAccount(String owner, long account, double initial){
        super(owner, account, initial);
        setOwner(owner);
        setAccountNum(account);
        setBalance(initial);
        setAccountType(1);
    }
    @Override
    public void addInterest() {
        double interest = 0.01;
        double newBalance = getBalance() * interest;
        setBalance(newBalance);
    }
}
