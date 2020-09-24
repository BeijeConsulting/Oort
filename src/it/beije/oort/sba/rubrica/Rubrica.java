package it.beije.oort.sba.rubrica;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Rubrica {
	private static final String PATH = "./src/it/beije/oort/sba/rubrica/";
	
	public static void main(String[] args) throws IOException {
		/* Creo 5 file con i nomi italiani, i cognomi, i prefissi e le emails, più il file rubrica su cui andrò a scrivere. */
		File nomiF = new File(PATH + "nomi_italiani.txt");
		File cognomiF = new File(PATH + "cognomi.txt");
		File prefissiF = new File(PATH + "prefissi.txt");
		File dominiF = new File(PATH + "domini.txt");
		File rubrica = new File(PATH + "rubrica.csv");
		/* istanzio un reader dalla classe ReadFile */
		ReadFile reader = new ReadFile();
		/* Creo quattro list con i nomi, i cognomi, i pefisssi e i domini presi dai rispettivi file .txt*/
		List<String> nomi = reader.getContent(nomiF);
		List<String> cognomi = reader.getContent(cognomiF);
		List<String> prefissi = reader.getContent(prefissiF);
		List<String> domini = reader.getContent(dominiF);
		// istanzio un oggetto dalla classe RubricaWriter con cui scriverò il file rubrica.csv
		RubricaWriter writer = new RubricaWriter();
		writer.rubricaWriter(rubrica, 1000, nomi, cognomi, prefissi, domini);
	}
}
