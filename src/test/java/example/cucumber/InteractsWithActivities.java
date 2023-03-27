package example.cucumber;

import app.SoftwareApp;
import app.TooManyActivities;
import domain.Project;
import domain.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class InteractsWithActivities {
    private final ErrorMessageHolder errorMessage;

    public InteractsWithActivities(ErrorMessageHolder errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Given("the projekt manager with the id {string} is logged in")
    public void theProjektManagerWithTheIdIsLoggedIn(String userId) {
        User user = new User("Amanda", userId);
        SoftwareApp.addUser(user);
        assertSame(user.getUserId(), userId);
        SoftwareApp.projectList.get(0).setProjectManager(user);
        Project project = SoftwareApp.getProject("23001");
        assert project != null;

        assertEquals(project.getProjectManager().getUserId(), "mang");
    }

    @Given("if the user with an id {string} is assigned {int} activities")
    public void if_the_user_with_an_id_is_assigned_activities(String userID, Integer nbOfActivities) throws Exception {
        // Add activities to the project
        for(int i = 1; i <= nbOfActivities; i++){
            SoftwareApp.addActivity("name" + i, "10", "1", "1", "23001");
            SoftwareApp.assignActivityToUser(userID, "23001", "23001" + "A" + i);
        }

        // Check that the user is assigned the correct number of activities
        User CurrentUser = SoftwareApp.getUserFromID(userID);
        assert CurrentUser.getAssignedActivitiesNumber() == nbOfActivities;
    }
    @When("the user with an id {string} is assigned the activity")
    public void the_user_with_an_id_is_assigned_the_activity(String string){
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
}
