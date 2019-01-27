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
        accountHolder.add(new Account ("Ted Murphy", 72354, 102.56, 0.035));
        accountHolder.add(new Account ("Jane Smith", 69713, 40.00, 0.035));
        accountHolder.add(new Account ("Edward Demsey", 93757, 759.32, 0.035));

        TextHandler menu = new TextHandler();
        System.out.println("Hello and welcome to AM Banking & Finances.");

        int acctNumber;
        double acctBalance;
        int menuChoice = 4;
        double withdrawFee = 0.5;
        boolean menuLoop = true;
        boolean acctNumberSet = false;
        boolean accountExists = false;
        int accountSelection;
        int accountIndex = 0;

        do {
            // display the menu, and get a user choice
            menu.displayMenu();
            menuChoice = menu.userInt();

            //if existing account, ask for the account number
            if (!acctNumberSet && menuChoice < 4){
                System.out.println("Please select your account number:");
                accountSelection = menu.userInt();
                System.out.println(accountSelection);

                // finds the account in the arraylist
                for(int i = 0; i < accountHolder.size(); i++){
                    System.out.println(accountHolder.get(i).getAccount() + " -- " + accountSelection);
                    // once it finds the correct account, saved the index for it
                    if (accountHolder.get(i).getAccount() == accountSelection && !accountExists) {
                        accountIndex = i;
                        accountExists = true;
                    }
                }
                if (!accountExists){
                    System.out.println("You have input an account that does not exist. Please create a new account");
                    menuChoice = 4;
                }
                acctNumberSet = true;
            }

            switch (menuChoice){
                // account balance
                case 1:
                    System.out.println("Your current balance is ");
                    System.out.println(accountHolder.get(accountIndex).getBalance());
                    break;
                // deposit
                case 2:
                    System.out.println("How much would you like to deposit?");
                    double depositValue = menu.userDouble();
                    accountHolder.get(accountIndex).deposit(depositValue);
                    System.out.println("You have successfully deposited " + depositValue + ", your balance is now " + accountHolder.get(accountIndex).getBalance());
                    break;
                // withdraw
                case 3:
                    System.out.println("How much would you like to withdraw? There is a 0.5 withdraw fee.");
                    double withdrawValue = menu.userDouble();
                    accountHolder.get(accountIndex).withdraw(withdrawValue, withdrawFee);
                    System.out.println("You have successfully withdrew " + withdrawValue + ", your balance is now " + accountHolder.get(accountIndex).getBalance());
                    break;
                // create a new account
                case 4:
                    System.out.println("When creating an account, you will be assigned an account number and add an owner and interest rate.");
                    System.out.println("Please select an account number.");
                    acctNumber = menu.userInt();
                    System.out.println("Please choose your initial deposit.");
                    acctBalance = menu.userDouble();
                    System.out.println("Now, please input the name of the owner of the account: ");
                    String owner = menu.userString();
                    System.out.println("And finally choose an interest between 0 and 1: ");
                    double interestRate = menu.userDouble();
                    // creates the account and adds it to the arraylist
                    Account acct = new Account(owner, acctNumber, acctBalance, interestRate);
                    accountHolder.add(acct);
                    System.out.println("You have successfully created a new account, with a current balance of " + acct.getBalance() + ".");
                    // set acctberSet flag to false to make the user reenter account number before managing the account
                    acctNumberSet = false;
                    break;
                // end the program
                case 5:
                    menuLoop = false;
                    break;
                default:
                    System.out.println("You have selected an invalid option, please try again.");
                    break;

            }
        } while (menuLoop);
    }
}
