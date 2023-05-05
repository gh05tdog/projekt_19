package application;

import app.SoftwareApp;
import domain.Project;
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
        }
    }


        public void showMainPage(String name) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Front-page.fxml"));
                AnchorPane root = loader.load();
                Stage frontpage = new Stage();
                this.frontpage = new Stage();
                frontpage.setScene(new Scene(root));

                // Get the new controller instance for the Front-page.fxml file
                frontPageController frontPageController = loader.getController();
                // Set the model and view for the new controller instance
                frontPageController.setModelAndView(theModel);
                // Set the username label for the new controller instance
                frontPageController.setUserNameLabel(name);
                // Close the login window
                frontPageController.setUserActivityCount(" ");

                frontPageController.setUserProjectCount("uno");

                frontPageController.setGeneralActivityCount(String.valueOf(Project.getNumberOfActivities()));

                frontPageController.setGeneralProjectCount(String.valueOf(SoftwareApp.getNumberOfProject()));

                login.close();
                frontpage.show();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    public void showAddUser (){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Add-User.fxml"));
            Pane root = loader.load();
            Stage AddUser = new Stage();
            AddUser.setScene(new Scene(root));

            // Get the new controller instance for the Front-page.fxml file
            addUserController addUserController = loader.getController();
            // Set the model and view for the new controller instance
            addUserController.setModelAndView(theModel, this);
            // Set the username label for the new controller instance

            frontpage.close();
            AddUser.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main (String[]args){
                    launch(args);
                }

}
