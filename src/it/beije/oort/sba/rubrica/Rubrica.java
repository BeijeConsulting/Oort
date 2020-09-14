package it.beije.oort.sba.rubrica;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Rubrica {
	private static final String PATH = "./src/it/beije/oort/sba/rubrica/";
	
	public static void main(String[] args) throws IOException {
		File nomiF = new File(PATH + "nomi_italiani.txt");
		File cognomiF = new File(PATH + "cognomi.txt");
		File prefissiF = new File(PATH + "prefissi.txt");
		File dominiF = new File(PATH + "domini.txt");
		File rubrica = new File(PATH + "rubrica.csv");
		ReadFile reader = new ReadFile();
		List<String> nomi = reader.getContent(nomiF);
		List<String> cognomi = reader.getContent(cognomiF);
		List<String> prefissi = reader.getContent(prefissiF);
		List<String> domini = reader.getContent(dominiF);
		RubricaWriter writer = new RubricaWriter();
		writer.rubricaWriter(rubrica, 100000, nomi, cognomi, prefissi, domini);
	}
}
