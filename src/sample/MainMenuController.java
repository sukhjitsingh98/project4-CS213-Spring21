package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    int orderNumber = Constants.FIRST_ORDER;
    StoreOrders storeOrders = new StoreOrders();
    Order currentOrder = new Order(orderNumber);

    //Create popup window for ordering coffee
    public void handleCoffeeOrder(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("OrderingCoffee.fxml"));
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Order Coffee");
        window.setScene(new Scene(root, 600, 475));
        window.show();
    }

    //Popup for ordering donuts
    public void handleDonutOrder(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("OrderingDonuts.fxml"));
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Order Donut");
        window.setScene(new Scene(root, 600, 475));
        window.show();
    }

    //Popup for ordering donuts
    public void handleCurrentOrder(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CurrentOrderDetail.fxml"));
        Parent root = loader.load();

        CurrentOrderDetailController currentOrderDetailController = loader.getController();
        currentOrderDetailController.getMainMenuCurrentOrder(currentOrder);

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Current Order Detail");
        window.setScene(new Scene(root, 600, 475));
        window.show();
    }

    //NOTE:For some reason this doesnt work
    public void addOrderItem(Object obj){
        //storeOrders.getOrder(storeOrders.getNumOrders()-1).add(obj);
        currentOrder.add(obj);
    }

    public void addOrderToStore(Object obj){
        storeOrders.add(obj);
    }

}
