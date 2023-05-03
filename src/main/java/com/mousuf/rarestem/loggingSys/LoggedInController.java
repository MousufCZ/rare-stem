package com.mousuf.rarestem.loggingSys;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class LoggedInController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_logOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
    }

    public void setUserInformation(String username) {
        label_loggedInName.setText(username);
    }
}
