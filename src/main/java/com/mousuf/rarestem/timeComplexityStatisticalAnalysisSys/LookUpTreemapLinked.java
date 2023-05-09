package com.mousuf.rarestem.timeComplexityStatisticalAnalysisSys;

import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;

import java.util.*;

public class LookUpTreemapLinked {
    private MongoClient client;
    private MongoDatabase db;
    private MongoCollection<Document> col;

    public LookUpTreemapLinked() {
        Dotenv dotenv = Dotenv.load();
        String uri;
        uri = dotenv.get("MONGODB_URI");
        client = MongoClients.create(uri);
        db = client.getDatabase("rs-db");
        col = db.getCollection("FBN1_individuals");
    }

    /*
     * These are my own code.
     * Built using the code references from using the documentation used
     * to build LookUpHashmapArray.
     * */

    public TreeMap<String, LinkedList<String>> getIndivDiseases() {
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

        TreeMap<String, LinkedList<String>> treeMap = new TreeMap<>();
        int totalDocuments = 0;
        System.out.println("Processing lookup results.");
        for (Document document : result) {
            Integer individualId = document.getInteger("Individual_id");
            ArrayList<Integer> diseaseIds = (ArrayList<Integer>) document.get("disease_id");
            LinkedList<String> diseases = new LinkedList<>();
            for (Integer diseaseId : diseaseIds) {
                diseases.add(String.valueOf(diseaseId));
            }

            treeMap.put(String.valueOf(individualId), diseases);
            totalDocuments++;

            if (totalDocuments % 10000 == 0) {
                System.out.println(String.format("Processed %d documents...", totalDocuments));
            }
        }
        System.out.println(String.format("Processed %d documents.", totalDocuments));
        System.out.println(String.format("Total number of unique Individual_id values: %d", treeMap.size()));

        // Stop
        System.out.println("Time complexity for #getIndividualDiseases, with TreeMap and LinkedList.");
        printTimeComplexty(startTime);
        return treeMap;
    }

    public int getTotalUniDiseaseIds(TreeMap<String, LinkedList<String>> treeMap) {
        // Start for Time complexity
        long startTime = System.nanoTime();
        LinkedList<String> allDiseaseIds = new LinkedList<>();
        for (LinkedList<String> diseases : treeMap.values()) {
            allDiseaseIds.addAll(diseases);
        }
        // Stop
        System.out.println("Time complexity for #getTotalUniqueDiseaseIds, with TreeMap and LinkedList.");
        printTimeComplexty(startTime);
        return new HashSet<>(allDiseaseIds).size();
    }

    public double getAvgDiseasesPerIndiv(TreeMap<String, LinkedList<String>> treeMap) {
        // Start for Time complexity
        long startTime = System.nanoTime();
        int totalDiseases = 0;
        for (LinkedList<String> diseases : treeMap.values()) {
            totalDiseases += diseases.size();
        }
        System.out.println("Time complexity for #getAverageDiseasesPerIndividual, with TreeMap and LinkedList.");
        printTimeComplexty(startTime);
        return (double) totalDiseases / treeMap.size();
    }

    public int findMaxAssocDiseases(TreeMap<String, LinkedList<String>> treeMap) {
        long startTime = System.nanoTime();
        int maxAssociatedDiseases = 0;
        for (LinkedList<String> diseases : treeMap.values()) {
            if (diseases.size() > maxAssociatedDiseases) {
                maxAssociatedDiseases = diseases.size();
            }
        }
        System.out.println("Time complexity for #findMaxAssociatedDiseases, with TreeMap and LinkedList.");
        printTimeComplexty(startTime);
        return maxAssociatedDiseases;
    }

    public List<String> findIndivWithDisease(String diseaseId, TreeMap<String, LinkedList<String>> individualDiseases) {
        long startTime = System.nanoTime();
        List<String> individuals = new LinkedList<>();
        for (Map.Entry<String, LinkedList<String>> entry : individualDiseases.entrySet()) {
            String individualId = entry.getKey();
            LinkedList<String> diseases = entry.getValue();
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
                System.out.println("Time complexity for #findIndividualsWithDisease, with TreeMap and LinkedList.");
                printTimeComplexty(startTime);
                return individuals;
            } else if (individuals.get(mid).compareTo(diseaseId) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println("No individuals with disease ID " + diseaseId + " found.");
        System.out.println("Time complexity for #findIndividualsWithDisease, with TreeMap and LinkedList.");
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
        LookUpTreemapLinked lookUpTreemapLinked = new LookUpTreemapLinked();
        TreeMap<String, LinkedList<String>> individualDiseases = lookUpTreemapLinked.getIndivDiseases();

        // You can then use the TreeMap containing the results as desired
        System.out.println(individualDiseases);

        int totalUniqueDiseaseIds = lookUpTreemapLinked.getTotalUniDiseaseIds(individualDiseases);
        System.out.println("Total number of unique disease_id values: " + totalUniqueDiseaseIds);

        double avgDiseasesPerIndiv = lookUpTreemapLinked.getAvgDiseasesPerIndiv(individualDiseases);
        System.out.println("Average diseases per individual: " + avgDiseasesPerIndiv);

        int maxAssociatedDiseases = lookUpTreemapLinked.findMaxAssocDiseases(individualDiseases);
        System.out.println("Max associated diseases per individual: " + maxAssociatedDiseases);

        String targetDiseaseId = "198";
        List<String> individualsWithDisease = lookUpTreemapLinked.findIndivWithDisease(targetDiseaseId, individualDiseases);
        System.out.println("Individuals with disease " + targetDiseaseId + ": " + individualsWithDisease);


    }
}