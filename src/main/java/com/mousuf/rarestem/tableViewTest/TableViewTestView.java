package com.mousuf.rarestem.tableViewTest;

import com.mousuf.rarestem.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/*
*  This code is an experimentation code from
*  Github project: https://github.com/kensoftphDOTcom/JavaFX-TableView/
*  */

public class TableViewTestView extends Application{
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/resources/com/mousuf/rarestem/table-view.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("JavaFX TableView");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
