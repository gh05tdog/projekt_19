package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class addProjectController {
    Model theModel;


    public void initialize(){
        projectAddInfoLabel.setText("");
    }
    public void setModelAndView(Model theModel) {
        this.theModel = theModel;
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
}
