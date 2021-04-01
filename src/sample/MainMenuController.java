package sample;

/**
 The MainMenuController class defines the methods associated with the MainMenu.fxml GUI file.
 The public methods define the actions performed when buttons are clicked in the GUI application.
 An currentOrders and storeOrders arraylists are created and the methods interact with this arraylist to add, remove, or
 manipulate the Order and StoreOrders data given by the user in the GUI application.

 @author German Munguia, Sukhjit Singh
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML
    Label messageLabel;

    int orderNumber = Constants.FIRST_ORDER;

    //StoreOrders object which will hold all orders made by user
    StoreOrders storeOrders = new StoreOrders();

    //Order object which will hold the current order being made by the user
    Order currentOrder = new Order(orderNumber);

    /**
     Loads the OrderingCoffee.fxml file and generates a GUI scene by running the associated code.
     The initial GUI application size is 399 pixels wide and 213 pixels long.
     A listener is applied to the OrderingDonutController object to obtain the Coffee Class Object from the child
     GUI and update the currentOrders arraylist in this Class.
     @param actionEvent associated with the clicking of the Order Coffee button
     */
    public void handleCoffeeOrder(ActionEvent actionEvent) throws IOException {
        messageLabel.setText("");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderingCoffee.fxml"));
        Parent root = loader.load();

        OrderingCoffeeController coffeeController = loader.getController();
        coffeeController.selectedThingProperty().addListener((observableValue, coffee, t1) -> {
            currentOrder.add(coffeeController.getCoffee());
            messageLabel.setText("Coffee Successfully Added to Order");
        });

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Order Coffee");
        window.setScene(new Scene(root, 399 , 213));
        window.showAndWait();
    }

    /**
     Loads the OrderingDonuts.fxml file and generates a GUI scene by running the associated code.
     The initial GUI application size is 600 pixels wide and 475 pixels long.
     A listener is applied to the OrderingDonutsController object to obtain the Donut arraylist from the child
     GUI and update the currentOrders arraylist in this Class.
     @param actionEvent associated with the clicking of the Order Donut button
     */
    public void handleDonutOrder(ActionEvent actionEvent) throws IOException {
        messageLabel.setText("");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderingDonuts.fxml"));
        Parent root = loader.load();

        OrderingDonutsController donutsController = loader.getController();
        donutsController.selectedThingProperty().addListener((observableValue, selectedDonutList, t1) -> {
            for (Donut donut : donutsController.getDonutList()){
                currentOrder.add(donut);
                messageLabel.setText("Donut(s) Successfully Added to Order");
            }
        });

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Order Donut");
        window.setScene(new Scene(root, 600, 475));
        window.showAndWait();
    }

    /**
     Loads the CurrentOrderDetail.fxml file and generates a GUI scene by running the associated code.
     The initial GUI application size is 455 pixels wide and 400 pixels long.
     A listener is applied to the CurrentOrderDetailController object to obtain the Order Class Object from the child
     GUI and update the storeOrders arraylist in this Class.
     A new Order object is generated after the current order is placed by the user.
     @param actionEvent associated with the clicking of the View/Edit Current Order button
     */
    public void handleCurrentOrder(ActionEvent actionEvent) throws IOException {
        messageLabel.setText("");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("CurrentOrderDetail.fxml"));
        Parent root = loader.load();

        CurrentOrderDetailController currentOrderDetailController = loader.getController();
        currentOrderDetailController.receiveMainMenuCurrentOrder(currentOrder);

        //When Place Order button is pressed, the modified order is sent
        currentOrderDetailController.selectedThingProperty().addListener((observableValue, currentOrder, t1) -> {
            //Order is complete and can be added to store orders list
            storeOrders.add(currentOrderDetailController.getCurrentOrder());
            messageLabel.setText("Order Successfully Placed");
            orderNumber++;
            //Create a new store order with incremented order number
            this.currentOrder = new Order(orderNumber);
        });

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Current Order Detail");
        window.setScene(new Scene(root, 455, 400));
        window.showAndWait();
    }

    /**
     Loads the StoreOrdersPage.fxml file and generates a GUI scene by running the associated code.
     The initial GUI application size is 455 pixels wide and 400 pixels long.
     @param actionEvent associated with the clicking of the View/Edit All Store Orders button
     */
    public void handleStoreOrders(ActionEvent actionEvent) throws IOException {
        messageLabel.setText("");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("StoreOrdersPage.fxml"));
        Parent root = loader.load();

        StoreOrdersPageController storeOrdersController = loader.getController();
        storeOrdersController.receiveMainMenuStoreOrder(storeOrders);

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Store Orders");
        window.setScene(new Scene(root, 455, 400));
        window.showAndWait();
    }

}
