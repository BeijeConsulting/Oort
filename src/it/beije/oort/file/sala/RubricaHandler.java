package it.beije.oort.file.sala;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class RubricaHandler {

	private RubricaHandler() {}
	
	public static void tranferRubrica(String inputPercorso, String outputPercorso) 
			throws IOException, ParserConfigurationException, SAXException, TransformerException {
		List<Contatto> listaInput;

		if(inputPercorso.endsWith(".xml"))
			listaInput = RubricaToolset.readXmlToList(inputPercorso);
		else if(inputPercorso.endsWith(".csv"))
			listaInput = RubricaToolset.readCsvToList(inputPercorso);
		else {
			listaInput = new ArrayList<Contatto>();
			System.out.println("Non ho riconosciuto il formato di input.");
		}
		
		if(listaInput.isEmpty()) System.out.println("Non inserisco nulla.");
		else if(outputPercorso.endsWith(".xml")) 
			RubricaToolset.contattoToXml(listaInput, outputPercorso);
		else if(outputPercorso.endsWith(".csv"))
			RubricaToolset.contattoToCsv(listaInput, outputPercorso);
		else System.out.println("Non ho riconosciuto il formato di output.");
	}
}
