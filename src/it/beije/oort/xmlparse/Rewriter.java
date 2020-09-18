package it.beije.oort.xmlparse;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class Rewriter {
	public void rewrite(File sorgente, File destinazione) throws IOException, ParserConfigurationException, SAXException, TransformerException {
		if (getEstensione(sorgente) == "csv") {
			File nuovoFile = new File("/temp/rubrica.csv");
			CsvToListConverter converter = new CsvToListConverter();
			List<Contatto> contatti = converter.convert(sorgente);
//			for (int i = 0; i < contatti.size(); i++) {
//				System.out.println(contatti.get(i));
//			}
			if (destinazione.exists()) {
				contatti.addAll(converter.convert(destinazione));
//				for (int i = 0; i < contatti.size(); i++) {
//					System.out.println(contatti.get(i));
//				}
			}
			ListToCsvConverter builder = new ListToCsvConverter();
			builder.buildCsv(contatti, nuovoFile);
		} else if (getEstensione(sorgente) == "xml") {
			File nuovoFile = new File("/temp/rubrica.xml");
			XmlReader reader = new XmlReader();
			List<Contatto> contatti = reader.readXml(sorgente);
//			for (int i = 0; i < contatti.size(); i++) {
//				System.out.println(contatti.get(i));
//			}
			if (destinazione.exists()) {
				contatti.addAll(reader.readXml(destinazione));
//				for (int i = 0; i < contatti.size(); i++) {
//					System.out.println(contatti.get(i));
//				}
			}
			XmlBuilder builder = new XmlBuilder();
			builder.build(contatti, nuovoFile);
		}
	}
	
	public static String getEstensione(File file) {
		String path = file.getPath();
		if (path.contains("xml")) {
			return "xml";
		}
		return "csv";
	}
}

