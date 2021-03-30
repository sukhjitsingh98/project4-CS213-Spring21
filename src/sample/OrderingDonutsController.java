package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

//Controller class for OrderingDonuts.fxml
public class OrderingDonutsController implements Initializable {

    //Create combobox and create a list with its values to the three donut choices to be initialized.
    @FXML
    ComboBox combobox;
    ObservableList<String> list = FXCollections.observableArrayList("Yeast Donut","Cake Donut","Donut Hole");

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
    TextField subtotalField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initialize combobox
        combobox.setItems(list);
        combobox.getSelectionModel().selectFirst(); //selects the first item as default instead of non-selection
        loadselectableDonuts(list.get(0)); //initialize the listview with the first selection since it's default.

        //initialize the combobox which selects the number of donuts.
        countCombobox.setItems(countList);
        countCombobox.getSelectionModel().selectFirst();

        subtotalField.setText("$0.00");

    }

    //will initialize the selectable listview depending on the donut type.
    public void loadselectableDonuts(String donutChosen){

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
            flavorList.add("Jelly Hole");
            flavorList.add("Cinnamon Sugar Hole");
            flavorList.add("Glazed Hole");
            //add the items on the list to the listview.
            selectableDonuts.getItems().addAll(flavorList);
        }
    }

    public void displaySelectedDonuts() {
        selectedDonuts.getItems().removeAll(namesofSelection);
        namesofSelection.removeAll(namesofSelection);
        //if there are no donuts selected, leave the list view empty.
        if(selectedDonutList.size() == 0) {
            return;
        }

        //Traverse through the list of selected donuts and each.
        for(int i = 0; i < selectedDonutList.size(); i++) {
            namesofSelection.add(selectedDonutList.get(i).getFlavor() + "(" + selectedDonutList.get(i).getItemQuantity() + ")");

        }
        selectedDonuts.getItems().addAll(namesofSelection);
    }

    //Once a donut type is chosen
    public void handleDonutSelection(ActionEvent actionEvent) {
        String donutChosen = combobox.getValue().toString();
        loadselectableDonuts(donutChosen);
    }

    @FXML
    ArrayList<Donut> selectedDonutList = new ArrayList<>();
    ObservableList<String> namesofSelection = FXCollections.observableArrayList();
    BigDecimal subtotal = new BigDecimal("0");

    //add a donut of the selected type, selected flavor, and selected quantity. As of now it does not check if that type of donut of the same flavor already exists.
    public void handleDonutAddition(ActionEvent actionEvent) {

        //make sure a listview item has been chosen.
        if(selectableDonuts.getSelectionModel().getSelectedItem() == null) {
            noSelectionWarning1.setText("Must first choose a flavor to add the donut.");
            return;

        }

        //create donut with selected type and count
        Donut crntDonut = new Donut(combobox.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(countCombobox.getSelectionModel().getSelectedItem().toString()), selectableDonuts.getSelectionModel().getSelectedItem().toString());

        //add the donut order to the list of selected donuts
        selectedDonutList.add(crntDonut);

        //Display the selectedDonutsCombobox
        displaySelectedDonuts();
        noSelectionWarning1.setText("");

        //update the price subtotal
        BigDecimal newSubtotal = subtotal.add(new BigDecimal(String.valueOf(crntDonut.itemPrice()))); //immutable so much create new
        subtotal = newSubtotal;
        subtotalField.setText("$" + newSubtotal);
    }

    //Remove one of the selected donuts.
    public void handleDonutRemoval(ActionEvent actionEvent) {

        //make sure a listview item has been chosen.
        if(selectedDonuts.getSelectionModel().getSelectedItem() == null) {
            noSelectionWarning1.setText("Must first choose a donut to remove.");
            return;
        }

        //Get the donut that is being removed.
        int donutIndex = Integer.parseInt(selectedDonuts.getSelectionModel().getSelectedIndices().toString().substring(1,2));
        //before removing the donut subtrack its price from the subtotal.
        BigDecimal newSubtotal = subtotal.subtract(new BigDecimal(String.valueOf(selectedDonutList.get(donutIndex).getItemPrice())));
        subtotal = newSubtotal;
        selectedDonutList.remove(donutIndex); //remove the donut
        displaySelectedDonuts(); //update the listview
        noSelectionWarning1.setText("");
        subtotalField.setText("$" + newSubtotal);
    }
}
