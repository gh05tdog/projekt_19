package example.cucumber;

import app.SoftwareApp;
import app.TooManyActivities;
import domain.Project;
import domain.User;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class AddsProject {
    private User user;


    @Given("that there is a user with the id {string}")
    public void that_there_is_a_user_with_the_id(String userId) {
        //If there is a user with the id, do nothing, else create new user
            User.createUser("abcd", userId);
            //Check if the user with the id is in UserList
            assertEquals(SoftwareApp.getUserFromID(userId).getUserId(), userId);


    }

    @And("that the user with the id {string} is logged in")
    public void thatTheUserWithTheIdIsLoggedIn(String userID) {
        user = SoftwareApp.getUserFromID(userID);
        SoftwareApp.CurrentUser = user.getUserId();
        assertEquals(SoftwareApp.CurrentUser, userID);
    }

    @When("the user adds a project with the name {string}")
    public void theUserAddsAProjectWithTheName(String projectName) {
        SoftwareApp.addProject(projectName);
    }


    @Then("there is a project named {string} the project has an id {string}")
    public void thereIsAProjectNamedTheProjectHasAnId(String name, String ProjectID) {
        // Check that the project is added to the list of projects
        assertEquals(SoftwareApp.getNumberOfProject(), 1);
        assertEquals(SoftwareApp.projectList.get(0).getProjectName(), name);
        assertEquals(SoftwareApp.projectList.get(0).getProjectId(), ProjectID);

    }

    @Given("there is a project with the id {string}")
    public void thereIsAProjectWithTheId(String ProjectID) {
        SoftwareApp.addProject("Lommeregner");
        assertEquals(SoftwareApp.projectList.get(0).getProjectId(), ProjectID);

    }

    @When("user adds a co-worker with the id {string} to the project with the id {string}")
    public void userAddsACoWorkerWithTheIdToTheProjectWithTheId(String username, String projectId) {
        User.createUser("john",username);
        SoftwareApp.addCoWorker(username, projectId);
        assertEquals(SoftwareApp.projectList.get(0).getWorkersList(username).getUserId(), username);

    }

    @When("the users select a project manager with the id {string} for the project with the id {string}")
    public void theUsersSelectAProjectManagerWithTheIdForTheProjectWithTheId(String projectManID, String ProjectID) {
        user = User.createUser("Manga", projectManID);
        // Write code here that turns the phrase above into concrete actions
        SoftwareApp.projectList.get(0).setProjectManager(user);
        Project project = SoftwareApp.getProject(ProjectID);
        assert project != null;

        assertEquals(project.getProjectManager().getUserId(), projectManID);
    }

    @Then("the project with the id {string} has a worker with the id {string} and a project manager with the id {string} assigned")
    public void theProjectWithTheIdHasAWorkerWithTheIdAndAProjectManagerWithTheIdAssigned(String projectId, String coWorkerId, String MangID) {
        Project project = SoftwareApp.getProject(projectId);
        assert project != null;
        assertEquals(project.getWorkersList(coWorkerId).getUserId(), coWorkerId);
        assertEquals(project.getProjectManager().getUserId(), MangID);

    }

    @When("the user adds an activity To the project with a name {string}, timebudget {string}, weeks {string}, start week {string} to the project with the id {string}")
    public void theUserAddsAnActivityToTheProjectWithANameTimebudgetWeeksStartWeekToTheProjectWithTheId(String name, String timebudget, String weeks, String startWeek, String projectId) {

        SoftwareApp.addActivity(name, timebudget, weeks, startWeek, projectId);

    }

    @Then("the project with the id {string} has an activiy with the name {string}, timebudget {string}, weeks {string}, start week {string}")
    public void theProjectWithTheIdHasAnActiviyWithTheNameTimebudgetWeeksStartWeek(String projectId, String name, String timebudget, String weeks, String startWeek) {
        // Write code here that turns the phrase above into concrete actions
        Project project = SoftwareApp.getProject(projectId);
        assert project != null;
        assertEquals(project.getActivityList().get(0).getActivityName(), name);
        assertEquals(project.getActivityList().get(0).getTimeBudget(), timebudget);
        assertEquals(project.getActivityList().get(0).getWeeks(), weeks);
        assertEquals(project.getActivityList().get(0).getStartWeek(), startWeek);
    }

    @Given("there is a user with the id {string}")
    public void thereIsAUserWithTheId(String string) {
        // Write code here that turns the phrase above into concrete actions
        User.createUser("emma", string);
        assertEquals(SoftwareApp.getUserFromID(string).getUserId(), string);
    }

    @When("The user assign employees {string} and {string} to activity {string} in project {string}")
    public void theUserAssignEmployeesAndToActivityInProject(String string, String string2, String string3, String string4) throws TooManyActivities {
        // Write code here that turns the phrase above into concrete actions
        System.out.println(SoftwareApp.UserList);
        SoftwareApp.assignActivityToUser(string,string4, string3);
        SoftwareApp.assignActivityToUser(string2, string4,string3);
        //Make sure that both employees are added to workersList based on the userID

    }

    @Then("employees {string} and {string} should be assigned to activity {string} in project {string}")
    public void employeesAndShouldBeAssignedToActivityInProject(String string, String string2, String string3, String string4) {
        // Write code here that turns the phrase above into concrete actions
        //go through UserActivityList and check if emma and john is in it
        throw new io.cucumber.java.PendingException();
    }
}


