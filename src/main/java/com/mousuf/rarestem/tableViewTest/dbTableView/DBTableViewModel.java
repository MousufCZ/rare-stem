package com.mousuf.rarestem.tableViewTest.dbTableView;
import com.mousuf.rarestem.tableViewTest.Person;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DBTableViewModel implements Initializable {
    @FXML
    private TableColumn<Person, String> fn;

    @FXML
    private TableColumn<Person, String> ln;

    @FXML
    private TableColumn<Person, String> o;

    @FXML
    public TableView<Person> table;

    private DBTableViewController dbTableViewController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fn.setCellValueFactory(new PropertyValueFactory<Person, String>("fn"));
        ln.setCellValueFactory(new PropertyValueFactory<Person, String>("ln"));
        o.setCellValueFactory(new PropertyValueFactory<Person, String>("o"));

        dbTableViewController = new DBTableViewController();
        List<Person> persons = dbTableViewController.getAllPersons();

        if (table != null) {
            System.out.println("FXCollections.observableArrayList(persons)");
            table.setItems(FXCollections.observableArrayList(persons));
        } else {
            System.out.println("table is null");
        }
    }

    public void onClose() {
        dbTableViewController.close();
    }
}
