package application;


import app.SoftwareApp;
import domain.Project;
import domain.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;



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
                initializeComponents();

                // Remove the listener after the first layout pass to avoid multiple calls
                scrollPaneUser.widthProperty().removeListener(this);
            }
        });

        scrollPaneGlobal.widthProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // Call initializeComponents() when the ScrollPane's width changes
                initializeComponents();

                // Remove the listener after the first layout pass to avoid multiple calls
                scrollPaneGlobal.widthProperty().removeListener(this);
            }
        });

    }

// The code seen below is created by chatGPT our lord and saviour
    private void initializeComponents(){

        //Loop through all projects and add a button for each project

        for(Project project : theModel.getProjects()){
        // Stack all buttons in the user scrollpane
      //      if (project.userInProject(SoftwareApp.getUserFromID(loginController.passwordField.getText())))
            {

                Button projectButtonUser = new Button(project.getProjectName() + " - " + project.getProjectId());
                projectButtonUser.setMinWidth(scrollPaneUser.getWidth());
                projectButtonUser.maxWidthProperty().bind(scrollPaneUser.widthProperty());
                projectButtonUser.setOnAction(event -> {

                    String buttonText = projectButtonUser.getText();

                    String IDExtract = buttonText.substring(buttonText.length() - 5);
                    System.out.println(IDExtract);
                    String nameExtract = buttonText.substring(0, buttonText.length() - 8);
                    theModel.projectPagePage(IDExtract, nameExtract);


                });
                projectVBoxUser.getChildren().add(projectButtonUser);
            }
        // Stack all buttons in the global scrollpane

            Button projectButtonGlobal = new Button(project.getProjectName() + " - " + project.getProjectId());

            projectButtonGlobal.setMinWidth(scrollPaneGlobal.getWidth());
            projectButtonGlobal.maxWidthProperty().bind(scrollPaneGlobal.widthProperty());

            projectButtonGlobal.setOnAction(event -> {

                String buttonText = projectButtonGlobal.getText();

                String IDExtract = buttonText.substring(buttonText.length()-5);
                System.out.println(IDExtract);
                String nameExtract = buttonText.substring(0,buttonText.length()-8);
                theModel.projectPagePage(IDExtract, nameExtract);


            });


            projectVboxGlobal.getChildren().add(projectButtonGlobal);
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


