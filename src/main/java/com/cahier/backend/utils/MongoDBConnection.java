package com.cahier.backend.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;

public class MongoDBConnection {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        String dbUsername = dotenv.get("DB_USERNAME");
        String dbPassword = dotenv.get("DB_PASSWORD");

        if (dbUsername == null || dbPassword == null) {
            System.out.println("Erreur : Impossible de charger les identifiants depuis le fichier .env");
            return;
        }

        String connectionString = "mongodb+srv://" + dbUsername + ":" + dbPassword +
                "@textbookcluster.psumt.mongodb.net/?retryWrites=true&w=majority&appName=textbookCluster";

        MongoClient mongoClient = MongoClients.create(connectionString);

        MongoDatabase database = mongoClient.getDatabase("textbookBD");

        System.out.println("Connexion réussie à la base de données MongoDB");

        mongoClient.close();
    }
}
