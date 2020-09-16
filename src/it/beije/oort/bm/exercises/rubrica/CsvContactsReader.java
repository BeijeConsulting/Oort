package it.beije.oort.bm.exercises.rubrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvContactsReader {
	private static final String NOME = "NOME", NOMI = "NOMI", COGNOME = "COGNOME", COGNOMI = "COGNOMI", TELEFONO = "TELEFONO", EMAIL = "EMAIL", EMAIL_SEP = "E-MAIL";
	public static List<Contatto> readFile(File csvFile) throws IOException{
		List<Contatto> contacts = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(csvFile));
		String header = reader.readLine();
		String[] columns = header.split(";");
		while(reader.ready()) {
			Contatto c = new Contatto();
			String line = reader.readLine();
			String[] elements = line.split(";");
			for(int i = 0; i<columns.length;i++) {
				switch(columns[i].toUpperCase()) {
					case NOME:
					case NOMI:
						c.setNome(elements[i]);
						break;
					case COGNOME:
					case COGNOMI:
						c.setCognome(elements[i]);
						break;
					case TELEFONO:
						c.setTelefono(elements[i]);
						break;
					case EMAIL_SEP:
					case EMAIL:
						c.setEmail(elements[i]);
						break;
				}
			}
			contacts.add(c);
		}
		reader.close();	
		return contacts;
	}
	
	public static void main(String[] args) throws IOException {
		File csv = new File("C:\\Users\\Padawan07\\Desktop\\risorse\\rubrica_maisto.csv");
		List<Contatto> list = readFile(csv);
		System.out.println(list);
	}
}
