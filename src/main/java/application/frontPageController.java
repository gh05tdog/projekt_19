package application;


import domain.Project;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;



public class frontPageController {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox projectVBox;

    private Model theModel;



    public void setModelAndView(Model theModel) {
        this.theModel = theModel;

        // Add a ChangeListener to the ScrollPane's widthProperty
        // This code was created with the help of https://stackoverflow.com/questions/16606162/javafx-how-to-get-the-scrollbars-of-a-scrollpane
        scrollPane.widthProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // Call initializeComponents() when the ScrollPane's width changes
                initializeComponents();

                // Remove the listener after the first layout pass to avoid multiple calls
                scrollPane.widthProperty().removeListener(this);
            }
        });
    }


    private void initializeComponents(){
        //Loop through all projects and add a button for each project
        for(Project project : theModel.getProjects()){
            Button projectButton = new Button(project.getProjectName());
            projectButton.setMinWidth(scrollPane.getWidth());
            projectButton.maxWidthProperty().bind(scrollPane.widthProperty());
            projectButton.setOnAction(event -> {
                // Handle project button click here
            });
            projectVBox.getChildren().add(projectButton);
        }
    }

    @FXML

    private Label userNameLabel;

    public void setUserNameLabel(String name) {
        userNameLabel.setText(name);
    }


    @FXML
    protected void createUserPressed() {
        theModel.createUser();
    }

    @FXML
    protected void createProjectPressed() {
        theModel.addProjectPage();
    }


}


