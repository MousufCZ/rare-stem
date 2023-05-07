package com.mousuf.rarestem;

import com.mousuf.rarestem.loggingSys.LoggingModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("table-view-sample-test.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 700);
        stage.setTitle("Rare Stem Prototype");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}