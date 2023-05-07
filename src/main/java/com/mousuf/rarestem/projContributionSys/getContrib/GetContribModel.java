package com.mousuf.rarestem.projContributionSys.getContrib;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class GetContribModel {
    private final MongoClient client;
    private final MongoDatabase db;
    private final MongoCollection<Document> col;

    public GetContribModel(){
        // Connect to MongoDB
        // Load environment variables from .env file
        Dotenv dotenv = Dotenv.load();
        // Get the value of the API_KEY environment variable
        String uri;
        uri = dotenv.get("MONGODB_URI");
        // Use the API key in your application
        System.out.println("MongoDB URI: " + uri);
        // create a MongoDB client
        client = MongoClients.create(uri);
        // get a database instance
        db = client.getDatabase("rs-db");
        System.out.println("Get Database successful.");
        // get a collection instance
        col = db.getCollection("user-proj");
        System.out.println("Get collection successful.");
    }

    public List<GetContrib> getAllContribs(){
        List<GetContrib> getContribs = new ArrayList<>();

        for (Document doc : col.find()) {
            String tc_projectName = doc.getString("project_name");
            String tc_projectDesc = doc.getString("project_description");
            String tc_projectOwner = doc.getString("project_owner");
            String tc_projectURL = doc.getString("projURL");
            getContribs.add(new GetContrib(tc_projectName, tc_projectDesc, tc_projectOwner, tc_projectURL));
        }
        return getContribs;
    }
    public void close() {
        client.close();
    }

    public static void main(String[] args) {
        GetContribModel obj = new GetContribModel();
        List<GetContrib> contribs = obj.getAllContribs();
        contribs.forEach(c -> System.out.println(c));
    }
}
