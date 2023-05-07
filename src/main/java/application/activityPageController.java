package application;


import app.SoftwareApp;
import domain.ActivityTimeSheet;
import domain.Project;
import domain.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    public View view;

    public Text projectNameLabel;
    public Text projectIDLabel;

    private Model theModel;

    public void setModelAndView(Model theModel, View view) {
        this.theModel = theModel;
        this.view = view;

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
        vBoxUserID.getChildren().clear();
        vBoxTimeSpent.getChildren().clear();

        Project project = SoftwareApp.getProject(projectIDLabel.getText());
        Project.Activities activity = Objects.requireNonNull(project).getActivity(activityID.getText());

        for (User user : SoftwareApp.UserList) {
            int timeSpent = user.getTimeSpentOnActivity(activityID.getText());
            if (timeSpent > 0) {
                addUserTimeToVBox(user.getUserId(), timeSpent, user);
            }
        }
    }

    private void addUserTimeToVBox(String userId, int timeSpent, User user) {
        Text userIdText = new Text(userId);
        String timeLogString = ActivityTimeSheet.getDateAndHours();
        Text timeSpentText = new Text(timeSpent + " hours\n" + timeLogString);

        vBoxUserID.getChildren().add(userIdText);
        vBoxTimeSpent.getChildren().add(timeSpentText);
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

    @FXML
    protected void returnProjectPage() {
        view.showProjectPage(projectIDLabel.getText(),projectNameLabel.getText());
    }
}