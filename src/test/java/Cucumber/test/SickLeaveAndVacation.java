package Cucumber.test;

import app.SoftwareApp;
import domain.User;
import domain.UserAlreadyExistsException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.util.Date;


import static org.junit.Assert.*;

public class SickLeaveAndVacation {

    @Given("a user has the id {string}")
    public void aUserHasTheId(String arg0) throws UserAlreadyExistsException {
        User.createUser("Kasper", arg0);
        assertEquals(SoftwareApp.getUserFromID(arg0).getUserId(), arg0);
    }

    @And("the user with the id {string} is logged in")
    public void theUserWithTheIdIsLoggedIn(String arg0) {
        User user = SoftwareApp.getUserFromID(arg0);
        SoftwareApp.CurrentUser = user.getUserId();
        assertEquals(SoftwareApp.CurrentUser, arg0);
    }


    @And("the user with the id {string} is not already on vacation")
    public void theUserWithTheIdIsNotAlreadyOnVacation(String arg0) {
        //Get today's date
       Date today = new Date();
       assertFalse(SoftwareApp.getUserFromID(arg0).isOnVacation(today));
    }

    @And("the user with the id {string} registers vacation with start date {string} and end date {string}")
    public void theUserWithTheIdRegistersVacationWithStartDateAndEndDate(String arg0, String arg1, String arg2) {
        System.out.println(SoftwareApp.getUserFromID(arg0).getUserId());
        SoftwareApp.getUserFromID(arg0).setVacation(arg1, arg2);
        System.out.println(SoftwareApp.getUserFromID(arg0).getUnAvailableDates());
    }

    @Then("the user with the id {string} is on vacation from {string} to {string}")
    public void theUserWithTheIdIsOnVacationFromTo(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the users availability should be updated")
    public void theUsersAvailabilityShouldBeUpdated() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}





