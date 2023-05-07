package Cucumber.test;

import app.SoftwareApp;
import domain.ActivityTimeSheet;
import domain.Project;
import domain.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class SickLeaveAndVacation {
    @Given("the user with the id {string} is not already on vacation")
    public void theUserWithTheIdIsNotAlreadyOnVacation(String string) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println(Project.SpecialActivityList);
        Project.SpecialActivityList.get(1).logHours(SoftwareApp.getUserFromID(string), 0, LocalDate.now());
        assertEquals(0, Project.SpecialActivityList.get(1).getUsedTime());
    }

    @Given("the user with the id {string} registers vacation with start date {string} and end date {string}")
    public void theUserWithTheIdRegistersVacationWithStartDateAndEndDate(String string, String string2, String string3) {
        User.addVacationDays(LocalDate.parse(string2), LocalDate.parse(string3));
        int days = LocalDate.parse(string3).getDayOfYear() - LocalDate.parse(string2).getDayOfYear();
        Project.SpecialActivityList.get(1).logHours(SoftwareApp.getUserFromID(string), days, LocalDate.parse(string2));
        assertEquals(days, Project.SpecialActivityList.get(1).getUsedTime());
    }

    @Then("the user with the id {string} is on vacation from {string} to {string}")
    public void theUserWithTheIdIsOnVacationFromTo(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        List<LocalDate> VacationDates = LocalDate.parse(string2).datesUntil(LocalDate.parse(string3)).collect(Collectors.toList());
        assertEquals(User.VacationDaysList, VacationDates);
    }

    @Given("the user with the id {string} is not already sick")
    public void theUserWithTheIdIsNotAlreadySick(String string) {
        // Write code here that turns the phrase above into concrete actions
        LocalDate sick = LocalDate.parse("2023-01-01");
        Project.SpecialActivityList.get(0).logHours(SoftwareApp.getUserFromID(string), 0,sick);
        assertEquals(0, Project.SpecialActivityList.get(0).getUsedTime());
    }
    @Given("the user with the id {string} registers sick leave from {string} to {string}")
    public void theUserWithTheIdRegistersSickLeaveFromTo(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        int days = LocalDate.parse(string3).getDayOfYear() - LocalDate.parse(string2).getDayOfYear();
        Project.SpecialActivityList.get(0).logHours(SoftwareApp.getUserFromID(string), days, LocalDate.parse(string2));
    }
    @Then("the user with the id {string} is sick from {string} to {string}")
    public void theUserWithTheIdIsSickFromTo(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        int days = LocalDate.parse(string3).getDayOfYear() - LocalDate.parse(string2).getDayOfYear();

        assertEquals(days, Project.SpecialActivityList.get(0).getUsedTime());
    }
}