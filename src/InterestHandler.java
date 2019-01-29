import java.util.HashMap;

public class InterestHandler extends Thread {

    private HashMap<Integer, Account> accountHashMap;

    InterestHandler(HashMap<Integer, Account> accountHashMap) {
        this.accountHashMap = accountHashMap;
    }

    public void run() {
        System.out.println("Started thread.");
        while (true) {
            try {
                // Add interest to all values in the HashMap
                for (Account i : accountHashMap.values()) {
                    i.addInterest();
                }
                // Sleep for 10 seconds after adding interest
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                System.out.println("Exception: " + ex.getMessage());
                break;
            }
        }
    }
}
