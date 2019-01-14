public class TextHandler {

    public void displayMenu (){
        System.out.println("Choose one of the following options:");
        System.out.println("1: Show your accounts balance");
        System.out.println("2: Deposit to your account balance");
        System.out.println("3: Withdraw from your account balance");
        System.out.println("4: Create a new account");
        System.out.println("5: End the program");
    }

    public void userInput(){
        System.out.println("Your value: " + input.nextInt());
    }

}
