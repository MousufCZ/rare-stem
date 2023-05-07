package com.mousuf.rarestem.loggingSys;

import com.mousuf.rarestem.HelloApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;
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
    @FXML
    private TableColumn<?, ?> tc_projectDesc;

    @FXML
    private TableColumn<?, ?> tc_projectName;

    @FXML
    private TableColumn<?, ?> tc_projectOwner;

    @FXML
    private TableColumn<?, ?> tc_projectURL;
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











