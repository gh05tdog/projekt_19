package Cucumber.test;

import app.SoftwareApp;
import domain.Project;
import domain.User;
import domain.UserAlreadyExistsException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class InteractsWithActivities {
    private final ErrorMessageHolder errorMessage;

    public InteractsWithActivities(ErrorMessageHolder errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Given("the project manager with the id {string} is logged in")
    public void theProjectManagerWithTheIdIsLoggedIn(String userId) {
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

    @Given("the user with an id {string} is assigned {int} activities")
    public void the_user_with_an_id_is_assigned_activities(String userID, Integer nbOfActivities) throws Exception, UserAlreadyExistsException {
        // Add activities to the project
        User.createUser("tejs",userID);

        for (int i = 1; i <= nbOfActivities; i++) {
            SoftwareApp.addActivity("name" + i, "10", "1", "1", "23001");

                SoftwareApp.assignActivityToUser(userID, "23001", "23001" + "A" + i);

        }

        assert SoftwareApp.getUserFromID(userID).getAssignedActivitiesNumber() == nbOfActivities;

        List<User> list1 = Objects.requireNonNull(SoftwareApp.getProject("23001")).getActivity("23001A1").getUserAssignedActivities();
        for (User user : list1) {

            System.out.println(user.getUserId());
        }

        for(int i = 1; i <= nbOfActivities; i++) {
            System.out.println("23001A" + i);
            List<User> list = Objects.requireNonNull(SoftwareApp.getProject("23001")).getActivity("23001A" + i).getUserAssignedActivities();
            //Loop through the list and check if the user is assigned to the activity if not throw an exception
            boolean isAssigned = false;
            for (User user : list) {
                if (user.getUserId().equals(userID)) {
                    isAssigned = true;
                    break;
                }
            }
            if (!isAssigned) {
                throw new Exception("User is not assigned to the activity");
            }

        }
    }

    @When("the user with an id {string} is assigned the activity")
    public void the_user_with_an_id_is_assigned_the_activity(String string) {
        User user = SoftwareApp.getUserFromID(string);
        try {
            SoftwareApp.addActivity("TOO-MANY", "10", "1", "1", "23001");
                SoftwareApp.assignActivityToUser(user.getUserId(), "23001", "23001A11");

        } catch (Exception e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("the user will get a warning {string}")
    public void the_user_will_get_a_warning(String string) {
        assertEquals(errorMessage.getErrorMessage(), string);
    }

    @Then("the user is assigned the activity")
    public void the_user_is_assigned_the_activity() {
        assertEquals(SoftwareApp.getUserFromID("test").getAssignedActivitiesNumber(), 11);
    }

    @When("the user with the id {string} logs {int} hours on activity {string}")
    public void theUserWithTheIdLogsHoursOnActivity(String arg0, int arg1, String arg2) {
        Objects.requireNonNull(SoftwareApp.getProject("23001")).getActivity(arg2).logHours(SoftwareApp.getUserFromID(arg0), arg1, LocalDate.now());
    }

    @Then("the used time on activity is {int}")
    public void theUsedTimeOnActivityIs(Integer int1) {
        int time = int1;
        assertEquals(((Objects.requireNonNull(SoftwareApp.getProject("23001"))).getActivity("23001A1").getUsedTime()), time);
    }


    @Then("the user timesheet should be updated with the logged time")
    public void theUserTimesheetShouldBeUpdatedWithTheLoggedTime() {
        assertEquals(SoftwareApp.getUserFromID("abcd").getTimeSpentOnActivity("23001A1"),2);
    }

    @Given("there are employees registered in the system")
    public void thereAreEmployeesRegisteredInTheSystem() {
        User user = new User("Amanda", "test");
        SoftwareApp.addUser(user);
        User user1 = new User("Amanda", "tesl");
        SoftwareApp.addUser(user1);
    }
}