package sample;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import javax.swing.*;

public class OrderingCoffeeController implements Initializable {

    //Create combobox and create a list with its values to the three donut choices to be initialized.
    @FXML ComboBox combobox;
    ObservableList<String> list = FXCollections.observableArrayList("Yeast Donut","Cake Donut","Donut Holes");

    @FXML
    private ListView<String> selectableDonuts;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        combobox.setItems(list);
    }

    //Once a donut type is chosen
    public void handleDonutSelection(ActionEvent actionEvent) {
        String donutChosen = combobox.getValue().toString();

        if(donutChosen.equals("Yeast Donut")) {

        }

        else if(donutChosen.equals("Cake Donut")) {

        }

        else if(donutChosen.equals("Donut Holes")) {

        }

    }



}
