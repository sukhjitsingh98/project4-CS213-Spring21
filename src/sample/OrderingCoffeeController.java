package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import javax.swing.*;

public class OrderingCoffeeController implements Initializable {

    @FXML
    TextField subTotalTextField;

    @FXML
    CheckBox creamCheckBox, syrupCheckBox, milkCheckBox, caramelCheckBox, whippedCreamCheckBox;

    @FXML
    ComboBox sizeCombobox;
    ObservableList<String> sizeList = FXCollections.observableArrayList("Tall", "Short","Grande", "Venti");

    @FXML
    ComboBox countCombobox;
    ObservableList<String> countList = FXCollections.observableArrayList("1","2","3","4","5");

    Coffee coffee = new Coffee("", 0);

    /*
    NOTE:
    Need to figure out way to send data from this screen to orders screen
    Next we need to figure out how and where to implement Order class
    I created a StoreOrders object in the mainMenu controller which will hold all the orders.
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //sizes
        sizeCombobox.setItems(sizeList);
        sizeCombobox.getSelectionModel().selectFirst(); //selects the first item as default instead of non-selection
        //counts
        countCombobox.setItems(countList);
        countCombobox.getSelectionModel().selectFirst();

        //since the default is tall and count is 1, update the price to default once implemented.
        coffee.setCoffeeType(sizeCombobox.getSelectionModel().getSelectedItem().toString());
        coffee.setCoffeeQuantity(Integer.parseInt((String) countCombobox.getSelectionModel().getSelectedItem()));
        subTotalTextField.setText(Double.toString(coffee.getCoffeePrice()));

    }

    //on selection update the price
    public void handleSizeSelection(ActionEvent actionEvent) {
        coffee.setCoffeeType(sizeCombobox.getSelectionModel().getSelectedItem().toString());
        subTotalTextField.setText(Double.toString(coffee.getCoffeePrice()));
    }

    public void handleCountSelection(ActionEvent actionEvent) {
        coffee.setCoffeeQuantity((int) countCombobox.getSelectionModel().getSelectedItem());
        subTotalTextField.setText(Double.toString(coffee.getCoffeePrice()));
    }

    public void handleCreamCheckBoxSelection(ActionEvent actionEvent){
        if(creamCheckBox.isSelected()){
            coffee.add("cream");
            subTotalTextField.setText(Double.toString(coffee.getCoffeePrice()));
        }
        else if(!creamCheckBox.isSelected()){
            coffee.remove("cream");
            subTotalTextField.setText(Double.toString(coffee.getCoffeePrice()));
        }
    }

    public void handleSyrupCheckBoxSelection(ActionEvent actionEvent){
        if(creamCheckBox.isSelected()){
            coffee.add("syrup");
            subTotalTextField.setText(Double.toString(coffee.getCoffeePrice()));
        }
        else if(!creamCheckBox.isSelected()){
            coffee.remove("syrup");
            subTotalTextField.setText(Double.toString(coffee.getCoffeePrice()));
        }
    }

    public void handleMilkCheckBoxSelection(ActionEvent actionEvent){
        if(creamCheckBox.isSelected()){
            coffee.add("milk");
            subTotalTextField.setText(Double.toString(coffee.getCoffeePrice()));
        }
        else if(!creamCheckBox.isSelected()){
            coffee.remove("milk");
            subTotalTextField.setText(Double.toString(coffee.getCoffeePrice()));
        }
    }

    public void handleCaramelCheckBoxSelection(ActionEvent actionEvent){
        if(creamCheckBox.isSelected()){
            coffee.add("caramel");
            subTotalTextField.setText(Double.toString(coffee.getCoffeePrice()));
        }
        else if(!creamCheckBox.isSelected()){
            coffee.remove("caramel");
            subTotalTextField.setText(Double.toString(coffee.getCoffeePrice()));
        }
    }

    public void handleWhippedCreamCheckBoxSelection(ActionEvent actionEvent){
        if(whippedCreamCheckBox.isSelected()){
            coffee.add("whipped cream");
            subTotalTextField.setText(Double.toString(coffee.getCoffeePrice()));
        }
        else if(!whippedCreamCheckBox.isSelected()){
            coffee.remove("whipped cream");
            subTotalTextField.setText(Double.toString(coffee.getCoffeePrice()));
        }
    }

    public void handleAddToOrder(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();

        //Get controller of main menu
        MainMenuController mainMenuController = loader.getController();
        mainMenuController.addOrderItem(coffee);


    }

}
