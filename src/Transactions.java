import java.util.Collections;
import java.util.HashMap;

public class Transactions {
    //-----------------------------------------------------------------
    //  Creates some bank accounts and requests various services.
    //-----------------------------------------------------------------
    public static void main(String[] args) {
        TextHandler menu = new TextHandler();
        FileHandler file = new FileHandler();
        NumberGenerator number = new NumberGenerator();

        // HashMap which contains all the created accounts
        HashMap<Integer, Account> accountHashMap;
        accountHashMap = file.getAccounts();

        // Creates a few different accounts on startup if the file doesn't contain these accounts yet
        if (!accountHashMap.containsKey(1)){
            createInitialAccounts(accountHashMap);
        }
        number.setAccountNumber(Collections.max(accountHashMap.keySet()));

        // Variables
        int menuChoice;
        double withdrawFee = 0.5;
        boolean menuLoop = true;

        // Initializing the thread for interestHandling
        InterestHandler interestThread = new InterestHandler(accountHashMap);
        interestThread.start();

        System.out.println("Hello and welcome to AM Banking & Finances.");
        //-------------------------------------------------
        // Do While that contains the full command box menu
        do {
            // display the menu, and get a user choice
            menu.displayMenu();
            menuChoice = menu.userInt();

            // Full menu,
            switch (menuChoice) {
                // Cases 1-3 will perform account transactions/operations
                case 1:
                case 2:
                case 3:
                    performTransaction(menu, accountHashMap, menuChoice, withdrawFee);
                    break;
                // Create account
                case 4:
                    System.out.println("Please input the name of the owner of the account: ");
                    String owner = menu.userString();
                    int accountChoice = getAccountType();
                    accountCreation(accountHashMap, accountChoice, owner);
                    break;
                // List all Accounts
                case 5:
                    System.out.println("Showing a list of all accounts");
                    System.out.println(String.format("%7s%20s%20s%20s", "Account", "Owner", "Balance", "Type"));
                    for (Account accounts : accountHashMap.values()) {
                        System.out.println(accounts.toString());
                    }
                    break;
                // Terminate program
                case 9:
                    menuLoop = false;
                    interestThread.interrupt();
                    break;
                default:
                    System.out.println("You have selected an invalid option, please try again.");
                    break;
            }
        } while (menuLoop);
        file.storeAccounts(accountHashMap);
    }

    private static int getAccountType() {
        TextHandler menu = new TextHandler();
        System.out.println("Please select the type of account you want.");
        System.out.println("A Checking Account will have an interest of 1%, while a Savings Account will have an interest of 10.");
        System.out.println("1: Checking Account");
        System.out.println("2: Savings Account");
        System.out.println("3: Credit Account");
        return menu.userInt();
    }

    //----------------------------------------------------
    // Performs an account transaction, deposit, withdraw or balance
    //----------------------------------------------------
    private static void performTransaction(TextHandler menu, HashMap<Integer, Account> accountHashMap, int menuChoice, double withdrawFee) {
        int selectedAccount;
        Account acct;
        System.out.println("Please select your account number:");
        selectedAccount = menu.userInt();
        acct = accountHashMap.get(selectedAccount);
        // Make sure the selected account exists
        if (accountHashMap.get(selectedAccount) != null) {
            // Splits up cases 1-3
            switch (menuChoice) {
                // Account Balance
                case 1:
                    System.out.println("Your current balance is ");
                    System.out.println(acct.getBalance());
                    if (acct instanceof SavingsAccount) {
                        System.out.println("You have accumulated a total interest of: " + ((SavingsAccount) acct).getTotalInterest() + ".");
                    }
                    break;
                // Deposit to Account
                case 2:
                    System.out.println("How much would you like to deposit?");
                    double depositValue = menu.userDouble();
                    acct.deposit(depositValue);
                    break;
                // Withdraw from Account
                case 3:
                    System.out.println("How much would you like to withdraw? There is a 0.5 withdraw fee.");
                    double withdrawValue = menu.userDouble();
                    acct.withdraw(withdrawValue, withdrawFee);
                    break;
            }
        } else {
            // If no account was found in the HashMap
            System.out.println("The account you selected does not exist.");
        }
    }

    //----------------------------------------------------
    // Creates a few different accounts on startup
    //----------------------------------------------------
    private static void createInitialAccounts(HashMap<Integer, Account> accountHashMap) {
        NumberGenerator number = new NumberGenerator();
        Account testAcc1 = new CheckingAccount("Ted Murphy", 1);
        Account testAcc2 = new SavingsAccount("Jane Smith", number.getUniqueNumber());
        Account testAcc3 = new CreditAccount("Edward Demsey", number.getUniqueNumber());
        accountHashMap.put(testAcc1.getAccountNum(), testAcc1);
        accountHashMap.put(testAcc2.getAccountNum(), testAcc2);
        accountHashMap.put(testAcc3.getAccountNum(), testAcc3);
        accountHashMap.get(testAcc1.getAccountNum()).deposit(110.0);
        accountHashMap.get(testAcc2.getAccountNum()).deposit(50.11);
        accountHashMap.get(testAcc3.getAccountNum()).withdraw(500, 0.25);
        accountHashMap.get(testAcc3.getAccountNum()).deposit(110.0);
    }

    //----------------------------------------------------
    // Sets up the account creation and asks for the relevant information
    //----------------------------------------------------
    private static void accountCreation(HashMap<Integer, Account> accountHashMap, int accountChoice, String owner) {
        TextHandler menu = new TextHandler();
        NumberGenerator number = new NumberGenerator();
        boolean accountCreated = true;
        double acctBalance;
        Account acct = null;
        switch (accountChoice) {
            case 1:
                System.out.println("You have selected a Checking Account.");
                acct = new CheckingAccount(owner, number.getUniqueNumber());
                break;
            case 2:
                System.out.println("You have selected a Savings Account.");
                acct = new SavingsAccount(owner, number.getUniqueNumber());
                break;
            case 3:
                System.out.println("You have selected a Credit Account.");
                acct = new CreditAccount(owner, number.getUniqueNumber());
                break;
            default:
                System.out.println("This is not a valid option, please try again.");
                accountCreated = false;
        }

        if (accountCreated){
            if (acct instanceof CreditAccount){
                acctBalance = 0;
            } else {
                System.out.println("Please choose your initial deposit.");
                acctBalance = menu.userDouble();
            }
            acct.deposit(acctBalance);
            accountHashMap.put(acct.getAccountNum(), acct);
            System.out.println("You have successfully created a new Savings Account, with a current balance of " + acct.getBalance() + ".");
        }
    }
}
