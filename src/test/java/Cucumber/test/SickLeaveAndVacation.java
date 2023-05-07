package Cucumber.test;

import app.SoftwareApp;
import com.sun.source.tree.AssertTree;
import domain.ActivityTimeSheet;
import domain.Project;
import domain.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SickLeaveAndVacation {
    @Given("the user with the id {string} is not already on vacation")
    public void theUserWithTheIdIsNotAlreadyOnVacation(String string) {
        //Check if the user as put hours in the vacation activity
        assertEquals(SoftwareApp.getProject("23001").getActivity("23001A").getUsedTime(),0);
    }

    @Given("the user with the id {string} registers vacation with start date {string} and end date {string}")
    public void theUserWithTheIdRegistersVacationWithStartDateAndEndDate(String string, String string2, String string3) {
        //Loop through the dates and add hours to the vacation activity
        LocalDate startDate = LocalDate.parse(string2);
        LocalDate endDate = LocalDate.parse(string3);
        while (startDate.isBefore(endDate)) {
            SoftwareApp.getProject("23001").getActivity("23001A").logHours(SoftwareApp.getUserFromID("kasp"), 8, startDate);
            startDate = startDate.plusDays(1);
        }
        //Asset that the hours are added
        assertEquals(SoftwareApp.getProject("23001").getActivity("23001A").getUsedTime(), 16);
    }

    @Then("the user with the id {string} is on vacation from {string} to {string}")
    public void theUserWithTheIdIsOnVacationFromTo(String string, String string2, String string3) {

    }

    @Given("the user with the id {string} is not already sick")
    public void theUserWithTheIdIsNotAlreadySick(String string) {

    }
    @Given("the user with the id {string} registers sick leave from {string} to {string}")
    public void theUserWithTheIdRegistersSickLeaveFromTo(String string, String string2, String string3) {

    }
    @Then("the user with the id {string} is sick from {string} to {string}")
    public void theUserWithTheIdIsSickFromTo(String string, String string2, String string3) {


    }
}