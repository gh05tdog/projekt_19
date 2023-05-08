package application;


import app.SoftwareApp;
import domain.ActivityTimeSheet;
import domain.Project;
import domain.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.text.Text;


import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class activityPageController {
    //Fields og constructor af Martin
    public Text activityIDLabel;
    public Text activityNameLabel;

    public Text projectNameLabel;
    public Text projectIDLabel;
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
    public Model model;

    @FXML
    public TextField date;

    public void setModelAndView(Model model, View view) {
        this.view = view;
        this.model = model;
        date.setText(String.valueOf(LocalDate.now()));

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
    //Lavet af Marcus
    private void initializeComponents() {
        vBoxUserID.getChildren().clear();
        vBoxTimeSpent.getChildren().clear();

        for (User user : SoftwareApp.UserList) {
            float timeSpent = user.getTimeSpentOnActivity(activityIDLabel.getText());
            if (timeSpent > 0) {
                addUserTimeToVBox(user.getUserId(), timeSpent, user);
            }
        }
    }
    //Lavet af Oliver
    private void update() {
        vBoxUserID.getChildren().clear();
        vBoxTimeSpent.getChildren().clear();

        Project.Activities activity = Objects.requireNonNull(SoftwareApp.getProject(projectIDLabel.getText())).getActivity(activityIDLabel.getText());
        activityIDLabel.setText(String.valueOf(activity.getActivityId()));
        activityNameLabel.setText(activity.getActivityName());
        startWeek.setText(String.valueOf(activity.getStartWeek()));
        endWeek.setText(String.valueOf(activity.getEndWeek()));
        allocatedTime.setText(String.valueOf(activity.getAllocatedTime()));
        setProcents();

        for (User user : SoftwareApp.UserList) {
            float timeSpent = user.getTimeSpentOnActivity(activityIDLabel.getText());
            if (timeSpent > 0) {
                addUserTimeToVBox(user.getUserId(), timeSpent, user);
            }
        }
    }

    //Lavet af Oliver
    private void addUserTimeToVBox(String userId, float timeSpent, User user) {

        for (ActivityTimeSheet activityTimeSheet : user.timeSheet) {
            if (activityTimeSheet.getActivityId().equals(activityIDLabel.getText())) {

                String dateAndHours = activityTimeSheet.getDateAndHours();
                activityTimeSheet.addHours(timeSpent, LocalDate.parse(date.getText()));
                Text userIdText = new Text(userId);
                Text timeSpentText = new Text(timeSpent + " hours\n" + dateAndHours);

                Button editButton = new Button("Edit");
                editButton.setOnAction(e -> editTimeEntry(user, timeSpent));
                editButton.setMaxWidth(Double.MAX_VALUE);

                Button removeButton = new Button("Remove");
                removeButton.setOnAction(e -> removeTimeEntry(user, timeSpent));
                removeButton.setMaxWidth(Double.MAX_VALUE);

                VBox buttonsBox = new VBox(editButton, removeButton);
                buttonsBox.setSpacing(5);

                HBox userInfoRow = new HBox(buttonsBox, userIdText);
                userInfoRow.setSpacing(10);

                vBoxUserID.getChildren().add(userInfoRow);
                vBoxTimeSpent.getChildren().add(timeSpentText);
                return;
            }
        }
        ActivityTimeSheet activity = new ActivityTimeSheet(activityIDLabel.getText(), 5, LocalDate.parse(date.getText()));
        String dateAndHours = activity.getDateAndHours();

        Text userIdText = new Text(userId);
        Text timeSpentText = new Text(timeSpent + " hours\n" + dateAndHours);

        Button editButton = new Button("Edit");
        editButton.setOnAction(e -> editTimeEntry(user, timeSpent));
        editButton.setMaxWidth(Double.MAX_VALUE);

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(e -> removeTimeEntry(user, timeSpent));
        removeButton.setMaxWidth(Double.MAX_VALUE);

        VBox buttonsBox = new VBox(editButton, removeButton);
        buttonsBox.setSpacing(5);

        HBox userInfoRow = new HBox(buttonsBox, userIdText);
        userInfoRow.setSpacing(10);

        vBoxUserID.getChildren().add(userInfoRow);
        vBoxTimeSpent.getChildren().add(timeSpentText);
    }

    //Lavet af Oliver
    private void editTimeEntry(User user, float oldTimeSpent) {
        // Show a dialog to edit the time entry
        TextInputDialog dialog = new TextInputDialog(String.valueOf(oldTimeSpent));
        dialog.setTitle("Edit Time Entry");
        dialog.setHeaderText("Edit the time spent for this user:");
        dialog.setContentText("Enter the new time spent (in hours):");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(timeString -> {
            try {
                float newTimeSpent = Float.parseFloat(timeString);
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
    //Lavet af Oliver
    private void removeTimeEntry(User user, float timeSpent) {
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

    //Lavet af Oliver
    public void setActivityName(String name) {
        activityNameLabel.setText(name);
    }
    //Lavet af Oliver
    public void setActivityIDLabel(String name) {
        activityIDLabel.setText(name);
    }
    //Lavet af Oliver
    public void setProjectIDLabel(String projectID) {
        projectIDLabel.setText(projectID);
        projectNameLabel.setText(Objects.requireNonNull(SoftwareApp.getProject(projectID)).getProjectName());

        endWeek.setText(Objects.requireNonNull(SoftwareApp.getProject(projectID)).getActivity(activityIDLabel.getText()).getEndWeek());
        startWeek.setText(Objects.requireNonNull(SoftwareApp.getProject(projectID)).getActivity(activityIDLabel.getText()).getStartWeek());
        allocatedTime.setText(Objects.requireNonNull(SoftwareApp.getProject(projectID)).getActivity(activityIDLabel.getText()).getAllocatedTime());
    }
    //Lavet af Martin
    @FXML
    protected void returnProjectPage() {
        view.showProjectPage(projectIDLabel.getText(),projectNameLabel.getText());
    }
    //Lavet af Martin
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
        activity.setallocatedTime(allocatedTime.getText());
        setProcents();
        update();
    }
    //Lavet af Martin
    public void registerTimeAction() {
        // Check if the input is a valid number
        if (!model.isNumeric(enterTime.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Please enter a valid number of hours.");
            alert.showAndWait();
        } else {
            float registeredTime = enterTime.getText().isEmpty() ? 0 : Float.parseFloat(enterTime.getText());
            if (registeredTime > 0) {
                User currentUser = SoftwareApp.getUserFromID(model.getCurrentUserID());


                //currentUser.updateTimeSheet(activityIDLabel.getText(), registeredTime, LocalDate.now());

                // Update the percentTime in the corresponding Project.Activities object
                Project.Activities activity = Objects.requireNonNull(SoftwareApp.getProject(projectIDLabel.getText())).getActivity(activityIDLabel.getText());
                activity.logHours(currentUser, registeredTime, LocalDate.parse(date.getText()));
                setProcents();
                update();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("Please enter a valid number of hours.");
                alert.showAndWait();
            }
        }
    }
    //Lavet af Martin
    public void setProcents() {
        Project.Activities activity = Objects.requireNonNull(SoftwareApp.getProject(projectIDLabel.getText())).getActivity(activityIDLabel.getText());
        percentTime.setText(String.format("%.2f%%", activity.getPercentTime()));
    }

}