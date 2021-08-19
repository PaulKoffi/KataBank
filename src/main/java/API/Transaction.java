package API;

import java.text.SimpleDateFormat;
import java.util.Date;

import static API.Amount.amountWith;

public class Transaction {

    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    private Date date;
    private Amount amount;
    private TransactionType transactionType;
    private Customer customer;

    public Transaction(Amount value, Date date, Customer customer, TransactionType transactionType) {
        this.amount = value;
        this.date = date;
        this.customer = customer;
        this.transactionType = transactionType;
    }

    public Amount getBalance(Amount current) {
        return current.plus(amount);
    }

    public void printTo(Amount currentBalance) {
        System.out.println("DATE  : "+sdf.format(date));
        if (amount.isGreaterThan(amountWith(0))) {
            System.out.println("CREDIT  : "+ amount.absoluteValue());
        } else {
            System.out.println("DEBIT  : "+amount.absoluteValue());
        }
        System.out.println("BALANCE : "+currentBalance.getValue());
        System.out.println("          #                ");
    }
}
