package example.cucumber;

import app.SoftwareApp;
import app.TooManyActivities;
import domain.Project;
import domain.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Objects;

import static app.SoftwareApp.CurrentUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class InteractsWithActivities {
    private final ErrorMessageHolder errorMessage;

    public InteractsWithActivities(ErrorMessageHolder errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Given("the projekt manager with the id {string} is logged in")
    public void theProjektManagerWithTheIdIsLoggedIn(String userId) {
        if (SoftwareApp.getUserFromID(userId) == null) {
            User user = new User("Amanda", userId);
            SoftwareApp.addUser(user);
            assertSame(user.getUserId(), userId);

            SoftwareApp.projectList.get(0).setProjectManager(user);
            Project project = SoftwareApp.getProject("23001");
            assert project != null;
            assertEquals(project.getProjectManager().getUserId(), "mang");
            return;

        }
        User user = SoftwareApp.getUserFromID(userId);

        SoftwareApp.projectList.get(0).setProjectManager(user);
        Project project = SoftwareApp.getProject("23001");
        assert project != null;

        assertEquals(project.getProjectManager().getUserId(), "mang");
    }

    @Given("if the user with an id {string} is assigned {int} activities")
    public void if_the_user_with_an_id_is_assigned_activities(String userID, Integer nbOfActivities) throws Exception {
        // Add activities to the project
        User.createUser("tejs",userID);
        for (int i = 1; i <= nbOfActivities; i++) {
            SoftwareApp.addActivity("name" + i, "10", "1", "1", "23001");
            SoftwareApp.assignActivityToUser(userID, "23001", "23001" + "A" + i);
        }

        // Check that the user is assigned the correct number of activities
        System.out.println(SoftwareApp.getUserFromID(userID).getAssignedActivitiesNumber());
        System.out.println(SoftwareApp.getUserFromID(userID).getAssignedActivities());

        //For each activity in the list of assigned activities print the activity id
        for(Project.Activities activity : SoftwareApp.getUserFromID(userID).getAssignedActivities()){
            System.out.println(activity.getActivityId());
        }

        assert SoftwareApp.getUserFromID(userID).getAssignedActivitiesNumber() == nbOfActivities;
    }

    @When("the user with an id {string} is assigned the activity")
    public void the_user_with_an_id_is_assigned_the_activity(String string) {
        User user = SoftwareApp.getUserFromID(string);
        try {
            System.out.println(SoftwareApp.getUserFromID("abcd").getAssignedActivitiesNumber());
            SoftwareApp.addActivity("TOO-MANY", "10", "1", "1", "23001");
            SoftwareApp.assignActivityToUser(user.getUserId(), "23001", "23001A11");
        } catch (TooManyActivities e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("the user will get a warning {string}")
    public void the_user_will_get_a_warning(String string) {
        assertEquals(errorMessage.getErrorMessage(), string);
    }

    @Then("the user is assigned the activity")
    public void the_user_is_assigned_the_activity() {
        assertEquals(SoftwareApp.getUserFromID("abcd").getAssignedActivitiesNumber(), 11);
    }

    @When("the user logs {int} hours on activity {string}")
    public void theUserLogsHoursOnActivity(int hours, String activityId) {
        Objects.requireNonNull(SoftwareApp.getProject("23001")).getActivity(activityId).logHours(SoftwareApp.getUserFromID(CurrentUser), hours);
    }

    @Then("the used time on activity is {int}")
    public void theUsedTimeOnActivityIs(Integer int1) {
        assertEquals(Objects.requireNonNull(SoftwareApp.getProject("23001")).getActivity("23001A1").getUsedTime(), int1);
    }

    @Then("the user timesheet should be updated with the logged time")
    public void theUserTimesheetShouldBeUpdatedWithTheLoggedTime() {
        assert SoftwareApp.getUserFromID(CurrentUser).getTimeSpentOnActivity("23001A1") == 2;
    }

    @Given("there are employees registered in the system")
    public void thereAreEmployeesRegisteredInTheSystem() {
        User user = new User("Amanda", "test");
        SoftwareApp.addUser(user);
        User user1 = new User("Amanda", "tesl");
        SoftwareApp.addUser(user1);
    }
}