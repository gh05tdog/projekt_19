package Cucumber.test;

import app.SoftwareApp;
import app.TooManyActivities;
import app.WayTooManyActivities;
import domain.Project;
import domain.User;


import domain.UserAlreadyExistsException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Objects;

import static org.junit.Assert.*;
//Denne feature er lavet af Oliver
public class AddsProject {

    private User user;
    @Given("that there is a user with the id {string} and the name {string}")
    public void thatThereIsAUserWithTheIdAndTheName(String userId, String Name) throws UserAlreadyExistsException {
        //If there is a user with the id, do nothing, else create new user
        User.createUser(Name, userId);
    }

    @When("the user adds a project with the name {string}")
    public void theUserAddsAProjectWithTheName(String projectName) {
        SoftwareApp.addProject(projectName);
    }


    @Then("there is a project named {string} the project has an id {string}")
    public void thereIsAProjectNamedTheProjectHasAnId(String name, String ProjectID) {
        assertEquals(Objects.requireNonNull(SoftwareApp.getProject(ProjectID)).getProjectId(), ProjectID);
        assertEquals(Objects.requireNonNull(SoftwareApp.getProject(ProjectID)).getProjectName(), name);

    }

    @Given("there is a project with the id {string}")
    public void thereIsAProjectWithTheId(String ProjectID) {
        //Check if there is a project with the id, if not create a new project
        for (Project project : SoftwareApp.projectList) {
            if (project.getProjectId().equals(ProjectID)) {
                return;
            }
        }
        SoftwareApp.addProject("Lommeregner");
        assertEquals(((Objects.requireNonNull(SoftwareApp.getProject(ProjectID)))).getProjectId(), ProjectID);
        //Print all projects

    }

    @When("user adds a co-worker with the id {string} to the project with the id {string}")
    public void userAddsACoWorkerWithTheIdToTheProjectWithTheId(String username, String projectId) throws UserAlreadyExistsException {
        User.createUser("john", username);
        SoftwareApp.addCoWorker(username, projectId);
        assertEquals(Objects.requireNonNull(SoftwareApp.getProject(projectId)).getWorkersList(username).getUserId(), username);

    }

    @When("the users select a project manager with the id {string} for the project with the id {string}")
    public void theUsersSelectAProjectManagerWithTheIdForTheProjectWithTheId(String projectManID, String ProjectID) throws UserAlreadyExistsException {
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

    @Then("the project with the id {string} has an activity with the name {string}, timebudget {string}, weeks {string}, start week {string}")
    public void theProjectWithTheIdHasAnActivityWithTheNameTimebudgetWeeksStartWeek(String projectId, String name, String timebudget, String weeks, String startWeek) {
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
        try {
            User.createUser("emma", string);
        } catch (UserAlreadyExistsException e){
            //Check if the user with the id is in UserList
            assertEquals(SoftwareApp.getUserFromID(string).getUserId(), string);
        }
        assertEquals(SoftwareApp.getUserFromID(string).getUserId(), string);
    }

    @When("The user assign employees {string} and {string} to activity {string} in project {string}")
    public void theUserAssignEmployeesAndToActivityInProject(String user1, String user2, String activityID, String projectID) throws TooManyActivities, WayTooManyActivities {
        // Write code here that turns the phrase above into concrete actions
        //System.out.println(SoftwareApp.UserList);
            SoftwareApp.assignActivityToUser(user1, projectID, activityID);
            SoftwareApp.assignActivityToUser(user2, projectID, activityID);

    }

    @Then("employees {string} and {string} should be assigned to activity {string} in project {string}")
    public void employeesAndShouldBeAssignedToActivityInProject(String User1, String User2, String activityId, String projectId) {
        // Write code here that turns the phrase above into concrete actions
        Project project = SoftwareApp.getProject(projectId);
        assert project != null;

        //Loop trough the userAssignedActivities and check if the users are assigned to the activity
        for (User user : project.getUserAssignedActivities(activityId)) {
            if (user.getUserId().equals(User1)) {
                assertEquals(user.getUserId(), User1);
            }
            if (user.getUserId().equals(User2)) {
                assertEquals(user.getUserId(), User2);
            }
        }
    }



}


