package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StoreOrdersPageController {

    @FXML
    ComboBox orderNumberCombobox;

    @FXML
    ListView storeOrdersListview;

    @FXML
    Label subtotalLabel;

    StoreOrders storeOrders;

    public void handleOrderSelection(ActionEvent actionEvent) {
        //due to the possible removal of orders, make sure it isn't empty
        if(orderNumberCombobox.getSelectionModel().isEmpty()) {
            return;
        }

        setStoreOrdersListview();
    }

    //delete the selected order.
    public void handleCancelOrder(ActionEvent actionEvent) {
        int orderPosition = orderNumberCombobox.getSelectionModel().getSelectedIndex();
        Order selectedOrder = storeOrders.getOrder(orderPosition);
        storeOrders.remove(selectedOrder);
        //If the removal leaves the list empty it do not call the update methods, leave everything empty as there are no orders to display.
        if(storeOrders.getNumOrders() == 0) {
            storeOrdersListview.getItems().clear();
            orderNumberCombobox.getSelectionModel().clearSelection();
            orderNumberCombobox.getItems().clear();
            subtotalLabel.setText("$0");
            return;
        }

            //If not empty, recreate the combobox.
            setOrderNumberCombobox();
            orderNumberCombobox.getSelectionModel().selectFirst();
    }

    public void handleExportOrder(ActionEvent actionEvent) {
    }

    public void receiveMainMenuCurrentOrder(StoreOrders storeOrders) {
        this.storeOrders = storeOrders;
        if(storeOrders.getNumOrders() != 0) {

            //Combobox must match the number of storeOrders.
            setOrderNumberCombobox();
            orderNumberCombobox.getSelectionModel().selectFirst();

            //make the listview match the selected order in the combobox. Also updates the price
            setStoreOrdersListview();
        }

    }

    //Count the number of orders and add them to the combobox
    public void setOrderNumberCombobox() {
        int storeOrderCount = storeOrders.getNumOrders();
        ObservableList orderNumberList = FXCollections.observableArrayList();
        for(int i = 0; i < storeOrderCount; i++) {
            orderNumberList.add(storeOrders.getOrder(i).getOrderNumber());
        }
        orderNumberCombobox.setItems(orderNumberList);
    }

    //place the MenuItems string into the listview
    public void setStoreOrdersListview(){
        //clear the list
        storeOrdersListview.getItems().clear();

        //get the selection from the orderNumber combobox.
        int orderPositon = orderNumberCombobox.getSelectionModel().getSelectedIndex();


        ArrayList<MenuItem> items = storeOrders.getOrder(orderPositon).getItems();
        for(int i = 0; i < items.size(); i++) {
            ObservableList itemInfo = FXCollections.observableArrayList(items.get(i).getItemString());
            storeOrdersListview.getItems().addAll(itemInfo);
        }

        //also update subtotal label
        Order selectedOrder = storeOrders.getOrder(orderPositon);
        //get the total for the order plus the sales tax
        subtotalLabel.setText("$" + String.format("%.2f", selectedOrder.getTotal() + selectedOrder.getTotal()*Constants.NJ_SALES_USE_TAX_RATE));
    }

}
