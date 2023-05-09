package com.mousuf.rarestem.loggingSys.logInController;

import com.mousuf.rarestem.loggingSys.loggingSysModel.LoggingModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

/*
* These are my code, code inspired from YoutTube Tutorial:
* WittCode (2021). JavaFX Login and Signup Form with Database Connection. [online] YouTube.
* Available at: https://www.youtube.com/watch?v=ltX5AtW9v30&t=2582s [Accessed 22 Apr. 2023].
*
* */

public class LogInController implements Initializable {
    @FXML
    private Button button_logIn;
    @FXML
    private Button button_signUp;
    @FXML
    private Button button_exit;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_password;

    /*
     * These are my codes.
     * button_logIn was inspired by YouTube Tutorial but created by me. See documentation for further reference.
     * */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_logIn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoggingModel.logInUsers(event, tf_email.getText(), tf_password.getText());
            }
        });

        button_signUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    LoggingModel.changeScene(event, "src/main/resources/com/mousuf/rarestem/sign-up.fxml", "Sign Up", null);
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
