package com.mousuf.rarestem.datasetSys.datasetController;

import com.mousuf.rarestem.loggingSys.loggingSysModel.LoggingModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

/*
* These are my codes
* */

public class DatasetController implements Initializable {

    @FXML
    private Button button_gene;
    @FXML
    private Button button_viewCOL1A2;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       button_gene.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    LoggingModel.changeScene(event, "src/main/resources/com/mousuf/rarestem/gene.fxml", "Gene Datasets", null);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        button_viewCOL1A2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    LoggingModel.changeScene(event, "src/main/resources/com/mousuf/rarestem/viewCOL1A2.fxml", "COL1A2", null);
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
                    LoggingModel.changeScene(event, "src/main/resources/com/mousuf/rarestem/user-contributions.fxml", "User Contributions", null);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        button_viewDatasets.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    LoggingModel.changeScene(event, "src/main/resources/com/mousuf/rarestem/dataset.fxml", "Datasets", null);
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