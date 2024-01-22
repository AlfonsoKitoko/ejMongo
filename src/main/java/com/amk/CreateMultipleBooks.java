package com.amk;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
//Insertar múltiples libros, con valores introducidos desde teclado
public class CreateMultipleBooks {
	static final String url="mongodb://localhost";
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		try(MongoClient mongoClient= MongoClients.create(url)){
			MongoDatabase mdb=mongoClient.getDatabase("midb");
			MongoCollection<Document> mcol=mdb.getCollection("libros");
			List<Document> libros=new ArrayList<>();
			System.out.print("¿Cuántos libros quieres introducir? ");
			int cant=sc.nextInt();
			sc.nextLine();
			for(int i=0;i<cant;i++) {
				//Introducir datos
				Document book=new Document("_id",new ObjectId());
				System.out.print("Introduce Título: ");
				String title = sc.nextLine();
				System.out.print("Introduce Autor/es: ");
				String autor = sc.nextLine();
				ArrayList<String> autores = new ArrayList<>(Arrays.asList(autor.split("\\s*,\\s*")));
				System.out.print("Introduce Editorial: ");
				String editorial = sc.nextLine();
				System.out.print("Introduce Precio: ");
				double precio = sc.nextDouble();
				sc.nextLine();
				//Crear Document
				book.append("title", title)
						.append("autor", autores)
						.append("editorial", editorial)
						.append("price", precio);
				libros.add(book);
			}
			//Insertar varios Document
			mcol.insertMany(libros,new InsertManyOptions());
		}
	}
}
