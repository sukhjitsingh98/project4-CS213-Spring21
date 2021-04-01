package sample;

/**
 The CurrentOrderDetailController class defines the methods associated with the CurrentOrderDetail.fxml GUI file.
 The public methods define the actions performed when buttons are clicked in the GUI application.
 The private methods are helper methods to aid in the functionality of the button methods.
 An Order Object is passed into this class and the methods interact with this object to add, remove, or
 manipulate the order data given by the user in the GUI application.

 @author German Munguia, Sukhjit Singh
 */

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

public class CurrentOrderDetailController{

    @FXML
    ListView currentOrderListView;
    @FXML
    Label errorMessageLabel, currentOrderSubTotalField, currentOrderSalesTaxField, currentOrderTotalField;
    @FXML
    Button placeOrderButton;

    Order currentOrder;
    //Variable which will be watched by the MainMenuController to check for any changes made to the currentOrder arraylist
    private final ReadOnlyObjectWrapper<Order> selectedThing = new ReadOnlyObjectWrapper<>();

    /**
     This method is called from the MainMenuController file during the initialization of this GUI allowing the
     cuurentOrder arraylist to be passed as a parameter to be manipulated in this class.
     The listview is populated with the String values of the order menu items.
     @param currentOrder arraylist from the MainMenuController class
     */
    public void receiveMainMenuCurrentOrder(Order currentOrder) {
        this.currentOrder =  currentOrder;

        if(!currentOrder.getItems().isEmpty()) {
            setCurrentOrderListView();
            currentOrderListView.getSelectionModel().selectFirst();
            setPriceFields();
        }
    }

    /**
     Checks if there are any menu items selected by the user in the listview of the GUI application, if so the
     selection is deleted from the currentOrders arraylist.
     Once the item is deleted, the listview is repopulated with the remaining menu item data, if no items remains, the
     listview remains empty.
     @param actionEvent associated with the clicking of the Cancel Order button
     */
    public void handleRemoveMenuItem(ActionEvent actionEvent){
        //Check if there are any items available to delete.
        if(currentOrder.getItems().isEmpty()){
            errorMessageLabel.setText("Error: No Items in Cart to Delete");
        }
        //Check if the user selected an item from the listview
        else if(currentOrderListView.getSelectionModel().isEmpty()){
            errorMessageLabel.setText("Error: Select an Item to Delete");
        }
        //Remove the item from the arraylist and update the listview and price fields.
        else{
            currentOrder.remove(currentOrder.getItems().get(currentOrderListView.getSelectionModel().getSelectedIndex()));
            currentOrderListView.getItems().clear();
            setCurrentOrderListView();
            setPriceFields();
        }
    }

    /**
     Helper method which gets the read only value of the selectedThing Order wrapper.
     The MainMenuController will watch for any changes made to the wrapper and update the currentOrder in that class
     based on the changes made in this class
     @return wrapper object read only value
     */
    public ReadOnlyObjectProperty<Order> selectedThingProperty() {
        return selectedThing.getReadOnlyProperty() ;
    }

    /**
     Getter method which returns the currentOrder Order object
     @return currentOrder which contains the updated Order instance
     */
    public Order getCurrentOrder(){
        return currentOrder;
    }

    /**
     Updates the selectedThing Order wrapper with the currentOrder object and closes this GUI page.
     This informs the MainMenuController that the currentOrder object is ready to be retrieved
     @param actionEvent associated with the clicking of the Cancel Order button
     */
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

    /**
     Helper method which calculates the subtotal price of the order by summing the individual menu item prices.
     @return sum of the individual item prices of the order.
     */
    private double calculateSubTotal(){
        double sum = 0;
        if(!currentOrder.getItems().isEmpty()){
            for (MenuItem menuItem : currentOrder.getItems()){
                sum += menuItem.getItemPrice();
            }
        }
        return sum;
    }

    /**
     Helper method which updates the listview with the menu item String representations for each item in the order.
     */
    private void setCurrentOrderListView(){
        for (MenuItem menuItem : currentOrder.getItems()){
            currentOrderListView.getItems().add(menuItem.getItemString());
        }
    }

    /**
     Helper method which updates the price labels with the calculated values for the total order subtotal, sales tax,
     and total price with tax.
     */
    private void setPriceFields(){
        double salesTax = calculateSubTotal() * Constants.NJ_SALES_USE_TAX_RATE;

        currentOrderSubTotalField.setText("$" + String.format("%.2f", calculateSubTotal()));
        currentOrderSalesTaxField.setText("$" + String.format("%.2f", salesTax));
        currentOrderTotalField.setText("$" + String.format("%.2f", (calculateSubTotal() + salesTax)));
    }
}
