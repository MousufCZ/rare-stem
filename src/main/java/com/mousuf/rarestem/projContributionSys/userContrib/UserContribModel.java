package com.mousuf.rarestem.projContributionSys.userContrib;
import com.mousuf.rarestem.projContributionSys.Contrib;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
public class UserContribModel {
    private final MongoClient client;
    private final MongoDatabase db;
    private final MongoCollection<Document> col;

    public UserContribModel(){
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
        col = db.getCollection("user_proj");
        System.out.println("Get collection successful.");
    }

    public List<Contrib> getAllContibution() throws MalformedURLException {
        List<Contrib> persons = new ArrayList<>();

        for (Document doc : col.find()) {
            String projectName = doc.getString("project_name");
            String projectDesc = doc.getString("project_description");
            String projectOwner = doc.getString("project_owner");
            String email = doc.getString("email");
            persons.add(new Contrib(projectName, projectDesc, projectOwner, email));
        }
        return persons;
    }
    public static void main(String[] args) throws MalformedURLException {
        UserContribModel obj = new UserContribModel();
        List<Contrib> persons = obj.getAllContibution();
        persons.forEach(c -> System.out.println(c));
    }
    public void close() {
        client.close();

    }
}
