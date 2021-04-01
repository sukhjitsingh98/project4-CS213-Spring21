package sample;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 The OrderingDonutsController class defines the methods associated with the OrderingDonuts.fxml GUI file.
 The public methods define the actions performed when buttons and combobox items are clicked in the GUI application.
 The private methods are helper methods to aid in the functionality of the button and combobox methods.
 A Donut Class arraylist is created and the methods interact with this arraylist to add, remove, or
 manipulate the Donut data given by the user in the GUI application.

 @author German Munguia, Sukhjit Singh
 */
public class OrderingDonutsController implements Initializable {

    //Create combobox and create a list with its values to the three donut choices to be initialized.
    @FXML
    ComboBox combobox;
    ObservableList<String> list = FXCollections.observableArrayList("Yeast Donut","Cake Donut","Donut Hole");

    @FXML
    Button addOrder;

    @FXML
    Label subTotalLabel;

    @FXML
    private ListView<String> selectableDonuts;
    ObservableList<String> flavorList = FXCollections.observableArrayList();

    @FXML
    ComboBox countCombobox;
    ObservableList<String> countList = FXCollections.observableArrayList("1","2","3","4","5");

    @FXML
    ListView<String> selectedDonuts;

    @FXML
    Label noSelectionWarning1;

    @FXML
    ArrayList<Donut> selectedDonutList = new ArrayList<>();
    ObservableList<String> namesOfSelection = FXCollections.observableArrayList();

    //Variable which will be watched by the MainMenuController to check for any changes made to the selectedDonutList arraylist
    private final ReadOnlyObjectWrapper<ArrayList<Donut>> selectedThing = new ReadOnlyObjectWrapper<>();

    /**
     This method is run when the GUI first opens up and populates the combobox with the different Donut types which
     can be selected and populates the quantity combobox with the available Donut quantities which can be selected.
     @param location used to resolve relative path attribute values.
     @param resources used to resolve resource key attribute values.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initialize combobox
        combobox.setItems(list);
        combobox.getSelectionModel().selectFirst(); //selects the first item as default instead of non-selection
        loadSelectableDonuts(list.get(0)); //initialize the listview with the first selection since it's default.

        countCombobox.setItems(countList);
        countCombobox.getSelectionModel().selectFirst();
        setSubTotalLabel();
    }

    /**
     Helper method which populates the first listview with the donut flavors which can be selected by the user based
     on the type of donut they selected from the combobox.
     @param donutChosen the donut that was selected from the listview.
     */
    private void loadSelectableDonuts(String donutChosen){

        //remove all items from the listview, then from the list
        selectableDonuts.getItems().removeAll(flavorList);
        flavorList.removeAll(flavorList);

        if(donutChosen.equals("Yeast Donut")) {
            //add the flavor available to that type.
            flavorList.add("Vanilla");
            flavorList.add("Strawberry");
            flavorList.add("Chocolate");
            //add the items on the list to the listview.
            selectableDonuts.getItems().addAll(flavorList);
        }
        else if(donutChosen.equals("Cake Donut")) {
            //add the flavor available to that type.
            flavorList.add("Jelly");
            flavorList.add("Old Fashioned");
            flavorList.add("Cream");
            //add the items on the list to the listview.
            selectableDonuts.getItems().addAll(flavorList);
        }
        else if(donutChosen.equals("Donut Hole")) {
            //add the flavor available to that type.
            flavorList.add("Jelly Holes");
            flavorList.add("Cinnamon Sugar Holes");
            flavorList.add("Glazed Holes");
            //add the items on the list to the listview.
            selectableDonuts.getItems().addAll(flavorList);
        }
    }

    /**
     Helper method which populates the second listview with the donut flavors which were selected by the user.
     */
    private void displaySelectedDonuts() {
        selectedDonuts.getItems().removeAll(namesOfSelection);
        namesOfSelection.removeAll(namesOfSelection);
        //if there are no donuts selected, leave the list view empty.
        if(selectedDonutList.size() == 0) {
            return;
        }

        //Traverse through the list of selected donuts and each.
        for(int i = 0; i < selectedDonutList.size(); i++) {
            namesOfSelection.add(selectedDonutList.get(i).getFlavor() + "(" + selectedDonutList.get(i).getItemQuantity() + ")");
        }
        selectedDonuts.getItems().addAll(namesOfSelection);
    }

    /**
     Checks which Donut type the user selected from the combobox and loads the flavors for that donut in the first
     listview.
     @param actionEvent associated with the clicking of a donut type in the combobox
     */
    public void handleDonutSelection(ActionEvent actionEvent) {
        String donutChosen = combobox.getValue().toString();
        loadSelectableDonuts(donutChosen);
    }

