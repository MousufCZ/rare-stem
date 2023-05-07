package com.mousuf.rarestem.tableViewTest.dbTableView;
import com.mousuf.rarestem.tableViewTest.Person;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mousuf.rarestem.tableViewTest.Person;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.fxml.FXML;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


public class DBTableViewModel {
    private final MongoClient client;
    private final MongoDatabase db;
    private final MongoCollection<Document> col;

    public DBTableViewModel() {

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
        col = db.getCollection("person");
        System.out.println("Get collection successful.");

    }

    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();

        for (Document doc : col.find()) {
            String fn = doc.getString("fn");
            String ln = doc.getString("ln");
            String o = doc.getString("o");

            persons.add(new Person(fn, ln, o));
        }

        return persons;
    }

    public void close() {
        client.close();
    }
}


