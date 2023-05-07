package application;

import app.SoftwareApp;
import app.TooManyActivities;
import domain.Project;
import domain.User;
import domain.UserAlreadyExistsException;

import java.util.List;


public class Model {
    private final View view;
    private String currentUser;


    public Model(View view) throws TooManyActivities, UserAlreadyExistsException {
        this.view = view;

        // Add a user to the database
        User newUser = User.createUser("Roberto", "test");
        currentUser = newUser.getUserId(); // Set the currentUser variable to the created user's userId
        SoftwareApp.addProject("din mor");
        SoftwareApp.addProject("Project102");
        SoftwareApp.addProject("Project103");
        SoftwareApp.addCoWorker(currentUser,"23001"); // Assign the user to a specific project
        SoftwareApp.addActivity("FirstActivity","100","10","4","23001");
        SoftwareApp.addActivity("secondtActivity","100","10","4","23001");
        SoftwareApp.addActivity("thirdActivity","100","10","4","23001");
        SoftwareApp.addActivity("FourthActivity","100","10","4","23001");
        SoftwareApp.assignActivityToUser("test","23001","23001A1");
        SoftwareApp.assignActivityToUser("test","23002","23001A2");
    }

    public void frontPagePage() {
        view.showMainPage(SoftwareApp.getUserFromID(getCurrentUserID()).getName());

    }



    public void createUser(){
        view.showAddUser();
    }

    public void addUser(String name, String userId) throws UserAlreadyExistsException {
        User.createUser(name, userId);
    }


    public void addProjectPage() {
        view.showAddProject();
    }

    public void addProject(String projectName) {
        SoftwareApp.addProject(projectName);
    }

    public String getCurrentUser () {
        return currentUser;

    }
    public void setCurrentUser(String string) {
        this.currentUser = string;
    }
    public void setCurrentUserID(String string) {
    }
    public String getCurrentUserID () {
        return currentUser;
    }

    public List<Project> getProjects() {
        return SoftwareApp.projectList;
    }

    public List<Project.Activities> getActivities(Project project){ return project.ActivityList; }

    public void projectPagePage(String IDExtract, String nameExtract) {
        view.showProjectPage(IDExtract, nameExtract);

    }

    public void logout() {
        view.loginPage();
    }

    public void manageProject() {
        view.manageProjectPage();
    }

}
