package com.mousuf.rarestem.loggingSys.loggedIn;

import com.mousuf.rarestem.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoggedInView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("logged-in.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("logged-in.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 700);
        stage.setTitle("Welcome");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
