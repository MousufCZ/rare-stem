package com.mousuf.rarestem.projContributionSys.getContrib;

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
    private Label label_loggedInName = new Label();
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
    private TableColumn<GetContrib, String> tc_projectDesc;

    @FXML
    private TableColumn<GetContrib, String> tc_projectName;

    @FXML
    private TableColumn<GetContrib, String> tc_projectOwner;

    @FXML
    private TableColumn<GetContrib, String> tc_projectURL;
    @FXML
    public TableView<GetContrib> tableContrib;
    private GetContribModel getContribModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tc_projectName.setCellValueFactory(new PropertyValueFactory<GetContrib, String>("project_name"));
        tc_projectDesc.setCellValueFactory(new PropertyValueFactory<GetContrib, String>("project_description"));
        tc_projectOwner.setCellValueFactory(new PropertyValueFactory<GetContrib, String>("project_owner"));
        tc_projectURL.setCellValueFactory(new PropertyValueFactory<GetContrib, String>("projURL"));

        getContribModel = new GetContribModel();
        List<GetContrib> getContribs = getContribModel.getAllContribs();
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
