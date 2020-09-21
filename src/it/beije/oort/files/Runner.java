package it.beije.oort.files;


import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class Runner {
	public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException {
		
//		MAIN DELL'APPENDER
//	
		File rubrica = new File("/temp/rubrica_maisto.csv");
		File rubricaUpdated = new File("/temp/rubrica_updated.xml");
		Appender textAppender = new Appender();
		textAppender.appendContacts(rubrica, rubricaUpdated);


		
//		MAIN DELLA CONVERSIONE RUBRICHE DA CSV A XML
//		
//		File fileCsv = new File("/temp/rubrica_sala.csv");
//		File fileDestinazione = new File("/temp/rubrica_sala.xml");
//		ContactConverter converter = new ContactConverter();
//		converter.convertToXml(fileCsv, fileDestinazione);
	}
}
