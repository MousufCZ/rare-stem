package com.mousuf.rarestem.projContributionSys.addContrib;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import org.bson.Document;

import java.net.MalformedURLException;

import static com.mousuf.rarestem.loggingSys.loggingSysModel.LoggingModel.changeScene;

public class ProjContribModel {

    public static void submitProjectContrib(ActionEvent event, String projName, String projDesc,
                                            String projOwner, String projOwnerEmail, String projURL){
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
            MongoCollection<Document> col = db.getCollection("user_proj");
            System.out.println("Get collection successful.");

            Document query = new Document("email", projOwnerEmail);
            Document result = col.find(query).first();
            if(result != null){
                // Insert new project
                Document document = new Document("email", projOwnerEmail)
                        .append("project_name", projName)
                        .append("project_owner", projOwner)
                        .append("project_description", projDesc)
                        .append("projURL", projURL);
                col.insertOne(document);
                System.out.println("Contribution created  successfully!");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Project added.");
                alert.show();
                // Commented out for URI test
                changeScene(event, "src/main/resources/com/mousuf/rarestem/logged-in.fxml", "Welcome", null);
            } else{
                System.out.println("Sign up email: " + projOwnerEmail + " does not exist. Ensure it is the same email as you used to sign up.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("This email does not exist. Ensure it is the same email as you used to sign up.");
                alert.show();
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
}
