import java.time.Clock;
import java.util.ArrayList;

public class InterestHandler extends Thread {

    Clock c;
    private ArrayList<Account> accountHandler;

    public InterestHandler(ArrayList accounterHandler){
        c = Clock.systemDefaultZone();
        this.accountHandler = accounterHandler;
    }

    public void run() {
        System.out.println("Started thread.");
        while (true){
            try{
                for (Account accounts : accountHandler){
                    accounts.addInterest();
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
