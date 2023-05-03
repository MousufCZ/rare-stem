package com.mousuf.rarestem;
import com.mongodb.client.result.UpdateResult;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DatabaseConn {
    public static void main( String[] args ) {
    /**
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

        //MongoClient client = MongoClients.create("mongodb+srv://mousufbyae:k0VDzzU9vOGItt6n@cluster0.uuqglsd.mongodb.net/test");
        MongoDatabase db = client.getDatabase("rs-db");
        MongoCollection col = db.getCollection("rs-db");

/*        Document filter = new Document("_id", "5");
        col.deleteOne(filter);*/

/*        Document filter = new Document("_id", "6");
        Document update = new Document("$set", new Document("age", "35"));
        col.updateOne(filter, update);*/

        Document sampleDoc = new Document("_id", "58").append("age", "33");
        //Document sampleDoc2 = new Document("_id", "8").append("age","33");
        col.insertOne(sampleDoc);
        //UpdateResult updateResult = col.updateOne(sampleDoc2);

    }
}