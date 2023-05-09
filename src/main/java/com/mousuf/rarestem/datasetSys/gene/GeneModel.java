package com.mousuf.rarestem.datasetSys.gene;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/*
 * These are all my code
 * References for solving mixed data challenges:
 * https://dba.stackexchange.com/questions/297854/how-does-mongodb-treat-mixed-types
 * https://stackoverflow.com/questions/72591551/read-multiple-types-in-a-collection-using-mongodb-c-sharp-driver
 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TableColumn.html
 * https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Field.html
 * */

public class GeneModel {
    private final MongoClient client;
    private final MongoDatabase db;
    private final MongoCollection<Document> col;

    public GeneModel(){
        Dotenv dotenv = Dotenv.load();
        String uri;
        uri = dotenv.get("MONGODB_URI");
        client = MongoClients.create(uri);
        db = client.getDatabase("rs-db");
        col = db.getCollection("gene");
    }


    public List<Gene> getAllGene() {
        List<Gene> genes = new ArrayList<>();

        for (Document doc : col.find()) {
            String gene_id = null;
            String gene_type = null;
            String name = null;
            String ncbi_mRna_id = null;
            String id_protein_ncbi = null;
            String position_c_mrna_start = null;
            String position_c_mrna_end = null;
            String position_c_cds_end = null;
            String position_g_mrna_start = null;
            String position_g_mrna_end = null;

            Object value = doc.get("gene_id");
            if (value instanceof Integer) {
                gene_id = String.valueOf(doc.getInteger("gene_id"));
            } else if (value instanceof String) {
                gene_id = doc.getString("gene_id");
            }

            gene_type = doc.getString("gene_type");
            name = doc.getString("name");
            ncbi_mRna_id = doc.getString("ncbi_mRna_id");
            id_protein_ncbi = doc.getString("id_protein_ncbi");

            value = doc.get("position_c_mrna_start");
            if (value instanceof Integer) {
                position_c_mrna_start = String.valueOf(doc.getInteger("position_c_mrna_start"));
            } else if (value instanceof String) {
                position_c_mrna_start = doc.getString("position_c_mrna_start");
            }

            value = doc.get("position_c_mrna_end");
            if (value instanceof Integer) {
                position_c_mrna_end = String.valueOf(doc.getInteger("position_c_mrna_end"));
            } else if (value instanceof String) {
                position_c_mrna_end = doc.getString("position_c_mrna_end");
            }

            value = doc.get("position_c_cds_end");
            if (value instanceof Integer) {
                position_c_cds_end = String.valueOf(doc.getInteger("position_c_cds_end"));
            } else if (value instanceof String) {
                position_c_cds_end = doc.getString("position_c_cds_end");
            }

            value = doc.get("position_g_mrna_start");
            if (value instanceof Integer) {
                position_g_mrna_start = String.valueOf(doc.getInteger("position_g_mrna_start"));
            } else if (value instanceof String) {
                position_g_mrna_start = doc.getString("position_g_mrna_start");
            }

            value = doc.get("position_g_mrna_end");
            if (value instanceof Integer) {
                position_g_mrna_end = String.valueOf(doc.getInteger("position_g_mrna_end"));
            } else if (value instanceof String) {
                position_g_mrna_end = doc.getString("position_g_mrna_end");
            }

            genes.add(new Gene (gene_id, gene_type, name, ncbi_mRna_id, id_protein_ncbi,
                    position_c_mrna_start, position_c_mrna_end, position_c_cds_end,
                    position_g_mrna_start, position_g_mrna_end));
        }
        return genes;
    }
    public void close() {
        client.close();
    }
}
