package it.beije.oort.lauria;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Rubrica {
	
	private static final String PATH_FILES = "C:\\Users\\Padawan06\\Documenti\\temp\\";
	
	// ATTENZIONE: modificare l'INTESTAZIONE comporta modificare l'ordine di scrittura dei campi 
	//			   dei contatti nel metodo rowBuilderCsv(Contatto contatto) di RubricaBuilder.
	private static final String INTESTAZIONE = "COGNOME;NOME;E-MAIL;TELEFONO";
	
	private static final int NUMERO_CONTATTI = 10;
	
	public static void main(String[] args) throws IOException {
		
		// PREPARAZIONE DEI FILE DI NOMI E COGNOMI, DA LEGGERE
		File fileNomi = new File(PATH_FILES + "nomi_italiani.txt");
		File fileCognomi = new File(PATH_FILES + "cognomi.txt");
		
		// DICHIARAZIONE DELLE VARIABILI
		List<String> recordNomitemp = new ArrayList<>();
		List<String> recordCognomitemp = new ArrayList<>();		

		List<Contatto> recordContatti = new ArrayList<>();
		
		List<String> listaTelefoni = new ArrayList<>();
		List<String> listaEmail = new ArrayList<>();
		
		
		// LETTURA E MEMORIZZAZIONE NOMI E COGNOMI
		recordNomitemp = RubricaBuilder.memContent(fileNomi, recordNomitemp);
		recordCognomitemp = RubricaBuilder.memContent(fileCognomi, recordCognomitemp); 
		
		// GENERATORE LISTA CONTATTI DA SCRIVERE IN RUBRICA
		RubricaBuilder.builderRandomContacts(NUMERO_CONTATTI, 
												recordNomitemp, 
												recordCognomitemp, 
												listaTelefoni, 
												listaEmail,
												recordContatti);
		
		// SCRITTURA DEI CONTATTI IN RUBRICA
		RubricaBuilder.writeRubricaCsv(PATH_FILES + "rubrica.csv", INTESTAZIONE, recordContatti);		

		System.out.println("Rubrica completata!");	
		
	}
}
