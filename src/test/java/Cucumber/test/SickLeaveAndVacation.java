package Cucumber.test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class SickLeaveAndVacation {
    @Given("the user with the id {string} is not already on vacation")
    public void theUserWithTheIdIsNotAlreadyOnVacation(String string) {

        throw new io.cucumber.java.PendingException();
    }

    @Given("the user with the id {string} registers vacation with start date {string} and end date {string}")
    public void theUserWithTheIdRegistersVacationWithStartDateAndEndDate(String string, String string2, String string3) {
        throw new io.cucumber.java.PendingException();
    }


    @Then("the user with the id {string} is on vacation from {string} to {string}")
    public void theUserWithTheIdIsOnVacationFromTo(String string, String string2, String string3) {
        throw new io.cucumber.java.PendingException();

    }
    @Given("the user with the id {string} is not already sick")
    public void theUserWithTheIdIsNotAlreadySick(String string) {
        throw new io.cucumber.java.PendingException();

    }
    @Given("the user with the id {string} registers sick leave from {string} to {string}")
    public void theUserWithTheIdRegistersSickLeaveFromTo(String string, String string2, String string3) {
        throw new io.cucumber.java.PendingException();

    }

    @Then("the user with the id {string} is sick from {string} to {string}")
    public void theUserWithTheIdIsSickFromTo(String string, String string2, String string3) {
        throw new io.cucumber.java.PendingException();

    }
}