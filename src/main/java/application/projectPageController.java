package application;


import app.SoftwareApp;
import domain.Project;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class projectPageController {

    public Label projectNameLabel;

    public Label projectIDLabel;
    private Model theModel;

    @FXML
    private ScrollPane projectScrollPane;

    @FXML
    private VBox vBoxName;

    @FXML
    private VBox vBoxID;

    @FXML
    private VBox vBoxTime;

    private View view;

    private Project currentProject;

    public void setModelAndView(Model theModel, View view) {
        this.theModel = theModel;
        this.view = view;
        projectScrollPane.widthProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // Call initializeComponents() when the ScrollPane's width changes
                initializeComponents();

                // Remove the listener after the first layout pass to avoid multiple calls
                projectScrollPane.widthProperty().removeListener(this);
            }
        });
    }

    private void initializeComponents() {
        currentProject = SoftwareApp.getProject(projectIDLabel.getText());
        System.out.println(projectIDLabel.getText());
        System.out.println(currentProject.getProjectName());
        System.out.println(currentProject.getNumberOfActivities());



        for (int i = 0; i < currentProject.getActivityList().size(); i++) {

            Project.Activities activity = currentProject.getActivityList().get(i);
            System.out.println(activity.getActivityName());
            Button activityButtonName = new Button();
            activityButtonName.setMinWidth(vBoxName.getWidth());
            activityButtonName.maxWidthProperty().bind(vBoxName.widthProperty());
            activityButtonName.setText(activity.getActivityName());

            Button activityButtonId = new Button();
            activityButtonId.setMinWidth(vBoxName.getWidth());
            activityButtonId.maxWidthProperty().bind(vBoxName.widthProperty());
            activityButtonId.setText(activity.getActivityId());

            Button activityButtonTime = new Button();
            activityButtonTime.setMinWidth(vBoxName.getWidth());
            activityButtonTime.maxWidthProperty().bind(vBoxName.widthProperty());
            activityButtonTime.setText(activity.getTimeBudget());
            // set button properties
            vBoxName.getChildren().add(activityButtonName);
            vBoxID.getChildren().add(activityButtonId);
            vBoxTime.getChildren().add(activityButtonTime);

        }





    }

    public void setProjectIDLabel(String name) {
        projectIDLabel.setText(name);
    }

    public void setProjectNameLabel(String name) {
        projectNameLabel.setText(name);
    }

    @FXML
    protected void returnFrontPage() {
        view.showMainPage(theModel.getCurrentUser());
    }

}
