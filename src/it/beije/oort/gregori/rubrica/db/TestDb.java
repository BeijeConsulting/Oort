package it.beije.oort.gregori.rubrica.db;

import java.io.File;
import java.io.IOException;
import java.util.List;

import it.beije.oort.gregori.parser.ReaderCsv;
import it.beije.oort.gregori.parser.WriterCsv;
import it.beije.oort.rubrica.Contatto;

public class TestDb {

	public static void main(String[] args) throws IOException {
		File csvFile = new File("/temp/duplicates_removed.csv");
//		WriterDb.writeContatti(ReaderCsv.readContatti(csvFile));
//		List<Contatto> contatti = ReaderDb.readContatti();
//		for(Contatto contatto : contatti) {
//			System.out.println(contatto);
//		}
		File filePath = new File("/temp/rubrica-from-db.csv");
		WriterCsv.writeContatti(ReaderDb.readContatti(), filePath);
		
	}
}
