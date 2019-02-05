class NumberGenerator {
    private static int accountNumber = 1;

    void setAccountNumber(int accountNumber){
        NumberGenerator.accountNumber = accountNumber;
    }
    int getUniqueNumber(){
        return ++accountNumber;
    }
}
