package com.mousuf.rarestem.loggingSys;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoggedInController implements Initializable {

    /*
    * This is my code and I built it by learning from Java in Two Semester: Featuring JavaFX (Autor: Quentin Charatan, Kans and Springerlink (Online Service, 2019)
    * */

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
            public void handle(ActionEvent event) {
                System.out.println("We are about to run log-in.fxml from LoggedInController");
                try {
                    LoggingModel.changeScene(event, "src/main/resources/com/mousuf/rarestem/log-in.fxml", "Rare Stem", null);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void setUserInformation(String email) {
        label_loggedInName.setText(email);
    }
}