    /**
     Checks if there is any donut selected by the user in the listview of the GUI application, if so the
     selection is added to the selectedDonutsList arraylist and the second listview is updated to reflect this change.
     Once the item is added, the subtotal is updated and the second listview is repopulated with the updated donut data.
     @param actionEvent associated with the clicking of the Add Order arrow button
     */
    public void handleDonutAddition(ActionEvent actionEvent) {

        //Make sure a listview item has been chosen.
        if(selectableDonuts.getSelectionModel().getSelectedItem() == null) {
            noSelectionWarning1.setText("Must first choose a flavor to add the donut.");
            return;

        }

        //Create donut with selected type and count
        Donut crntDonut = new Donut(combobox.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(countCombobox.getSelectionModel().getSelectedItem().toString()));
        crntDonut.add(selectableDonuts.getSelectionModel().getSelectedItem().toString());

        //Check if donut already exists, if so display error message
        if(isDuplicateDonut(crntDonut)) {
            noSelectionWarning1.setText("This flavored donut is already selected.");
            return;
        }

        //Add the donut order to the list of selected donuts
        selectedDonutList.add(crntDonut);
        setSubTotalLabel();

        //Display the selectedDonuts listview
        displaySelectedDonuts();
        noSelectionWarning1.setText("");
    }

    /**
     Checks if there are any donut flavors selected by the user in the listview of the GUI application, if so the
     selection is deleted from the selectedDonutsList arraylist.
     Once the item is deleted, the subtotal is updated and the listview is repopulated with the remaining donut data, if
     no donuts remains, the listview remains empty.
     @param actionEvent associated with the clicking of the Remove Donut arrow button
     */
    public void handleDonutRemoval(ActionEvent actionEvent) {

        //make sure a listview item has been chosen.
        if(selectedDonuts.getSelectionModel().getSelectedItem() == null) {
            noSelectionWarning1.setText("Must first choose a donut to remove.");
            return;
        }

        //Get the donut that is being removed.
        int donutIndex = Integer.parseInt(selectedDonuts.getSelectionModel().getSelectedIndices().toString().substring(1,2));

        selectedDonutList.remove(donutIndex); //remove the donut
        setSubTotalLabel();
        displaySelectedDonuts(); //update the listview
        noSelectionWarning1.setText("");
    }

    /**
     Helper method which returns a Boolean value representing if an individual donut is a duplicate.
     @param donut object whose flavor will be checked for duplicate value
     @return true is Donut flavor is already in the selectedDonutList, false otherwise
     */
    private boolean isDuplicateDonut(Donut donut){
        for (Donut addedDonut : selectedDonutList){
            if(addedDonut.getFlavor().equals(donut.getFlavor())){
                return true;
            }
        }
        return false;
    }

    /**
     Helper method which updates the price label with the calculated values for the donut order subtotal.
     */
    private void setSubTotalLabel(){
        subTotalLabel.setText("$" + String.format("%.2f", calculateSubTotal()));
    }

    /**
     Helper method which calculates the subtotal price of the donuts by summing the individual donut prices.
     @return sum of the individual donut prices of the order.
     */
    private double calculateSubTotal(){
        double sum = 0;
        if(!selectedDonutList.isEmpty()){
            for (Donut donut : selectedDonutList){
                sum += donut.getDonutPrice();
            }
        }
        return sum;
    }

    /**
     Helper method which gets the read only value of the selectedThing Donut wrapper.
     The MainMenuController will watch for any changes made to the wrapper and update the currentOrder in that class
     based on the changes made in this class
     @return wrapper object read only value
     */
    public ReadOnlyObjectProperty<ArrayList<Donut>> selectedThingProperty() {
        return selectedThing.getReadOnlyProperty() ;
    }

    /**
     Getter method which returns the selectedDonutList arraylist
     @return selectedDonutList which contains the Donut order data
     */
    public ArrayList<Donut> getDonutList(){
        return selectedDonutList;
    }

    /**
     Updates the selectedThing Donut wrapper with the selectedDonutList arraylist and closes this GUI page.
     This informs the MainMenuController that the selectedDonutList arraylist is ready to be retrieved
     @param actionEvent associated with the clicking of the Add to Order button
     */
    public void handleAddToOrder(ActionEvent actionEvent) throws IOException {

        if(selectedDonutList.isEmpty()) {
            noSelectionWarning1.setText("Must order at least one donut to place an order.");
            return;
        }

        selectedThing.set(selectedDonutList);

        //Code to close the screen once order is placed
        Stage stage = (Stage) addOrder.getScene().getWindow();
        stage.close();

    }

}
