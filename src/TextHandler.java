import java.util.Scanner;

public class TextHandler {

    public void displayMenu (){
        System.out.println("Choose one of the following options:");
        System.out.println("1: Show your accounts balance");
        System.out.println("2: Deposit to your account balance");
        System.out.println("3: Withdraw from your account balance");
        System.out.println("4: Create a new account");
        System.out.println("5: End the program");
    }

    public int userInt(){
        Scanner reader = new Scanner(System.in);
        System.out.println("Your value: ");
        return reader.nextInt();
    }

    public double userDouble(){
        Scanner reader = new Scanner(System.in);
        System.out.println("Your value: ");
        return reader.nextDouble();
    }

    public String userString(){
        Scanner reader = new Scanner(System.in);
        System.out.println("Your value: ");
        return reader.nextLine();
    }

}
