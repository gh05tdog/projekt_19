package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class frontPageController {

    public void setModelAndView(Model theModel, View view) {
    }
    @FXML
    private Label userNameLabel;
    public void setUserNameLabel(String name) {
        userNameLabel.setText(name);
    }
}
