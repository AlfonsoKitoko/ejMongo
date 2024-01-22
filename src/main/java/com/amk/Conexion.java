package com.amk;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Conexion {
	private static final String url="mongodb://localhost";
	public static void main(String[] args) {
		try(MongoClient mongoClient= MongoClients.create(url)){
			List<Document> mdb=mongoClient.listDatabases().into(new ArrayList<>());
			//Mostramos cada base de datos en formato Json
			mdb.forEach(db-> System.out.println(db.toJson()));
		}
	}
}
