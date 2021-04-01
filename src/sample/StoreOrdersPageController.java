package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 The StoreOrdersPageController class defines the methods associated with the StoreOrdersPage.fxml GUI file.
 The public methods define the actions performed when buttons and combo boxes are clicked in the GUI application.
 The private methods are helper methods to aid in the functionality of the button and combo box methods.
 A StoreOrders Object is passed into this class and the methods interact with this object to add, remove, or
 manipulate store order data given by the user in the GUI application.

 @author German Munguia, Sukhjit Singh
 */

public class StoreOrdersPageController {

    @FXML
    ComboBox orderNumberCombobox;

    @FXML
    ListView storeOrdersListview;

    @FXML
    Label subtotalLabel, errorMessageLabel;

    StoreOrders storeOrders;

    /**
     Checks if there are any orders placed by the user in the GUI application when a combobox item selection is made, if
     so the listview is populated with the selected order's data, otherwise nothing happens.
     Calls the setStoreOrdersListview() method to populate the listview
     @param actionEvent associated with the clicking of a selection in the combobox
     */
    public void handleOrderSelection(ActionEvent actionEvent) {
        //due to the possible removal of orders, make sure it isn't empty
        if(orderNumberCombobox.getSelectionModel().isEmpty()) {
            return;
        }

        setStoreOrdersListview();
    }

    /**
     Checks if there are any orders selected by the user in the listview of the GUI application, if so the selection is
     deleted from the storeOrders arraylist.
     Once the item is deleted, the listview is repopulated with a different order's data, if no order remains, the
     listview remains empty.
     Calls the setOrderNumberCombobox() method to recreate the combobox
     @param actionEvent associated with the clicking of the Cancel Order button
     */
    public void handleCancelOrder(ActionEvent actionEvent) {

        //Check if there are any orders and if the listview is empty (When GUI first opens and cancel button is pressed)
        if(storeOrders.getNumOrders() == 0 && storeOrdersListview.getSelectionModel().isEmpty()){
            errorMessageLabel.setText("Error: There Are No Orders Available To Delete");
            return;
        }

        //Remove any possible error message
        errorMessageLabel.setText("");
        //Get index of order to be removed
        int orderPosition = orderNumberCombobox.getSelectionModel().getSelectedIndex();
        Order selectedOrder = storeOrders.getOrder(orderPosition);
        storeOrders.remove(selectedOrder);

        //If the removal leaves the list empty do not call the update methods, leave everything empty as there are no orders to display.
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

    /**
     Checks if there are any orders in the storeOrders arraylist, if so a new fileChooser object is created to export
     the order data to a text file.
     @param actionEvent associated with the clicking of the Cancel Order button
     */
    public void handleExportOrder(ActionEvent actionEvent) {

        if(storeOrders == null) {
            errorMessageLabel.setText("Empty database cannot be exported.\n");
            return;
        }

        //allow for the location of the exported file be chosen.
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export File");
        File selectedFile = fileChooser.showSaveDialog(null);

        try{
            //use printwriter to write the toString values of each employee.
            PrintWriter pw = new PrintWriter(selectedFile);

            for(int i = 0; i < storeOrders.getNumOrders(); i++) {

                pw.println("Order #" + storeOrders.getOrder(i).getOrderNumber());
                pw.println("Order Total = $" + String.format("%.2f",
                        calculateSingleOrderTotal(storeOrders.getOrder(i))));

                for(MenuItem menuItem : storeOrders.getOrder(i).getItems()){
                    pw.println(menuItem.getItemString());
                }

                pw.println("-------------------------------------------------");
            }
            pw.close(); //close the file
        }
        catch(Exception ex){
            errorMessageLabel.setText("The file was not exported.\n");
            return;
        }


    }

    /**
     This method is called from the MainMenuController file during the initialization of this GUI allowing the
     storeOrders arraylist to be passed as a parameter to be manipulated in this class.
     @param storeOrders arraylist from the MainMenuController class
     */
    public void receiveMainMenuStoreOrder(StoreOrders storeOrders) {
        this.storeOrders = storeOrders;
        if(storeOrders.getNumOrders() != 0) {

            //Combobox must match the number of storeOrders.
            setOrderNumberCombobox();
            orderNumberCombobox.getSelectionModel().selectFirst();

            //make the listview match the selected order in the combobox. Also updates the price
            setStoreOrdersListview();
        }
    }

    /**
     Helper method which sets the storeOrder arraylist elements as items in the combobox identified by their order
     number.
     */
    private void setOrderNumberCombobox() {
        int storeOrderCount = storeOrders.getNumOrders();
        ObservableList orderNumberList = FXCollections.observableArrayList();
        for(int i = 0; i < storeOrderCount; i++) {
            orderNumberList.add(storeOrders.getOrder(i).getOrderNumber());
        }
        orderNumberCombobox.setItems(orderNumberList);
    }

    /**
     Helper method which sets the listview with the string representation of each individual item of the selected
     order. The order's total price textfield is also updated with the total price of the current order.
     */
    private void setStoreOrdersListview(){
        //clear the list
        storeOrdersListview.getItems().clear();

        //get the selection from the orderNumber combobox.
        int orderPosition = orderNumberCombobox.getSelectionModel().getSelectedIndex();

        ArrayList<MenuItem> items = storeOrders.getOrder(orderPosition).getItems();
        for(int i = 0; i < items.size(); i++) {
            ObservableList itemInfo = FXCollections.observableArrayList(items.get(i).getItemString());
            storeOrdersListview.getItems().addAll(itemInfo);
        }

        //also update total label
        Order selectedOrder = storeOrders.getOrder(orderPosition);
        //get the total for the order plus the sales tax
        subtotalLabel.setText("$" + String.format("%.2f", calculateSingleOrderTotal(selectedOrder)));
    }

    /**
     Helper method which calculates the total price of the given order with NJ sales tax.
     @param order whose total price with tax is to be calculated.
     @return sum of the individual item prices of the given order with the NJ sales tax applied to them.
     */
    private double calculateSingleOrderTotal(Order order){
        double sum = order.getTotal() + order.getTotal()*Constants.NJ_SALES_USE_TAX_RATE;
        return sum;
    }

}
