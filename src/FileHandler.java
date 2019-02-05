import java.io.*;
import java.util.HashMap;

class FileHandler {
    private String fileName = "HashMap.txt";
    //----------------------------------------------------
    // Gets the HashMap in a file once the program is started
    //----------------------------------------------------
    HashMap<Integer, Account> getAccounts(){
        HashMap<Integer, Account> accountHashMap = new HashMap<>();
        try {
            FileInputStream fi = new FileInputStream(new File(fileName));
            ObjectInputStream oi = new ObjectInputStream(fi);

            accountHashMap = (HashMap<Integer, Account>) oi.readObject();
            /*System.out.println("The existing accounts are: ");
            for (Account accounts : accountHashMap.values()) {
                System.out.println(accounts.toString());
            }*/

            fi.close();
            oi.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.getMessage();
        } catch (ClassNotFoundException e) {
            e.getException();
        }

        return accountHashMap;
    }

    //----------------------------------------------------
    // Store the HashMap in a file once the program is ended
    //----------------------------------------------------
    void storeAccounts(HashMap<Integer, Account> accountHashMap){
        try{
            File HashMap = new File(fileName);
            try{
                boolean newFile = HashMap.createNewFile();
            } catch (Exception e){
                System.out.println("File could not be created.");
            }

            FileOutputStream fOut = new FileOutputStream(HashMap);
            ObjectOutputStream oOut = new ObjectOutputStream(fOut);

            oOut.writeObject(accountHashMap);

            fOut.close();
            oOut.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("We have an IOException: " + e);
        }
    }
}
