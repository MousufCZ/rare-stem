package com.mousuf.rarestem.dataset.col1a2;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.scene.control.Label;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class COL1A2Model {
    private final MongoClient client;
    private final MongoDatabase db;
    private final MongoCollection<Document> col;
    public COL1A2Model(){
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
        col = db.getCollection("COL1A2_variants_on_genome");
        System.out.println("Get collection successful.");
    }
    public List<COL1A2>getAllCOL1A2(){
        List<COL1A2> cOL1A2Collection = new ArrayList<>();

        for (Document doc : col.find()){
            Integer variants_on_genome_id = null;
            Integer allele = null;
            Integer effect_id = null;
            Integer position_g_start = null;
            Integer position_g_end = null;
            String average_frequency = null; // mixed data
            String variantOnGenome_DBID = null;
            String variantOnGenome_DNA = null;

            variants_on_genome_id = doc.getInteger("variants_on_genome_id");
            allele = doc.getInteger("allel");
            effect_id = doc.getInteger("effect_id");
            position_g_start = doc.getInteger("position_g_start");
            position_g_end = doc.getInteger("position_g_end");

            Object value = doc.get("average_frequency");
            if (value instanceof Integer) {
                average_frequency = String.valueOf(doc.getInteger("average_frequency"));
            } else if (value instanceof String) {
                average_frequency = doc.getString("average_frequency");
            }

            variantOnGenome_DBID = doc.getString("VariantOnGenome_DBID");
            variantOnGenome_DNA = doc.getString("VariantOnGenome_DNA");

            cOL1A2Collection.add(new COL1A2(variants_on_genome_id, allele,effect_id, position_g_start,
                    position_g_end, average_frequency, variantOnGenome_DBID, variantOnGenome_DNA));
        }
        return cOL1A2Collection;
    }
    public void close() {
        client.close();
    }
}