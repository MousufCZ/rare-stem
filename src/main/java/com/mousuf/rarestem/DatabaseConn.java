package com.mousuf.rarestem;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.mongodb.client.result.UpdateResult;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseConn {
    public static void main( String[] args ) {
    /*
     * Credential protection of MongoDB Connection URI with the
     * use of enviorment viarble file .env, and the use of
     * dotenv-java to recall the config file variable. The reuse
     * of code are from https://github.com/cdimascio/dotenv-java/
     *
     * Documentation rules:
     * https://www.oracle.com/uk/technical-resources/articles/java/javadoc-tool.html
     *
     * @param uri  the string to call MongoDB Connection String
     *             URI.
     */
        // Load environment variables from .env file
        Dotenv dotenv = Dotenv.load();

        // Get the value of the API_KEY environment variable
        String uri;
        uri = dotenv.get("MONGODB_URI");
        // Use the API key in your application
        System.out.println("MongoDB URI: " + uri);

        MongoClient client = MongoClients.create(uri);

        MongoDatabase db = client.getDatabase("rs-db");
        System.out.println("Get Database successful.");
        MongoCollection<Document> col = db.getCollection("users");
        System.out.println("Get collection successful.");

        Document query = new Document("first_name", "Adam");
        Document result = col.find(query).first();
        if (result != null) {
            String id = result.getObjectId("_id").toString();
            //String id = result.getString("_id");
            System.out.println("User ID: " + id);
        } else {
            System.out.println("User not found");
        }


        //MongoClient client = MongoClients.create("mongodb+srv://mousufbyae:k0VDzzU9vOGItt6n@cluster0.uuqglsd.mongodb.net/test");
/*        MongoDatabase db = client.getDatabase("rs-db");
        System.out.println("Get Database successful.");
        MongoCollection col = db.getCollection("users");
        //MongoCollection<Document> col = db.getCollection("users");
        System.out.println("Get collection successful.");

        Document query = new Document("username", "adam");
        Document result = col.find(query).first();
        if (result != null) {
            String id = result.getString("_id");
            System.out.println("User ID: " + id);
        } else {
            System.out.println("User not found");
        }*/

/*        Document query = new Document("protein_id", "0001");
        Document result = col.find(query).first();

        if (result != null) {
            System.out.println(result.toJson());
        } else {
            System.out.println("No document found.");
        }*/

/*        Document query =
                new Document("$or", Arrays.asList(
                        new Document("protien_id", "0001"),
                        new Document("protien_id", "0002")));
        List results = new ArrayList<>();
        col.find(query).into(results);
        if (results.isEmpty()) {
            System.out.println("No results found.");
        } else {
            for (Object doc : results) {
                System.out.println(doc.toString());
            }
        }*/

        //int userID = 32;
/*        Document query = new Document("age", "33");
        Document result = col.find(query).first();
        String id = result.getString("_id");
        System.out.println(id);*/

        /*if (result != null) {
            String id = result.getString("_id");
            System.out.println("User ID: " + id);
        } else {
            System.out.println("User not found");
        }*/

/*        Document filter = new Document("_id", "5");
        col.deleteOne(filter);*/

/*        Document filter = new Document("_id", "6");
        Document update = new Document("$set", new Document("age", "35"));
        col.updateOne(filter, update);*/

/*        // Test DB works(uncomment)
        //Document sampleDoc = new Document("_id", "2").append("gene_id", "FBN1+");
        Document sampleDoc2 = new Document("_id", "8").append("age","33");
        //col.insertOne(sampleDoc);
        UpdateResult updateResult = col.updateOne(sampleDoc2);*/

    }
}