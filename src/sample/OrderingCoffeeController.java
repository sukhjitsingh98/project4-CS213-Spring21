package sample;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.swing.*;

public class OrderingCoffeeController implements Initializable {

    @FXML
    TextField subTotalTextField;

    @FXML
    Button addToOrderButton;

    @FXML
    CheckBox creamCheckBox, syrupCheckBox, milkCheckBox, caramelCheckBox, whippedCreamCheckBox;

    @FXML
    ComboBox sizeCombobox;
    ObservableList<String> sizeList = FXCollections.observableArrayList("Tall", "Short","Grande", "Venti");

    @FXML
    ComboBox countCombobox;
    ObservableList<String> countList = FXCollections.observableArrayList("1","2","3","4","5");

    Coffee coffee = new Coffee("", 0);

    private DecimalFormat df2 = new DecimalFormat("#.##");
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
        subTotalTextField.setText(df2.format(coffee.getCoffeePrice()));

    }

    //on selection update the price
    public void handleSizeSelection(ActionEvent actionEvent) {
        coffee.setCoffeeType(sizeCombobox.getSelectionModel().getSelectedItem().toString());
        subTotalTextField.setText(df2.format(coffee.getCoffeePrice()));
    }

    public void handleCountSelection(ActionEvent actionEvent) {
        coffee.setCoffeeQuantity(Integer.parseInt((String) countCombobox.getSelectionModel().getSelectedItem()));
        subTotalTextField.setText(df2.format(coffee.getCoffeePrice()));
    }

    public void handleCreamCheckBoxSelection(ActionEvent actionEvent){
        if(creamCheckBox.isSelected()){
            coffee.add("cream");
            subTotalTextField.setText(df2.format(coffee.getCoffeePrice()));
        }
        else if(!creamCheckBox.isSelected()){
            coffee.remove("cream");
            subTotalTextField.setText(df2.format(coffee.getCoffeePrice()));
        }
    }

    public void handleSyrupCheckBoxSelection(ActionEvent actionEvent){
        if(syrupCheckBox.isSelected()){
            coffee.add("syrup");
            subTotalTextField.setText(df2.format(coffee.getCoffeePrice()));
        }
        else if(!syrupCheckBox.isSelected()){
            coffee.remove("syrup");
            subTotalTextField.setText(df2.format(coffee.getCoffeePrice()));
        }
    }

    public void handleMilkCheckBoxSelection(ActionEvent actionEvent){
        if(milkCheckBox.isSelected()){
            coffee.add("milk");
            subTotalTextField.setText(df2.format(coffee.getCoffeePrice()));
        }
        else if(!milkCheckBox.isSelected()){
            coffee.remove("milk");
            subTotalTextField.setText(df2.format(coffee.getCoffeePrice()));
        }
    }

    public void handleCaramelCheckBoxSelection(ActionEvent actionEvent){
        if(caramelCheckBox.isSelected()){
            coffee.add("caramel");
            subTotalTextField.setText(df2.format(coffee.getCoffeePrice()));
        }
        else if(!caramelCheckBox.isSelected()){
            coffee.remove("caramel");
            subTotalTextField.setText(df2.format(coffee.getCoffeePrice()));
        }
    }

    public void handleWhippedCreamCheckBoxSelection(ActionEvent actionEvent){
        if(whippedCreamCheckBox.isSelected()){
            coffee.add("whipped cream");
            subTotalTextField.setText(df2.format(coffee.getCoffeePrice()));
        }
        else if(!whippedCreamCheckBox.isSelected()){
            coffee.remove("whipped cream");
            subTotalTextField.setText(df2.format(coffee.getCoffeePrice()));
        }
    }

    private final ReadOnlyObjectWrapper<Coffee> selectedThing = new ReadOnlyObjectWrapper<>();

    public ReadOnlyObjectProperty<Coffee> selectedThingProperty() {
        return selectedThing.getReadOnlyProperty() ;
    }

    public Coffee getCoffee(){
        return coffee;
    }

    public void handleAddToOrder(ActionEvent actionEvent) throws IOException {
        selectedThing.set(coffee);

        //Code to close the screen once order is placed
        Stage stage = (Stage) addToOrderButton.getScene().getWindow();
        stage.close();

    }

}
