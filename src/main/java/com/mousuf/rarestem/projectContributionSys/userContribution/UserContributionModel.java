package com.mousuf.rarestem.projectContributionSys.userContribution;
import com.mousuf.rarestem.projectContributionSys.Contribution;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/*
* These are my code
* */

public class UserContributionModel {
    private final MongoClient client;
    private final MongoDatabase db;
    private final MongoCollection<Document> col;

    public UserContributionModel(){
        Dotenv dotenv = Dotenv.load();
        String uri;
        uri = dotenv.get("MONGODB_URI");
        client = MongoClients.create(uri);
        db = client.getDatabase("rs-db");
        col = db.getCollection("user_proj");
    }

    public List<Contribution> getAllContibution() throws MalformedURLException {
        List<Contribution> persons = new ArrayList<>();

        for (Document doc : col.find()) {
            String projectName = doc.getString("project_name");
            String projectDesc = doc.getString("project_description");
            String projectOwner = doc.getString("project_owner");
            String email = doc.getString("email");
            persons.add(new Contribution(projectName, projectDesc, projectOwner, email));
        }
        return persons;
    }
    public static void main(String[] args) throws MalformedURLException {
        UserContributionModel obj = new UserContributionModel();
        List<Contribution> persons = obj.getAllContibution();
        persons.forEach(c -> System.out.println(c));
    }

    public void close() {
        client.close();

    }
}