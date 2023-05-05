package application;

import app.SoftwareApp;
import app.TooManyActivities;
import domain.ActivityTimeSheet;
import domain.Project;
import domain.User;

import java.util.List;

public class Model {
    private final View view;


    public Model(View view) throws TooManyActivities {
        this.view = view;
        // Add a user to the database
        User.createUser("Roberto", "test");
        SoftwareApp.addProject("Project101");
        SoftwareApp.addActivity("name","100","10","02/02/2024","23001");
        SoftwareApp.assignActivityToUser("test","23001","23001A1");

    }

    public void login(String name) {
        view.showMainPage(name);
    }

    public void createUser(){
        view.showAddUser();
    }

}
