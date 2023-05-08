package com.mousuf.rarestem.dbSetup;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;

public class DatabaseQuery {
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

    }
}