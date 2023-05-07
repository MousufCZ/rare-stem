package com.mousuf.rarestem.projContributionSys.userContrib;

import com.mousuf.rarestem.tableViewTest.Person;
import com.mousuf.rarestem.tableViewTest.dbTableView.DBTableViewModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserContribController implements Initializable {
    @FXML
    private TableColumn<Person, String> fn;

    @FXML
    private TableColumn<Person, String> ln;

    @FXML
    private TableColumn<Person, String> o;

    @FXML
    public TableView<Person> tableContrib;

    private UserContribModel userContribModel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fn.setCellValueFactory(new PropertyValueFactory<Person, String>("fn"));
        ln.setCellValueFactory(new PropertyValueFactory<Person, String>("ln"));
        o.setCellValueFactory(new PropertyValueFactory<Person, String>("o"));

        userContribModel = new UserContribModel();
        List<Person> persons = userContribModel.getAllContibution();

        if (tableContrib != null) {
            System.out.println("FXCollections.observableArrayList(persons)");
            tableContrib.setItems(FXCollections.observableArrayList(persons));
        } else {
            System.out.println("table is null");
        }
    }

    public void onClose() {
        userContribModel.close();
    }
    }
