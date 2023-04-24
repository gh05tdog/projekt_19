package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class View extends Application {

    public void start(Stage primaryStage){

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/FXML/Login-page.fxml"));

            AnchorPane root = loader.load();


        }catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args){
        launch(args);
    }



}
