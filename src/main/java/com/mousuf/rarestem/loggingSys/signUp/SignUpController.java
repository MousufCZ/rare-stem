package com.mousuf.rarestem.loggingSys.signUp;

import com.mousuf.rarestem.loggingSys.loggingSysModel.LoggingModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private Button button_login;
    @FXML
    private Button button_register;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_password;
    @FXML
    private TextField tf_firstName;
    @FXML
    private TextField tf_surname;
    @FXML
    private RadioButton radioButton_technical;
    @FXML
    private RadioButton radioButton_researcher;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButton_technical.setToggleGroup(toggleGroup);
        radioButton_researcher.setToggleGroup(toggleGroup);
        radioButton_technical.setSelected(true);

        button_register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String toggleName = ((RadioButton) toggleGroup.getSelectedToggle()).getText();

                if(!tf_email.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty() &&
                        !tf_firstName.getText().trim().isEmpty() && !tf_surname.getText().trim().isEmpty()){
                    LoggingModel.signUpUser(event, tf_email.getText(), tf_password.getText(),
                            tf_firstName.getText(), tf_surname.getText(), toggleName);
                } else {
                    System.out.println("All fields in registration field requires filling in");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all fields");
                    alert.show();
                }
            }
        });
        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("We are about to run log-in.fxml from SignUpController");
                try {
                    LoggingModel.changeScene(event, "src/main/resources/com/mousuf/rarestem/log-in.fxml", "Log In", null);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}




