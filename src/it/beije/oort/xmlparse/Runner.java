package it.beije.oort.xmlparse;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class Runner {
	public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException {
//		File fileXml = new File("/temp/rubrica_bassanelli.csv");
//		XmlBuilder builder = new XmlBuilder();
//		CsvToListConverter converter = new CsvToListConverter();
//		builder.build(converter.convert(fileXml));
		File fileCsv = new File("/temp/rubricaXmlToCsv.csv");
		File fileXml = new File("/temp/rubricaXml.xml");
		XmlReader reader = new XmlReader();
		List<Contatto> contatti = reader.readXml(fileXml);
		ListToCsvConverter converter = new ListToCsvConverter();
		converter.buildCsv(contatti, fileCsv);
	}
}
