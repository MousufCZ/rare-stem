package com.mousuf.rarestem.projectContributionSys.addContribution;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mousuf.rarestem.loggingSys.loggingSysModel.LoggingModel;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import org.bson.Document;

import java.net.MalformedURLException;

/*
* These are all my code
* */

public class ProjectContributionModel {

    public static void submitProjectContrib(ActionEvent event, String projName, String projDesc,
                                            String projOwner, String projOwnerEmail, String projURL){
        MongoClient client = null;
        MongoDatabase db = null;

        try{
            Dotenv dotenv = Dotenv.load();
            String uri;
            uri = dotenv.get("MONGODB_URI");
            client = MongoClients.create(uri);
            db = client.getDatabase("rs-db");
            MongoCollection<Document> col = db.getCollection("user_proj");

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
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Project added.");
                alert.show();
                // Commented out for URI test
                LoggingModel.changeScene(event, "src/main/resources/com/mousuf/rarestem/user-contributions.fxml", "User Projects", null);
            } else{
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