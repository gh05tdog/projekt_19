package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.awt.*;


public class frontPageController {

    public void setModelAndView(Model theModel, View view) {
    }
    @FXML
    private Label userNameLabel;
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

    public void setGeneralActivityCount(String string) {generalActivityCount.setText(string); }

    @FXML
    private Label generalProjectCount;

    public void setGeneralProjectCount(String string) {generalProjectCount.setText(string); }
}

