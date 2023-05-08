package application;

import app.SoftwareApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
//Hele denne klasse er lavet af Martin
public class loginController {

    public Button loginButton;
    private Model theModel;
    @FXML
    public PasswordField passwordField;


    // Create initialize function
    public void initialize()
    {
        // Set the login button to be the default button so that the user can press enter to log in
        passwordField.clear();
        loginButton.setDefaultButton(true);
    }

    @FXML
    protected String LoginButtonPressed()
    {
        // Check if the user exists with the function getUserFromID
        // if the user exists, then login
        // else, show error message

        if(SoftwareApp.getUserFromID(passwordField.getText().toLowerCase()) != null)
        {
            theModel.setCurrentUser(SoftwareApp.getUserFromID(passwordField.getText().toLowerCase()).getUserId());
            theModel.setCurrentUserID(passwordField.getText().toLowerCase());
            theModel.frontPagePage();
        }
        return SoftwareApp.getUserFromID(passwordField.getText().toLowerCase()).getName();
    }

    public void setModelAndView(Model model) {
        this.theModel = model;
    }
}
