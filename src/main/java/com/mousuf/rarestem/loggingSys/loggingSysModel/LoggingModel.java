package com.mousuf.rarestem.loggingSys.loggingSysModel;


import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;



import io.github.cdimascio.dotenv.Dotenv;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;
import com.mongodb.MongoException;
import com.mongodb.MongoCredential;
import com.mongodb.ConnectionString;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class LoggingModel {
    /*
    * Faced location null exception
    * */
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String email) throws MalformedURLException {
        Parent root = null;
        URL url = new File(fxmlFile).toURI().toURL();
        FXMLLoader loader = new FXMLLoader();
        if (email != null) {
            try {
                root = loader.load(url);

/*                // Code to implement welcome page
                URL loggiedin_url = new File("src/main/resources/com/mousuf/rarestem/logged-in.fxml").toURI().toURL();
                root = loader.load(loggiedin_url);
                System.out.println("CHECK: LogginModel: trying to get loggedincontroller()");*/
/*                LoggedInController loggedInController = loader.getController();
                loggedInController.setUserInformation(email);*/
                //LoggedInController loggedInController = loader.getController();

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

    /*
    * ========================================================
    * I used the MongoDB setup code DatabaseConn.java.
    * I build a dummy User Database with email and password.
    * ========================================================
    * */

    public static void signUpUser(ActionEvent event, String email, String password,
                                  String firstName, String surname, String role){

        MongoClient client = null;
        MongoDatabase db = null;

        try{
            Dotenv dotenv = Dotenv.load();
            String uri;
            uri = dotenv.get("MONGODB_URI");
            // Use the API key in your application
            System.out.println("MongoDB URI: " + uri);

            //create connection to "rs-db"
            client = MongoClients.create(uri);
            db = client.getDatabase("rs-db");
            System.out.println("Get Database successful.");
            MongoCollection<Document> col = db.getCollection("users");
            System.out.println("Get collection successful.");

            Document query = new Document("email", email);
            Document result = col.find(query).first();
            if(result != null){
                System.out.println("Sign up email: " + email + " already exists.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("This email is already take.");
                alert.show();
            } else{
                // Insert new user
                Document document = new Document("email", email)
                        .append("password", password)
                        .append("first_name", firstName)
                        .append("surname", surname)
                        .append("role", role)
                        ;
                col.insertOne(document);
                System.out.println("User created successfully!");
                // Commented out for URI test
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

    @Getter @Setter private String email;
    public static void logInUsers(ActionEvent event, String email, String password){

        MongoClient client = null;
        MongoDatabase db = null;

        try {
            Dotenv dotenv = Dotenv.load();
            String uri;
            uri = dotenv.get("MONGODB_URI");
            // Use the API key in your application
            System.out.println("MongoDB URI: " + uri);

            //create connection to "rs-db"
            client = MongoClients.create(uri);
            db = client.getDatabase("rs-db");
            System.out.println("Get Database successful.");
            MongoCollection<Document> col = db.getCollection("users");
            System.out.println("Get collection successful.");

            Document query = new Document("email", email);
            Document result = col.find(query).first();
            if (result == null){
                System.out.println("User Email: " + email + " does not exist!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("This user does not exist.");
                alert.show();
            } else{
                String getPassword = result.getString("password");
                if(password.equals(getPassword)){
                    System.out.println("User log in username and password accepted");
                    changeScene(event, "src/main/resources/com/mousuf/rarestem/logged-in.fxml", "Welcome", email);

/*                    // Code to implement welcome page
                    LoggedInController loggedInController = new LoggedInController();
                    loggedInController.setUserInformation(email);*/
                } else {
                    System.out.println("Password did not match");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("The password is incorrect.");
                    alert.show();
                }
            }
        } catch (Exception e) {
            System.out.println("CHECK: LoggingModel: Email retriving in logged in exception error");
            throw new RuntimeException(e);
        } finally {
            if (client != null){
                client.close();
            }
        }
    }
}







