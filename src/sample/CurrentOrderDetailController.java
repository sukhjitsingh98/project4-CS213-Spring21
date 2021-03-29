package sample;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CurrentOrderDetailController implements Initializable {

    @FXML
    ListView currentOrderListView;
    @FXML
    Label errorMessageLabel;
    @FXML
    TextField currentOrderSubTotalField, currentOrderSalesTaxField, currentOrderTotalField;
    @FXML
    Button placeOrderButton;

    Order currentOrder;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Anything we put here never gets updated. Best to keep blank
    }

    public void receiveMainMenuCurrentOrder(Order currentOrder) {
        this.currentOrder =  currentOrder;

        if(!currentOrder.getItems().isEmpty()) {
            setCurrentOrderListView();
            currentOrderListView.getSelectionModel().selectFirst();
            setPriceFields();
        }
    }

    public void handleRemoveMenuItem(ActionEvent actionEvent){
        if(currentOrder.getItems().isEmpty()){
            errorMessageLabel.setText("Error: No Items in Cart to Delete");
        }
        else if(currentOrderListView.getSelectionModel().isEmpty()){
            errorMessageLabel.setText("Error: Select an Item to Delete");
        }
        else{
            currentOrder.remove(currentOrder.getItems().get(currentOrderListView.getSelectionModel().getSelectedIndex()));
            currentOrderListView.getItems().clear();
            setCurrentOrderListView();
            setPriceFields();
        }

    }

    private final ReadOnlyObjectWrapper<Order> selectedThing = new ReadOnlyObjectWrapper<>();

    public ReadOnlyObjectProperty<Order> selectedThingProperty() {
        return selectedThing.getReadOnlyProperty() ;
    }

    public Order getCurrentOrder(){
        return currentOrder;
    }

    public void handlePlaceOrder(ActionEvent actionEvent){
        if(!currentOrder.getItems().isEmpty()) {
            selectedThing.set(currentOrder);

            //Code to close the screen once order is placed
            Stage stage = (Stage) placeOrderButton.getScene().getWindow();
            stage.close();
        }
        else{
            errorMessageLabel.setText("Error: No Items in Cart to Order");
        }
    }

    private double calculateSubTotal(){
        double sum = 0;
        if(!currentOrder.getItems().isEmpty()){
            for (MenuItem menuItem : currentOrder.getItems()){
                sum += menuItem.getItemPrice();
            }
        }
        return sum;
    }

    private void setCurrentOrderListView(){
        for (MenuItem menuItem : currentOrder.getItems()){
            currentOrderListView.getItems().add(menuItem.getItemString());
        }
    }

    private void setPriceFields(){
        double salesTax = calculateSubTotal() * Constants.NJ_SALES_USE_TAX_RATE;

        currentOrderSubTotalField.setText("$" + String.format("%.2f", calculateSubTotal()));
        currentOrderSalesTaxField.setText("$" + String.format("%.2f", salesTax));
        currentOrderTotalField.setText("$" + String.format("%.2f", (calculateSubTotal() + salesTax)));
    }
}
