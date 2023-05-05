package application;


import domain.Project;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class projectPageController {

    private Model theModel;

    private Label label;

    public void setModelAndView(Model theModel) {

        this.theModel = theModel;
    }


@FXML
    private Label projectIDLabel;
    public void setProjectIDLabel(String name) {
        projectIDLabel.setText(name);
    }

}
