package it.beije.oort.rubrica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

public class RubricaCsvXml {
	
	
	private static List<Contatto> LetturaFile(File file) throws IOException, ParserConfigurationException, SAXException{
		List<Contatto> fileList = new ArrayList<Contatto>();
		if(file.toString().endsWith("xml")) {
			fileList = ParserXmlRubrica.readContattiXml(file);
		} else if(file.toString().endsWith("csv")) {
			ParserCsvRubrica p = new ParserCsvRubrica();
			fileList = p.listBuilder(file);
		}
		return fileList;
	}
	
	
	
	private static void rubricaWriter (File fileOutput, List<Contatto> list) throws IOException, DOMException, ParserConfigurationException, TransformerException {
		ParserCsvRubrica p = new ParserCsvRubrica();
		if(fileOutput.toString().endsWith("csv")) {
			ParserXmlRubrica.rubricaCsvWriter(fileOutput, list);
		} else if(fileOutput.toString().endsWith("xml")) {
			p.rubricaXmlWriter(list, fileOutput.toString());
		}
	}
	
	
	public void rubricaHandler(File input, File output) throws ParserConfigurationException, SAXException, IOException, DOMException, TransformerException {
		List<Contatto> inputList = LetturaFile(input);
		if(output.exists()) {
			List<Contatto> outputList = LetturaFile(output); 
			inputList.addAll(0, outputList);
		}
		rubricaWriter(output, inputList);
	}
	
	
	public static void main(String[] args) throws DOMException, ParserConfigurationException, SAXException, IOException, TransformerException {
		RubricaCsvXml r = new RubricaCsvXml();
		File xml = new File ("./src/it/beije/oort/rubrica/rubrica.xml");
		File csv = new File ("./src/it/beije/oort/rubrica/rubrica.csv");
		r.rubricaHandler(xml, csv);	
	}

}
