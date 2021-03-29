package sample;

import java.io.IOException;
import java.net.URL;
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
        setSubTotalTextField();

    }

    //on selection update the price
    public void handleSizeSelection(ActionEvent actionEvent) {
        coffee.setCoffeeType(sizeCombobox.getSelectionModel().getSelectedItem().toString());
        setSubTotalTextField();
    }

    public void handleCountSelection(ActionEvent actionEvent) {
        coffee.setCoffeeQuantity(Integer.parseInt((String) countCombobox.getSelectionModel().getSelectedItem()));
        setSubTotalTextField();
    }

    public void handleCreamCheckBoxSelection(ActionEvent actionEvent){
        if(creamCheckBox.isSelected()){
            coffee.add("cream");
            setSubTotalTextField();
        }
        else if(!creamCheckBox.isSelected()){
            coffee.remove("cream");
            setSubTotalTextField();
        }
    }

    public void handleSyrupCheckBoxSelection(ActionEvent actionEvent){
        if(syrupCheckBox.isSelected()){
            coffee.add("syrup");
            setSubTotalTextField();
        }
        else if(!syrupCheckBox.isSelected()){
            coffee.remove("syrup");
            setSubTotalTextField();
        }
    }

    public void handleMilkCheckBoxSelection(ActionEvent actionEvent){
        if(milkCheckBox.isSelected()){
            coffee.add("milk");
            setSubTotalTextField();
        }
        else if(!milkCheckBox.isSelected()){
            coffee.remove("milk");
            setSubTotalTextField();
        }
    }

    public void handleCaramelCheckBoxSelection(ActionEvent actionEvent){
        if(caramelCheckBox.isSelected()){
            coffee.add("caramel");
            setSubTotalTextField();
        }
        else if(!caramelCheckBox.isSelected()){
            coffee.remove("caramel");
            setSubTotalTextField();
        }
    }

    public void handleWhippedCreamCheckBoxSelection(ActionEvent actionEvent){
        if(whippedCreamCheckBox.isSelected()){
            coffee.add("whipped cream");
            setSubTotalTextField();
        }
        else if(!whippedCreamCheckBox.isSelected()){
            coffee.remove("whipped cream");
            setSubTotalTextField();
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

    private void setSubTotalTextField(){
        subTotalTextField.setText(String.format("%.2f", coffee.getCoffeePrice()));
    }

}
