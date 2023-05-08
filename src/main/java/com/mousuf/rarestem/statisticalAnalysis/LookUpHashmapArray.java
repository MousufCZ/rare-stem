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

public class LookUpHashmapArray {
    private MongoClient client;
    private MongoDatabase db;
    private MongoCollection<Document> col;

    public LookUpHashmapArray() {
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

    /*
     * These are my own code.
     * Built using the code references from using the following documentation:
     * https://mongodb.github.io/mongo-java-driver/3.6/javadoc/com/mongodb/client/AggregateIterable.html
     * https://stackoverflow.com/questions/59517472/how-to-save-aggregateiterabledocument-values-to-a-map-in-mongodb
     * https://mkyong.com/mongodb/java-mongodb-insert-a-document/
     * https://www.baeldung.com/java-mongodb-insert-hashmap
     * https://stackoverflow.com/questions/351565/system-currenttimemillis-vs-system-nanotime
     * */

    public HashMap<String, ArrayList<String>> getIndivDiseases() {
        long startTime = System.nanoTime();
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
            Integer individualId = document.getInteger("Individual_id");
            ArrayList<Integer> diseaseIds = (ArrayList<Integer>) document.get("disease_id");
            ArrayList<String> diseases = new ArrayList<>();
            for (Integer diseaseId : diseaseIds) {
                diseases.add(String.valueOf(diseaseId));
            }

            hashMap.put(String.valueOf(individualId), diseases);
            totalDocuments++;

            if (totalDocuments % 10000 == 0) {
                System.out.println(String.format("Processed %d documents...", totalDocuments));
            }
            totalDocuments++;
        }
        System.out.println(String.format("Processed %d documents.", totalDocuments));
        System.out.println(String.format("Total number of unique Individual_id values: %d", hashMap.size()));

        // Stop
        System.out.println("Time complexity for #getIndividualDiseases, with Hashmap and Arrays.");
        printTimeComplexty(startTime);
        return hashMap;
    }

    public int getTotalUniDiseaseIds(HashMap<String, ArrayList<String>> hashMap) {
        // Start for Time complexity
        long startTime = System.nanoTime();
        ArrayList<String> allDiseaseIds = new ArrayList<>();
        for (ArrayList<String> diseases : hashMap.values()) {
            allDiseaseIds.addAll(diseases);
        }
        // Stop
        System.out.println("Time complexity for #getTotalUniqueDiseaseIds, with Hashmap and Arrays.");
        printTimeComplexty(startTime);
        return new HashSet<>(allDiseaseIds).size();
    }

    public double getAvgDiseasesPerIndiv(HashMap<String, ArrayList<String>> hashMap) {
        // Start for Time complexity
        long startTime = System.nanoTime();
        int totalDiseases = 0;
        for (ArrayList<String> diseases : hashMap.values()) {
            totalDiseases += diseases.size();
        }
        System.out.println("Time complexity for #getAverageDiseasesPerIndividual, with Hashmap and Arrays.");
        printTimeComplexty(startTime);
        return (double) totalDiseases / hashMap.size();
    }

    public int findMaxAssocDiseases(HashMap<String, ArrayList<String>> hashMap) {
        long startTime = System.nanoTime();
        int maxAssociatedDiseases = 0;
        for (ArrayList<String> diseases : hashMap.values()) {
            if (diseases.size() > maxAssociatedDiseases) {
                maxAssociatedDiseases = diseases.size();
            }
        }
        System.out.println("Time complexity for #findMaxAssociatedDiseases, with Hashmap and Arrays.");
        printTimeComplexty(startTime);
        return maxAssociatedDiseases;
    }

    public ArrayList<String> findIndivWithDisease(String diseaseId, HashMap<String, ArrayList<String>> individualDiseases) {
        long startTime = System.nanoTime();
        ArrayList<String> individuals = new ArrayList<>();
        for (String individualId : individualDiseases.keySet()) {
            ArrayList<String> diseases = individualDiseases.get(individualId);
            if (diseases.contains(diseaseId)) {
                individuals.add(individualId);
            }
        }

        // Bubble sort through the individuals
        for (int i = 0; i < individuals.size() - 1; i++) {
            for (int j = 0; j < individuals.size() - i - 1; j++) {
                if (individuals.get(j).compareTo(individuals.get(j + 1)) > 0) {
                    String temp = individuals.get(j);
                    individuals.set(j, individuals.get(j + 1));
                    individuals.set(j + 1, temp);
                }
            }
        }

        // Binary searching of the individuals with relation to dieaseID
        int left = 0;
        int right = individuals.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (individuals.get(mid).equals(diseaseId)) {
                System.out.println("Individual with disease ID " + diseaseId + ": " + individuals.get(mid));
                return individuals;
            } else if (individuals.get(mid).compareTo(diseaseId) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println("No individuals with disease ID " + diseaseId + " found.");
        System.out.println("Time complexity for #findIndividualsWithDisease, with Hashmap and Arrays.");
        printTimeComplexty(startTime);
        return individuals;
    }

    public void printTimeComplexty(long startTime) {
        long endTime = System.nanoTime();
        long elapsedTimeNs = endTime - startTime;
        double elapsedTimeSec = elapsedTimeNs / 1_000_000_000.0;

        long hours = (long) (elapsedTimeSec / 3600);
        long minutes = (long) ((elapsedTimeSec % 3600) / 60);
        double seconds = elapsedTimeSec % 60;

        String formattedTime = String.format("%02d:%02d:%06.7f", hours, minutes, seconds);
        System.out.println("Elapsed time: " + formattedTime);
    }

    public static void main(String[] args) {
        LookUpHashmapArray lookUpHashmapArray = new LookUpHashmapArray();
        HashMap<String, ArrayList<String>> individualDiseases = lookUpHashmapArray.getIndivDiseases();

        int totalUniqueDiseaseIds = lookUpHashmapArray.getTotalUniDiseaseIds(individualDiseases);
        System.out.println("Total number of unique disease_id values: " + totalUniqueDiseaseIds);

        double averageDiseasesPerIndividual = lookUpHashmapArray.getAvgDiseasesPerIndiv(individualDiseases);
        System.out.println("Average number of disease_id values per Individual_id: " + averageDiseasesPerIndividual);

        int maxAssociatedDiseases = lookUpHashmapArray.findMaxAssocDiseases(individualDiseases);
        System.out.println("Max associated diseases per individual: " + maxAssociatedDiseases);

        String targetDiseaseId = "198";
        ArrayList<String> individualsWithDisease = lookUpHashmapArray.findIndivWithDisease(targetDiseaseId, individualDiseases);
        System.out.println("Individuals with disease " + targetDiseaseId + ": " + individualsWithDisease);
    }
}
