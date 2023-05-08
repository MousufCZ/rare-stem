package com.mousuf.rarestem.statisticalAnalysis;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;

public class LookUpToDS {
    private MongoClient client;
    private MongoDatabase db;
    private MongoCollection<Document> col;

    public LookUpToDS(){
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
        col = db.getCollection("FBN1_individuals");
        System.out.println("Get collection successful.");

        Document query = new Document("Individual_Origin_Population", "russian");
        Document result = col.find(query).first();
        if (result != null) {
            //String id = String.valueOf(result.getInteger("variant_id"));
            Integer id = result.getInteger("Individual_id");
            System.out.println("User ID: " + id);
        } else {
            System.out.println("User not found");
        }
    }

    public static void main (String[] args){
        LookUpToDS lookUpToDS = new LookUpToDS();
    }

}
