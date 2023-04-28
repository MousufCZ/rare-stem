package com.mousuf.rarestem;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseConn {
    public static void main( String[] args ) {
        MongoClient client = MongoClients.create("mongodb+srv://mousufbyae:k0VDzzU9vOGItt6n@cluster0.uuqglsd.mongodb.net/test");
        MongoDatabase db = client.getDatabase("rs-db");
        MongoCollection col = db.getCollection("rs-db");

        Document filter = new Document("_id", "4");
        col.deleteOne(filter);

/*        Document filter = new Document("_id", "4");
        Document update = new Document("$set", new Document("age", "35"));
        col.updateOne(filter, update);*/

/*        Document sampleDoc = new Document("_id", "4").append("age", "33");
        col.insertOne(sampleDoc);*/
    }
}