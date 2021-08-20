package API;
import java.util.Date;

import static API.Amount.amountWith;

public class Account
{
    private Statement statement;
    private Customer customer;
    private Amount balance = amountWith(0);
    APIBankFacade apiBankFacade = new APIBankFacade();

    public Account(Statement statement, Customer customer) {
        this.statement = statement;
        this.customer = customer;
    }

    public void deposit(Amount value, Date date) {
        addTransaction(value, date,customer, TransactionType.Deposit);
    }

    public void withdrawal(Amount value, Date date) {
        addTransaction(value.negative(), date,customer, TransactionType.Withdrawal);
    }

    public void displayStatement() {
        statement.printTo();
    }

    private void addTransaction(Amount value, Date date, Customer customer, TransactionType transactionType) {
        Transaction transaction = new Transaction(value, date, customer, transactionType);
        Amount balanceAfterTransaction = transaction.getBalance(balance);
        balance = balanceAfterTransaction;
        apiBankFacade.addTransaction(transaction);
        statement.addLine(transaction, balanceAfterTransaction);
    }

    public Amount getBalance() {
        return balance;
    }

    public Statement getStatement() {
        return statement;
    }
}
