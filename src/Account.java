
import java.text.NumberFormat;

public abstract class Account {
    //private final double RATE = 0.035; interest rate of 3.5%
    private int acctNumber;
    private double balance;
    private String name;
    private int accountType;
    private static int latestAccountNum = 23;

    //-----------------------------------------------------------------
    //  Sets up the account by defining its owner, account number,
    //  and initial balance.
    //-----------------------------------------------------------------
    Account(String owner, int account) {
        setOwner(owner);
        setAccountNum(account);
    }


    //-----------------------------------------------------------------
    // SET functions
    //-----------------------------------------------------------------
    private void setOwner(String owner) {
        this.name = owner;
    }

    private void setAccountNum(int account) {
        this.acctNumber = account;
    }

    void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    void setBalance(double initial) {
        this.balance = initial;
    }


    //-----------------------------------------------------------------
    // GET functions
    //-----------------------------------------------------------------
    int getAccountNum() {
        return acctNumber;
    }

    public int getAccountType() {
        return accountType;
    }

    public String getOwner() {
        return name;
    }

    //-----------------------------------------------------------------
    //  Returns the current balance of the account.
    //-----------------------------------------------------------------
    double getBalance() {
        return balance;
    }

    //-----------------------------------------------------------------
    //  Deposits the specified amount into the account. Returns the
    //  new balance.
    //-----------------------------------------------------------------
    public double deposit(double amount) {
        balance = balance + amount;
        System.out.println("You have deposited " + amount + " into your account.");
        System.out.println("You now have a balance of " + balance);
        return balance;
    }

    //-----------------------------------------------------------------
    //  Withdraws the specified amount from the account and applies
    //  the fee. Returns the new balance.
    //-----------------------------------------------------------------
    public double withdraw(double amount, double fee) {
        balance = balance - amount - fee;
        System.out.println("You have withdrawn " + amount + " from your account.");
        System.out.println("You now have a balance of " + balance);
        return balance;
    }

    //-----------------------------------------------------------------
    //  Adds interest to the account and returns the new balance.
    //-----------------------------------------------------------------
    public abstract void addInterest();


    //-----------------------------------------------------------------
    //  Returns a one-line description of the account as a string.
    //-----------------------------------------------------------------
    public String toString() {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();

        if (accountType == 1) {
            return String.format("%7s%20s%20s%20s", acctNumber, name, fmt.format(balance), "Checking Account");
        }
        if (accountType == 2) {
            return String.format("%7s%20s%20s%20s", acctNumber, name, fmt.format(balance), "Savings Account");
        }
        if (accountType == 3) {
            return String.format("%7s%20s%20s%20s", acctNumber, name, fmt.format(balance), "Credit Account");
        } else return String.format("%7s%20s%20s%20s", acctNumber, name, fmt.format(balance), accountType);
    }

    static int generateAccountNum() {
        return ++latestAccountNum;
    }
}
