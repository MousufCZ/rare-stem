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
import javafx.stage.Stage;

import java.io.IOException;
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
                System.out.println("We are about to run signup.fxml");
                /*
                 * Original code:
                 * LoggingModel.changeScene(event, "/sign-up.fxml", "Sign Up", null);
                 * Debugging code to import Scene directly: https://www.youtube.com/watch?v=mokD1I7hl-o&list=PLS1QulWo1RIaUGP446_pWLgTZPiFizEMq&index=11
                 * */

                Stage primaryStage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sign-up.fxml"));
                System.out.println("past signup.fxml");

                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), 1280, 700);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                primaryStage.setTitle("Rare Stem Prototype");
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });

    }
}











