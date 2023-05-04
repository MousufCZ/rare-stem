package com.mousuf.rarestem.loggingSys;


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
import org.bson.Document;
import com.mongodb.MongoException;
import com.mongodb.MongoCredential;
import com.mongodb.ConnectionString;

import java.io.IOException;

public class LoggingModel {
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String email) {
        Parent root = null;
        if (email != null) {
            try {
                FXMLLoader loader = new FXMLLoader(LoggingModel.class.getResource(fxmlFile));
                root = loader.load();
                LoggedInController loggedInController = loader.getController();
                loggedInController.setUserInformation(email);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(LoggingModel.class.getResource(fxmlFile));
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

            MongoCollection<Document> col = db.getCollection("users");
            BasicDBObject query = new BasicDBObject();
            query.put("email", email);
            FindIterable<Document> iterable = col.find(query);
            Document result = iterable.first();
            if(result != null){
                System.out.println("Sign up email: " + email + " already exists.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("This email is already take.");
                alert.show();
            } else{
                // Insert new user
                Document document = new Document("email", email)
                        .append("password", password)
/*                        .append("first_name", firstName)
                        .append("surname", surname)
                        .append("role", role)*/
                        ;
                col.insertOne(document);
                System.out.println("User created successfully!");
                changeScene(event, "logged-in.fxml", "Welcome", email);
            }
        } catch(MongoException e){
            e.printStackTrace();
        } finally {
            if (client != null){
                client.close();
            }
            if (db != null){
                client.close();
            }
        }
    }

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

            // Check if user email exists
            MongoCollection<Document> col = db.getCollection("users");
            BasicDBObject query = new BasicDBObject();
            query.put("email", email);
            FindIterable<Document> iterable = col.find(query);
            Document result = iterable.first();

            if (result == null){
                System.out.println("User Email: " + email + " does not exist!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("This user does not exist.");
                alert.show();
            } else{
                String getPassword = result.getString("password");
                if(password.equals(getPassword)){
                    System.out.println("User log in username and password accepted");
                    changeScene(event, "logged-in.fxml", "Welcome", email);
                } else {
                    System.out.println("Password did not match");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("The password is incorrect.");
                    alert.show();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (client != null){
                client.close();
            }
        }
    }
}







