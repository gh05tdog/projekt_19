package application;

import app.SoftwareApp;
import domain.ActivityTimeSheet;
import domain.Project;
import domain.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

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
}
