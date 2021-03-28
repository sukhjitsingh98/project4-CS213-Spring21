package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


/*
Right now the issue is that once coffee is chosen, the user clicks add to order button in coffee GUI. The coffee data
 is passed to this controller and saved in a local method version of the currentOrder object. The problem is that it
 doesnt save in the global currentOrder object meaning that accessing any changes outside of the method results in
 nothing. The object is not updated with the new data.
 */
public class MainMenuController {

    int orderNumber = Constants.FIRST_ORDER;
    StoreOrders storeOrders = new StoreOrders();
    Order currentOrder = new Order(orderNumber);

    //Create popup window for ordering coffee
    public void handleCoffeeOrder(ActionEvent actionEvent) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("OrderingCoffee.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderingCoffee.fxml"));
        Parent root = loader.load();

        OrderingCoffeeController coffeeController = loader.getController();
        coffeeController.selectedThingProperty().addListener((observableValue, coffee, t1) -> {
            currentOrder.add(coffeeController.getCoffee());
            System.out.println(currentOrder.getItems().get(0).getItemPrice()); //For debugging
        });

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Order Coffee");
        window.setScene(new Scene(root, 600, 475));
        window.showAndWait();
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

        //USE THIS TO DEBUG
        System.out.println(currentOrder.getItems().get(0).getItemPrice());

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Current Order Detail");
        window.setScene(new Scene(root, 600, 475));
        window.show();
    }

    public void addOrderToStore(Object obj){
        storeOrders.add(obj);
    }

}
