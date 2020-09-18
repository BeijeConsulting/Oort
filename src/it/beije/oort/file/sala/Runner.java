package it.beije.oort.file.sala;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Runner {

	public static void main(String[] args) 
			throws IOException, ParserConfigurationException, SAXException{
		GeneratoreRubrica g = new GeneratoreRubrica();
		List<Contatto> list = g.generaRubrica(Valori.getNomi(), Valori.getCognomi(), 1000);
		RubricaToolset.contattoToCsv(list, "C:/Users/Padawan12/Desktop/rubrica_sala.csv");
	}
	
}

