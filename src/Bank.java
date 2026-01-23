import java.util.ArrayList;
import java.util.List;

public class Bank  {
    private static List<Client> clients = new ArrayList<>();
    private static List<Account> accounts = new ArrayList<>();
    public Bank(){
    this.clients = new ArrayList<>();
    this.accounts = new ArrayList<>();
}
    public static void addClient(Client client) {
        for (Client c : clients) {
            if (c.getCustomerNumber() == (client.getCustomerNumber())) {
                System.out.println("Error: A client with this customer number already exists.");
                return;
            }
        }
        clients.add(client);
    }
    public static void displayAllAccounts() {
    if(accounts.isEmpty()){
        System.out.println("No accounts found");
        return;
    }

      for(Account a : accounts){
          System.out.println("Accounts info : ");
          System.out.println("Account number: " + a.getAccountNumber());
          System.out.println("Account Type: " + a.getType());
          System.out.println("Balance: " + a.getBalance());

          Client client =  a.getClient();

          System.out.println("Client info : ");
          System.out.println("Client number: " + client.getCustomerNumber());
          System.out.println("Client name: " + client.getName());
          System.out.println("Client number: " + client.getCustomerNumber());

          if(a instanceof SavingAccount){
              SavingAccount savingAccount = (SavingAccount) a;
              System.out.println("Saving account info : ");
              System.out.println("Interest rate: " + savingAccount.getInterestRate());
              System.out.println("potential Interest: "+ savingAccount.calculateInterest());

          }
      }

}
    public List<Account> getAccounts() {
            return accounts;
        }
    public boolean createAccount(String accountNumber, double initialBalance, String customerNumber, String accountType) {
        if (findAccount(accountNumber) != null) {
            System.out.println("this Account already exists");
            return false;
        }

        Client client = findClientByNumber(customerNumber);
        if (client == null) {
            System.out.println("Client with customer number: " + customerNumber + " not found");
            return false;
        }

        if (initialBalance < 0) {
            System.out.println("Initial balance can't be negative.");
            return false;
        }

        Account account = new Account(accountNumber, initialBalance, client ,accountType);
        accounts.add(account);
        System.out.println("Account created successfully for " + client.getName());
        return true;
    }
    public Client findClientByNumber(String customerNumber) {
    for (Client client : clients) {
        if (client.getCustomerNumber().equals(customerNumber)) {
            return client;
        }
    }
    return null;
}
    public Account findAccount(String accountNumber) {
    for (Account account : accounts) {
        if (account.getAccountNumber().equals(accountNumber)) {
            return account;
        }
    }
    return null;
}
    public Double checkBalance(String accountNumber) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            System.out.println("Account with customer number: " + accountNumber + " not found");
            return null;
        }
        System.out.println("Account balance is " + account.getBalance());
        return account.getBalance();
}
    public void deposit(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            System.out.println("Account with customer number: " + accountNumber + " not found");
        }
        account.setBalance(account.getBalance() + amount);
        System.out.println("Account balance is " + account.getBalance());
    }
    public void withdraw(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            System.out.println("Account number: " + accountNumber + " not found");
        }
        account.setBalance(account.getBalance() - amount);
        System.out.println("Account balance is " + account.getBalance());
    }
    public void deleteAccount(String accountNumber) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            System.out.println("Account with customer number: " + accountNumber + " not found");
        }

        if(account.balance > 0 ){
            System.out.println("you can't delete your account");
        }else {
            accounts.remove(account);
            System.out.println("Account number: " + accountNumber + " is deleted successfully");
        }

    }
    public boolean createSavingsAccount(String accountNumber, double initialBalance, String customerNumber, double interestRate, String accountType) {
            // Check if account number already exists
            if (findAccount(accountNumber) != null) {
                System.out.println("Error: An account with this number already exists.");
                return false;
            }

            // Find the client
            Client client = findClientByNumber(customerNumber);
            if (client == null) {
                System.out.println("Error: Client not found with customer number: " + customerNumber);
                return false;
            }

            // Validate initial balance and interest rate
            if (initialBalance < 0) {
                System.out.println("Error: Initial balance cannot be negative.");
                return false;
            }
            if (interestRate < 0) {
                System.out.println("Error: Interest rate cannot be negative.");
                return false;
            }

            SavingAccount account = new SavingAccount(accountNumber, initialBalance, client, interestRate,accountType);
            accounts.add(account);
            System.out.println("Savings account created successfully for " + client.getName());
            return true;
        }
}
