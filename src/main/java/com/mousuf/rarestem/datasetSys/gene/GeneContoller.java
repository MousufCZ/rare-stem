package com.mousuf.rarestem.datasetSys.gene;

import com.mousuf.rarestem.loggingSys.loggingSysModel.LoggingModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/*
 * These are all my code.
 * I got inspiration from YoutTube: https://www.youtube.com/watch?v=V9nDH2iBJSM&t=962s
 * See tableViewTest package in Git repository version:
 * Commit id: 72fb4cb3
 * Revision no: 72fb4cb3be71151aa8e5882eabd084c472808210
 *
 * Degubbed code using the following documentations and forums:
 * https://stackoverflow.com/questions/28387446/tablecolumn-not-populating-integer-values
 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TableColumn.html
 * https://gist.github.com/sharifulislam52/d17b4e1654a8214046d409b0a7d63c3b
 * https://www.youtube.com/watch?v=3tmz-0g3EPs
 * */

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
            tableGene.setItems(FXCollections.observableArrayList(genes));
        } else {
            System.out.println("table is null");
        }

        button_home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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
                try {
                    LoggingModel.changeScene(event, "src/main/resources/com/mousuf/rarestem/user-contributions.fxml", "User Projects", null);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        button_viewDatasets.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    LoggingModel.changeScene(event, "src/main/resources/com/mousuf/rarestem/dataset.fxml", "View Datasets", null);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        button_logOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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
                Platform.exit();
            }
        });
    }
}