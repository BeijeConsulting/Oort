package it.beije.oort.lauria.db;

import java.io.File;
//import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.beije.oort.lauria.Contatto;
import it.beije.oort.lauria.CsvParser;
import it.beije.oort.lauria.XmlParser;

public class DBImport {
	
	private static final String PATH_FILES = "C:\\Users\\Padawan06\\Documenti\\temp\\";
//	private static final String INTESTAZIONE = "COGNOME;NOME;EMAIL;TELEFONO";
	
	public static void main(String[] args) {
		
		String fileName = "rubrica.csv";
		File fileRubrica = new File(PATH_FILES + fileName);
		
		List<Contatto> recordContatti = new ArrayList<>();
		
		if(fileName.endsWith(".csv")) {
			try{
				recordContatti = CsvParser.readContattiCsv(fileRubrica);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(fileName.endsWith(".xml")) {
			try{
				recordContatti = XmlParser.readContattiXml(fileRubrica);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		String table = fileName.substring(0, fileName.length()-4);
		DBtools.preparedInsert(table, recordContatti);
		System.out.println("End.");
	}
}
