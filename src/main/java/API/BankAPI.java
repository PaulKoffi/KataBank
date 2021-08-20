package API;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class BankAPI {

    private ArrayList<Transaction> transactionArrayList = new ArrayList<>();

    private ArrayList<Customer> customersArrayList = new ArrayList<>();

    // MVP : on suppose que chaque client ne peut avoir qu'un seul compte
    private HashMap<Customer, Account> accountList;

    private BankAPI() {
        accountList = new HashMap<>();
    }

    private static BankAPI INSTANCE = new BankAPI();

    public static BankAPI getInstance() {
        return INSTANCE;
    }

    public boolean addNewCustomer(String firstName, String lastName){
          if(!checkCustomer(firstName,lastName)){
              return customersArrayList.add(new Customer(firstName,lastName));
          }
          return false;
    }

    public boolean checkCustomer(String firstName, String lastName){
        for(Customer customer : customersArrayList){
            if (customer.getFirstName().equals(firstName) && customer.getLastName().equals(lastName)) {
                return true;
            }
        }
        return false;
    }

    public boolean addNewTransaction(Transaction transaction){
        return transactionArrayList.add(transaction);
    }

    public boolean createAccount(String firstName, String lastName){
        if(checkCustomer(firstName,lastName)){
            for(Customer customer : customersArrayList){
                if (customer.getFirstName().equals(firstName) && customer.getLastName().equals(lastName)) {
                    this.accountList.put(customer, new Account(new Statement(), customer));
                    return true;
                }
            }
        }
        return false;
    }

    public List<Transaction> getTransactionsByCustomer(String firstName, String lastName){
        return transactionArrayList.stream()
                .filter(line -> line.getCustomer().getFirstName().equals(firstName) && line.getCustomer().getLastName().equals(lastName) )
                .collect(Collectors.toList());
    }

    public void deposit(String firstName, String lastName,Amount value, Date date){
        for(Customer customer : customersArrayList){
            if (customer.getFirstName().equals(firstName) && customer.getLastName().equals(lastName)) {
                this.accountList.get(customer).deposit(value,date);
                break;
            }
        }
    }

    public void withdrawal(String firstName, String lastName,Amount value, Date date){
        for(Customer customer : customersArrayList){
            if (customer.getFirstName().equals(firstName) && customer.getLastName().equals(lastName)) {
                this.accountList.get(customer).withdrawal(value,date);
                break;
            }
        }
    }

    public String getBalanceOfAccountByCustomer(String firstName, String lastName){
        for(Customer customer : customersArrayList){
            if (customer.getFirstName().equals(firstName) && customer.getLastName().equals(lastName)) {
                return "Balance : "+ this.accountList.get(customer).getBalance().getValue();
            }
        }
        return "account not found !";
    }

    public Statement getStatementByCustomer(String firstName, String lastName){
        for(Customer customer : customersArrayList){
            if (customer.getFirstName().equals(firstName) && customer.getLastName().equals(lastName)) {
                return this.accountList.get(customer).getStatement();
            }
        }
        return new Statement();
    }
}
