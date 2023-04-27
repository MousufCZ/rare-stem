package com.mousuf.rarestem;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DatabaseConn {
    public static void main( String[] args ) {

        MongoClient client = MongoClients.create("mongodb+srv://mousufbyae:OVf32A0a72vVuH2v@cluster0.uuqglsd.mongodb.net/test");
        MongoDatabase db = client.getDatabase("rs-db");
        MongoCollection col = db.getCollection("rs-db");

        Document sampleDoc = new Document("_id", "2").append("name", "Mousuf Chowdhury");

        col.insertOne(sampleDoc);
    }
}