import java.io.FileOutputStream;
import java.util.Scanner;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main {
    private static Bank bank = new Bank();
    static Scanner scanner = new Scanner(System.in);
    private static void displayMenu() {
        System.out.println("\n========================================");
        System.out.println("              MAIN MENU");
        System.out.println("========================================");
        System.out.println("  1. Display all accounts");
        System.out.println("  2. create Account ");
        System.out.println("  3. Check balance");
        System.out.println("  4. Deposit money");
        System.out.println("  5. withdraw money");
        System.out.println("  6. Delete account");
        System.out.println("  7. Create a savings account");
        System.out.println("  8. Export accounts to Excel");
        System.out.println("  9. Transfer money");
        System.out.println("  0. Exit");
        System.out.println("========================================");
    }
    static private void controller(int choice) {
        switch (choice) {
            case 1:
                Bank.displayAllAccounts();
                break;
            case 2:
                createClientWithAccount();
                break;
            case 3:
                checkBalance();
                break;
            case 4:
                getAmounttodeposit();
                break;
            case 5:
                getAmounttowithdraw();
                break;
            case 6:
                deleteAccount();
                break;
            case 7:
                exportToExcel();
                break;
            case 8:
                TransferMoney();
                break;
            case 0:
                System.out.println("Exit the program");
                System.exit(0);
                break;
        }
    }
    private static void createClientWithAccount() {

        try {
         addClient();
            int accountType = getAccounttyp();
            if (accountType == 1) {
                createNormalAccout();
            }
            else if (accountType == 2) {
                createSavingsAccount();
            }
            else {
                System.out.println("Invalid account type!");
                return;
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input format!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void addClient() {
        System.out.println("=== ADD NEW CUSTOMER ===");

        try {
            System.out.print("Enter Client ID: ");
            int clientId = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter Client Name: ");
            String clientName = scanner.nextLine();

            System.out.print("Enter Customer Number: ");
            String customerNumber = scanner.nextLine();


            Client newClient = new Client(clientId, clientName, customerNumber);
            Bank.addClient(newClient);

            if (bank.findClientByNumber(customerNumber) == null) {
                System.out.println("Failed to create client. Account creation cancelled.");
                return;
            }

            System.out.println("Client created successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid ID format.");
        }
    }
    public static int getAccounttyp(){
        System.out.println("\n--- ACCOUNT INFORMATION ---");
        System.out.println("Select Account Type:");
        System.out.println("  1. Regular Account");
        System.out.println("  2. Savings Account");
        System.out.print("Your choice: ");

        int accountType = Integer.parseInt(scanner.nextLine());
        return accountType;
    }
    public static void createNormalAccout() {
        String accountType = "Normal";

        System.out.println("Enter Customer Number");
        String customerNumber = scanner.nextLine();

        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();

        System.out.print("Enter Initial Balance: ");
        double initialBalance = Double.parseDouble(scanner.nextLine());

        bank.createAccount(accountNumber, initialBalance, customerNumber,accountType);
    }
    public static void checkBalance() {
        System.out.println("\t=== CHECK BALANCE ===");
        String accountNumber = getAccountNumber("Enter account number: ");
        bank.checkBalance(accountNumber);
    }
    public static String getAccountNumber(String message) {
        System.out.print(message);
        String accountNumber = scanner.nextLine();
        return accountNumber;
    }
    public static void getAmounttodeposit() {
        System.out.println("=== DEPOSIT MONEY ===");
        String accountNumber = getAccountNumber("Enter account number: ");
        System.out.println("Enter amount to be deposited: ");
        double amount = scanner.nextDouble();
        bank.deposit(accountNumber, amount);
    }
    public static void getAmounttowithdraw() {
        System.out.println("=== WITHDRAW MONEY ===");
        String accountNumber = getAccountNumber("Enter account number: ");
        System.out.println("Enter amount to be withdrawn: ");
        double amount = scanner.nextDouble();
        bank.withdraw(accountNumber, amount);
    }
    public static void deleteAccount() {
        System.out.println("=== DELETE ACCOUNT ===");
        String accountNumber = getAccountNumber("Enter account number: ");
        bank.deleteAccount(accountNumber);
    }
    private static void createSavingsAccount() {
        System.out.println("=== CREATE SAVINGS ACCOUNT ===");
        String accountType = "Savings";

        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        try {
            System.out.print("Enter initial balance: ");
            double initialBalance = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter customer number: ");
            String customerNumber = scanner.nextLine();

            System.out.print("Enter interest rate (%): ");
            double interestRate = Double.parseDouble(scanner.nextLine());

            bank.createSavingsAccount(accountNumber, initialBalance, customerNumber, interestRate,accountType);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format.");
        }
    }
    private static void exportToExcel() {
        System.out.println("=== EXPORT TO EXCEL ===");

        if (bank.getAccounts().isEmpty()) {
            System.out.println("No accounts to export.");
            return;
        }

        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Bank Accounts");

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Account Number", "Customer Name", "Account Type", "Balance"};

            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Fill data rows
            int rowNum = 1;
            for (Account account : bank.getAccounts()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(account.getAccountNumber());
                row.createCell(1).setCellValue(account.getClient().getName());
                row.createCell(2).setCellValue(account.getType());
                row.createCell(3).setCellValue(account.getBalance());
            }

            // Write to file
            String filename = "bank_accounts.xlsx";
            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();

            System.out.println("Accounts exported successfully to: " + filename);
        } catch (Exception e) {
            System.out.println("Error exporting to Excel: " + e.getMessage());
        }
    }
    static public int getChoicenumber(){
        int number= 0;
       System.out.println("Enter your Choice");
        number = Integer.parseInt(scanner.nextLine());
       while(number < 0 || number > 8){
           System.out.println("Enter a number between 0 and 8");
       }
       return number;
    }
    public static void TransferMoney() {
        System.out.println("=== TRANSFER MONEY ===");
        String From = getAccountNumber("Enter sender Account Number: ");
        System.out.println("Enter amount to be transferred: ");
        double Amount = Double.parseDouble(scanner.nextLine());
        bank.withdraw(From,Amount);
        String To = getAccountNumber("Enter sender Account Number: ");
        bank.deposit(To,Amount);

    }

    public static void main(String[] args) {
        char tryagain = 'y';
        do{
            displayMenu();
            int choice = getChoicenumber();
            controller(choice);
            System.out.println("do you want to continue");
            tryagain = scanner.next().charAt(0);
            scanner.nextLine();
    }while(tryagain == 'y' ||  tryagain == 'Y');

}
}