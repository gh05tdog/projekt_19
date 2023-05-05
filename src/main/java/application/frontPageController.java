package application;


import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class frontPageController {

    private Model theModel;

    public void setModelAndView(Model theModel) {
        this.theModel = theModel;
    }

    @FXML
    private Label userNameLabel;

    public void setUserNameLabel(String name) {
        userNameLabel.setText(name);
    }

    @FXML
    protected void createUserPressed() {
        theModel.createUser();
    }


}


