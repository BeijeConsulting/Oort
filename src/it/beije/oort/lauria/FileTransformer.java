package it.beije.oort.lauria;

import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;


public class FileTransformer {
	private static final String PATH_FILES = "C:\\Users\\Padawan06\\Documenti\\temp\\";
	
	// ATTENZIONE: modificare l'INTESTAZIONE comporta modificare l'ordine di scrittura dei campi 
	//			   dei contatti nel metodo rowBuilderCsv(Contatto contatto) di RubricaBuilder.
	private static final String INTESTAZIONE = "COGNOME;NOME;E-MAIL;TELEFONO";
	
	public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException, SAXException {
		
		// TRASFORMARE UNA RUBRICA DI CONTATTI CSV IN XML
		List<Contatto> listaContattiXml = CsvParser.readContattiCsv(PATH_FILES + "rubrica6.csv");
		
		XmlParser.writeContattiXml(PATH_FILES + "contatti6.xml", listaContattiXml);
		
		System.out.println("Rubrica XML completata!");
		
		// TRASFORMARE UNA RUBRICA DI CONTATTI XML IN CSV
		
		List<Contatto> listaContattiCsv = XmlParser.readContattiXml(PATH_FILES+"contatti.xml");
		
		CsvParser.writeContattiCsv(PATH_FILES + "rubricaCsv.csv", INTESTAZIONE, listaContattiCsv);
				
		System.out.println("Rubrica CSV completata!");
		
	}	
}
