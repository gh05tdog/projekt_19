package application;


import app.CSVgenerator;
import app.SoftwareApp;
import domain.Project;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class projectPageController {

    public Label projectNameLabel;

    public Label projectIDLabel;
    public CSVgenerator csVgenerator;
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

            Button activityButton = new Button(activityName);
            activityButton.setMinWidth(vBoxName.getWidth());
            activityButton.maxWidthProperty().bind(vBoxName.widthProperty());
            activityButton.setOnAction(e -> view.showActivityPage(activity.getActivityId(), activity.getActivityName(), projectIDLabel.getText()));

            Label idLabel = new Label(activityId);
            idLabel.setMinWidth(vBoxID.getWidth());
            idLabel.maxWidthProperty().bind(vBoxID.widthProperty());
            idLabel.setPadding(new Insets(4, 4, 4, 5)); // add left padding of 10 pixels and bottom margin of 5 pixels

            Label timeLabel = new Label(activity.getTimeBudget());
            timeLabel.setMinWidth(vBoxTime.getWidth());
            timeLabel.maxWidthProperty().bind(vBoxTime.widthProperty());
            timeLabel.setPadding(new Insets(4, 4, 4, 5)); // add left padding of 10 pixels and bottom margin of 5 pixels

            vBoxName.getChildren().add(activityButton);
            vBoxID.getChildren().add(idLabel);
            vBoxTime.getChildren().add(timeLabel);
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
        view.showMainPage(projectNameLabel.getText());
    }

    @FXML
    protected void manageProjectPagePressed() {
        theModel.manageProject(projectIDLabel.getText(), projectNameLabel.getText());

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
