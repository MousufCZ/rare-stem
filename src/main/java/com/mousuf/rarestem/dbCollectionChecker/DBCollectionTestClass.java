package com.mousuf.rarestem.dbCollectionChecker;
import com.mongodb.client.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;

public class DBCollectionTestClass {
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
     * The first 6 line of the code are reuse from Dotenv and MongoDb.
     * This code is reused in all Models in each system and the two java files
     * in timeComplexityStatisticalAnalysisSys.
     *
     * Every file in this project folder has further referencing in the final project
     * writeup. See Methods, Result and Appendix.
     *
     * @param uri  the string to call MongoDB Connection String
     *             URI.
     */
        Dotenv dotenv = Dotenv.load();
        String uri;
        uri = dotenv.get("MONGODB_URI");
        MongoClient client = MongoClients.create(uri);
        MongoDatabase db = client.getDatabase("rs-db");
        MongoCollection<Document> col = db.getCollection("user_proj");

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
    }
}