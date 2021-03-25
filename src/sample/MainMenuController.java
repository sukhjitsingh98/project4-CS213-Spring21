package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    //Create popup window for odering coffee
    public void handleCoffeeOrder(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("OrderingCoffee.fxml"));
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Order Coffee");
        window.setScene(new Scene(root, 600, 275));
        window.show();
    }

    //Popup for ordering donuts
    public void handleDonutOrder(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("OrderingDonuts.fxml"));
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Order Donut");
        window.setScene(new Scene(root, 600, 275));
        window.show();
    }

}
