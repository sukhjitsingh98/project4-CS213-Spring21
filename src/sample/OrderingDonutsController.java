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

//Controller class for OrderingDonuts.fxml
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

    //will initialize the selectable listview depending on the donut type.
    public void loadSelectableDonuts(String donutChosen){

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

    public void displaySelectedDonuts() {
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

    //Once a donut type is chosen
    public void handleDonutSelection(ActionEvent actionEvent) {
        String donutChosen = combobox.getValue().toString();
        loadSelectableDonuts(donutChosen);
    }

    //add a donut of the selected type, selected flavor, and selected quantity. RESOLVED: As of now it does not check
    // if that type of donut of the same flavor already exists.
    public void handleDonutAddition(ActionEvent actionEvent) {

        //make sure a listview item has been chosen.
        if(selectableDonuts.getSelectionModel().getSelectedItem() == null) {
            noSelectionWarning1.setText("Must first choose a flavor to add the donut.");
            return;

        }

        //create donut with selected type and count
        Donut crntDonut = new Donut(combobox.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(countCombobox.getSelectionModel().getSelectedItem().toString()));
        crntDonut.add(selectableDonuts.getSelectionModel().getSelectedItem().toString());
        //System.out.println(combobox.getSelectionModel().getSelectedItem().toString() + " : " + Integer.parseInt(countCombobox.getSelectionModel().getSelectedItem().toString()) + " : " + selectableDonuts.getSelectionModel().getSelectedItem().toString());
        //System.out.println(crntDonut.getItemPrice());

        //Check if donut already exists, if so display error message
        if(isDuplicateDonut(crntDonut)) {
            noSelectionWarning1.setText("This flavored donut is already selected.");
            return;
        }

        //add the donut order to the list of selected donuts
        selectedDonutList.add(crntDonut);
        setSubTotalLabel();

        //Display the selectedDonutsCombobox
        displaySelectedDonuts();
        noSelectionWarning1.setText("");
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
        selectedDonutList.remove(donutIndex); //remove the donut
        setSubTotalLabel();
        displaySelectedDonuts(); //update the listview
        noSelectionWarning1.setText("");
    }

    //Check if donut already exists in the list, if so return true
    private boolean isDuplicateDonut(Donut donut){
        for (Donut addedDonut : selectedDonutList){
            if(addedDonut.getFlavor().equals(donut.getFlavor())){
                return true;
            }
        }
        return false;
    }

    private void setSubTotalLabel(){
        subTotalLabel.setText("$" + String.format("%.2f", calculateSubTotal()));
    }

    private double calculateSubTotal(){
        double sum = 0;
        if(!selectedDonutList.isEmpty()){
            for (Donut donut : selectedDonutList){
                sum += donut.getDonutPrice();
            }
        }
        return sum;
    }

    //------------------------Establish connection with MainMenu------------------------
    private final ReadOnlyObjectWrapper<ArrayList<Donut>> selectedThing = new ReadOnlyObjectWrapper<>();

    public ReadOnlyObjectProperty<ArrayList<Donut>> selectedThingProperty() {
        return selectedThing.getReadOnlyProperty() ;
    }

    public ArrayList<Donut> getDonutList(){
        return selectedDonutList;
    }

    public void handleAddToOrder(ActionEvent actionEvent) throws IOException {
        selectedThing.set(selectedDonutList);

        //Code to close the screen once order is placed
        Stage stage = (Stage) addOrder.getScene().getWindow();
        stage.close();

    }

}
