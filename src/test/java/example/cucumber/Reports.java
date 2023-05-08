package example.cucumber;


import app.SoftwareApp;
import domain.Project;
import domain.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;


public class Reports {
    @Given("there is an activity with a name {string}, timebudget {string}, weeks {string}, start week {string}")
    public void thereIsAnActivityWithANameTimebudgetWeeksStartWeek(String string, String string2, String string3, String string4) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


    @Given("there are completed activities")
    public void thereAreCompletedActivities() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I generate a report for the project")
    public void iGenerateAReportForTheProject() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
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
