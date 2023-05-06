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
        User.createUser("Roberto", "test");
        SoftwareApp.addProject("din mor");
        SoftwareApp.addProject("Project102");
        SoftwareApp.addProject("Project103");
        SoftwareApp.addActivity("FirstActivity","100","10","02/02/2024","23001");
        SoftwareApp.assignActivityToUser("test","23001","23001A1");

    }

    public void frontPagePage(String name) {
        view.showMainPage(name);
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

    public List<Project> getProjects() {
        return SoftwareApp.projectList;
    }

    public List<Project.Activities> getActivities(){ return Project.ActivityList; }

    public void projectPagePage(String IDExtract, String nameExtract) {
        view.showProjectPage(IDExtract, nameExtract);

    }

}
