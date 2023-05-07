package application;

import app.SoftwareApp;
import app.TooManyActivities;
import domain.ActivityTimeSheet;
import domain.Project;
import domain.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import javafx.scene.text.Text;


import java.util.Objects;
import java.util.Optional;

public class manageProjectController {

    public Model model;
    public View view;

    public Text manageProjectID;

    public Text manageProjectName;

    @FXML
    public TextField addActivityName;

    @FXML
    public TextField addTimeBudget;

    @FXML
    public TextField addStartWeek;

    @FXML
    public TextField enterWeekAmount;
    public TextField userIDManager;
    @FXML
    private TextField activityUserID;

    @FXML
    private TextField activityActivityID;


    public Project project;





    public void setModelAndView(Model theModel, View view) {


        this.model = theModel;
        this.view = view;
    }

    public void setProjectID(String projectID) {
        manageProjectID.setText(projectID);

    }
    public void setProjectName(String projectName){
        manageProjectName.setText(projectName);
    }

    public void returnProjectPagePressed() {
        view.showProjectPage(manageProjectID.getText(),manageProjectName.getText());

    }

    public void addActivityToProject() {
        project = SoftwareApp.getProject(manageProjectID.getText());
        project.addActivity(addActivityName.getText(),addTimeBudget.getText(),enterWeekAmount.getText(),addStartWeek.getText());

    }

    public void addActivityToUser () throws TooManyActivities {
        SoftwareApp.assignActivityToUser(activityUserID.getText(),manageProjectID.getText(),activityActivityID.getText());

    }

    public void assignProjectManAction() {
        User user = SoftwareApp.getUserFromID(userIDManager.getText());

        if (user != null) {
            Objects.requireNonNull(SoftwareApp.getProject(manageProjectID.getText())).setProjectManager(user);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("User not found");
            alert.setContentText("Please enter a valid user ID");
            alert.showAndWait();
        }
    }
}
