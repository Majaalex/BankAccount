import java.util.HashMap;
import java.util.Random;

class NumberGenerator {

    int getUniqueNumber(HashMap<Integer, Account> accountHashMap){
        Random random = new Random();
        int maxNumber = 5000;
        int minNumber = 1;
        int uniqueNum;
        do {
            uniqueNum = random.nextInt(maxNumber) + minNumber;
        } while (accountHashMap.containsKey(uniqueNum));
        return uniqueNum;
    }
}
