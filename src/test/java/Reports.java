import app.CSVgenerator;
import app.SoftwareApp;

import domain.User;
import domain.UserAlreadyExistsException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.Objects;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class Reports {
    @Given("there is an activity with a name {string}, timebudget {string}, weeks {string}, start week {string}")
    public void thereIsAnActivityWithANameTimebudgetWeeksStartWeek(String Name, String timebudget, String weeks, String startweek) {
        // Write code here that turns the phrase above into concrete actions
        SoftwareApp.addActivity(Name, timebudget, weeks, startweek, "23001");
        SoftwareApp.addActivity(Name, timebudget, weeks, startweek, "23001");
        assertEquals(Objects.requireNonNull(SoftwareApp.getProject("23001")).getActivity("23001A1").getActivityName(), Name);
        assertEquals(Objects.requireNonNull(SoftwareApp.getProject("23001")).getActivity("23001A1").getTimeBudget(), timebudget);
        assertEquals(Objects.requireNonNull(SoftwareApp.getProject("23001")).getActivity("23001A1").getWeeks(), weeks);
        assertEquals(Objects.requireNonNull(SoftwareApp.getProject("23001")).getActivity("23001A1").getStartWeek(), startweek);
    }

    @Given("there are completed activities")
    public void thereAreCompletedActivities() throws UserAlreadyExistsException {
        User.createUser("Amanda", "aman");
        //get today's date
        LocalDate date = LocalDate.now();
        // make sure that the activity is completed
        Objects.requireNonNull(SoftwareApp.getProject("23001")).getActivity("23001A1").logHours(SoftwareApp.getUserFromID("aman"), 10, date);
        Objects.requireNonNull(SoftwareApp.getProject("23001")).getActivity("23001A1").setCompleted();

        assertTrue(Objects.requireNonNull(SoftwareApp.getProject("23001")).getActivity("23001A1").isCompleted());
        assert SoftwareApp.getUserFromID("aman").getTimeSpentOnActivity("23001A1") == 10;
    }
    @When("I generate a report for the project")
    public void iGenerateAReportForTheProject() throws Exception, UserAlreadyExistsException {
        User.createUser("Phillip", "phil");
            SoftwareApp.assignActivityToUser("aman", "23001", "23001A2");
            SoftwareApp.assignActivityToUser("phil", "23001", "23001A2");

       // System.out.println(Project.workersList);
    }

    @Then("the report shows the hours worked on each activity and the total time for the project")
    public void theReportShowsTheHoursWorkedOnEachActivityAndTheTotalTimeForTheProject() {
        assertEquals(((Objects.requireNonNull(SoftwareApp.getProject("23001"))).getActivity("23001A1").getUsedTime()), 10);
        assertEquals(Objects.requireNonNull(SoftwareApp.getProject("23001")).getActivity("23001A1").getTotalTimeSpentOnProject(), 10);
    }

    @When("the user click the {string} button and have selected the date range")
    public void theUserClickTheButtonAndHaveSelectedTheDateRange(String string) {
        // Write code here that turns the phrase above into concrete actions
        //Date range is set to be the current week, for now
        LocalDate date = LocalDate.now();
        int WhichWeek = Integer.parseInt(String.valueOf(date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR)));
        CSVgenerator csv = new CSVgenerator(SoftwareApp.getProject("23001"));
        csv.saveCSVReportToFile(WhichWeek);
    }

    @Then("the report should be generated and the file should be downloaded")
    public void theReportShouldBeGeneratedAndTheFileShouldBeDownloaded() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(Files.exists(Paths.get("Weekly Report - " +SoftwareApp.getCurrentWeek() + ".csv")));
    }
}
