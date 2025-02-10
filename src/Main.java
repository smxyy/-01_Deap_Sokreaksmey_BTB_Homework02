//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int choice;
        Account savings = null;
        Account checkings = null;
        do {
            Helper.printMainMenu();
            choice = Helper.inputChoice("Choose an option (1-7) : ", 7);
            switch(choice){
                case 1 -> {
                    Helper.printSubMenu1(" Creating Account ");
                    int option = Helper.inputChoice("What type of account do you want to create? : ", 3);
                    switch (option){
                        case 1: {
                            if(CheckingAccount.instanceCount >= 1)
                                Helper.printMessage("Your checking account is already in use!!", 0);
                            else
                                checkings = Helper.createAccount(checkings, "checking");
                            break;
                        }
                        case 2: {
                            if(SavingAccount.instanceCount >= 1)
                                Helper.printMessage("Your saving account is already in use!!", 0);
                            else
                                savings = Helper.createAccount(savings, "saving");
                            break;
                        }
                        case 3: break;
                    }
                }
                case 2 -> {
                    Helper.printSubMenu1(" Deposit Money ");
                    int option = Helper.inputChoice("Choose an option: ", 3);
                    switch (option){
                        case 1: {
                            if (CheckingAccount.instanceCount < 1){
                                Helper.printMessage("You have to create an account first!!", 2);
                            } else if (CheckingAccount.instanceCount == 1) {
                                double receive = Helper.inputAmount("Enter money to deposit: ", "Deposit");
                                checkings.deposit(receive, false);
                            }
                            break;
                        }
                        case 2: {
                            if (SavingAccount.instanceCount < 1){
                                Helper.printMessage("You have to create an account first!!", 2);
                            } else if (SavingAccount.instanceCount == 1) {
                                double receive = Helper.inputAmount("Enter money to deposit: ", "Deposit");
                                savings.deposit(receive, false);
                            }
                            break;
                        }
                        case 3: break;
                    }
                }
                case 3 -> {
                    int flag = 0;
                    do {
                        Helper.printSubMenu1(" Withdraw Money ");
                        int option = Helper.inputChoice("Choose an option: ", 3);
                        switch (option) {
                            case 1: {
                                if (CheckingAccount.instanceCount < 1) {
                                    Helper.printMessage("You have to create an account first!!", 2);
                                } else if (CheckingAccount.instanceCount == 1) {
                                    double withdraw = Helper.inputAmount("Enter money to withdraw: ", "Withdraw");
                                    flag = checkings.withdraw(withdraw);
                                }
                                break;
                            }
                            case 2: {
                                char answer = ' ';
                                do {
                                    if (SavingAccount.instanceCount < 1) {
                                        Helper.printMessage("You have to create an account first!!", 2);
                                        flag = 1;
                                    } else if (SavingAccount.instanceCount == 1) {
                                        double withdraw = Helper.inputAmount("Enter money to withdraw: ", "Withdraw");
                                        flag = savings.withdraw(withdraw);
                                        if (flag == 2)
                                            answer = Helper.inputChar("Please input y to continue and n to back: ");
                                    }
                                } while (answer != 'n' && flag != 1);
                                break;
                            }
                            case 3:
                                flag = 1;
                                break;
                        }
                    }while(flag != 1);
                }
                case 4 -> {
                    int flag = 0;
                    do {
                        if (CheckingAccount.instanceCount == 1 && SavingAccount.instanceCount == 1) {
                            Helper.printSubMenu1(" Transfer Money ");
                            int option = Helper.inputChoice("Choose an option: ", 3);
                            switch (option) {
                                case 1: {
                                    char answer = ' ';
                                    do {
                                        double transfer = Helper.inputAmount("Enter money to transfer: ", "Transfer");
                                        flag = checkings.transfer(transfer, savings, false);
                                        if (flag == 2)
                                            answer = Helper.inputChar("Please input y to continue and n to back: ");
                                    } while (answer != 'n' && flag != 1);
                                    break;
                                }
                                case 2: {
                                    char answer = 'a';
                                    do {
                                        double transfer = Helper.inputAmount("Enter money to transfer: ", "Transfer");
                                        flag = savings.transfer(transfer, checkings, false);
                                        if (flag == 2)
                                            answer = Helper.inputChar("Please input y to continue and n to back: ");
                                    }while ( answer != 'n' && flag != 1);
                                    break;
                                }
                                case 3: {
                                    break;
                                }
                            }
                        }
                        else{
                            Helper.printMessage("Not enough owned account to perform the transferring!!",0);
                            flag = 1;
                        }
                    }while(flag != 1);
                }
                case 5 -> {
                    if(CheckingAccount.instanceCount == 0 && SavingAccount.instanceCount == 0)
                        Helper.printMessage("No account information to display!!", 0);
                    if(CheckingAccount.instanceCount == 1)
                        checkings.displayAccountInfo();
                    if(SavingAccount.instanceCount == 1)
                        savings.displayAccountInfo();
                }
                case 6 -> {
                    Helper.printSubMenu1("  Delete Account  ");
                    if(CheckingAccount.instanceCount == 0 || SavingAccount.instanceCount == 0) {
                        Helper.printMessage("Not enough owned account to perform deletion on one account!!", 0);
                        break;
                    }else {
                        int option = Helper.inputChoice("Choose an option: ", 3);
                        char answer;
                        switch (option) {
                            case 1:
                                answer = Helper.inputChar("Are you sure you want to delete this account: ");
                                if (answer == 'y') {
                                    int flag = checkings.transfer(checkings.getBalance(), savings, true);
                                    if (flag == 1) {
                                        checkings = null;
                                        CheckingAccount.instanceCount = 0;
                                        Helper.printMessage("Transferring all balance from Checking account to Savings Account.\nAccount is deleted successfully!!", 1);
                                    }
                                }
                                break;
                            case 2:
                                answer = Helper.inputChar("Are you sure you want to delete this account? (y/n): ");
                                if (answer == 'y') {
                                    int flag = savings.transfer(savings.getBalance(), checkings, true);
                                    if (flag == 1) {
                                        savings = null;
                                        SavingAccount.instanceCount = 0;
                                        Helper.printMessage("Transferring all balance from Savings account to Checking Account.\nAccount is deleted successfully!!", 1);
                                    }
                                }
                                break;
                            case 3:
                                break;
                        }
                    }
                }
                case 7 -> {
                    System.out.println("I wish you best of luck!! 🙏🥰🌺");
                    System.exit(0);
                }
            }
        }while(choice != 7);
    }
}