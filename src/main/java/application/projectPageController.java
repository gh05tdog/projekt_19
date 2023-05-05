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

    public Label projectNameLabel;

    public Label projectIDLabel;
    private Model theModel;

public void setModelAndView(Model theModel) {
    this.theModel = theModel;
}

    public void setProjectIDLabel (String name) {
        projectIDLabel.setText(name);
    }

    public void setProjectNameLabel (String name) {
         projectNameLabel.setText(name);
    }

}
