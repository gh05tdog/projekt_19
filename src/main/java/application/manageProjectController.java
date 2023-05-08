package application;

import app.SoftwareApp;
import app.TooManyActivities;
import app.WayTooManyActivities;
import domain.Project;
import domain.User;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import javafx.scene.text.Text;


import java.util.Objects;
import java.util.Optional;

public class manageProjectController {

    public Model model;
    public View view;

    public Text manageProjectID;

    public Text manageProjectName;

    @FXML
    public TextField addActivityName;

    @FXML
    public TextField addTimeBudget;

    @FXML
    public TextField addStartWeek;

    @FXML
    public TextField enterWeekAmount;
    public TextField userIDManager;
    @FXML
    private TextField activityUserID;

    @FXML
    private TextField activityActivityID;


    public Project project;

    @FXML
    public ScrollPane scrollPaneAvailableUsers;

    @FXML
    public VBox vBoxUserList;

    @FXML
    public Button checkUsers;

    @FXML
    public TextField activityField;

    private SoftwareApp SoftwareApp;




    public void setModelAndView(Model theModel, View view, SoftwareApp SoftwareApp) {

        this.SoftwareApp = SoftwareApp;
        this.model = theModel;
        this.view = view;

    }
    public void checkAvailableUsers() {
        vBoxUserList.getChildren().clear();

        //Check the Field is not empty
        if (activityField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Activity Selected");
            alert.setContentText("Please select an activity to check available users");
            alert.showAndWait();
            return;
        }

        int Startweek = Integer.parseInt(SoftwareApp.getProject(manageProjectID.getText()).getActivity(activityField.getText()).getStartWeek());
        int endweek = Integer.parseInt(SoftwareApp.getProject(manageProjectID.getText()).getActivity(activityField.getText()).getEndWeek());


        for (User user : SoftwareApp.UserList) {
            int activities_in_week = 0;


            for (Project.Activities activities : user.getUserActivityList()) {


                System.out.println(activities.getActivityName());


                if (Integer.parseInt(activities.getStartWeek()) >= Startweek && Integer.parseInt(activities.getStartWeek()) <= endweek) {
                    activities_in_week += 1;
                }
            }

                if (activities_in_week < 10) {
                    {
                        Button projectButtonUser = new Button(user.getName());
                        projectButtonUser.setMinWidth(vBoxUserList.getWidth());
                        projectButtonUser.maxWidthProperty().bind(vBoxUserList.widthProperty());
                        vBoxUserList.getChildren().add(projectButtonUser);
                    }

                }
            }
        }

    public void setProjectID(String projectID) {
        manageProjectID.setText(projectID);

    }
    public void setProjectName(String projectName){
        manageProjectName.setText(projectName);
    }

    public void returnProjectPagePressed() {
        view.showProjectPage(manageProjectID.getText(),manageProjectName.getText());

    }

    public void addActivityToProject() {
        project = SoftwareApp.getProject(manageProjectID.getText());
        assert project != null;
        project.addActivity(addActivityName.getText(),addTimeBudget.getText(),enterWeekAmount.getText(),addStartWeek.getText());

    }

    public void addActivityToUser () {
        try {
            SoftwareApp.assignActivityToUser(activityUserID.getText(),manageProjectID.getText(),activityActivityID.getText());
        } catch (TooManyActivities tooManyActivities) {
            //Give a warning to the user that the user has too many activities but ask if they want to continue
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("User has too many activities");
            alert.setContentText("Do you want to continue?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Objects.requireNonNull(SoftwareApp.getProject(manageProjectID.getText())).assignActivityToUser(activityUserID.getText(), activityActivityID.getText());
            }
        }catch (WayTooManyActivities wayTooManyActivities){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("User has way too many activities");
            alert.setContentText("Please assign the activity to another user");
            alert.showAndWait();
        }

    }
    public void assignProjectManAction() {
        User user = SoftwareApp.getUserFromID(userIDManager.getText());

        if (user != null) {
            Objects.requireNonNull(SoftwareApp.getProject(manageProjectID.getText())).setProjectManager(user);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("User not found");
            alert.setContentText("Please enter a valid user ID");
            alert.showAndWait();
        }
    }
}
