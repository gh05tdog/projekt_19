package Cucumber.test;

import app.CSVgenerator;
import app.SoftwareApp;

import domain.Project;
import domain.User;
import domain.UserAlreadyExistsException;
import io.cucumber.java.en.And;
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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class Reports {

    private final app.SoftwareApp SoftwareApp;

    public Reports(app.SoftwareApp softwareApp, ErrorMessageHolder errorMessage) {
        this.SoftwareApp = softwareApp;
    }

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

    @And("there are completed activities for the project with the id {string}")
    public void thereAreCompletedActivitiesForTheProjectWithTheId(String arg0) {
        if(Objects.requireNonNull(SoftwareApp.getProject("23001")).getActivity("23001A1").getUsedTime() == 0){
            Objects.requireNonNull(SoftwareApp.getProject(arg0)).getActivity("23001A1").logHours(SoftwareApp.getUserFromID("mang"), 10, LocalDate.now());
            assert(Objects.requireNonNull(SoftwareApp.getProject("23001")).getActivity("23001A1").getUsedTime() > 0);
        }

    }
    @When("I generate a report for the project")
    public void iGenerateAReportForTheProject() {
        // Write code here that turns the phrase above into concrete actions
        CSVgenerator csv = new CSVgenerator(SoftwareApp.getProject("23001"));
        csv.saveCSVReportToFile(SoftwareApp.getCurrentWeek());
        assertTrue(Files.exists(Paths.get("Weekly Report - " +SoftwareApp.getCurrentWeek() + ".csv")));
    }

    @Then("the report shows the hours worked on each activity and the total time for the project")
    public void theReportShowsTheHoursWorkedOnEachActivityAndTheTotalTimeForTheProject() {
        String reportPath = "Weekly Report - " + SoftwareApp.getCurrentWeek() + ".csv";
        Map<String, Integer> hoursWorkedPerActivity = new HashMap<>();
        int totalHoursWorked = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(reportPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Activity ID:")) {
                    String activityId = line.split(": ")[1].trim();
                    line = br.readLine(); // Skip Time Budget
                    line = br.readLine(); // Skip Weeks
                    line = br.readLine(); // Skip Start Week
                    line = br.readLine(); // Skip Is completed
                    line = br.readLine(); // Skip Assigned User ID
                    line = br.readLine(); // Skip Assigned User Name
                    line = br.readLine(); // Read Time Spent on Activity

                    Pattern pattern = Pattern.compile("Time Spent on Activity: (\\d+)");
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        int hoursWorked = Integer.parseInt(matcher.group(1));
                        totalHoursWorked += hoursWorked;
                        hoursWorkedPerActivity.put(activityId, hoursWorked);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Verify the hours worked on each activity
        for (Project.Activities activity : Objects.requireNonNull(SoftwareApp.getProject("23001")).getActivityList()) {
            float expectedHoursWorked = activity.getUsedTime();
            float actualHoursWorked = hoursWorkedPerActivity.getOrDefault(activity.getActivityId(), 0);
            System.out.println(activity.getUsedTime());
            assert(expectedHoursWorked == actualHoursWorked);
        }

        // Verify the total time for the project
        int expectedTotalHoursWorked = Objects.requireNonNull(SoftwareApp.getProject("23001")).getTotalTimeSpentOnProject();
        assertEquals(expectedTotalHoursWorked, totalHoursWorked);
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
