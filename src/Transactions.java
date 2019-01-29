import java.util.HashMap;

public class Transactions
{
    //-----------------------------------------------------------------
    //  Creates some bank accounts and requests various services.
    //-----------------------------------------------------------------

    public static void main (String[] args)
    {
        TextHandler menu = new TextHandler();

        // HashMap which contains all the created accounts
        HashMap<Integer, Account> accountHashMap = new HashMap<>();
        // Creates a few different accounts on startup
        initialAccounts(accountHashMap);


        System.out.println("Hello and welcome to AM Banking & Finances.");

        int menuChoice;
        double withdrawFee = 0.5;
        boolean menuLoop = true;
        // Stores the selected account number
        int accountSelection;
        // Variable that gets the selected account
        Account acct;

        // Initializing the thread for interestHandling
        InterestHandler interestThread = new InterestHandler(accountHashMap);
        interestThread.start();

        //-------------------------------------------------
        // Do While that contains the full command box menu
        do {
            // display the menu, and get a user choice
            menu.displayMenu();
            menuChoice = menu.userInt();

            // Full menu,
            switch (menuChoice){
                // Cases 1-3 will all ask for an account number
                case 1:
                case 2:
                case 3:
                    System.out.println("Please select your account number:");
                    accountSelection = menu.userInt();
                    acct = accountHashMap.get(accountSelection);
                    // Make sure the selected account exists
                    if (accountHashMap.get(accountSelection) != null){
                        // Splits up cases 1-3
                        switch (menuChoice){
                                // Account Balance
                            case 1:
                                System.out.println("Your current balance is ");
                                System.out.println(acct.getBalance());
                                if(acct instanceof SavingsAccount){
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
                        // If no account was found in the hashmap
                        System.out.println("The account you selected does not exist.");
                        break;
                    }
                    break;
                    // Create account
                case 4:
                    System.out.println("Please select the type of account you want.");
                    System.out.println("A Checking Account will have an interest of 1%, while a Savings Account will have an interest of 10.");
                    System.out.println("1: Checking Account");
                    System.out.println("2: Savings Account");
                    System.out.println("3: Credit Account");
                    int accountChoice = menu.userInt();
                    accountSelector(accountHashMap, menu, accountChoice);
                    break;
                    // List all Accounts
                case 5:

                    System.out.println("Showing a list of all accounts");
                    System.out.println(String.format("%7s%20s%20s%20s","Account","Owner","Balance","Type"));
                    for (Account accounts : accountHashMap.values()){
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

    }

    //----------------------------------------------------
    // Creates a few different accounts on startup
    //----------------------------------------------------
    private static void initialAccounts(HashMap<Integer, Account> accountHashMap) {
        Account testAcc1 = new CheckingAccount("Ted Murphy", Account.generateAccountNum());
        Account testAcc2 = new SavingsAccount("Jane Smith", Account.generateAccountNum());
        Account testAcc3 = new CreditAccount("Edward Demsey", Account.generateAccountNum());
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
    private static void accountSelector(HashMap<Integer, Account> accountHashMap, TextHandler menu, int accountChoice) {
        int accountType;
        boolean accountTypeCheck;
        String owner;
        double acctBalance;
        switch (accountChoice){
            case 1:
                System.out.println("You have selected a Checking Account, please fill in the following details.");
                accountType = 1;
                accountTypeCheck = true;
                break;
            case 2:
                System.out.println("You have selected a Savings Account, please fill in the following details.");
                accountType = 2;
                accountTypeCheck = true;
                break;
            case 3:
                System.out.println("You have selected a Credit Account, please fill in the following details.");
                accountType = 3;
                accountTypeCheck = true;
                break;
                default:
                    System.out.println("This is not a valid option, please try again.");
                    accountType = 0;
                    accountTypeCheck = false;
        }
        // If the user selected a valid accountType the user gets past this check
        if (accountTypeCheck){
            System.out.println("Please input the name of the owner of the account: ");
            owner = menu.userString();
            if (accountType != 3){
                System.out.println("Please choose your initial deposit.");
                acctBalance = menu.userDouble();
            } else {
                acctBalance = 0;
            }
            accountCreation(accountHashMap, owner, acctBalance, accountType);
        }
    }

    //----------------------------------------------------
    // Creates the account as specified by accountSelector
    //----------------------------------------------------
    private static void accountCreation(HashMap<Integer, Account> accountHashMap, String owner, double acctBalance, int accountType) {
        Account acct = null;
        //----------------------------------------//
        // the user is making a Checking account //
        if (accountType == 1){
            acct = new CheckingAccount(owner, Account.generateAccountNum());
            }
        //---------------------------------------//
        // the user is making a Savings account //
        if(accountType == 2){
            acct = new SavingsAccount(owner, Account.generateAccountNum());
            }
        //--------------------------------------//
        // the user is making a Credit account //
        if(accountType == 3){
            acct = new CreditAccount(owner, Account.generateAccountNum());

        }
        if (acct != null){
            acct.deposit(acctBalance);
            accountHashMap.put(acct.getAccountNum(),acct);
            System.out.println("You have successfully created a new Savings Account, with a current balance of " + acct.getBalance() + ".");
        }
        }
}
