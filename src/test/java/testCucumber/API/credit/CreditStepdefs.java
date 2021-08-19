package testCucumber.API.credit;

import API.APIBankFacade;
import API.Amount;
import API.Customer;
import io.cucumber.java8.Fr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditStepdefs  implements Fr {

    private Customer customer;
    private static final double DELTA = 1e-15;
    APIBankFacade apiBankFacade = new APIBankFacade();

    public CreditStepdefs() {

        Etantdonné("un client {string}, {string} qui vient de créer son compte",
                (String firstName, String lastName) -> {
                    assertTrue(apiBankFacade.addCustomer(firstName,lastName));
                    assertTrue(apiBankFacade.createAccount(firstName,lastName));
                    customer = new Customer(firstName,lastName);
                });

        Quand("paul vérifie le solde de son compte, il est de {string} euros",
                (String amount) -> {
                    String result = "Balance : "+Double.parseDouble(amount);
                    assertEquals(result, apiBankFacade.getBalanceOfAccountByCustomer(customer.getFirstName(), customer.getLastName()));
                });

        Quand("paul crédite son compte le {string} de {string} euros",
                (String date,String amount) -> {
                    apiBankFacade.deposit(customer.getFirstName(), customer.getLastName(), Amount.amountWith(Integer.parseInt(amount)) , formatDate(date) );
                });

        Et("que la banque procède à l'opération",
                () -> {

                });

        Alors("son solde à la banque est maintenant de {string} euros",
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
