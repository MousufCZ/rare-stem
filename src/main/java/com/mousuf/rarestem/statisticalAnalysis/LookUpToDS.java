package com.mousuf.rarestem.statisticalAnalysis;

import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

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

    }
    public HashMap<String, ArrayList<String>> getIndividualDiseases() {
        System.out.println("Starting lookup aggregation.");
        AggregateIterable<Document> result = col.aggregate(Arrays.asList(
                Aggregates.lookup(
                        "FBN1_individuals_to_diseases",
                        "Individual_id",
                        "individual_id",
                        "diseases"),
                Aggregates.project(Projections.fields(
                        Projections.excludeId(),
                        Projections.include(
                                "Individual_id"),
                        Projections.computed(
                                "disease_id",
                                "$diseases.disease_id")
                ))
        ));
        System.out.println("Lookup aggregation completed.");

        HashMap<String, ArrayList<String>> hashMap = new HashMap<>();
        int totalDocuments = 0;
        System.out.println("Processing lookup results.");
        for (Document document : result) {
            String individualId = document.getInteger("Individual_id").toString();
            String diseaseId = document.getInteger("disease_id").toString();
            System.out.println("COMPLETE: getInteger to toSting().");
            //Integer diseaseId = document.getInteger("disease_id");

            if (hashMap.containsKey(individualId)) {
                hashMap.get(individualId).add(diseaseId);
            } else {
                ArrayList<String> diseases = new ArrayList<>();
                diseases.add(diseaseId);
                hashMap.put((individualId), diseases);
            }
            if (totalDocuments % 10000 == 0) {
                System.out.println(String.format("Processed %d documents...", totalDocuments));
            }
            totalDocuments++;
        }
        System.out.println(String.format("Processed %d documents.", totalDocuments));
        System.out.println(String.format("Total number of unique Individual_id values: %d", hashMap.size()));
        return hashMap;
    }

    public void processIndividualDiseases(HashMap<String, ArrayList<String>> hashMap) {
        ArrayList<String> allDiseaseIds = new ArrayList<>();
        for (ArrayList<String> diseases : hashMap.values()) {
            allDiseaseIds.addAll(diseases);
        }
        System.out.println(String.format("Total number of unique disease_id values: %d", new HashSet<>(allDiseaseIds).size()));
        int totalDiseases = 0;
        for (ArrayList<String> diseases : hashMap.values()) {
            totalDiseases += diseases.size();
        }
        double averageDiseasesPerIndividual = (double) totalDiseases / hashMap.size();
        System.out.println(String.format("Average number of disease_id values per Individual_id: %.2f", averageDiseasesPerIndividual));
    }


    public static void main (String[] args){
        LookUpToDS lookUpToDS = new LookUpToDS();
        HashMap<String, ArrayList<String>> individualDiseases = lookUpToDS.getIndividualDiseases();
        lookUpToDS.processIndividualDiseases(individualDiseases);
    }

}
