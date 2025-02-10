import com.sun.security.jgss.GSSUtil;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {
    static Scanner scanner = new Scanner(System.in);
    static void printHeader(String label, String symbol, int num){
        int sideSpace = (num - label.length()) / 2;
        System.out.println(symbol.repeat(sideSpace) + label + symbol.repeat(sideSpace));
    }
    static void printMainMenu(){
        System.out.println();
        printHeader("  Online Banking System  ", "=", 80);
        System.out.println("1. Create Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Transfer Money");
        System.out.println("5. Display Account Information");
        System.out.println("6. Delete Account");
        System.out.println("7. Exit");
        System.out.println("=".repeat(80));
    }
    static void printSubMenu1(String label){
        System.out.println();
        printHeader(label, "=", 80);
        System.out.println("1. Checking Account");
        System.out.println("2. Saving Account");
        System.out.println("3. Back");
        System.out.println("=".repeat(60));
    }
    static void printMessage(String message, int flag){
        if (flag == 1)
            System.out.println( "✅ " + Color.GREEN.getCode() + message + Color.RESET.getCode());
        else if (flag == 0)
            System.out.println( "❌ " + Color.RED.getCode() + message + Color.RESET.getCode());
        else if (flag == 2)
            System.out.println( "⚠️ " + Color.YELLOW.getCode() + message + Color.RESET.getCode());
    }
    static int inputChoice(String label, int totalChoice){
        String input;
        String message = "Please choose from 1 to " + totalChoice + "!";
        boolean validatedChoice = false;
        String regex = "^[1-" + totalChoice + "]$";
        do{
            System.out.print("=> " + label);
            input = scanner.nextLine();
            validatedChoice = Pattern.matches(regex, input);
            if(!validatedChoice)
                printMessage(message, 0);
        } while(!validatedChoice);
        return Integer.parseInt(input);
    }
    static char inputChar(String label){
        String input;
        String message = "Please enter only y/Y or n/N!!";
        boolean validatedChoice;
        String regex = "^[yYnN]$";
        do{
            System.out.print(label);
            input = scanner.nextLine();
            validatedChoice = Pattern.matches(regex, input);
            if(!validatedChoice)
                printMessage(message, 0);
        } while(!validatedChoice);
        return input.toLowerCase().charAt(0);
    }
    static String inputString(String label, String keyword){
        String input;
        String message = "Please enter a valid " + keyword + " using A-Z or a-z!";
        boolean validatedChoice = false;
        String regex = "^[A-Za-z ]+$";
        do{
            System.out.print(label);
            input = scanner.nextLine();
            validatedChoice = Pattern.matches(regex, input);
            if(!validatedChoice)
                printMessage(message, 0);
        } while(!validatedChoice);
        return input;
    }
    static String inputGender(String label){
        String input;
        String message = "Accepts male or female only!!";
        boolean validatedChoice;
        String regex = "^(?i)(male|female)$";
        do{
            System.out.print(label);
            input = scanner.nextLine();
            validatedChoice = Pattern.matches(regex, input);
            if(!validatedChoice)
                printMessage(message, 0);
        } while(!validatedChoice);
        if(input.toLowerCase().equals("female"))
            input = "Female";
        else if (input.toLowerCase().equals("male"))
            input = "Male";
        return input;
    }
    static String inputPhoneNumber(String label){
        String input;
        String message = "Accepts phone number that has 9 to 10 digits!!";
        boolean validatedChoice;
        String regex = "^0\\d{8,9}$";
        do{
            System.out.print(label);
            input = scanner.nextLine();
            validatedChoice = Pattern.matches(regex, input);
            if(!validatedChoice)
                printMessage(message, 0);
        } while(!validatedChoice);
        return input;
    }
    static boolean isValidDate(int day, int month, int year) {
        // Check if the year is in the valid range
        if (year < 1900 || year > 2099) return false;

        // Check if month is valid
        if (month < 1 || month > 12) return false;

        // Determine the max days in the given month
        int maxDays;
        switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> maxDays = 31;  // Months with 31 days
            case 4, 6, 9, 11 -> maxDays = 30;  // Months with 30 days
            case 2 -> {
                // Leap year check
                boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
                maxDays = isLeapYear ? 29 : 28;
            }
            default -> {
                return false;  // Should never happen since month range is checked
            }
        }

        // Validate the day
        return day >= 1 && day <= maxDays;
    }
    static String inputBirthDate(String label){
        String input;
        String message = "Please enter a valid date of birth!";
        boolean validatedDay = false;
        String regex = "^(0[1-9]|[1-2][0-9]|3[0-1])-(0[1-9]|1[0-2])-(1[0-9]{3}|2[0-9]{3})$";
        int age;
        int year = 0;
        do {
            System.out.print(label);
            input = scanner.nextLine();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            if (matcher.find()) {
                int day = Integer.parseInt(matcher.group(1));
                int month = Integer.parseInt(matcher.group(2));
                year = Integer.parseInt(matcher.group(3));
                validatedDay = isValidDate(day, month, year);
            }
            if (!validatedDay) {
                printMessage(message, 0);
            }else if(validatedDay){
                age = 2025 - year;
                if(age > 150 || age < 16){
                    printMessage("Age must be between 16 and 150!", 0);
                    validatedDay = false;
                }
            }
        }while(!validatedDay);
        return input;
    }
    static double inputAmount(String label, String keyword){
        String input;
        String message = "Amount can not be negative!!";
        boolean validatedAmount = false;
        String regex = "^\\d+(\\.\\d{1,2})?$";
        do{
            System.out.print("=> " + label);
            input = scanner.nextLine();
            validatedAmount = Pattern.matches(regex, input);
            if(!validatedAmount)
                printMessage(message, 0);
            else{
                if(Double.parseDouble(input) == 0.0) {
                    printMessage(keyword + " amount can not be zero!!", 0);
                    validatedAmount = false;
                }
            }
        } while(!validatedAmount);
        return Double.parseDouble(input);
    }
    static Account createAccount(Account account, String keyword){
        boolean flag = false;
        String successMessage = "Your " + keyword + "s account has been created successfully!!";
        String failMessage = "Your " + keyword + "s account has not been created!!";
        printHeader(" Account Information ", "=", 80);
        String username = inputString("Enter username: ", "username");
        String birthdate = inputBirthDate("Enter date of birth (dd-mm-yyyy): ");
        String gender = inputGender("Enter gender: ");
        String phoneNumber = inputPhoneNumber("Enter phone number: ");
        if(keyword == "checking") {
            account = new CheckingAccount(new User(username, birthdate, gender, phoneNumber), 0);
            flag = true;
        }
        else if(keyword == "saving") {
            account = new SavingAccount(new User(username, birthdate, gender, phoneNumber), 0);
            flag = true;
        }
        if(flag) {
            System.out.println("=".repeat(60));
            printMessage(successMessage, 1);
        } else {
            System.out.println("=".repeat(60));
            printMessage(failMessage, 0);
        }

        return account;
    }
}