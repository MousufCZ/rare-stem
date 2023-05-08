package com.mousuf.rarestem.dataset.gene;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mousuf.rarestem.projContributionSys.Contrib;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class GeneModel {
    private final MongoClient client;
    private final MongoDatabase db;
    private final MongoCollection<Document> col;

    public GeneModel(){
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
        col = db.getCollection("gene");
        System.out.println("Get collection successful.");
    }

    /*
    * References for solving mixed data challenges:
    * https://dba.stackexchange.com/questions/297854/how-does-mongodb-treat-mixed-types
    * https://stackoverflow.com/questions/72591551/read-multiple-types-in-a-collection-using-mongodb-c-sharp-driver
    * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TableColumn.html
    * https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Field.html
    * */
    public List<Gene> getAllGene() {
        List<Gene> genes = new ArrayList<>();

        for (Document doc : col.find()) {
            int gene_id = doc.getInteger("gene_id").toString();
            String gene_type = doc.getString("gene_type");
            String name = doc.getString("name");
            String ncbi_mRna_id = doc.getString("ncbi_mRna_id");
            String id_protein_ncbi = doc.getString("id_protein_ncbi");
            String position_c_mrna_start = doc.getString("position_c_mrna_start");
            String position_c_mrna_end = doc.getString("position_c_mrna_end");
            int position_c_cds_end = doc.getInteger("position_c_cds_end");
            int position_g_mrna_start = doc.getInteger("position_g_mrna_start");
            int position_g_mrna_end = doc.getInteger("position_g_mrna_end");
            genes.add(new Gene(gene_id, gene_type, name, ncbi_mRna_id, id_protein_ncbi,
                    position_c_mrna_start, position_c_mrna_end, position_c_cds_end,
                    position_g_mrna_start, position_g_mrna_end));
        }
        return genes;
    }

    public void close() {
        client.close();
    }



}
