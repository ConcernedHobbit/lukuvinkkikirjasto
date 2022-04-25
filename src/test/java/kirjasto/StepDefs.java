package kirjasto;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.Before;

import static org.junit.Assert.*;

public class StepDefs {
    VideoHint videohint;
    BookHint bookhint;
    BlogHint bloghint;
    PodcastHint podcasthint;

    @Given("Videohint is initialized")
    public void videoHintIsInitialized() {
        videohint = new VideoHint("header", HintType.VIDEO, "link", "comment");
    }

    @Given("Bookhint is initialized")
    public void bookHintIsInitialized() {
        bookhint = new BookHint("header", HintType.BOOK, "author", "publisher", 2022);
    }

    @Given("Bloghint is initialized")
    public void blogHintIsInitialized() {
        bloghint = new BlogHint("header", HintType.BLOGPOST, "author", "publisher");
    }

    @Given("Podcasthint is initialized")
    public void podcastHintIsInitialized() {
        podcasthint = new PodcastHint("header", HintType.PODCAST, "author", "name", "description");
    }

    @When("videohint is given comment {string}")
    public void videoHintIsGivenComment(String str) {
        videohint.setComment(str);
    }

    @When("videohint is given url {string}")
    public void videoHintIsGivenUrl(String str) {
        videohint.setUrl(str);
    }

    @When("bookhint is given author {string}")
    public void bookHintIsGivenAuthor(String str) {
        bookhint.setAuthor(str);
    }

    @When("bookhint is given publisher {string}")
    public void bookHintIsGivenPublisher(String str) {
        bookhint.setPublisher(str);
    }

    @When("bookhint is given release year {int}")
    public void bookHintIsGivenReleaseYear(int year) {
        bookhint.setYear(year);
    }

    @When("bloghint is given url {string}")
    public void blogHintIsGivenUrl(String str) {
        bloghint.setUrl(str);
    } 

    @When("bloghint is given author {string}")
    public void blogHintIsGivenAuthor(String str) {
        bloghint.setAuthor(str);
    }

    @When("podcasthint is given author {string}")
    public void podcastHintIsGivenAuthor(String str) {
        podcasthint.setAuthor(str);
    }

    @When("podcasthint is given name {string}")
    public void podcastHintIsGivenName(String str) {
        podcasthint.setName(str);
    }

    @When("podcasthint is given description {string}")
    public void podcastHintIsGivenDescription(String str) {
        podcasthint.setDescription(str);
    }

    @Then("the comment should be {string}")
    public void theCommentShouldBe(String str) {
        assertEquals(str, videohint.getComment());
    }

    @Then("the videohint url should be {string}")
    public void theVideohintUrlShouldBe(String str) {
        assertEquals(str, videohint.getUrl());
    }

    @Then("the authors name should be {string}")
    public void theAuthorsNameShouldBe(String str) {
        assertEquals(str, bookhint.getAuthor());
    }

    @Then("the publisher should be {string}")
    public void thePublisherShouldBe(String str) {
        assertEquals(str, bookhint.getPublisher());
    }

    @Then("the release year should be {int}")
    public void theReleaseYearShouldBe(int year) {
        assertEquals(year, bookhint.getYear());
    }

    @Then("the bloghint url should be {string}")
    public void theBloghintUrlShouldBe(String str) {
        assertEquals(str, bloghint.getUrl());
    }

    @Then("the blogpost author should be {string}")
    public void theBlogpostAuthorShouldBe(String str) {
        assertEquals(str, bloghint.getAuthor());
    }

    @Then("author of the podcasthint should be {string}")
    public void authorOfThePodcastHintShouldBe(String str) {
        assertEquals(str, podcasthint.getAuthor());
    }

    @Then("the name of the podcasthint should be {string}")
    public void theNameOfThePodcastHintShouldBe(String str) {
        assertEquals(str, podcasthint.getName());
    }

    @Then("the podcasthint should have description {string}")
    public void thePodcastHintShouldHaveDescription(String str) {
        assertEquals(str, podcasthint.getDescription());
    }

}