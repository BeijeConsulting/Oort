package it.beije.oort.team2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class myRubrica {
	public static ArrayList<String> nomi = new ArrayList<>();
	public static ArrayList<String> cognomi = new ArrayList<>();
	
	public static void listaNomi() throws IOException {
		File file = new File("/temp/lista_nomi.txt");
		if(file.exists()) {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
		
			while (bufferedReader.ready()) {
				nomi.add(bufferedReader.readLine());
			}
					
		}
		else 
				System.out.println("Il file non esiste!");
		
	 	}
	
	public static void listaCognomi() throws IOException {
		File file = new File("/temp/lista_cognomi.txt");
		if(file.exists()) {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
		
			while (bufferedReader.ready()) {
				cognomi.add(bufferedReader.readLine());
			}
					
		}
		else 
				System.out.println("Il file non esiste!");
		
	 	}
	
	public static void scritturaFile() {
		File file = new File("/temp/rubrica.txt");
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
		ArrayList<Record> records = new ArrayList<>();
		for(int i=0; i<records.size(); i++) {
			String name = records.get(i).getNome();
			String surname = records.get(i).getCognome();
			String phone = records.get(i).getTelefono();
			String email = records.get(i).getMail();
			bufferedWriter.append(name + ";" + surname + ";" + phone + ";" + email + "\n");
		}
		
		
	}

	public static void main(String[] args) throws IOException {
		
		

	}

}
