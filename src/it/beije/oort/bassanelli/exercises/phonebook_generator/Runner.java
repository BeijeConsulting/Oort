package it.beije.oort.bassanelli.exercises.phonebook_generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Runner {

	public static void main(String[] args) throws IOException, Exception {

		List<String> namesList = listGenerator("/temp/nomi_italiani.txt");
		List<String> surnamesList = listGenerator("/temp/cognomi.txt");
		List<String> prefixsList = listGenerator("/temp/prefissi.txt");
		List<String> domainsList = listGenerator("/temp/domini.txt");

		// List<Contact> recordsList = new ArrayList<Contact>();

		List<Contact> contactsFromCsv = Phonebook.readCsvFile("/temp/records.csv");

		List<String> mobilesList = Phonebook.extractField(contactsFromCsv, "TELEFONO");
		List<String> emailsList = Phonebook.extractField(contactsFromCsv, "EMAIL");

		System.out.println("Start: " + LocalTime.now());

		List<Contact> recordsList = Phonebook.recordsGenerator(1000, namesList, surnamesList, prefixsList, mobilesList,
				domainsList, emailsList);

		System.out.println("Done records: " + LocalTime.now());

		// Phonebook.writeCsvFile(recordsList, "COGNOME;NOME;EMAIL;TELEFONO",
		// "/temp/records.csv", true);

		System.out.println("Done file: " + LocalTime.now());

		// List<Contact> contactsFromCsv =
		// Phonebook.readCsvFile("/temp/rubrica_bassanelli.csv");

		// Phonebook.writeXmlFile(contactsFromCsv, "/temp/rubrica.xml");

		// Phonebook.updateFile("/temp/records.csv", "/temp/records.csv");

		// List<Contact> contactsFromXml = Phonebook.readXmlFile("/temp/rubrica.xml");

		// List<Contact> temp = Phonebook.readCsvFile("/temp/rubrica_bassanelli.csv");
		
		// DBManager.writeIntoDatabase(temp);

		// List<Contact> list2 = DBManager.readFromDatabase();

		// Phonebook.writeCsvFile(temp, "COGNOME;NOME;EMAIL;TELEFONO", "/temp/new_rubrica.csv", true);

		// Phonebook.printAllRecords(recordsList, "NOME;COGNOME;TELEFONO;EMAIL");
		
		// Map<String, Integer> duplicates = DuplicateManager.searchDuplicates(recordsList, "EMAIL");
		
		// List<Contact> t = DuplicateManager.joinContattiWithAlias(recordsList, "EMAIL");
		
		// Phonebook.sortByField(t, "NOME");
		
		// Phonebook.printAllRecords(list2, "NOME;COGNOME;TELEFONO;EMAIL");

	}

	public static List<String> listGenerator(String path) throws IOException, Exception {

		List<String> list = new ArrayList<>();

		File file = new File(path);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		while (bufferedReader.ready()) {
			String row = bufferedReader.readLine();
			list.add(row);
		}

		bufferedReader.close();

		return list;
	}

}
