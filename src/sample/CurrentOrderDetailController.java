package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class CurrentOrderDetailController implements Initializable {

    @FXML
    ListView currentOrderListView;

    Order currentOrder = new Order(0);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //sizes
        if(!currentOrder.getItems().isEmpty()) {
            currentOrderListView.getItems().add(currentOrder.getItems().get(0).getItemPrice());
        }
    }

    public void getMainMenuCurrentOrder(Order currentOrder) {
        this.currentOrder =  currentOrder;
        //System.out.println(this.currentOrder.getItems().get(0).getItemPrice());
    }
}
