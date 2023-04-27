package application;

import app.SoftwareApp;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

public class loginController {
    private Model theModel;
    @FXML
    private PasswordField passwordField;


    @FXML
    protected void LoginButtonPressed()
    {
        // Check if the user exists with the function getUserFromID
        // if the user exists, then login
        // else, show error message

        if(SoftwareApp.getUserFromID(passwordField.getText()) != null)
        {
            theModel.login(SoftwareApp.getUserFromID(passwordField.getText()).getName());
        }else
        {
            System.out.println("User does not exist");
        }
    }
    public void setModelAndView(Model model)
    {
        this.theModel = model;
    }

}
