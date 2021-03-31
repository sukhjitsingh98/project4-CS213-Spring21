package sample;

/**
 The StoreOrdersPageController class defines the methods associated with the StoreOrdersPage.fxml GUI file.
 The public methods define the actions performed when buttons and combo boxes are clicked in the GUI application.
 The private methods are helper methods to aid in the functionality of the button and combo box methods.
 An instance of the StoreOrders class is passed into this class and the methods interact with this object to add,
 remove, or manipulate store order data given by the user in the GUI application.
 @author German Munguia, Sukhjit Singh
 */

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

public class StoreOrdersPageController {

    @FXML
    ComboBox orderNumberCombobox;

    @FXML
    ListView storeOrdersListview;

    @FXML
    Label subtotalLabel, errorMessageLabel;

    StoreOrders storeOrders;

    /**
     Checks if there are any orders placed by the user in the GUI application, if so the listview is populated with
     each individual order's data, otherwise nothing happens.
     @param actionEvent associated with the clicking of the add button
     */
    public void handleOrderSelection(ActionEvent actionEvent) {
        //due to the possible removal of orders, make sure it isn't empty
        if(orderNumberCombobox.getSelectionModel().isEmpty()) {
            return;
        }

        setStoreOrdersListview();
    }

    //delete the selected order.
    public void handleCancelOrder(ActionEvent actionEvent) {

        if(storeOrders.getNumOrders() == 0 && storeOrdersListview.getSelectionModel().isEmpty()){
            errorMessageLabel.setText("Error: There Are No Orders Available To Delete");
            return;
        }

        errorMessageLabel.setText("");
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
    private void setOrderNumberCombobox() {
        int storeOrderCount = storeOrders.getNumOrders();
        ObservableList orderNumberList = FXCollections.observableArrayList();
        for(int i = 0; i < storeOrderCount; i++) {
            orderNumberList.add(storeOrders.getOrder(i).getOrderNumber());
        }
        orderNumberCombobox.setItems(orderNumberList);
    }

    //place the MenuItems string into the listview
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

        //also update subtotal label
        Order selectedOrder = storeOrders.getOrder(orderPosition);
        //get the total for the order plus the sales tax
        subtotalLabel.setText("$" + String.format("%.2f", calculateSingleOrderTotal(selectedOrder)));
    }

    //Calculate the total for a single order
    private double calculateSingleOrderTotal(Order order){
        double sum = order.getTotal() + order.getTotal()*Constants.NJ_SALES_USE_TAX_RATE;
        return sum;
    }

}
