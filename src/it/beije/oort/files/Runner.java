package it.beije.oort.files;


import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class Runner {
	public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException {
		File rubrica = new File("/temp/rubrica_madonia.csv");
		File rubricaUpdated = new File("/temp/rubrica_updated.xml");
		Appender textAppender = new Appender();
		textAppender.appendContacts(rubrica, rubricaUpdated);
	}
}
