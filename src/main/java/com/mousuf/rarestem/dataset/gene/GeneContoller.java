package com.mousuf.rarestem.dataset.gene;

import com.mousuf.rarestem.loggingSys.loggingSysModel.LoggingModel;
import com.mousuf.rarestem.projContributionSys.Contrib;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GeneContoller implements Initializable {
    @FXML
    private Button button_viewDatasets;
    @FXML
    private Button button_home;
    @FXML
    private Button button_userProjects;
    @FXML
    private Button button_logOut;
    @FXML
    private Button button_exit;
    @FXML
    private Button button_addProject;

    @FXML
    private TableColumn<Gene, String> col_position_g_mrna_end;

    @FXML
    private TableColumn<Gene, String> col_gene_id;

    @FXML
    private TableColumn<Gene, String> col_gene_type;

    @FXML
    private TableColumn<Gene, String> col_id_protein_ncbi;

    @FXML
    private TableColumn<Gene, String> col_name;

    @FXML
    private TableColumn<Gene, String> col_ncbi_mRna_id;

    @FXML
    private TableColumn<Gene, String> col_position_c_mrna_end;

    @FXML
    private TableColumn<Gene, String> col_position_c_mrna_start;

    @FXML
    private TableColumn<Gene, String> col_position_c_cds_end;

    @FXML
    private TableColumn<Gene, String> col_position_g_mrna_start;

    @FXML
    private TableView<Gene> tableGene;
    private GeneModel geneModel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_gene_id.setCellValueFactory(new PropertyValueFactory<Gene, String>("gene_id"));
        col_gene_type.setCellValueFactory(new PropertyValueFactory<Gene, String>("gene_type"));
        col_name.setCellValueFactory(new PropertyValueFactory<Gene, String>("name"));
        col_ncbi_mRna_id.setCellValueFactory(new PropertyValueFactory<Gene, String>("ncbi_mRna_id"));
        col_id_protein_ncbi.setCellValueFactory(new PropertyValueFactory<Gene, String>("id_protein_ncbi"));
        col_position_c_mrna_start.setCellValueFactory(new PropertyValueFactory<Gene, String>("position_c_mrna_start"));
        col_position_c_mrna_end.setCellValueFactory(new PropertyValueFactory<Gene, String>("position_c_mrna_end"));
        col_position_c_cds_end.setCellValueFactory(new PropertyValueFactory<Gene, String>("position_c_cds_end"));
        col_position_g_mrna_start.setCellValueFactory(new PropertyValueFactory<Gene, String>("position_g_mrna_start"));
        col_position_g_mrna_end.setCellValueFactory(new PropertyValueFactory<Gene, String>("position_g_mrna_end"));

        geneModel = new GeneModel();
        List<Gene> genes = null;
        try {
            genes = geneModel.getAllGene();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (tableGene != null) {
            System.out.println("FXCollections.observableArrayList(genes)");
            tableGene.setItems(FXCollections.observableArrayList(genes));
        } else {
            System.out.println("table is null");
        }

        button_home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("LoggedInController#button_MyProfile: About to initiate addProject EventHandling");
                try {
                    LoggingModel.changeScene(event, "src/main/resources/com/mousuf/rarestem/logged-in.fxml", "Welcome", null);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        button_addProject.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("LoggedInController#button_addProject: About to initiate addProject EventHandling");
                try {
                    LoggingModel.changeScene(event, "src/main/resources/com/mousuf/rarestem/proj-contrib.fxml", "Add Project", null);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        button_userProjects.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("LoggedInController#button_addProject: About to initiate addProject EventHandling");
                try {
                    LoggingModel.changeScene(event, "src/main/resources/com/mousuf/rarestem/user-contributions.fxml", "Add Project", null);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        button_viewDatasets.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("LoggedInController#button_addProject: About to initiate addProject EventHandling");
                try {
                    LoggingModel.changeScene(event, "src/main/resources/com/mousuf/rarestem/dataset.fxml", "Add Project", null);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        button_logOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("We are about to run log-in.fxml from LoggedInController");
                try {
                    LoggingModel.changeScene(event, "src/main/resources/com/mousuf/rarestem/log-in.fxml", "Rare Stem", null);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        button_exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("LoggedInController#button_logOut: Closing connection");
                System.out.println("LoggedInController#button_logOut: About to close the application");
                Platform.exit();
            }
        });
    }
}
