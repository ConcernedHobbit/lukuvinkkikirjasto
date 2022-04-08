package kirjasto;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.Assert.*;

public class StepDefs {
    Hint hint;

    @Given("Hint is initialized")
    public void hintIsInitialized() {
        hint = new Hint("header", "link", "author", "publisher", 2022, 1);
    }

    @When("it is given header {word}")
    public void itIsGivenHeader(String str) {
        hint.setHeader(str);
    }

    @Then("the header should be {word}")
    public void theHeaderShouldBe(String str) {
        assertEquals(str, hint.getHeader());
    }

}