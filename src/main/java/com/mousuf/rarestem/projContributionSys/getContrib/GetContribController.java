package com.mousuf.rarestem.projContributionSys.getContrib;

import com.mousuf.rarestem.projContributionSys.Contrib;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GetContribController implements Initializable {

    @FXML
    private Label label_loggedInName;
    @FXML
    private Button button_viewDatasets;
    @FXML
    private Button button_myProfile;
    @FXML
    private Button button_myActivity;
    @FXML
    private Button button_logOut;
    @FXML
    private Button button_exit;
    @FXML
    private Button button_addProject;
    // Table
    @FXML
    private TableColumn<Contrib, String> tc_projectDesc;

    @FXML
    private TableColumn<Contrib, String> tc_projectName;

    @FXML
    private TableColumn<Contrib, String> tc_projectOwner;

    @FXML
    private TableColumn<Contrib, String> tc_projectURL;
    @FXML
    public TableView<Contrib> tableContrib;
    private GetContribModel getContribModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tc_projectName.setCellValueFactory(new PropertyValueFactory<Contrib, String>("tc_projectName"));
        tc_projectDesc.setCellValueFactory(new PropertyValueFactory<Contrib, String>("tc_projectDesc"));
        tc_projectOwner.setCellValueFactory(new PropertyValueFactory<Contrib, String>("tc_projectOwner"));
        tc_projectURL.setCellValueFactory(new PropertyValueFactory<Contrib, String>("tc_projectURL"));

        getContribModel = new GetContribModel();
        List<Contrib> getContribs = getContribModel.getAllContribs();
        if (tableContrib != null) {
            System.out.println("FXCollections.observableArrayList(persons)");
            tableContrib.setItems(FXCollections.observableArrayList(getContribs));
        } else {
            System.out.println("table is null");
        }
    }
    public void onClose() {
        getContribModel.close();
    }
}
