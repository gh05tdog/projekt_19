package example.cucumber;

import app.SoftwareApp;
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
        user = new User("Amanda", userId);
        SoftwareApp.addUser(user);
        assertSame(user.getUserId(), userId);

    }

    @And("that the user with the id {string} is logged in")
    public void thatTheUserWithTheIdIsLoggedIn(String userID) {

        SoftwareApp.CurrentUser = user.getUserId();

        System.out.println(SoftwareApp.CurrentUser);

        assertEquals(SoftwareApp.CurrentUser, userID);

    }

    @When("the user adds a project with the name {string}")
    public void theUserAddsAProjectWithTheName(String projectName) {
        SoftwareApp.addProject(projectName);
    }


    @When("the project is added")
    public void theProjectIsAdded() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("there is a project named {string} the project has an id {string}")
    public void thereIsAProjectNamedTheProjectHasAnId(String arg0, String arg1) {
        // Check that the project is added to the list of projects
        assertEquals(SoftwareApp.getNumberOfProject(), 1);
        assertEquals(SoftwareApp.projectList.get(0).getProjectName(), arg0);
        assertEquals(SoftwareApp.projectList.get(0).getProjectId(), arg1);

    }
    @Given("there is a project with the id {string}")
    public void thereIsAProjectWithTheId(String ProjectID) {
        SoftwareApp.addProject("Lommeregner");
        assertEquals(SoftwareApp.projectList.get(0).getProjectId(), ProjectID);

    }
    @When("user adds a co-worker with the id {string} to the project with the id {string}")
    public void userAddsACoWorkerWithTheIdToTheProjectWithTheId(String username, String projectId){
        user = new User("John", "john");
        SoftwareApp.addUser(user);

        SoftwareApp.addCoWorker(username, projectId);

        assertEquals(SoftwareApp.projectList.get(0).getWorkersList().get(0).getUserId(), username);

    }
    @When("the users select a project manager with the id {string} for the project with the id {string}")
    public void theUsersSelectAProjectManagerWithTheIdForTheProjectWithTheId(String projectManID, String ProjectID) {
        user = new User("Manga", projectManID);
        SoftwareApp.addUser(user);

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
        assertEquals(project.getWorkersList().get(0).getUserId(), coWorkerId);
        assertEquals(project.getProjectManager().getUserId(), MangID);

    }


}