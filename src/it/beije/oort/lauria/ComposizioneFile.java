package it.beije.oort.lauria;

import java.io.File;

/**
 * Classe che testa i metodi della libreria AddCsvXml per aggiungere 
 * il contenuto di un file, in coda al contenuto di un altro file.
 * I tipi di file supportati sono .csv ed .xml .
 * Gestione di esistenza del file di output diretamente nei metodi di AddCsvXml.
 * 
 * @author mFrenk
 *
 */
public class ComposizioneFile {
	private static final String PATH_FILES = "C:\\Users\\Padawan06\\Documenti\\temp\\";
	
	public static void main(String[] args) throws Exception {
	
		String fileNameInput = "rubrica4.csv"; 
		String fileNameOutput = "rubricaNew.csv"; 		
		
		File fileIn = new File(PATH_FILES + fileNameInput);
		File fileOut = new File(PATH_FILES + fileNameOutput);
			

			if(fileNameInput.endsWith(".csv") && fileNameOutput.endsWith(".csv")) {				
				AddCsvXml.csvAddCsv(fileIn, fileOut);	
				
			}else if(fileNameInput.endsWith(".csv") && fileNameOutput.endsWith(".xml")) {		
				AddCsvXml.csvAddXml(fileIn, fileOut);
				
			}else if(fileNameInput.endsWith(".xml") && fileNameOutput.endsWith(".csv")) {					
				AddCsvXml.xmlAddCsv(fileIn, fileOut);

			}else if(fileNameInput.endsWith(".xml") && fileNameOutput.endsWith(".xml")){
				AddCsvXml.xmlAddXml(fileIn, fileOut);
				
			}else {
				System.out.println("Il formato del file di output, non è supportato.");
			}
			

		System.out.println("Fine.");
			
	}
}
