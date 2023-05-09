package com.mousuf.rarestem.projectContributionSys.addContribution;

import com.mousuf.rarestem.loggingSys.loggingSysModel.LoggingModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import lombok.Data;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

/*
 * These are my codes
 * */

@Data
public class ProjectContributionController implements Initializable {
    @FXML
    private Label label_loggedInName = new Label();
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
    private TextField tf_projName;
    @FXML
    private TextField tf_projDesc;
    @FXML
    private TextField tf_projOwner;
    @FXML
    private TextField tf_projOwnerEmail;
    @FXML
    private TextField tf_projURL;

    @FXML
    private Button button_submitProject;

    public void initialize(URL url, ResourceBundle resourceBundle) {
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
            }
        });

        button_submitProject.setOnAction(new EventHandler<ActionEvent>() {
            /*
            *  .trim()
            *  https://www.geeksforgeeks.org/java-string-trim-method-example/
            * */
            @Override
            public void handle(ActionEvent event) {
                if(!tf_projName.getText().trim().isEmpty() && !tf_projDesc.getText().trim().isEmpty() &&
                        !tf_projOwner.getText().trim().isEmpty() && !tf_projOwnerEmail.getText().trim().isEmpty() &&
                        !tf_projURL.getText().trim().isEmpty()){
                    ProjectContributionModel.submitProjectContrib(event, tf_projName.getText(), tf_projDesc.getText(),
                            tf_projOwner.getText(), tf_projOwnerEmail.getText(), tf_projURL.getText());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all fields");
                    alert.show();
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

        button_exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
    }
}
