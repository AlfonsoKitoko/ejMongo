package com.amk;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.eq;

//Mostrar libro de una editorial
//find(eq("<columna>","<valor>"))
public class ShowBook {
	private static final String url="mongodb://localhost";
	public static void main(String[] args) {
		try(MongoClient mongoClient= MongoClients.create(url)){
			MongoDatabase mdb= mongoClient.getDatabase("midb");
			MongoCollection<Document> mcol=mdb.getCollection("libros");
			String editorial="d";
			//Mediante cursor y FindIterable
			/*FindIterable<Document> iterable=mcol.find(eq("editorial",editorial));
			MongoCursor<Document> cursor=iterable.iterator();
			System.out.println("Lista de libros pertenecientes a la editorial: "+editorial);
			while(cursor.hasNext()){
				System.out.println(cursor.next().toJson());
			}*/
			//Mediante Consumer(funcion lambda)
			System.out.println("Lista de libros pertenecientes a la editorial: "+editorial);
			Consumer<Document> printConsumer=document -> System.out.println(document.toJson());
			mcol.find(eq("editorial",editorial)).forEach(printConsumer);
		}
	}
}
