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

public class StoreOrdersPageController implements Initializable {

    @FXML
    ComboBox orderNumberCombobox;

    @FXML
    ListView storeOrdersListview;

    @FXML
    Label subtotalLabel;

    StoreOrders storeOrders;

    public void handleOrderSelection(ActionEvent actionEvent) {
        setStoreOrdersListview();
    }

    public void handleCancelOrder(ActionEvent actionEvent) {
    }

    public void handleExportOrder(ActionEvent actionEvent) {
    }

    public void receiveMainMenuCurrentOrder(StoreOrders storeOrders) {
        this.storeOrders = storeOrders;
        if(storeOrders.getNumOrders() != 0) {

            //Combobox must match the number of storeOrders.
            setOrderNumberCombobox();
            orderNumberCombobox.getSelectionModel().selectFirst();

            //make the listview match the selected order in the combobox.
            setStoreOrdersListview();

            //Set the price of the storeOrder
        }

    }

    //Count the number of orders and add them to the combobox
    public void setOrderNumberCombobox() {
        int storeOrderCount = storeOrders.getNumOrders();
        ObservableList orderNumberList = FXCollections.observableArrayList();
        for(int i = 0; i < storeOrderCount; i++) {
            orderNumberList.add(i+1);
        }
        orderNumberCombobox.setItems(orderNumberList);
    }

    //place the MenuItems string into the listview
    public void setStoreOrdersListview(){
        //clear the list
        storeOrdersListview.getItems().clear();

        //get the selection from the orderNumber combobox.
        int orderNumber = Integer.parseInt(orderNumberCombobox.getSelectionModel().getSelectedItem().toString());

        ArrayList<MenuItem> items = storeOrders.getOrder(orderNumber -1).getItems(); //orders start at 1, subtract by 1.
        for(int i = 0; i < items.size(); i++) {
            ObservableList itemInfo = FXCollections.observableArrayList(items.get(i).getItemString());
            storeOrdersListview.getItems().addAll(itemInfo);
        }

        //also update subtotal label
        Order selectedOrder = storeOrders.getOrder(orderNumber-1);
        //get the total for the order plus the sales tax
        subtotalLabel.setText("$" + String.format("%.2f", selectedOrder.getTotal() + selectedOrder.getTotal()*Constants.NJ_SALES_USE_TAX_RATE));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
