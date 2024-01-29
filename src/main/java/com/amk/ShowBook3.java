package com.amk;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.*;

//Mostrar títulos de todos los libros de un autor (solo o acompañado)
//find(eq("<columna>","<valor>"));
public class ShowBook3 {
	private static final String url="mongodb://localhost";
	public static void main(String[] args) {
		try(MongoClient mongoClient= MongoClients.create(url)){
			MongoDatabase mdb= mongoClient.getDatabase("midb");
			MongoCollection<Document> mcol=mdb.getCollection("libros");
			String autor="d";
			//Mediante cursor y FindIterable
			/*
			FindIterable<Document> iterable=mcol.find(and(eq("editorial",editorial),lte("price",precio)));
			MongoCursor<Document> cursor=iterable.iterator();
			System.out.println("Lista de libros escritos por "+autor);
			while(cursor.hasNext()){
				System.out.println(cursor.next().toJson());
			}
			*/
			//Mediante Consumer(funcion lambda)
			System.out.println("Lista de libros escritos por "+autor);
			Consumer<Document> printConsumer= document -> System.out.println(document.toJson());
			mcol.find(eq("autor",autor)).forEach(printConsumer);
		}
	}
}
