package example.cucumber;


import app.SoftwareApp;
import domain.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import static app.SoftwareApp.CurrentUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class Reports {
    @Given("there is an activity with a name {string}, timebudget {string}, weeks {string}, start week {string}")
    public void thereIsAnActivityWithANameTimebudgetWeeksStartWeek(String Name, String timebudget, String weeks, String startweek) {
        // Write code here that turns the phrase above into concrete actions
        SoftwareApp.addActivity(Name, timebudget, weeks, startweek, "23001");
        SoftwareApp.addActivity(Name, timebudget, weeks, startweek, "23001");
        assertEquals(Objects.requireNonNull(SoftwareApp.getProject("23001")).getActivity("23001A2").getActivityName(), Name);
        assertEquals(Objects.requireNonNull(SoftwareApp.getProject("23001")).getActivity("23001A2").getTimeBudget(), timebudget);
        assertEquals(Objects.requireNonNull(SoftwareApp.getProject("23001")).getActivity("23001A2").getWeeks(), weeks);
        assertEquals(Objects.requireNonNull(SoftwareApp.getProject("23001")).getActivity("23001A2").getStartWeek(), startweek);
    }


    @Given("there are completed activities")
    public void thereAreCompletedActivities() {
        User newuser = new User("Amanda", "aman");
        SoftwareApp.addUser(newuser);

        //get today's date
        LocalDate date = LocalDate.now();
        // make sure that the activity is completed
        Objects.requireNonNull(SoftwareApp.getProject("23001")).getActivity("23001A2").logHours(SoftwareApp.getUserFromID("aman"), 10, date);
        Objects.requireNonNull(SoftwareApp.getProject("23001")).getActivity("23001A2").setCompleted();

        assertTrue(Objects.requireNonNull(SoftwareApp.getProject("23001")).getActivity("23001A2").isCompleted());
        assert SoftwareApp.getUserFromID("aman").getTimeSpentOnActivity("23001A2") == 10;

    }
    @When("I generate a report for the project")
    public void iGenerateAReportForTheProject() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("the report shows the hours worked on each activity and the total time for the project")
    public void theReportShowsTheHoursWorkedOnEachActivityAndTheTotalTimeForTheProject() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the user navigate to the {string} page")
    public void theUserNavigateToThePage(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Given("select the date range for the report")
    public void selectTheDateRangeForTheReport() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("the user click the {string} button")
    public void theUserClickTheButton(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the user should see a table displaying the productivity of each team member for the selected time period")
    public void theUserShouldSeeATableDisplayingTheProductivityOfEachTeamMemberForTheSelectedTimePeriod() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the user should be able to download the report as a CSV or PDF file")
    public void theUserShouldBeAbleToDownloadTheReportAsACSVOrPDFFile() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the user should see a list of previously generated weekly reports")
    public void theUserShouldSeeAListOfPreviouslyGeneratedWeeklyReports() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the user should be able to download any of them as a CSV or PDF file")
    public void theUserShouldBeAbleToDownloadAnyOfThemAsACSVOrPDFFile() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
