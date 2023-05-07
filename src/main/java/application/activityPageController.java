package application;


import app.SoftwareApp;
import domain.ActivityTimeSheet;
import domain.Project;
import domain.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import javafx.scene.text.Text;


import java.util.Objects;
import java.util.Optional;

public class activityPageController {

    public Text activityID;
    public Text activityName;
    public Button returnToFrontPage;
    public VBox vBoxUserID;
    public VBox vBoxTimeSpent;
    public VBox vBoxButtons;
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

        for (User user : SoftwareApp.UserList) {
            int timeSpent = user.getTimeSpentOnActivity(activityID.getText());
            if (timeSpent > 0) {
                addUserTimeToVBox(user.getUserId(), timeSpent, user);
            }
        }
    }

    private void update() {
        vBoxUserID.getChildren().clear();
        vBoxTimeSpent.getChildren().clear();
        vBoxButtons.getChildren().clear();

        Project.Activities activity = Objects.requireNonNull(SoftwareApp.getProject(projectIDLabel.getText())).getActivity(activityID.getText());
        activityID.setText(String.valueOf(activity.getActivityId()));
        activityName.setText(activity.getActivityName());
        startWeek.setText(String.valueOf(activity.getStartWeek()));
        endWeek.setText(String.valueOf(activity.getEndWeek()));
        allocatedTime.setText(String.valueOf(activity.getWeeks()));

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

        Button editButton = new Button("Edit");
        editButton.setOnAction(e -> editTimeEntry(user, timeSpent));

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(e -> removeTimeEntry(user, timeSpent));

        vBoxUserID.getChildren().add(userIdText);
        vBoxTimeSpent.getChildren().add(timeSpentText);
        vBoxButtons.getChildren().addAll(editButton, removeButton);
    }

    private void editTimeEntry(User user, int oldTimeSpent) {
        // Show a dialog to edit the time entry
        TextInputDialog dialog = new TextInputDialog(String.valueOf(oldTimeSpent));
        dialog.setTitle("Edit Time Entry");
        dialog.setHeaderText("Edit the time spent for this user:");
        dialog.setContentText("Enter the new time spent (in hours):");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(timeString -> {
            try {
                int newTimeSpent = Integer.parseInt(timeString);
                user.editTimeSpent(activityID, oldTimeSpent, newTimeSpent);
                update();
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("Please enter a valid number of hours.");
                alert.showAndWait();
            }
        });
    }

    private void removeTimeEntry(User user, int timeSpent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Remove Time Entry");
        alert.setHeaderText("Are you sure you want to remove this time entry?");
        alert.setContentText("This action cannot be undone.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            user.removeTimeSpent(activityID, timeSpent);
            update();
        }
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