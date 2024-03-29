
package com.mousuf.rarestem.loggingSys.loggedIn;

import com.mousuf.rarestem.loggingSys.loggingSysModel.LoggingModel;
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

public class LoggedInController implements Initializable {

    /*
    * This is my code and I built it by learning from Java in Two Semester: Featuring JavaFX (Autor: Quentin Charatan, Kans and Springerlink (Online Service, 2019)
    * */

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
    // Table

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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