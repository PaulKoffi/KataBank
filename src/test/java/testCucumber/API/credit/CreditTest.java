package testCucumber.API.credit;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;

@RunWith(value = Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = "src/test/resources/features/API/credit")

public class CreditTest {

}
