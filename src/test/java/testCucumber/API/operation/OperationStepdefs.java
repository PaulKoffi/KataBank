package testCucumber.API.operation;

import API.*;
import io.cucumber.java8.Fr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationStepdefs implements Fr {

    private Customer customer;
    private static final double DELTA = 1e-15;
    APIBankFacade apiBankFacade = new APIBankFacade();
    private Statement statement;
    String DATE_FORMAT = "dd/MM/yyyy";
    private SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

    public OperationStepdefs() {

        Etantdonné("le client {string}, {string} qui vient de créer son compte en banque",
                (String firstName, String lastName) -> {
                    assertTrue(apiBankFacade.addCustomer(firstName,lastName));
                    assertTrue(apiBankFacade.createAccount(firstName,lastName));
                    customer = new Customer(firstName,lastName);
                });

        Quand("il vérifie le solde du compte, il est de {string} euros",
                (String amount) -> {
                    String result = "Balance : "+Double.parseDouble(amount);
                    assertEquals(result, apiBankFacade.getBalanceOfAccountByCustomer(customer.getFirstName(), customer.getLastName()));
                });

        Quand("il crédite le compte le {string} de {string} euros",
                (String date,String amount) -> {
                    apiBankFacade.deposit(customer.getFirstName(), customer.getLastName(), Amount.amountWith(Integer.parseInt(amount)) , formatDate(date) );
                });

        Et("que la banque exécute l'opération",
                () -> {

                });

        Alors("son solde à la banque est de {string} euros et",
                (String amount) -> {
                    String result = "Balance : "+Double.parseDouble(amount);
                    assertEquals(result, apiBankFacade.getBalanceOfAccountByCustomer(customer.getFirstName(), customer.getLastName()));
                });

        Quand("il effectue ensuite un retrait de son compte le {string} de {string} euros",
                (String date,String amount) -> {
                    apiBankFacade.withdrawal(customer.getFirstName(), customer.getLastName(), Amount.amountWith(Integer.parseInt(amount)) , formatDate(date) );
                });

        Et("que la banque effectue l'opération,",
                () -> {

                });

        Alors("son solde à la banque affiche maintenant {string} euros",
                (String amount) -> {
                    String result = "Balance : "+Double.parseDouble(amount);
                    assertEquals(result, apiBankFacade.getBalanceOfAccountByCustomer(customer.getFirstName(), customer.getLastName()));
                });

        Quand("il consulte les opérations de son compte",
                () -> {
                    statement = apiBankFacade.getStatementByCustomer(customer.getFirstName(), customer.getLastName());
                });

        Alors("il y'a un crédit de {string} euros le {string}, la balance est alors de {string}",
                (String amount, String date, String balance) -> {
                    StatementLine line = statement.getStatementLines().get(0);
                    assertEquals(Double.parseDouble(amount), line.getTransaction().getAmount().getValue(), DELTA);
                    assertEquals(TransactionType.Deposit, line.getTransaction().getTransactionType());
                    assertEquals(date, sdf.format(line.getTransaction().getDate()));
                    assertEquals(Double.parseDouble(balance), line.getBalance().getValue(), DELTA);
                });

        Et("il y'a un retrait de {string} euros le {string}, la balance est alors de {string}",
                (String amount, String date, String balance) -> {
                    StatementLine line = statement.getStatementLines().get(1);
                    assertEquals(-Double.parseDouble(amount), line.getTransaction().getAmount().getValue(), DELTA);
                    assertEquals(TransactionType.Withdrawal, line.getTransaction().getTransactionType());
                    assertEquals(date, sdf.format(line.getTransaction().getDate()));
                    assertEquals(Double.parseDouble(balance), line.getBalance().getValue(), DELTA);
                });
    }

    private Date formatDate(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = formatter.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
