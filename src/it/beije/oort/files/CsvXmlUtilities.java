package it.beije.oort.files;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import it.beije.oort.bru.db.rubrica.Contatto;

public class CsvXmlUtilities {
	public static void appendContacts(File sorgente, File destinazione) throws IOException, ParserConfigurationException, SAXException, TransformerException {
//		CsvReader reader = new CsvReader();
//		CsvBuilder builder = new CsvBuilder();
//		XmlReader readerXml = new XmlReader();
//		XmlBuilder builderXml = new XmlBuilder();
		//ContactConverter converter = new ContactConverter();
		File fileTemporaneoCsv = new File("/temp/fileTemporaneo.csv");
		File fileTemporaneoXml = new File("/temp/fileTemporaneo.xml");
		
		//Caso in cui il file di destinazione è in formato .csv
		if (destinazione.getPath().contains(".csv")) {
			//Se il sorgente è un file .xml viene convertito in un file temporaneo .csv
			if (sorgente.getPath().contains(".xml")) {
				convertToCsv(sorgente, fileTemporaneoCsv);
				sorgente = fileTemporaneoCsv;
			}
			List<Contatto> contatti = CsvParser.readContatti(sorgente);
			//Se il file destinazione esisteva già, non viene sovrascritto.
			if (destinazione.exists()) {
				contatti.addAll(0, CsvParser.readContatti(destinazione));
				CsvParser.buildContatti(contatti, destinazione);
				System.out.println("Done updating CSV file!");
			//Se non esisteva viene creato un nuovo file.
			} else {
				CsvParser.buildContatti(contatti, destinazione);
				System.out.println("Done creating CSV file!");
			}
		//Caso in cui il file di destinazione non è in csv (in questo è xml).
		} else {
			//Se il sorgente è csv viene convertito in un file temporaneo xml.
			if (sorgente.getPath().contains(".csv")) {
				convertToXml(sorgente, fileTemporaneoXml);
				sorgente = fileTemporaneoXml;
			}
			List<Contatto> contatti = XmlParser.readContatti(sorgente);
			//Se il fileDestinazione esisteva già non viene sovrascritto.
			if (destinazione.exists()) {
				contatti.addAll(0, XmlParser.readContatti(destinazione));
				XmlParser.buildContatti(contatti, destinazione);
				System.out.println("Done updating XML file!");
			//Se non esisteva viene creato un nuovo file xml.
			} else {
				XmlParser.buildContatti(contatti, destinazione);
				System.out.println("Done creating XML file!");
			}
		}
		
		//Se ho usato i fileTemporanei, li cancello.
		if (sorgente == fileTemporaneoCsv || sorgente == fileTemporaneoXml) {
			sorgente.delete();
		}
	}
	
	public static void convertToXml(File fileCsv, File fileDestinazione) throws IOException, ParserConfigurationException, TransformerException {
		List<Contatto> contatti = CsvParser.readContatti(fileCsv);
		CsvParser.buildContatti(contatti, fileDestinazione);
	}
	
	public static void convertToCsv(File fileXml, File fileDestinazione) throws ParserConfigurationException, SAXException, IOException {
		List<Contatto> contatti = CsvParser.readContatti(fileXml);
		CsvParser.buildContatti(contatti, fileDestinazione);
	}
}
