package application;

import app.SoftwareApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class addProjectController {
    Model theModel;

    View view;


    public void initialize(){
        projectAddInfoLabel.setText("");
    }
    public void setModelAndView(Model theModel, View theView) {

        this.theModel = theModel;
        this.view = theView;
    }



    @FXML
    TextField projectNameField;

    @FXML
    Label projectAddInfoLabel;

    @FXML
    protected void addProjectButtonAction() {
        if(projectNameField.getText() != null & projectNameField.getText().length() > 0){
            theModel.addProject(projectNameField.getText());
            projectAddInfoLabel.setText("Project " + projectNameField.getText() + " added");
        }
        else{
            projectAddInfoLabel.setText("Please enter a project name");
        }
    }
    @FXML
    protected void returnFrontPage() {
        view.showMainPage(SoftwareApp.getUserFromID(theModel.getCurrentUserID()).getName());



    }
}
