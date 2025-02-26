package com.amk;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.eq;

//Actualizar precio de todos los libros de una editorial, incremento en 5eur
public class UpdateBook {
	private static final String url="mongodb://localhost";
	public static void main(String[] args) {
		try(MongoClient mongoClient= MongoClients.create(url)){
			MongoDatabase mdb=mongoClient.getDatabase("midb");
			MongoCollection<Document> mcol=mdb.getCollection("libros");
			String editorial="d";
			//Mostrar libros antes
			System.out.println("Lista de libros pertenecientes a la editorial: "+editorial);
			Consumer<Document> printConsumer= document -> System.out.println(document.toJson());
			mcol.find(eq("editorial",editorial)).forEach(printConsumer);
			Document filtrado=new Document();
			filtrado.append("editorial",editorial);
			Bson actual= Updates.inc("price",5);
			mcol.updateMany(filtrado,actual);
			//Mostrar libros después
			System.out.println("Precios después de la actualización:");
			mcol.find(eq("editorial",editorial)).forEach(printConsumer);
		}
	}
}
