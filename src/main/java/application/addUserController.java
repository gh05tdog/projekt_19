package application;


import domain.UserAlreadyExistsException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class addUserController {

    public Button addUserButton;
    private Model theModel;





    public void setModelAndView(Model theModel) {
        this.theModel = theModel;
    }

    @FXML
    private Label userNameInfoLabel;

    public void setUserNameLabel(String name) {
        userNameInfoLabel.setText(name);
    }

    @FXML
    private TextField userName;
    @FXML
    private TextField userID;


    @FXML
    protected void addUserButtonAction() {
        if (userName.getText() != null & userID.getText() != null & userID.getText().length() == 4) {
            try {
                theModel.addUser(userName.getText(), userID.getText());
                setUserNameLabel("User " + userName.getText() + " added");
            }catch (UserAlreadyExistsException e){
                setUserNameLabel("User already exists");
            }
        }
        else {
            setUserNameLabel("Please enter a name and ID");
        }
    }
}

