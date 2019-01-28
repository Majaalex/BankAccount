//********************************************************************
//  Account.java       Author: Lewis/Loftus
//
//  Represents a bank account with basic services such as deposit
//  and withdraw.
//********************************************************************

import java.text.NumberFormat;

public abstract class Account
{
   //private final double RATE = 0.035; interest rate of 3.5%

   private long acctNumber;
   private double balance;
   private String name;
   private int accountType;
   private double rate;

   //-----------------------------------------------------------------
   //  Sets up the account by defining its owner, account number,
   //  and initial balance.
   //-----------------------------------------------------------------
   public Account (String owner, long account)
   {
      setOwner(owner);
      setAccountNum(account);
   }


   //-----------------------------------------------------------------
   // SET functions
   //-----------------------------------------------------------------
   public void setOwner(String owner){
      this.name = owner;
   }
   public void setBalance(double initial){
      this.balance = initial;
   }
   public void setAccountNum(long account){
      this.acctNumber = account;
   }
   public void setAccountType(int accountType){
      this.accountType = accountType;
   }


   //-----------------------------------------------------------------
   // GET functions
   //-----------------------------------------------------------------
   public long getAccountNum(){
      return acctNumber;
   }
   public int getAccountType(){
      return accountType;
   }
   public String getOwner(){
       return name;
   }

   //-----------------------------------------------------------------
   //  Returns the current balance of the account.
   //-----------------------------------------------------------------
   public double getBalance ()
   {
      return balance;
   }

   //-----------------------------------------------------------------
   //  Deposits the specified amount into the account. Returns the
   //  new balance.
   //-----------------------------------------------------------------
   public double deposit (double amount)
   {
       balance = balance + amount;
       System.out.println("You have deposited " + amount + " into your account.");
       System.out.println("You now have a balance of " + balance);
      return balance;
   }

   //-----------------------------------------------------------------
   //  Withdraws the specified amount from the account and applies
   //  the fee. Returns the new balance.
   //-----------------------------------------------------------------
   public double withdraw (double amount, double fee)
   {
      balance = balance - amount - fee;
      System.out.println("You have withdrawn " + amount + " from your account.");
       System.out.println("You now have a balance of " + balance);
      return balance;
   }

   //-----------------------------------------------------------------
   //  Adds interest to the account and returns the new balance.
   //-----------------------------------------------------------------
   public abstract void addInterest ();



   //-----------------------------------------------------------------
   //  Returns a one-line description of the account as a string.
   //-----------------------------------------------------------------
   public String toString ()
   {
      NumberFormat fmt = NumberFormat.getCurrencyInstance();

      return (acctNumber + "\t" + name + "\t" + fmt.format(balance) + "\t" + accountType);
   }
}
