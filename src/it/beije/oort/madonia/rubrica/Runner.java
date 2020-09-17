package it.beije.oort.madonia.rubrica;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class Runner {
	
	private static final String PATH_FILES = "/temp/rubrica/";
	private static List<String> csvFiles = 
			Arrays.asList("rubrica_bassanelli.csv", "rubrica_brugaletta.csv", "rubrica_busseni.csv",
					"rubrica_franceschi.csv", "rubrica_girardi.csv", "rubrica_gregori.csv",
					"rubrica_madonia.csv", "rubrica_maisto.csv", "rubrica_mancuso.csv",
					"rubrica_mater.csv", "rubrica_sala.csv");

	public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException {
		
		String pathfileCsv = csvFiles.get((int) (Math.random() * csvFiles.size()));
		String pathfileXml = new StringBuilder().append(pathfileCsv.split("\\.")[0]).append(".xml").toString();
		System.out.println(pathfileXml);
		WriterXmlRubrica.writeXmlFile(ParserCsvRubrica.creaListaContatti(PATH_FILES + pathfileCsv), PATH_FILES + pathfileXml);
	}

}
