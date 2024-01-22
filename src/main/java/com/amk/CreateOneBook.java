package com.amk;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
//Insertar un libro
public class CreateOneBook {
	static final String url="mongodb://localhost";
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		try(MongoClient mongoClient= MongoClients.create(url)){
			MongoDatabase mdb=mongoClient.getDatabase("midb");
			MongoCollection<Document> mcol=mdb.getCollection("libros");
			Document book=new Document("_id",new ObjectId());
			//Introducir datos
			String title="El Quijote";
			String autor="Miguel de Cervantes";
			ArrayList<String> autores= new ArrayList<>(Arrays.asList(autor.split("\\s*,\\s*")));
			String editorial="España";
			double precio=33;
			//Crear Document
			book.append("title",title)
					.append("autor",autores)
					.append("editorial",editorial)
					.append("price",precio);
			//Insertar un Document
			mcol.insertOne(book);
		}
	}
}
