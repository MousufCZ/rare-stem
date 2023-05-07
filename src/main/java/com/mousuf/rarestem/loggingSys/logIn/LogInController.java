package com.mousuf.rarestem.loggingSys.logIn;

import com.mousuf.rarestem.loggingSys.loggingSysModel.LoggingModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    @FXML
    private Button button_logIn;
    @FXML
    private Button button_signUp;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_password;

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
                System.out.println("CHECK: LogInController: We are about to run signup.fxml from LogInController");
                try {
                    LoggingModel.changeScene(event, "src/main/resources/com/mousuf/rarestem/sign-up.fxml", "Sign Up", null);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}











