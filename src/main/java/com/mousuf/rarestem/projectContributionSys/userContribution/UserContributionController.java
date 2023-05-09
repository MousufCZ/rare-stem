package com.mousuf.rarestem.projectContributionSys.userContribution;
import com.mousuf.rarestem.loggingSys.loggingSysModel.LoggingModel;
import com.mousuf.rarestem.projectContributionSys.Contribution;
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
 * These are my codes
 * */

public class UserContributionController implements Initializable {
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
    private TableColumn<Contribution, String> col_projectName;

    @FXML
    private TableColumn<Contribution, String> col_projectOwner;

    @FXML
    private TableColumn<Contribution, String> col_projectDesc;
    @FXML
    private TableColumn<Contribution, String> col_projectGetInTouch;

    @FXML
    public TableView<Contribution> tableContrib;

    private UserContributionModel userContributionModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_projectName.setCellValueFactory(new PropertyValueFactory<Contribution, String>("projectName"));
        col_projectOwner.setCellValueFactory(new PropertyValueFactory<Contribution, String>("projectOwner"));
        col_projectDesc.setCellValueFactory(new PropertyValueFactory<Contribution, String>("projectDesc"));
        col_projectGetInTouch.setCellValueFactory(new PropertyValueFactory<Contribution, String>("email"));

        userContributionModel = new UserContributionModel();
        List<Contribution> persons = null;
        try {
            persons = userContributionModel.getAllContibution();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        if (tableContrib != null) {
            tableContrib.setItems(FXCollections.observableArrayList(persons));
        } else {
            System.out.println("table is null");
        }

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
                    LoggingModel.changeScene(event, "src/main/resources/com/mousuf/rarestem/dataset.fxml", "Dataset", null);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        button_exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                userContributionModel.close();
                Platform.exit();
            }
        });

    }

    public void onClose() {
        userContributionModel.close();
    }
}