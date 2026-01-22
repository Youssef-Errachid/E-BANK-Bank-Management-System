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
        System.out.println("  2. Create a bank account");
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
                getAccountinfo();
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
                createSavingsAccount();
                break;
            case 8:
                exportToExcel();
                break;
            case 9:
                TransferMoney();
                break;
            case 0:
                System.out.println("Exit the program");
                System.exit(0);
                break;
        }
    }

    public static void getAccountinfo() {

            System.out.println("=== CREATE BANK ACCOUNT ===");

            System.out.println("Enter Customer ID:");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter Customer name:");
            String name = scanner.nextLine();

            System.out.println("Enter Customer number: ");
            String customerNumber = scanner.nextLine();

            Client client = new Client(id,name,customerNumber);
            Bank.addClient(client);

            System.out.print("Enter account number: ");
            String accountNumber = scanner.nextLine();

            System.out.print("Enter initial balance: ");
            double initialBalance = Double.parseDouble(scanner.nextLine());

            bank.createAccount(accountNumber, initialBalance, customerNumber);
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

        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        try {
            System.out.print("Enter initial balance: ");
            double initialBalance = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter customer number: ");
            String customerNumber = scanner.nextLine();

            System.out.print("Enter interest rate (%): ");
            double interestRate = Double.parseDouble(scanner.nextLine());

            bank.createSavingsAccount(accountNumber, initialBalance, customerNumber, interestRate);
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
       while(number < 0 || number > 9){
           System.out.println("Enter a number between 0 and 9");
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