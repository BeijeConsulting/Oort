package it.beije.oort.bm.exercises.rubrica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class RubricaFileUpdater {

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException {
		if(args.length != 2) {
			System.out.println("Correct usage: java XmlFileUpdater <souceFile> <destinationFile>");
			System.exit(0);
		}
		File source = new File(args[0]);
		File dest = new File(args[1]);
		if(!extensionsCheck(source, dest)) {
			System.out.println("Problem: You must specify only .xml or .csv files for both source and destination.");
		}
		updateFile(source, dest);
		
		
	}

	private static void updateFile(File source, File dest) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		List<Contatto> contatti;
		List<Contatto> tmp1, tmp2;
		if(dest.exists()) {
			tmp2 = dest.getName().endsWith(".csv") ? CsvContactsReader.readFile(dest) : XmlParser.readContatti(dest); //loads existent contacts
		}else {
			tmp2 = new ArrayList<>();
		}
		tmp1 = source.getName().endsWith(".csv") ? CsvContactsReader.readFile(source) : XmlParser.readContatti(source); //loads source contacts
		contatti = ListUtilities.mergeLists(tmp2,tmp1);
		if(dest.getName().endsWith(".csv")) {
			CsvContactsWriter.writeFile(dest, contatti);
		}else {
			XmlParser.writeFile(dest, contatti);
		}
		
		
	}

	private static boolean extensionsCheck(File... files) {
		boolean ret = true;
		for(File f : files) {
			String ext = f.getName().substring(f.getName().length()-4, f.getName().length());
			if(!(ext.equals(".csv") || ext.equals(".xml"))) {
				ret = false;
				break;
			}
		}
		return ret;
	}

}
