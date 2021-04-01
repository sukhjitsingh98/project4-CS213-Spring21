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
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 The OrderingCoffeeController class defines the methods associated with the OrderingCoffee.fxml GUI file.
 The public methods define the actions performed when buttons, checkbox, and combobox items are clicked in the GUI
 application.
 The private methods are helper methods to aid in the functionality of the button, checkbox, and combobox methods.
 An Addins arraylist is created and the methods interact with this arraylist to add, remove, or
 manipulate the Coffee addin data given by the user in the GUI application.

 @author German Munguia, Sukhjit Singh
 */
public class OrderingCoffeeController implements Initializable {

    @FXML
    Label subTotalLabel;

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

    //Variable which will be watched by the MainMenuController to check for any changes made to the Coffee class object
    private final ReadOnlyObjectWrapper<Coffee> selectedThing = new ReadOnlyObjectWrapper<>();

    /**
     This method is run when the GUI first opens up and populates the combobox with the different Coffee cup sizes which
     can be selected and populates the quantity combobox with the available Coffee quantities which can be selected.
     @param location used to resolve relative path attribute values.
     @param resources used to resolve resource key attribute values.
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

        //Display the subtotal price
        setSubTotalLabel();

    }

    /**
     Checks which Coffee cup size the user selected from the combobox and assigns that value to the Coffee object and
     updates the subtotal price.
     @param actionEvent associated with the clicking of a coffee size in the combobox
     */
    public void handleSizeSelection(ActionEvent actionEvent) {
        coffee.setCoffeeType(sizeCombobox.getSelectionModel().getSelectedItem().toString());
        setSubTotalLabel();
    }

    /**
     Checks which quantity amount the user selected from the combobox and assigns that value to the Coffee object and
     updates the subtotal price.
     @param actionEvent associated with the clicking of Count in the combobox
     */
    public void handleCountSelection(ActionEvent actionEvent) {
        coffee.setCoffeeQuantity(Integer.parseInt((String) countCombobox.getSelectionModel().getSelectedItem()));
        setSubTotalLabel();
    }

    /**
     Checks if the creamCheckBox is selected and based on the selection it adds or removes the cream addin from the
     Coffee object and updates the subtotal price.
     @param actionEvent associated with the clicking of the creamCheckBox
     */
    public void handleCreamCheckBoxSelection(ActionEvent actionEvent){
        if(creamCheckBox.isSelected()){
            coffee.add("cream");
            setSubTotalLabel();
        }
        else if(!creamCheckBox.isSelected()){
            coffee.remove("cream");
            setSubTotalLabel();
        }
    }

    /**
     Checks if the syrupCheckBox is selected and based on the selection it adds or removes the syrup addin from the
     Coffee object and updates the subtotal price.
     @param actionEvent associated with the clicking of the syrupCheckBox
     */
    public void handleSyrupCheckBoxSelection(ActionEvent actionEvent){
        if(syrupCheckBox.isSelected()){
            coffee.add("syrup");
            setSubTotalLabel();
        }
        else if(!syrupCheckBox.isSelected()){
            coffee.remove("syrup");
            setSubTotalLabel();
        }
    }

    /**
     Checks if the milkCheckBox is selected and based on the selection it adds or removes the milk addin from the
     Coffee object and updates the subtotal price.
     @param actionEvent associated with the clicking of the milkCheckBox
     */
    public void handleMilkCheckBoxSelection(ActionEvent actionEvent){
        if(milkCheckBox.isSelected()){
            coffee.add("milk");
            setSubTotalLabel();
        }
        else if(!milkCheckBox.isSelected()){
            coffee.remove("milk");
            setSubTotalLabel();
        }
    }

    /**
     Checks if the caramelCheckBox is selected and based on the selection it adds or removes the caramel addin from the
     Coffee object and updates the subtotal price.
     @param actionEvent associated with the clicking of the caramelCheckBox
     */
    public void handleCaramelCheckBoxSelection(ActionEvent actionEvent){
        if(caramelCheckBox.isSelected()){
            coffee.add("caramel");
            setSubTotalLabel();
        }
        else if(!caramelCheckBox.isSelected()){
            coffee.remove("caramel");
            setSubTotalLabel();
        }
    }

    /**
     Checks if the whippedCreamCheckBox is selected and based on the selection it adds or removes the whipped cream
     addin from the Coffee object and updates the subtotal price.
     @param actionEvent associated with the clicking of the whippedCreamCheckBox
     */
    public void handleWhippedCreamCheckBoxSelection(ActionEvent actionEvent){
        if(whippedCreamCheckBox.isSelected()){
            coffee.add("whipped cream");
            setSubTotalLabel();
        }
        else if(!whippedCreamCheckBox.isSelected()){
            coffee.remove("whipped cream");
            setSubTotalLabel();
        }
    }

    /**
     Helper method which gets the read only value of the selectedThing Coffee wrapper.
     The MainMenuController will watch for any changes made to the wrapper and update the currentOrder in that class
     based on the changes made in this class
     @return wrapper object read only value
     */
    public ReadOnlyObjectProperty<Coffee> selectedThingProperty() {
        return selectedThing.getReadOnlyProperty() ;
    }

    /**
     Getter method which returns the coffee Coffee Class Object
     @return coffee object which contains the order data for this coffee
     */
    public Coffee getCoffee(){
        return coffee;
    }

    /**
     Updates the selectedThing Coffee wrapper with the coffee Coffee Class Object and closes this GUI page.
     This informs the MainMenuController that the coffee Coffee Class Object is ready to be retrieved
     @param actionEvent associated with the clicking of the Add to Order button
     */
    public void handleAddToOrder(ActionEvent actionEvent) throws IOException {
        selectedThing.set(coffee);

        //Code to close the screen once order is placed
        Stage stage = (Stage) addToOrderButton.getScene().getWindow();
        stage.close();
    }

    /**
     Helper method which assigns the subTotalLabel label with the subtotal price of the coffee
     */
    private void setSubTotalLabel(){
        subTotalLabel.setText("$" + String.format("%.2f", coffee.getCoffeePrice()));
    }

}
