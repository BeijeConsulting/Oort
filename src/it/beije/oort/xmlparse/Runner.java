package it.beije.oort.xmlparse;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class Runner {
	public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException {
		File fileXml = new File("/temp/rubrica_bassanelli.csv");
		XmlBuilder builder = new XmlBuilder();
		CsvToListConverter converter = new CsvToListConverter();
		builder.build(converter.convert(fileXml));
	}
}
