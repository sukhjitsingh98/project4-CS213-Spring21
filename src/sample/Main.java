package sample;

/**
 The main class loads and runs the MainMenu FXML file and MainMenuController class code.
 The JavaFX library is loaded and is used to load and run the GUI application associated with the MainMenu.FXML file.
 @author German Munguia, Sukhjit Singh
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     Loads the MainMenu.fxml file and generates a GUI scene by running the associated code.
     The initial GUI application size is 400 pixels wide and 375 pixels long.
     @param primaryStage the stage associated with the MainMenu.fxml file
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        primaryStage.setTitle("Coffee Shop Main Menu");
        primaryStage.setScene(new Scene(root, 400, 375));
        primaryStage.show();
    }

    /**
     Calls the launch() method passing in the args string array as a parameter.
     @param args Unused parameter
     */
    public static void main(String[] args) {
        launch(args);
    }
}
