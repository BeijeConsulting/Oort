package it.beije.oort.xmlparse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class Runner {
	public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException {
		File rubrica = new File("/temp/rubrica_bassanelli.csv");
		File rubrica2 = new File("/temp/rubrica_maisto.csv");
		//File rubricaXml = new File("/temp/rubricaXml.xml");
		File destinazione = new File("/temp/rubrica.csv");
		Rewriter rewriter = new Rewriter();
		rewriter.rewrite(rubrica2, destinazione);
	}
}
