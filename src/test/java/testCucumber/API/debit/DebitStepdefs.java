package testCucumber.API.debit;

import API.APIBankFacade;
import API.Amount;
import API.Customer;
import io.cucumber.java8.Fr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebitStepdefs implements Fr {

    private Customer customer;
    APIBankFacade apiBankFacade = new APIBankFacade();

    public DebitStepdefs() {

        Etantdonné("le client {string}, {string} qui vient de créer son compte",
                (String firstName, String lastName) -> {
                    assertTrue(apiBankFacade.addCustomer(firstName,lastName));
                    assertTrue(apiBankFacade.createAccount(firstName,lastName));
                    customer = new Customer(firstName,lastName);
                });

        Quand("il vérifie le solde de son compte, il est de {string} euros",
                (String amount) -> {
                    String result = "Balance : "+Double.parseDouble(amount);
                    assertEquals(result, apiBankFacade.getBalanceOfAccountByCustomer(customer.getFirstName(), customer.getLastName()));
                });

        Quand("il crédite son compte le {string} de {string} euros",
                (String date,String amount) -> {
                    apiBankFacade.deposit(customer.getFirstName(), customer.getLastName(), Amount.amountWith(Integer.parseInt(amount)) , formatDate(date) );
                });

        Et("que la banque procède ensuite à l'opération",
                () -> {

                });

        Alors("son solde à la banque est de {string} euros",
                (String amount) -> {
                    String result = "Balance : "+Double.parseDouble(amount);
                    assertEquals(result, apiBankFacade.getBalanceOfAccountByCustomer(customer.getFirstName(), customer.getLastName()));
                });

        Quand("il effectue un retrait de son compte le {string} de {string} euros",
                (String date,String amount) -> {
                    apiBankFacade.withdrawal(customer.getFirstName(), customer.getLastName(), Amount.amountWith(Integer.parseInt(amount)) , formatDate(date) );
                });

        Et("que la banque effectue l'opération",
                () -> {

                });

        Alors("son solde à la banque affiche {string} euros",
                (String amount) -> {
                    String result = "Balance : "+Double.parseDouble(amount);
                    assertEquals(result, apiBankFacade.getBalanceOfAccountByCustomer(customer.getFirstName(), customer.getLastName()));
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
