package it.beije.oort.lauria;

import java.io.File;
//import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DBImport {
	
	private static final String PATH_FILES = "C:\\Users\\Padawan06\\Documenti\\temp\\";
//	private static final String INTESTAZIONE = "COGNOME;NOME;EMAIL;TELEFONO";
	
	public static void main(String[] args) {
		
		String fileName = "contatti_busseni.xml";
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
