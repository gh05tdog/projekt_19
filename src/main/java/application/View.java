package application;

import domain.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;


public class View extends Application {

    private Model theModel;


    private Stage login;

    private PasswordField passwordField;


    @Override
    public void start(Stage primaryStage) {

        try {

            // Add a user to the database
            User.createUser("Test", "test");

            theModel = new Model(this);


            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Login-page.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            //Load the controller
            loginController theController = (loginController) fxmlLoader.getController();
            theController.setModelAndView(theModel);
            //passwordField = theController.getPasswordField();
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
                AnchorPane root = (AnchorPane) loader.load();
                Stage frontpage = new Stage();
                frontpage.setScene(new Scene(root));

                // Get the new controller instance for the Front-page.fxml file
                frontPageController frontPageController = (frontPageController) loader.getController();
                // Set the model and view for the new controller instance
                frontPageController.setModelAndView(theModel, this);
                // Set the username label for the new controller instance
                frontPageController.setUserNameLabel(name);

                frontpage.show();
                login.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    public static void main(String[] args){
        launch(args);
    }


}
