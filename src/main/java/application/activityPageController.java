package application;


import app.SoftwareApp;
import domain.ActivityTimeSheet;
import domain.Project;
import domain.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import javafx.scene.text.Text;


import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class activityPageController {

    public Text activityIDLabel;
    public Text activityNameLabel;

    public Text projectNameLabel;
    public Text projectIDLabel;
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
    public Model model;


    public void setModelAndView(Model model, View view) {
        this.view = view;
        this.model = model;

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
            int timeSpent = user.getTimeSpentOnActivity(activityIDLabel.getText());
            if (timeSpent > 0) {
                addUserTimeToVBox(user.getUserId(), timeSpent, user);
            }
        }
    }

    private void update() {
        vBoxUserID.getChildren().clear();
        vBoxTimeSpent.getChildren().clear();
        vBoxButtons.getChildren().clear();

        Project.Activities activity = Objects.requireNonNull(SoftwareApp.getProject(projectIDLabel.getText())).getActivity(activityIDLabel.getText());
        activityIDLabel.setText(String.valueOf(activity.getActivityId()));
        activityNameLabel.setText(activity.getActivityName());
        startWeek.setText(String.valueOf(activity.getStartWeek()));
        endWeek.setText(String.valueOf(activity.getEndWeek()));
        allocatedTime.setText(String.valueOf(activity.getWeeks()));

        for (User user : SoftwareApp.UserList) {
            int timeSpent = user.getTimeSpentOnActivity(activityIDLabel.getText());
            if (timeSpent > 0) {
                addUserTimeToVBox(user.getUserId(), timeSpent, user);
            }
        }
    }



    private void addUserTimeToVBox(String userId, int timeSpent, User user) {
        Text userIdText = new Text(userId);
        ActivityTimeSheet activity = new ActivityTimeSheet("activityId", 5, LocalDate.now());
        String dateAndHours = activity.getDateAndHours();
        Text timeSpentText = new Text(timeSpent + " hours\n" + dateAndHours);

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
                user.editTimeSpent(activityIDLabel, oldTimeSpent, newTimeSpent);
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
            user.removeTimeSpent(activityIDLabel, timeSpent);
            update();
        }
    }


    public void setActivityName(String name) {
        activityNameLabel.setText(name);
    }

    public void setActivityIDLabel(String name) {
        activityIDLabel.setText(name);
    }

    public void setProjectIDLabel(String projectID) {
        projectIDLabel.setText(projectID);
        projectNameLabel.setText(Objects.requireNonNull(SoftwareApp.getProject(projectID)).getProjectName());

        endWeek.setText(Objects.requireNonNull(SoftwareApp.getProject(projectID)).getActivity(activityIDLabel.getText()).getEndWeek());
        startWeek.setText(Objects.requireNonNull(SoftwareApp.getProject(projectID)).getActivity(activityIDLabel.getText()).getStartWeek());
        allocatedTime.setText(Objects.requireNonNull(SoftwareApp.getProject(projectID)).getActivity(activityIDLabel.getText()).getWeeks());
    }

    @FXML
    protected void returnProjectPage() {
        view.showProjectPage(projectIDLabel.getText(),projectNameLabel.getText());
    }

    public void updateActivityInfoAction() {
        Project.Activities activity = Objects.requireNonNull(SoftwareApp.getProject(projectIDLabel.getText())).getActivity(activityIDLabel.getText());
        //Check if the inputs are empty
        if (activityNameLabel.getText().isEmpty() || startWeek.getText().isEmpty() || endWeek.getText().isEmpty() || allocatedTime.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Please fill in all the fields.");
            alert.showAndWait();
            return;
        }
        activity.setStartWeek(startWeek.getText());
        activity.setEndWeek(endWeek.getText());
        update();

        //Check if the data is correct
        System.out.println(activity.getStartWeek());
        System.out.println(activity.getEndWeek());
        System.out.println(activity.getWeeks());
    }

    public void registerTimeAction() {
        //Check if the input is a valid number
        if (!model.isNumeric(enterTime.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Please enter a valid number of hours.");
            alert.showAndWait();
        }else{

        int registeredTime = enterTime.getText().isEmpty() ? 0 : Integer.parseInt(enterTime.getText());
        if (registeredTime > 0) {
            SoftwareApp.getUserFromID(model.getCurrentUserID()).updateTimeSheet(activityIDLabel.getText(), registeredTime, LocalDate.now());
            update();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Please enter a valid number of hours.");
            alert.showAndWait();
        }
    }}
}