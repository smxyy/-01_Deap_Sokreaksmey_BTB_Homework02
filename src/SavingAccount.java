public class SavingAccount implements Account{
    private static int min = 100000000;
    private static int max = 999999999;

    //    count object creation
    static int instanceCount = 0;

    // Generate a random account number between min and max (inclusive)
    static long accountNumber;
    private final static double rate = 0.05;
    private User userSavingAccount;
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public SavingAccount(User userSavingAccount, double balance) {
        if(instanceCount >= 1)
            Helper.printMessage("Only one saving account is allowed to create!!", 0);
        else {
            this.userSavingAccount = userSavingAccount;
            this.balance = balance;
            accountNumber = (long) (Math.random() * (max - min + 1)) + min;
            instanceCount++;
        }
    }

    @Override
    public void deposit (double amount, boolean transfer){
        if(amount >= 200 && !transfer)
            setBalance(getBalance() + amount + (amount * 0.05));
        else
            setBalance(getBalance() + amount);
        if(!transfer) {
            System.out.println("\n" + " ".repeat(19) + "⬇️");
            Helper.printHeader("Saving Account", " ", 38);
            System.out.println("Received    :" + " ".repeat(15) + "$ " + amount);
            System.out.println("Total Amount:" + " ".repeat(15) + "$ " + getBalance());
            System.out.println("=".repeat(80));
            System.out.println();
            Helper.printMessage("Deposit Successfully!!", 1);
        }
    }

    @Override
    public int withdraw (double amount){
        if (amount > getBalance()) {
            Helper.printMessage("Insufficient amount to withdraw!!", 0);
            return 0;
        }
        else {
            double maxWithdraw = getBalance() * 0.8;
            if (amount > maxWithdraw) {
                Helper.printMessage("Cannot withdraw $" + amount + ". At least $" + (getBalance() - maxWithdraw) + " must remain in the account!!", 0);
                return 2;
            } else {
                setBalance(getBalance() - amount);
            }
            System.out.println("\n" + " ".repeat(19) + "⬇️" );
            Helper.printHeader("Saving Account", " ", 38);
            System.out.println("Withdraw    :" + " ".repeat(15) + "$ " + amount);
            System.out.println("Total Amount:" + " ".repeat(15) + "$ " + getBalance());
            System.out.println("=".repeat(80));
            System.out.println();
            Helper.printMessage("Withdrawal Successfully!!", 1);
            return 1;
        }
    }
    @Override
    public int transfer (double amount, Account targetAccount, boolean delete){
        if (amount > getBalance()){
            Helper.printMessage("Insufficient amount for transferring!!", 0);
            return 2;
        }else {
            if(delete){
                setBalance(getBalance() - amount);
                targetAccount.deposit(amount, true);
            }else {
                double maxTransfer = getBalance() * 0.8;
                if (amount > maxTransfer) {
                    Helper.printMessage("Cannot transfer $" + amount + ". At least $" + (getBalance() - maxTransfer) + " must remain in the account!!", 0);
                    return 2;
                } else {
                    setBalance(getBalance() - amount);
                    targetAccount.deposit(amount, true);
                }
            }
        }

        System.out.println("\n" + " ".repeat(19) + "⬇️" );
        Helper.printHeader("  Saving Account  ", "=", 38);
        System.out.println("Transferred : " + " ".repeat(15) + "$" + amount);
        System.out.println("From        :   Saving Account with ID: " + SavingAccount.accountNumber);
        System.out.println("To          : Checking Account with ID: " + CheckingAccount.accountNumber);
        System.out.println("Total Remain: " + " ".repeat(15) + "$" + getBalance());

        System.out.println("\n" + " ".repeat(19) + "⬇️" );
        Helper.printHeader("  Checking Account  ", "=", 38);
        System.out.println("Received    :" + " ".repeat(15) + "$ " + amount);
        System.out.println("Total Amount:" + " ".repeat(15) + "$ " + targetAccount.getBalance());
        System.out.println("=".repeat(80));
        System.out.println();
        Helper.printMessage("Transfer Successfully!!", 1);
        return 1;
    }
    @Override
    public void displayAccountInfo (){
        Helper.printHeader("  Savings Account  ", "=", 80);
        System.out.println("Account Type: Savings Account");
        System.out.println("Account Number: " + SavingAccount.accountNumber);
        System.out.println("User Name: " + userSavingAccount.getUsername());
        System.out.println("Date of Birth: " + userSavingAccount.getBirthDate());
        System.out.println("Gender: " + userSavingAccount.getGender());
        System.out.println("Phone Number: " + userSavingAccount.getPhoneNumber());
        System.out.println("Balance: " + getBalance() + " $");
        System.out.println("=".repeat(80));
    }
}
