package application;


import app.SoftwareApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Objects;

public class activityPageController {

    public Text activityID;
    public Text activityName;
    public Button returnToFrontPage;
    public VBox vBoxUserID;
    public VBox vBoxTimeSpent;
    public TextField enterTime;
    public Button addToActivity;
    public TextField startWeek;
    public TextField endWeek;
    public TextField allocatedTime;
    public Text percentTime;

    public Text projectNameLabel;
    public Text projectIDLabel;

    private Model theModel;

    public void setModelAndView(Model theModel, View view) {
        this.theModel = theModel;

        // Add a ChangeListener to the ScrollPane's widthProperty
        // This code was created with the help of https://stackoverflow.com/questions/16606162/javafx-how-to-get-the-scrollbars-of-a-scrollpane
        vBoxTimeSpent.widthProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // Call initializeComponents() when the ScrollPane's width change

                initializeComponents();

                // Remove the listener after the first layout pass to avoid multiple calls
                vBoxTimeSpent.widthProperty().removeListener(this);
            }
        });

    }

    private void initializeComponents() {
        // Get the current projects activity list
        // For each activity in the list, create a new HBox with the activity name and ID
        // Add the HBox to the VBox
        vBoxUserID.getChildren().clear();
        vBoxTimeSpent.getChildren().clear();
        /*Project project = SoftwareApp.getTheModel().getCurrentProject();
        for (int i = 0; i < theModel.getActivityList().size(); i++) {
            Text userID = new Text(theModel.getActivityList().get(i).getUserID());
            Text timeSpent = new Text(theModel.getActivityList().get(i).getTimeSpent());
            vBoxUserID.getChildren().add(userID);
            vBoxTimeSpent.getChildren().add(timeSpent);
        }*/
    }

    public void setActvityName(String name) {
        activityName.setText(name);
    }

    public void setActivityIDLabel(String name) {
        activityID.setText(name);
    }

    public void setProjectIDLabel(String projectID) {
        projectIDLabel.setText(projectID);
        projectNameLabel.setText(Objects.requireNonNull(SoftwareApp.getProject(projectID)).getProjectName());

        endWeek.setText(Objects.requireNonNull(SoftwareApp.getProject(projectID)).getActivity(activityID.getText()).getEndWeek());
        startWeek.setText(Objects.requireNonNull(SoftwareApp.getProject(projectID)).getActivity(activityID.getText()).getStartWeek());
        allocatedTime.setText(Objects.requireNonNull(SoftwareApp.getProject(projectID)).getActivity(activityID.getText()).getWeeks());
    }

    public void returnToFrontPage(ActionEvent actionEvent) {
    }
}