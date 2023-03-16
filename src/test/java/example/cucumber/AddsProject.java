package example.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddsProject {
    @Given("that the user with the id {string} is logged in")
    public void thatTheUserWithTheIdIsLoggedIn(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Given("there is a project with and the year is {string} and the name of the project is {string}")
    public void thereIsAProjectWithAndTheYearIsAndTheNameOfTheProjectIs(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("the project is added")
    public void theProjectIsAdded() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("there is a project named {string} with an id")
    public void thereIsAProjectNamedWithAnId(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
