package it.beije.oort.files;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class ContactConverter {
	public void convertToXml(File fileCsv, File fileDestinazione) throws IOException, ParserConfigurationException, TransformerException {
		CsvReader reader = new CsvReader();
		List<Contatto> contatti = reader.readContatti(fileCsv);
		XmlBuilder builder = new XmlBuilder();
		builder.buildContatti(contatti, fileDestinazione);
	}
	
	public void convertToCsv(File fileXml, File fileDestinazione) throws ParserConfigurationException, SAXException, IOException {
		XmlReader reader = new XmlReader();
		List<Contatto> contatti = reader.readContatti(fileXml);
		CsvBuilder builder = new CsvBuilder();
		builder.buildContatti(contatti, fileDestinazione);
	}
}
