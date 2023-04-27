package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;


public class View extends Application {

    public Label stateLabel;
    private Model theModel;
    private Controller theController;

    @Override
    public void start(Stage primaryStage) {

        try {

            theModel = new Model(this);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Login-page.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            theController = (Controller) fxmlLoader.getController();
            theController.setModelAndView(theModel, this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public void showMainPage() {
        	try {
        		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Front-page.fxml"));
        		AnchorPane root = (AnchorPane) loader.load();
        		Stage stage = new Stage();
        		stage.setScene(new Scene(root));
        		stage.show();
        	} catch (Exception e) {
        		e.printStackTrace();
        	}

    }
    public static void main(String[] args){
        launch(args);
    }
}
