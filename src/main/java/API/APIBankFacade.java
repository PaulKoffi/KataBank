package API;

import java.util.Date;
import java.util.List;

public class APIBankFacade {

    public APIBankFacade () { }

    public boolean addTransaction(Transaction transaction){
        return BankAPI.getInstance().addNewTransaction(transaction);
    }

    public boolean addCustomer(String firstName, String lastName){
        return BankAPI.getInstance().addNewCustomer(firstName,lastName);
    }

    public List<Transaction> getTransactionsByCustomer(String firstName, String lastName){
        return BankAPI.getInstance().getTransactionsByCustomer(firstName, lastName);
    }

    public boolean createAccount(String firstName, String lastName){
        return BankAPI.getInstance().createAccount(firstName, lastName);
    }

    public void deposit(String firstName, String lastName,Amount value, Date date){
        BankAPI.getInstance().deposit(firstName,lastName,value,date);
    }

    public void withdrawal(String firstName, String lastName,Amount value, Date date){
        BankAPI.getInstance().withdrawal(firstName,lastName,value,date);
    }

    public String getBalanceOfAccountByCustomer(String firstName, String lastName){
        return BankAPI.getInstance().getBalanceOfAccountByCustomer(firstName,lastName);
    }

    public Statement getStatementByCustomer(String firstName, String lastName){
        return BankAPI.getInstance().getStatementByCustomer(firstName,lastName);
    }
}
