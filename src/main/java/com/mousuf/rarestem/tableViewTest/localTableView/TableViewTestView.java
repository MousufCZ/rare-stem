package com.mousuf.rarestem.tableViewTest.localTableView;

import com.mousuf.rarestem.Main;
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
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("table-view-sample-test.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 700);
        stage.setTitle("TableViewTest");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
