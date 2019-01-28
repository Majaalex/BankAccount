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
        // create an arraylist and add a few entries
        ArrayList<Account> accountHolder = new ArrayList<>();
        accountHolder.add(new CheckingAccount("Ted Murphy", 72354, 102.56));
        accountHolder.add(new CheckingAccount ("Jane Smith", 69713, 40.00));
        accountHolder.add(new SavingsAccount ("Edward Demsey", 93757, 759.32));

        TextHandler menu = new TextHandler();
        System.out.println("Hello and welcome to AM Banking & Finances.");

        int menuChoice;
        double withdrawFee = 0.5;
        boolean menuLoop = true;
        boolean accountExists;
        int accountSelection;
        int accountIndex = 0;

        // constructors variables
        String owner;
        int acctNumber;
        double acctBalance;

        // account creation section
        //-----a check if an accountType gets selected,
        //and then stores which type of account ------
        boolean accountTypeCheck;
        int accountType;

        // Initializing the thread for interestHadnling
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
                // Shows the account balance
                case 1:
                    System.out.println("Your current balance is ");
                    System.out.println(accountHolder.get(accountIndex).getBalance());
                    if(accountHolder.get(accountIndex) instanceof SavingsAccount){
                        SavingsAccount accountAsSaving = (SavingsAccount) accountHolder.get(accountIndex);
                        System.out.println("You have accumulated a total interest of: " + accountAsSaving.getTotalInterest());
                    }
                    break;
                // Deposits a value into the selected account
                case 2:
                    System.out.println("How much would you like to deposit?");
                    double depositValue = menu.userDouble();
                    accountHolder.get(accountIndex).deposit(depositValue);
                    System.out.println("You have successfully deposited " + depositValue + ", your balance is now " + accountHolder.get(accountIndex).getBalance());
                    break;
                // Withdraws a value from the selected account
                case 3:
                    System.out.println("How much would you like to withdraw? There is a 0.5 withdraw fee.");
                    double withdrawValue = menu.userDouble();
                    accountHolder.get(accountIndex).withdraw(withdrawValue, withdrawFee);
                    System.out.println("You have successfully withdrew " + withdrawValue + ", your balance is now " + accountHolder.get(accountIndex).getBalance());
                    break;
                // Create a new account
                case 4:
                    System.out.println("Please select the type of account you want.");
                    System.out.println("A Checking Account will have an interest of 1%, while a Savings Account will have an interest of 10.");
                    System.out.println("1: Checking Account");
                    System.out.println("2: Savings Account");
                    int accountChoice = menu.userInt();
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
                            default:
                                System.out.println("This is not a valid option, please try again.");
                                accountType = 0;
                                accountTypeCheck = false;
                    }
                    // If the user selected a valid accountType the user gets past this check
                    if (accountTypeCheck){
                        System.out.println("Please select an account number.");
                        acctNumber = menu.userInt();
                        System.out.println("Please choose your initial deposit.");
                        acctBalance = menu.userDouble();
                        System.out.println("Now, please input the name of the owner of the account: ");
                        owner = menu.userString();

                        //----------------------------------------//
                        // the user is making a Checking account //
                        if (accountType == 1){
                            Account acct = new CheckingAccount(owner, acctNumber, acctBalance);
                            accountHolder.add(acct);
                            System.out.println("You have successfully created a new Checking Account, with a current balance of " + acct.getBalance() + ".");
                        }
                        //---------------------------------------//
                        // the user is making a Savings account //
                        if(accountType == 2){
                            Account acct = new SavingsAccount(owner, acctNumber, acctBalance);
                            accountHolder.add(acct);
                            System.out.println("You have successfully created a new Savings Account, with a current balance of " + acct.getBalance() + ".");
                        }
                    }
                    break;
                // end the program
                case 5:
                    menuLoop = false;
                    interestThread.interrupt();
                    break;
                case 6:
                    System.out.println("Showing a list of all accounts");
                    for (Account accounts : accountHolder){
                        System.out.println(accounts.getAccountNum() + " || " + accounts.getBalance());
                    }
                default:
                    System.out.println("You have selected an invalid option, please try again.");
                    break;

            }
            System.out.println(accountHolder);
        } while (menuLoop);

    }
}
