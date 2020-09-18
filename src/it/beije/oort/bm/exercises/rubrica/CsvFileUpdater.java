package it.beije.oort.bm.exercises.rubrica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class CsvFileUpdater {

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException {
		if(args.length != 2) {
			System.out.println("Correct usage: java CsvFileUpdater <souceFile (xml or csv)> <destinationFileCsv>");
			System.exit(0);
		}
		File source = new File(args[0]);
		File dest = new File(args[1]);
		switch(args[0].substring(args[0].length()-4, args[0].length())) {
			case ".csv":
				updateFromCsv(source, dest);
				break;
			case ".xml":
				updateFromXml(source,dest);
				break;
			default:
				System.out.println("Wrong input file extension: sourceFile must be .csv or .xml");
				System.exit(0);
		}
		
		
	}

	private static void updateFromXml(File source, File dest) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		List<List<Contatto>> contatti = new ArrayList<>();
		List<Contatto> tmp1, tmp2;
		if(dest.exists()) {
			tmp2 = CsvContactsReader.readFile(dest);
			contatti.add(tmp2); //loads present contacts
		}
		tmp1 = XmlParser.readContatti(source);
		contatti.add(tmp1); //adds new contacts
		CsvContactsWriter.writeFile(dest, contatti);
	}

	private static void updateFromCsv(File source, File dest) throws IOException, ParserConfigurationException, TransformerException, SAXException {
		List<List<Contatto>> contatti = new ArrayList<>();
		List<Contatto> tmp1, tmp2;
		if(dest.exists()) {
			tmp2 = CsvContactsReader.readFile(dest);
			contatti.add(tmp2); //loads present contacts
		}
		tmp1 = CsvContactsReader.readFile(source);
		contatti.add(tmp1); //adds new contacts
		CsvContactsWriter.writeFile(dest, contatti);
		
	}




}
