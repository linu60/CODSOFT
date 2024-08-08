import java.util.Scanner;

public class CurrencyConverter {
    private static final double USD_TO_INR = 79.37;
    private static final double USD_TO_EUR = 0.98;
    private static final double INR_TO_USD = 0.013;
    private static final double INR_TO_EUR = 0.012;
    private static final double EUR_TO_INR = 80.85;
    private static final double EUR_TO_USD = 1.02;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            displayMenu();

            System.out.print("Select the currency you have (1-3): ");
            int baseChoice = sc.nextInt();

            System.out.print("Select the currency you want (1-3): ");
            int targetChoice = sc.nextInt();

            System.out.print("Enter the amount you want to convert: ");
            double amount = sc.nextDouble();

            double convertedAmount = convertCurrency(baseChoice, targetChoice, amount);
            if (convertedAmount != -1) {
                displayResult(baseChoice, targetChoice, amount, convertedAmount);
            } else {
                System.out.println("Invalid currency selection. Please try again.");
            }

            System.out.print("Would you like to make another conversion? (yes/no): ");
            sc.nextLine();  // Consume the newline
            String continueChoice = sc.nextLine();
            if (!continueChoice.equalsIgnoreCase("yes")) {
                System.out.println("Thank you for using the Currency Converter. Goodbye!");
                break;
            }
        }
        sc.close();
    }

    private static void displayMenu() {
        System.out.println("Currency Options:");
        System.out.println("1. Indian Rupee (INR)");
        System.out.println("2. US Dollar (USD)");
        System.out.println("3. Euro (EUR)");
        System.out.println();
    }

    private static double convertCurrency(int baseChoice, int targetChoice, double amount) {
        if (baseChoice == targetChoice) {
            return amount;  // No conversion needed
        }

        switch (baseChoice) {
            case 1:  // INR
                return convertFromINR(targetChoice, amount);
            case 2:  // USD
                return convertFromUSD(targetChoice, amount);
            case 3:  // EUR
                return convertFromEUR(targetChoice, amount);
            default:
                return -1;
        }
    }

    private static double convertFromINR(int targetChoice, double amount) {
        switch (targetChoice) {
            case 2:
                return amount * INR_TO_USD;  // INR to USD
            case 3:
                return amount * INR_TO_EUR;  // INR to EUR
            default:
                return -1;
        }
    }

    private static double convertFromUSD(int targetChoice, double amount) {
        switch (targetChoice) {
            case 1:
                return amount * USD_TO_INR;  // USD to INR
            case 3:
                return amount * USD_TO_EUR;  // USD to EUR
            default:
                return -1;
        }
    }

    private static double convertFromEUR(int targetChoice, double amount) {
        switch (targetChoice) {
            case 1:
                return amount * EUR_TO_INR;  // EUR to INR
            case 2:
                return amount * EUR_TO_USD;  // EUR to USD
            default:
                return -1;
        }
    }

    private static void displayResult(int baseChoice, int targetChoice, double amount, double convertedAmount) {
        String baseCurrency = getCurrencyName(baseChoice);
        String targetCurrency = getCurrencyName(targetChoice);

        System.out.printf("%.2f %s is equivalent to %.2f %s.%n", amount, baseCurrency, convertedAmount, targetCurrency);
    }

    private static String getCurrencyName(int choice) {
        switch (choice) {
            case 1:
                return "INR";
            case 2:
                return "USD";
            case 3:
                return "EUR";
            default:
                return "Unknown Currency";
        }
    }
}
