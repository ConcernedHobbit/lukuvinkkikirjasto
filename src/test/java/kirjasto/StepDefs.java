package kirjasto;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.Assert.*;

public class StepDefs {
    VideoHint videoHint;

    @Given("Hint is initialized")
    public void hintIsInitialized() {
        videoHint = new VideoHint("header", HintType.VIDEO, "link", "comment");
    }

    @When("it is given header {word}")
    public void itIsGivenHeader(String str) {
        videoHint.setHeader(str);
    }

    @Then("the header should be {word}")
    public void theHeaderShouldBe(String str) {
        assertEquals(str, videoHint.getHeader());
    }

}