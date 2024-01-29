package com.amk;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.*;

//Mostrar libros de una editorial con precio inferior a 20eur
//find(and(eq("<columna>","<valor>"),lt("<columna>","<valor>"));
public class ShowBook2 {
	private static final String url="mongodb://localhost";
	public static void main(String[] args) {
		try(MongoClient mongoClient= MongoClients.create(url)){
			MongoDatabase mdb= mongoClient.getDatabase("midb");
			MongoCollection<Document> mcol=mdb.getCollection("libros");
			String editorial="d";
			double precio=20;
			//Mediante cursor y FindIterable
			/*
			FindIterable<Document> iterable=mcol.find(and(eq("editorial",editorial),lte("price",precio)));
			MongoCursor<Document> cursor=iterable.iterator();
			System.out.println("Lista de libros pertenecientes a la editorial: "+editorial);
			while(cursor.hasNext()){
				System.out.println(cursor.next().toJson());
			}
			*/
			//Mediante Consumer(funcion lambda)
			System.out.println("Lista de libros pertenecientes a la editorial: "+editorial+", con precio menor o igual a "+precio+" €");
			Consumer<Document> printConsumer=document -> System.out.println(document.toJson());
			mcol.find(and(eq("editorial",editorial),lte("price",precio))).forEach(printConsumer);
		}
	}
}