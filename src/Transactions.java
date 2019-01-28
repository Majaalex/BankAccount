//********************************************************************
//  Transactions.java       Author: Lewis/Loftus
//
//  Demonstrates the creation and use of multiple Account objects.
//********************************************************************

import java.util.ArrayList;

public class Transactions
{
    //-----------------------------------------------------------------
    //  Creates some bank accounts and requests various services.
    //-----------------------------------------------------------------

    public static void main (String[] args)
    {
        TextHandler menu = new TextHandler();

        // This ArrayList holds all created accounts
        ArrayList<Account> accountHolder = new ArrayList<>();
        // Creates a few different accounts on startup
        initialAccounts(accountHolder);


        System.out.println("Hello and welcome to AM Banking & Finances.");

        int menuChoice;
        double withdrawFee = 0.5;
        boolean menuLoop = true;
        boolean accountExists;
        int accountSelection;
        int accountIndex = 0;

        // constructors variables
        /*String owner;
        int acctNumber;
        double acctBalance;

        // account creation section
        //-----a check if an accountType gets selected,
        //and then stores which type of account ------
        boolean accountTypeCheck;
        int accountType;*/

        // Initializing the thread for interestHandling
        InterestHandler interestThread = new InterestHandler(accountHolder);
        interestThread.start();

        //-------------------------------------------------
        // Do While that contains the full command box menu
        do {
            // display the menu, and get a user choice
            menu.displayMenu();
            menuChoice = menu.userInt();

            //if existing account, ask for the account number
            if (menuChoice < 4){
                System.out.println("Please select your account number:");
                accountSelection = menu.userInt();

                // finds the account in the arraylist
                accountExists = false;
                for(int i = 0; i < accountHolder.size(); i++){
                    // once it finds the correct account, saved the index for it
                    if (accountHolder.get(i).getAccountNum() == accountSelection) {
                        accountIndex = i;
                        accountExists = true;
                    }
                }
                if (!accountExists){
                    System.out.println("You have input an account that does not exist. Please create a new account");
                    menuChoice = 4;
                }
            }

            switch (menuChoice){

                //------------------Shows the account balance
                case 1:
                    System.out.println("Your current balance is ");
                    System.out.println(accountHolder.get(accountIndex).getBalance());
                    if(accountHolder.get(accountIndex) instanceof SavingsAccount){
                        SavingsAccount accountAsSaving = (SavingsAccount) accountHolder.get(accountIndex);
                        System.out.println("You have accumulated a total interest of: " + accountAsSaving.getTotalInterest());
                    }
                    break;
                //------------------Deposits a value into the selected account
                case 2:
                    System.out.println("How much would you like to deposit?");
                    double depositValue = menu.userDouble();
                    accountHolder.get(accountIndex).deposit(depositValue);
                    break;
                // Withdraws a value from the selected account
                case 3:
                    System.out.println("How much would you like to withdraw? There is a 0.5 withdraw fee.");
                    double withdrawValue = menu.userDouble();
                    accountHolder.get(accountIndex).withdraw(withdrawValue, withdrawFee);
                    break;
                //-------------------Create a new account
                case 4:
                    System.out.println("Please select the type of account you want.");
                    System.out.println("A Checking Account will have an interest of 1%, while a Savings Account will have an interest of 10.");
                    System.out.println("1: Checking Account");
                    System.out.println("2: Savings Account");
                    System.out.println("3: Credit Account");
                    int accountChoice = menu.userInt();
                    accountSelector(accountHolder, menu, accountChoice);
                    break;
                // end the program
                case 5:
                    menuLoop = false;
                    interestThread.interrupt();
                    break;
                case 6:
                    System.out.println("Showing a list of all accounts");
                    for (Account accounts : accountHolder){
                        System.out.println(accounts.toString());
                       /* accounts.toString();
                        System.out.println("Account Number: " + accounts.getAccountNum() + ", Owner: "  + accounts.getOwner() +
                                ", Account Type: "+ accounts.getAccountType() + ", Balance: " + accounts.getBalance());*/
                    }
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
    private static void initialAccounts(ArrayList<Account> accountHolder) {
        accountHolder.add(new CheckingAccount("Ted Murphy", 12));
        accountHolder.get(0).deposit(110.0);
        accountHolder.add(new CheckingAccount ("Jane Smith", 13));
        accountHolder.get(1).deposit(50.11);
        accountHolder.add(new SavingsAccount ("Edward Demsey", 14));
        accountHolder.get(2).deposit(720);
        accountHolder.add(new CreditAccount("Kalle Anka", 15));
        accountHolder.get(3).withdraw(500,0.25);
        accountHolder.get(3).deposit(50);
    }

    //----------------------------------------------------
    // Sets up the account creation and asks for the relevant information
    //----------------------------------------------------
    private static void accountSelector(ArrayList<Account> accountHolder, TextHandler menu, int accountChoice) {
        int accountType;
        boolean accountTypeCheck;
        int acctNumber;
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
            System.out.println("Please select an account number.");
            acctNumber = menu.userInt();
            System.out.println("Now, please input the name of the owner of the account: ");
            owner = menu.userString();
            System.out.println("Please choose your initial deposit.");
            acctBalance = menu.userDouble();

            accountCreation(accountHolder, owner, acctNumber, acctBalance, accountType);
        }
    }

    //----------------------------------------------------
    // Creates the account as specified by accountSelector
    //----------------------------------------------------
    private static void accountCreation(ArrayList<Account> accountHolder, String owner, int acctNumber, double acctBalance, int accountType) {
        //----------------------------------------//
        // the user is making a Checking account //
        if (accountType == 1){
            Account acct = new CheckingAccount(owner, acctNumber);
            acct.deposit(acctBalance);
            accountHolder.add(acct);
            System.out.println("You have successfully created a new Checking Account, with a current balance of " + acct.getBalance() + ".");
        }
        //---------------------------------------//
        // the user is making a Savings account //
        if(accountType == 2){
            Account acct = new SavingsAccount(owner, acctNumber);
            acct.deposit(acctBalance);
            accountHolder.add(acct);
            System.out.println("You have successfully created a new Savings Account, with a current balance of " + acct.getBalance() + ".");
        }
        //--------------------------------------//
        // the user is making a Credit account //
        if(accountType == 3){
            Account acct = new CreditAccount(owner, acctNumber);
            acct.deposit(acctBalance);
            accountHolder.add(acct);
            System.out.println("You have successfully created a new Savings Account, with a current balance of " + acct.getBalance() + ".");
        }
    }
}
