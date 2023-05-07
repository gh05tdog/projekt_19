package application;


import app.CSVgenerator;
import app.SoftwareApp;
import domain.Project;
import domain.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class projectPageController {

    public TextField projectNameField;

    public Label projectIDLabel;
    public Button manageProject;
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

    public CSVgenerator csVgenerator;
    @FXML
    private TextField getDate;

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
        Project currentProject = SoftwareApp.getProject(projectIDLabel.getText());
        assert currentProject != null;


        for (int i = 0; i < currentProject.getActivityList().size(); i++) {
            Project.Activities activity = currentProject.getActivityList().get(i);
            final String activityId = activity.getActivityId();
            final String activityName = activity.getActivityName();

            Button activityButtonName = new Button();
            activityButtonName.setMinWidth(vBoxName.getWidth());
            activityButtonName.maxWidthProperty().bind(vBoxName.widthProperty());
            activityButtonName.setText(activityName);
            activityButtonName.setId(activityId); // set the ID of the button to the activity ID

            activityButtonName.setOnAction(e -> {
                
                view.showActivityPage(activity.getActivityId(), activity.getActivityName(), projectIDLabel.getText()); // use activity's properties directly
            });


            Button activityButtonId = new Button();
            activityButtonId.setMinWidth(vBoxName.getWidth());
            activityButtonId.maxWidthProperty().bind(vBoxName.widthProperty());
            activityButtonId.setText(activityId);

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
        projectNameField.setText(name);
    }

    @FXML
    protected void returnFrontPage() {
        view.showMainPage(SoftwareApp.getUserFromID(theModel.getCurrentUserID()).getName());
    }

    @FXML
    protected void manageProjectPagePressed() {
        User manger = Objects.requireNonNull(SoftwareApp.getProject(projectIDLabel.getText())).getManager();
        if(manger == null || manger.getUserId().equals(theModel.getCurrentUserID())) {
            theModel.manageProject(projectIDLabel.getText(), projectNameField.getText());
        }else {
            view.showAlert("You are not the manager of this project");
        }

    }

    public void generateReportPressed() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date date = dateFormat.parse(getDate.getText());
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      int week = calendar.get(Calendar.WEEK_OF_YEAR);
      csVgenerator = new CSVgenerator(SoftwareApp.getProject(projectIDLabel.getText()));
      csVgenerator.saveCSVReportToFile(week);
      

    }
}
