package it.beije.oort.bassanelli.exercises.phonebookgenerator;

import java.io.*;
import java.util.*;


public class PhonebookGenerator {

	public static void main(String[] args) throws IOException, Exception  {
		
		List<String> namesList = listGenerator("/temp/nomi_italiani.txt");
		List<String> surnamesList = listGenerator("/temp/cognomi.txt");
		List<String> prefixsList = listGenerator("/temp/prefissi.txt");
		List<String> domainsList = listGenerator("/temp/domini.txt");
		
		int namesSize = namesList.size();
		int surnamesSize = surnamesList.size();
		int prefixsSize = prefixsList.size();
		int domainsSize = domainsList.size();
		
		List<String> recordsList = new ArrayList<String>();
		
		Random r = new Random();
		String record = "";
		String name = "";
		String surname = "";
		String phonenumber = "";
		String domain = "";
		String email = "";
	
		for (int i = 0; i < 1000; i++) {
			r = new Random();
			name = namesList.get(r.nextInt(namesSize));
			surname = surnamesList.get(r.nextInt(surnamesSize));
			phonenumber = generazioneNumero(prefixsList);
			domain = domainsList.get(r.nextInt(domainsSize));
			email = name + "." + surname + "@" + domain;
			
			record = name + ";" + surname + ";" + phonenumber + ";" + email + "\n";
			
			// System.out.println(record);
			recordsList.add(record);
		}
		
		String row = "NOMI;COGNOMI;TELEFONO;EMAIL\n";
		for (String arg : recordsList) {
			//System.out.println(arg);
			row += arg;
		}
		
		File file = new File("/temp/records.csv");
		
		FileWriter writer = new FileWriter(file);
		
		writer.write(row);
		
		writer.flush();
		writer.close();
		
	}
	
	public static List<String> listGenerator(String path) throws IOException, Exception {
		
		List<String> list = new ArrayList<>();
		
		File file = new File(path);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		while (bufferedReader.ready()) {
			String riga = bufferedReader.readLine();
			list.add(riga);
		}
		
		bufferedReader.close();
		
		return list;
	}
	
	
	public static String generazioneNumero(List<String> args) {
		Random random = new Random();
		int suffisso = random.nextInt(9999999);
		while (suffisso < 1000000) {
			suffisso = random.nextInt(9999999);
		}
		String prefisso = args.get(random.nextInt(5));
		String risultato = "" + prefisso + suffisso;
		return risultato;
	}
}
