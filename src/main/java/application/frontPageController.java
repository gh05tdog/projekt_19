package application;


import app.SoftwareApp;
import domain.Project;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;


public class frontPageController {

    @FXML
    private ScrollPane scrollPaneUser;

    @FXML
    private ScrollPane scrollPaneGlobal;

    @FXML
    private VBox projectVBoxUser;

    private Model theModel;

    @FXML
    private VBox projectVboxGlobal;



    public void setModelAndView(Model theModel) {
        this.theModel = theModel;

        // Add a ChangeListener to the ScrollPane's widthProperty
        // This code was created with the help of https://stackoverflow.com/questions/16606162/javafx-how-to-get-the-scrollbars-of-a-scrollpane
        scrollPaneUser.widthProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // Call initializeComponents() when the ScrollPane's width changes
                initializeComponentsUser();

                // Remove the listener after the first layout pass to avoid multiple calls
                scrollPaneUser.widthProperty().removeListener(this);
            }
        });
        scrollPaneGlobal.widthProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // Call initializeComponents() when the ScrollPane's width changes
                initializeComponentsGlobal();

                // Remove the listener after the first layout pass to avoid multiple calls
                scrollPaneGlobal.widthProperty().removeListener(this);
            }
        });
    }

// The code seen below is created by chatGPT our lord and saviour
    private void initializeComponentsUser() {

        //Loop through all projects and add a button for each project
        for (Project project : theModel.getProjects()) {
            System.out.println(project.getWorkersList(project.getProjectId()));
            System.out.println(theModel.getCurrentUserID());
            if (project.userInProject(SoftwareApp.getUserFromID(theModel.getCurrentUserID()))) {

                System.out.println(SoftwareApp.getUserFromID(theModel.getCurrentUserID()).getUserId());

                {
                    Button projectButtonUser = new Button(project.getProjectName() + " - " + project.getProjectId());
                    projectButtonUser.setMinWidth(scrollPaneUser.getWidth());
                    projectButtonUser.maxWidthProperty().bind(scrollPaneUser.widthProperty());
                    projectButtonUser.setOnAction(event -> {

                        String buttonText1 = projectButtonUser.getText();

                        String IDExtract1 = buttonText1.substring(buttonText1.length() - 5);
                        String nameExtract1 = buttonText1.substring(0, buttonText1.length() - 8);
                        theModel.projectPagePage(IDExtract1, nameExtract1);

                    });
                    projectVBoxUser.getChildren().add(projectButtonUser);
                }
            }
        }
    }
    public void initializeComponentsGlobal () {
     for(Project project : theModel.getProjects()) {
         // Stack all buttons in the global scrollpane

         Button projectButtonGlobal = new Button(project.getProjectName() + " - " + project.getProjectId());

         projectButtonGlobal.setMinWidth(scrollPaneGlobal.getWidth());
         projectButtonGlobal.maxWidthProperty().bind(scrollPaneGlobal.widthProperty());

         projectButtonGlobal.setOnAction(event -> {

             String buttonText2 = projectButtonGlobal.getText();

             String IDExtract2 = buttonText2.substring(buttonText2.length() - 5);
             System.out.println(IDExtract2);
             String nameExtract2 = buttonText2.substring(0, buttonText2.length() - 8);
             theModel.projectPagePage(IDExtract2, nameExtract2);


         });

         projectVboxGlobal.getChildren().add(projectButtonGlobal);


     }
    }

    @FXML
    private Label nameLabel;
    public void setNameLabel(String name) {
        nameLabel.setText(name);
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


