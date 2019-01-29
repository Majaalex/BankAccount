import java.time.Clock;
import java.util.ArrayList;
import java.util.HashMap;

public class InterestHandler extends Thread {

    private HashMap<Integer, Account> accountHashMap;

    public InterestHandler(HashMap accountHashMap){
        this.accountHashMap = accountHashMap;
    }

    public void run() {
        System.out.println("Started thread.");
        while (true){
            try{
                //Hashmap
                for (Account i : accountHashMap.values()){
                    i.addInterest();
                }
                Thread.sleep(10000);
            }
            catch (InterruptedException ex){
                System.out.println("Exception: " + ex.getMessage());
                break;
            }
        }
    }
}
