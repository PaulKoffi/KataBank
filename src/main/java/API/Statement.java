package API;

import java.util.ArrayList;

public class Statement {

    private ArrayList<StatementLine> statementLines = new ArrayList<>();

    public Statement(){

    }

    public void addLine(Transaction transaction, Amount currentBalance) {
        statementLines.add(new StatementLine(transaction, currentBalance));
    }

    public void printTo() {
        System.out.println("-------------------------------------------------------------------------------------");
        for (StatementLine statementLine : statementLines) {
            statementLine.printTo();
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }
}
