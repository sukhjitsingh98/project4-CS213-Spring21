package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

//Controller class for OrderingDonuts.fxml
public class OrderingDonutsController implements Initializable {

    //Create combobox and create a list with its values to the three donut choices to be initialized.
    @FXML
    ComboBox combobox;
    ObservableList<String> list = FXCollections.observableArrayList("Yeast Donut","Cake Donut","Donut Holes");

    @FXML
    private ListView<String> selectableDonuts;
    ObservableList<String> flavorList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initialize combobox
        combobox.setItems(list);

        //ListView1
        flavorList.add("hi");
        selectableDonuts.getItems().addAll(flavorList);
    }

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
            System.out.println("T");

        }

        else if(donutChosen.equals("Cake Donut")) {
            //add the flavor available to that type.
            flavorList.add("Jelly");
            flavorList.add("Old Fashioned");
            flavorList.add("Cream");
            //add the items on the list to the listview.
            selectableDonuts.getItems().addAll(flavorList);
        }

        else if(donutChosen.equals("Donut Holes")) {
            //add the flavor available to that type.
            flavorList.add("Jelly Holes");
            flavorList.add("Cinnamon Sugar Holes");
            flavorList.add("Glazed Holes");
            //add the items on the list to the listview.
            selectableDonuts.getItems().addAll(flavorList);
        }

    }

    //Once a donut type is chosen
    public void handleDonutSelection(ActionEvent actionEvent) {
        String donutChosen = combobox.getValue().toString();
        loadselectableDonuts(donutChosen);
    }
}
