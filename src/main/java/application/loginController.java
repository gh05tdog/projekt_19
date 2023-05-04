package application;

import app.SoftwareApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

public class loginController {
    public Button loginButton;
    private Model theModel;
    @FXML
    private PasswordField passwordField;

    // Create initialize function
    public void initialize()
    {
        // Set the login button to be the default button so that the user can press enter to log in
        loginButton.setDefaultButton(true);
    }

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