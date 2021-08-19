package API;

public class APIBankFacade {

    public APIBankFacade () { }

    public boolean AddTransaction(Transaction transaction){
        return BankAPI.getInstance().addNewTransaction(transaction);
    }

    public boolean AddCustomer(String firstName, String lastName){
        return BankAPI.getInstance().addNewCustomer(firstName,lastName);
    }
}
