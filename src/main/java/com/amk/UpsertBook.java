package com.amk;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.eq;

public class UpsertBook {
	private static final String url="mongodb://localhost";

	public static void main(String[] args) {
		try(MongoClient mongoClient= MongoClients.create(url)){
			MongoDatabase mdb=mongoClient.getDatabase("midb");
			MongoCollection<Document> mcol=mdb.getCollection("libros");
			String editorial="d";
			String editorial2="dad";
			//Mostrar libros antes
			System.out.println("Lista de libros pertenecientes a la editorial: "+editorial);
			Consumer<Document> printConsumer= document -> System.out.println(document.toJson());
			mcol.find(eq("editorial",editorial)).forEach(printConsumer);
			Document filtrado=new Document();
			filtrado.append("editorial",editorial);
			Bson actual= Updates.set("editorial",editorial2);
			UpdateOptions options=new UpdateOptions().upsert(true);
			mcol.updateMany(filtrado,actual,options);
			//Mostrar libros después
			System.out.println("Precios después de la actualización:");
			mcol.find(eq("editorial",editorial2)).forEach(printConsumer);
		}
	}
}
