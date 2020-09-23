package it.beije.oort.sba.rubrica.datarec;

import java.io.File;
import java.io.IOException;
import java.util.List;

import it.beije.oort.sba.rubrica.Contatto;

public class Main {

	public static void main(String[] args) throws IOException {
		File input = new File("./sba-resources/rubrica_random/rubrica.csv");
		File output = new File("./sba-resources/rubrica_random/merged_rubrica.csv");
		List<Contatto> contacts = CsvParser.readCsv(input);
		ContactsMap map = new ContactsMap(contacts);
		contacts = ContactsMerger.composeList(map);
		CsvParser.writeCsv(output, contacts);
		
	}
}
