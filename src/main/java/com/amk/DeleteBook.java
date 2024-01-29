package com.amk;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.eq;

//Borrar libro mediante identificador
public class DeleteBook {
	private static final String url="mongodb://localhost";

	public static void main(String[] args) {
		try(MongoClient mongoClient=MongoClients.create(url)) {
			MongoDatabase mdb = mongoClient.getDatabase("midb");
			MongoCollection<Document> mcol = mdb.getCollection("libros");
			//Muestra antes de borrar
			Consumer<Document> printConsumer = document -> System.out.println(document.toJson());
			mcol.find(eq("editorial","d")).forEach(printConsumer);
			//borrado por titulo
			String titulo = "a";
			mcol.deleteMany(new Document("title", titulo));
			//Muestra después de borrar
			System.out.println("aaa");
			mcol.find(eq("editorial","d")).forEach(printConsumer);
		}
	}
}
