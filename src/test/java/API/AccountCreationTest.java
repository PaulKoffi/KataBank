package API;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class AccountCreationTest {

    APIBankFacade apiBankFacade = new APIBankFacade();

    @Before
    public void initialize() {
    }


    @Test
    public void createNewAccount() {
        Customer c1 = new Customer("paul","koffi");
        assertTrue(apiBankFacade.addCustomer(c1.getFirstName(), c1.getLastName()));
        assertFalse(apiBankFacade.addCustomer(c1.getFirstName(), c1.getLastName()));
    }

}
