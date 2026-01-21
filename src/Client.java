import java.util.ArrayList;
import java.util.List;

public class Client extends Person {
    private String customerNumber;
    private List<Account> accounts =  new ArrayList<Account>();
    public Client(int id,String name,String customerNumber) {
        super(id, name);
        this.customerNumber = customerNumber;
    }
    public String getCustomerNumber() {
        return customerNumber;
    }
    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
    public List<Account> getAccounts(){
        return accounts;
    }
    public void addAccount(Account account){
        accounts.add(account);
    }
    public void removeAccount(Account account){
        accounts.remove(account);
    }
}
