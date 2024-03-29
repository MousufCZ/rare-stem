package com.mousuf.rarestem.dataset.col1a2;

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

public class COL1A2Controller implements Initializable {
    @FXML
    private Button button_addProject;

    @FXML
    private Button button_exit;

    @FXML
    private Button button_home;

    @FXML
    private Button button_logOut;

    @FXML
    private Button button_userProjects;

    @FXML
    private Button button_viewDatasets;

    @FXML
    private TableColumn<COL1A2, Integer> col_allele;

    @FXML
    private TableColumn<COL1A2, String> col_average_frequency;

    @FXML
    private TableColumn<COL1A2, Integer> col_effect_id;

    @FXML
    private TableColumn<COL1A2, Integer> col_position_g_end;

    @FXML
    private TableColumn<COL1A2, Integer> col_position_g_start;

    @FXML
    private TableColumn<COL1A2, String> col_variantOnGenome_DBID;

    @FXML
    private TableColumn<COL1A2, String> col_variantOnGenome_DNA;

    @FXML
    private TableColumn<COL1A2, Integer> col_variants_on_genome_id;

    @FXML
    private TableView<COL1A2> tableCOL1A2;
    private COL1A2Model cOL1A2Model;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_variants_on_genome_id.setCellValueFactory(new PropertyValueFactory<COL1A2, Integer>("variants_on_genome_id"));
        col_allele.setCellValueFactory(new PropertyValueFactory<COL1A2, Integer>("allele"));
        col_effect_id.setCellValueFactory(new PropertyValueFactory<COL1A2, Integer>("effect_id"));
        col_position_g_start.setCellValueFactory(new PropertyValueFactory<COL1A2, Integer>("position_g_start"));
        col_position_g_end.setCellValueFactory(new PropertyValueFactory<COL1A2, Integer>("position_g_end"));
        col_average_frequency.setCellValueFactory(new PropertyValueFactory<COL1A2, String>("average_frequency"));
        col_variantOnGenome_DBID.setCellValueFactory(new PropertyValueFactory<COL1A2, String>("variantOnGenome_DBID"));
        col_variantOnGenome_DNA.setCellValueFactory(new PropertyValueFactory<COL1A2, String>("variantOnGenome_DNA"));

        cOL1A2Model = new COL1A2Model();
        List<COL1A2> cOL1A2Collection = null;
        try {
            cOL1A2Collection = cOL1A2Model.getAllCOL1A2();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (tableCOL1A2 != null) {
            System.out.println("FXCollections.observableArrayList(genes)");
            tableCOL1A2.setItems(FXCollections.observableArrayList(cOL1A2Collection));
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
