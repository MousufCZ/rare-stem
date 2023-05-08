package com.mousuf.rarestem.projContributionSys.addContrib;


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


@Data
public class ProjContribController implements Initializable {
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

    public void clearTextField(){
        getTf_projName().clear();
        getTf_projDesc().clear();
        getTf_projOwner().clear();
        getTf_projOwnerEmail().clear();
        getTf_projURL().clear();
    }

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
                    ProjContribModel.submitProjectContrib(event, tf_projName.getText(), tf_projDesc.getText(),
                            tf_projOwner.getText(), tf_projOwnerEmail.getText(), tf_projURL.getText());
                } else {
                    System.out.println("All fields in registration field requires filling in");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all fields");
                    alert.show();
                }
                //clearTextField();
            }
        });
        button_viewDatasets.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("LoggedInController#button_addProject: About to initiate addProject EventHandling");
                try {
                    LoggingModel.changeScene(event, "src/main/resources/com/mousuf/rarestem/dataset.fxml", "View Dataset", null);
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
                    LoggingModel.changeScene(event, "src/main/resources/com/mousuf/rarestem/user-contributions.fxml", "View Dataset", null);
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
