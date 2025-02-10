public interface Account {
    void deposit (double amount, boolean transfer);
    int withdraw (double amount);
    int transfer (double amount, Account targetAccount, boolean delete);
    void displayAccountInfo ();
    double getBalance();
}
