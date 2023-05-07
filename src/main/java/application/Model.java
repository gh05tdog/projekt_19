package application;

import app.SoftwareApp;
import app.TooManyActivities;
import domain.Project;
import domain.User;
import domain.UserAlreadyExistsException;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Model {
    private final View view;
    private String currentUser;


    public Model(View view) throws TooManyActivities, UserAlreadyExistsException {
        this.view = view;

        // Add a user to the database
        User newUser = User.createUser("Roberto", "test");
        User.createUser("Tom", "toom");
        currentUser = newUser.getUserId(); // Set the currentUser variable to the created user's userId
        SoftwareApp.addProject("Lommeregner");
        SoftwareApp.addProject("Project102");
        SoftwareApp.addProject("Project103");
        SoftwareApp.addCoWorker(currentUser,"23001"); // Assign the user to a specific project
        SoftwareApp.addActivity("Projekt beskrivelse","100","10","4","23001");
        // Add time to the activity
        Objects.requireNonNull(SoftwareApp.getProject("23001")).getActivity("23001A1").logHours(SoftwareApp.getUserFromID("toom"),1, LocalDate.parse("2021-05-01"));

        SoftwareApp.addActivity("secondtActivity","100","10","4","23001");
        SoftwareApp.addActivity("thirdActivity","70","10","4","23001");
        SoftwareApp.addActivity("FourthActivity","50","10","4","23001");
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

    public void setCurrentUser(String string) {
        this.currentUser = string;
    }
    public void setCurrentUserID(String string) {
        this.currentUser = string;
    }
    public String getCurrentUserID () {
        return currentUser;
    }

    public List<Project> getProjects() {
        return SoftwareApp.projectList;
    }

    public void projectPagePage(String IDExtract, String nameExtract) {
        view.showProjectPage(IDExtract, nameExtract);

    }

    public void logout() {
        view.loginPage();
    }

    public void manageProject(String projectID,String projectName) {
        view.manageProjectPage(projectID,projectName);
    }

    public void showMainPage(String name) {
        view.showMainPage(name);
    }

    public boolean isNumeric(String str) {
        ParsePosition pos = new ParsePosition(0);
        NumberFormat.getInstance().parse(str, pos);
        return str.length() == pos.getIndex();
    }

    public void changeProjectName(String newProjectName, String projectID) {
        Objects.requireNonNull(SoftwareApp.getProject(projectID)).setProjectName(newProjectName);
    }
}

