package com.mousuf.rarestem.loggingSys.loggingSysModel;


import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/*
 * Credential protection of MongoDB Connection URI with the
 * use of enviorment viarble file .env, and the use of
 * dotenv-java to recall the config file variable. The reuse
 * of code are from https://github.com/cdimascio/dotenv-java/
 *
 * These are my code, code inspired and reused from YoutTube Tutorial:
 * WittCode (2021). JavaFX Login and Signup Form with Database Connection. [online] YouTube.
 * Available at: https://www.youtube.com/watch?v=ltX5AtW9v30&t=2582s [Accessed 22 Apr. 2023].
 *
 * in addition, 5 lines in user signup and 4 in logIn users are reuse
 * */

public class LoggingModel {
    /*
    * See backlog id: blog.003
    * Urgent change of Sting email for future iteration.
    * */
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String email) throws MalformedURLException {
        Parent root = null;
        URL url = new File(fxmlFile).toURI().toURL();
        FXMLLoader loader = new FXMLLoader();
        if (email != null) {
            try {
                root = loader.load(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = loader.load(url);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1280, 700);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public static void signUpUser(ActionEvent event, String email, String password,
                                  String firstName, String surname, String role){

        MongoClient client = null;
        MongoDatabase db = null;

        try{
            Dotenv dotenv = Dotenv.load();
            String uri;
            uri = dotenv.get("MONGODB_URI");

            client = MongoClients.create(uri);
            db = client.getDatabase("rs-db");
            MongoCollection<Document> col = db.getCollection("users");

            Document query = new Document("email", email);
            Document result = col.find(query).first();
            if(result != null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("This email is already take.");
                alert.show();
            } else{
                Document document = new Document("email", email)
                        .append("password", password)
                        .append("first_name", firstName)
                        .append("surname", surname)
                        .append("role", role)
                        ;
                col.insertOne(document);
                changeScene(event, "src/main/resources/com/mousuf/rarestem/logged-in.fxml", "Welcome", email);
            }
        } catch(MongoException e){
            e.printStackTrace();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } finally {
            if (client != null){
                client.close();
            }
            if (db != null){
                client.close();
            }
        }
    }

    public static void logInUsers(ActionEvent event, String email, String password) {

        MongoClient client = null;
        MongoDatabase db = null;

        try {
            Dotenv dotenv = Dotenv.load();
            String uri;
            uri = dotenv.get("MONGODB_URI");
            System.out.println("LoggingModel#logInUser: MongoDB URI: " + uri);

            client = MongoClients.create(uri);
            db = client.getDatabase("rs-db");
            System.out.println("LoggingModel#logInUser: Get Database successful.");

            MongoCollection<Document> col = db.getCollection("users");
            System.out.println("LoggingModel#logInUser: Get collection successful.");

            Document query = new Document("email", email);
            Document result = col.find(query).first();
            if (result == null) {
                System.out.println("LoggingModel#logInUser: User Email: " + email + " does not exist!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("This user does not exist.");
                alert.show();
            } else {
                String getPassword = result.getString("password");
                if (password.equals(getPassword)) {
                    System.out.println("LoggingModel#logInUser: User log in username and password accepted");
                    changeScene(event, "src/main/resources/com/mousuf/rarestem/logged-in.fxml", "Welcome", email);
                } else {
                    System.out.println("Password did not match");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("The password is incorrect.");
                    alert.show();
                }
            }
        } catch (Exception e) {
            System.out.println("LoggingModel#logInUser: Email retrieving in logged in exception error");
            throw new RuntimeException(e);
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }
}







