package application;

import app.SoftwareApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;


public class frontPageController {
    public void setModelAndView(Model theModel, View view) {}
    @FXML
    private Label userNameLabel;

    public Model theModel;
    public void setUserNameLabel(String name) {
        userNameLabel.setText(name);
    }

    @FXML
    private Label userActivityCount;

    public void setUserActivityCount(String string) {userActivityCount.setText(string); }

    @FXML
    private Label userProjectCount;

    public void setUserProjectCount(String string) {userProjectCount.setText(string); }

    @FXML
    private Label generalActivityCount;

    public void setGeneralActivityCount(Label label) {generalActivityCount = (" "); }

    @FXML
    private Label generalProjectCount;

    public void setGeneralProjectCount(String string) {generalProjectCount.setText(string); }

    @FXML
    protected void createUserPressed()
    { theModel.createUser();
        }
    }


