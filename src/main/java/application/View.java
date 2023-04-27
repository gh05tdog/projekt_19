package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class View extends Application {

    private Model theModel;

    private Stage login;

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
                frontpage.setScene(new Scene(root));

                // Get the new controller instance for the Front-page.fxml file
                frontPageController frontPageController = loader.getController();
                // Set the model and view for the new controller instance
                frontPageController.setModelAndView(theModel, this);
                // Set the username label for the new controller instance
                frontPageController.setUserNameLabel(name);
                // Close the login window
                login.close();
                frontpage.show();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    public static void main(String[] args){
        launch(args);
    }


}
