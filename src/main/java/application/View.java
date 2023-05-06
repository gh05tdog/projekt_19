package application;

import domain.Project;
import domain.UserAlreadyExistsException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class View extends Application {

    private Model theModel;

    private Stage login;

    private Stage frontpage;

    private Stage addUser;

    private Stage addProject;

    private Stage projectPage;

    @Override
    public void start(Stage primaryStage) {

        try {
            theModel = new Model(this);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Login-page.fxml"));
            AnchorPane root1 = fxmlLoader.load();

            //Load the controller
            loginController theController = fxmlLoader.getController();
            theController.setModelAndView(theModel);

            login = new Stage();
            login.setScene(new Scene(root1));
            login.show();

        } catch (Exception e) {
            e.printStackTrace();
        } catch (UserAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
    }


        public void showMainPage(String name) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Front-page.fxml"));
                AnchorPane root = loader.load();
                this.frontpage = new Stage();
                frontpage.setScene(new Scene(root));

                // Get the new controller instance for the Front-page.fxml file
                frontPageController frontPageController = loader.getController();
                // Set the model and view for the new controller instance
                frontPageController.setModelAndView(theModel);
                // Set the username label for the new controller instance
                frontPageController.setUserNameLabel(name);
                // Close the login window

                login.close();

                if (addUser != null) {
                    addUser.close();
                }
                if (addProject != null) {
                    addProject.close();
                }
                if (projectPage != null) {
                    projectPage.close();
                }

                frontpage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    public void showAddUser (){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Add-User.fxml"));
            Pane root = loader.load();
            this.addUser = new Stage();
            addUser.setScene(new Scene(root));

            // Get the new controller instance for the Front-page.fxml file
            addUserController addUserController = loader.getController();
            // Set the model and view for the new controller instance
            addUserController.setModelAndView(theModel, this);
            // Set the username label for the new controller instance

            frontpage.close();
            addUser.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAddProject() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Add-Project.fxml"));
            Pane root = loader.load();
            this.addProject = new Stage();
            addProject.setScene(new Scene(root));

            // Get the new controller instance for the Front-page.fxml file
            addProjectController addProjectController = loader.getController();
            // Set the model and view for the new controller instance
            addProjectController.setModelAndView(theModel, this);
            // Set the username label for the new controller instance

            frontpage.close();
            addProject.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showProjectPage (String projectID, String nameExtract) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project-page.fxml"));
            AnchorPane root = loader.load();
            this.projectPage = new Stage();
            projectPage.setScene(new Scene(root));

            // Get the new controller instance for the Front-page.fxml file
            projectPageController projectPageController = loader.getController();
            // Set the model and view for the new controller instance
            projectPageController.setModelAndView(theModel, this);
            // Set the project for the new controller instance
            projectPageController.setProjectNameLabel(nameExtract);
            // Set the username label for the new controller instance
            projectPageController.setProjectIDLabel(projectID);
            // Close the login window

            frontpage.close();

            projectPage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public static void main (String[]args){
        launch(args);
    }

}
