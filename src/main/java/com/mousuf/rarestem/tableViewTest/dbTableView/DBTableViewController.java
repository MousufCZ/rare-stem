package com.mousuf.rarestem.tableViewTest.dbTableView;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mousuf.rarestem.tableViewTest.Person;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.bson.Document;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
public class DBTableViewController implements Initializable {
@FXML
private TableColumn<Person, String> fn;

@FXML
private TableColumn<Person, String> ln;

@FXML
private TableColumn<Person, String> o;

@FXML
public TableView<Person> table;

private DBTableViewModel dbTableViewModel;

@Override
public void initialize(URL url, ResourceBundle resourceBundle) {
        fn.setCellValueFactory(new PropertyValueFactory<Person, String>("fn"));
        ln.setCellValueFactory(new PropertyValueFactory<Person, String>("ln"));
        o.setCellValueFactory(new PropertyValueFactory<Person, String>("o"));

        dbTableViewModel = new DBTableViewModel();
        List<Person> persons = dbTableViewModel.getAllPersons();

        if (table != null) {
            System.out.println("FXCollections.observableArrayList(persons)");
            table.setItems(FXCollections.observableArrayList(persons));
        } else {
        System.out.println("table is null");
        }
        }

    public void onClose() {
        dbTableViewModel.close();
        }
}