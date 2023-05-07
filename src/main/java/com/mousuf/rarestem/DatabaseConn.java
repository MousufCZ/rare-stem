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
        MongoCollection<Document> col = db.getCollection("user_proj");
        System.out.println("Get collection successful.");

        int userID = 2942;
        //Document query = new Document("screening_id", userID);
        Document query = new Document("email", "alisha@gmail.com");
        Document result = col.find(query).first();
        if (result != null) {
            //String id = String.valueOf(result.getInteger("variant_id"));
            String id = result.getString("projURL");
            System.out.println("User ID: " + id);
        } else {
            System.out.println("User not found");
        }

/*        Document filter = new Document("_id", "5");
        col.deleteOne(filter);

        Document filter = new Document("_id", "6");
        Document update = new Document("$set", new Document("age", "35"));
        col.updateOne(filter, update);

        // Test DB works(uncomment)
        //Document sampleDoc = new Document("_id", "2").append("gene_id", "FBN1+");
        Document sampleDoc2 = new Document("_id", "8").append("age","33");
        //col.insertOne(sampleDoc);
        UpdateResult updateResult = col.updateOne(sampleDoc2);*/

    }
}