package API;

import java.util.ArrayList;

public class BankAPI {

    private ArrayList<Transaction> transactionArrayList = new ArrayList<>();
    private ArrayList<Customer> customersArrayList = new ArrayList<>();
    private ArrayList<Account>  accountArrayList = new ArrayList<>();

    private BankAPI() {

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
                    return accountArrayList.add(new Account(new Statement(), customer));
                }
            }
        }
        return false;
    }

}
