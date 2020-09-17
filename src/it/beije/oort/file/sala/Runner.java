package it.beije.oort.file.sala;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Runner {

	public static void main(String[] args) 
			throws IOException, ParserConfigurationException, SAXException{
//		List<Contatto> list = CsvInterpreter.readCsvToList("C:/Users/Padawan12/Desktop/rubriche/rubrica_sala.csv");
		List<Contatto> list = RubricaToolset.readXmlToList("C:/Users/Padawan12/Desktop/rubriche/rubrica_sala.xml");
		for(Contatto c : list) {
			System.out.println(c.toString());
			//System.out.println(c.toXml());
		}
		//CsvInterpreter.contattoToXml(list, "C:/Users/Padawan12/Desktop/rubriche/rubrica_sala.xml");
	
	}
	
}

