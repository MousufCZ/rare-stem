package com.mousuf.rarestem.tableViewTest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
public class TableViewController implements Initializable {
    @FXML
    private TableColumn<Person, String> fn;

    @FXML
    private TableColumn<Person, String> ln;

    @FXML
    private TableColumn<Person, String> o;

    @FXML
    public TableView<Person> table;

     public ObservableList<Person> initialData(){
        Person p12 = new Person("FN1","Last Sample Name","London");
        Person p22 = new Person("FN2","Last Sample Name","London");
        return FXCollections.observableArrayList(p12, p22);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fn.setCellValueFactory(new PropertyValueFactory<Person, String>("fn"));
        ln.setCellValueFactory(new PropertyValueFactory<Person, String>("ln"));
        o.setCellValueFactory(new PropertyValueFactory<Person, String>("o"));
        System.out.println("retirning null?");
        if (table != null) {
            table.setItems(initialData());
        } else {
            System.out.println("table is null");
        }
    }
}