package API;

public class StatementLine {

    private Transaction transaction;
    private Amount balance;

    public StatementLine(Transaction transaction, Amount currentBalance) {
        this.transaction = transaction;
        this.balance = currentBalance;
    }

    public void printTo() {
        this.transaction.printTo(balance);
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Amount getBalance() {
        return balance;
    }
}
