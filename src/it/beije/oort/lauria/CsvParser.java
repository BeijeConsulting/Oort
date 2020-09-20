package it.beije.oort.lauria;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {

	/**
	 * Lettura di un file.csv contenente una lista di contatti e memorizzazione in una lista di contatti.
	 * 
	 * @param fileName : nome del file da leggere in formato csv
	 * @return Lista dei contatti memorizzati.
	 * @throws IOException
	 */
	public static List<Contatto> readContattiCsv(String fileName) throws IOException{
		File file = new File(fileName);
		return CsvParser.readContattiCsv(file);
	}
	
	/**
	 * Lettura di un file.csv contenente una lista di contatti e memorizzazione in una lista di contatti.
	 * 
	 * @param fileName : file da leggere in formato csv
	 * @return Lista dei contatti memorizzati.
	 * @throws IOException
	 */
	public static List<Contatto> readContattiCsv(File file) throws IOException{
		List<Contatto> arrayContatti = new ArrayList<Contatto>();
		
		FileReader fileReader = new FileReader(file);			
		BufferedReader bufferedReader = new BufferedReader(fileReader);	
		
		Contatto contatto = new Contatto();
		
		//System.out.println(bufferedReader.readLine());
		String riga0 = bufferedReader.readLine();
		String[] campi0 = riga0.split(";");
		for(int i = 0; i<campi0.length; i++) {
//			System.out.print(campi0[i]+" ");
//			System.out.println(bufferedReader.readLine());
//			if(campi[i].equalsIgnoreCase("nome")) {
//				prova.setNome(campi[i]);
//			}
		}
//		System.out.println();
			
		String Nome ="", Cognome="", Telefono="", Mail="";
	
		while (bufferedReader.ready()) {	
			
			String riga = bufferedReader.readLine();
			//System.out.println(riga);
			String[] campiriga = riga.split(";");
	
			for(int i = 0; i<campiriga.length; i++) {
				//System.out.print(" "+campiriga[i]);
				switch(campi0[i].toLowerCase()) {
					case "nome": Nome = campiriga[i]; break;
					case "cognome": Cognome = campiriga[i]; break;
					case "telefono": Telefono = campiriga[i]; break;
					case "email": Mail = campiriga[i]; break;
				}
				
			contatto = new Contatto(Nome, Cognome, Telefono, Mail);	
			
			}
	
			arrayContatti.add( contatto);
		}			
		
		fileReader.close();
		bufferedReader.close();
		
		return arrayContatti;
	}
	
	/**
	 * Metodo per la scrittura di una lista di contatti passata in ingresso, sul file fileName in formato .csv .
	 * 
	 * @param fileName : Nome file di output in formato .csv .
	 * @param intestazione : Ordine di scrittura dei campi di un contatto.
	 * @param recordContatti : Lista di contatti da scrivere nel file fileName di output.
	 * @throws IOException
	 */
	public static void writeContattiCsv(String fileName, String intestazione, List<Contatto> recordContatti) throws IOException {		
		File output = new File(fileName);
		CsvParser.writeContattiCsv(output, intestazione, recordContatti);
	}

	/**
	 * Metodo per la scrittura di una lista di contatti passata in ingresso, sul file fileName in formato .csv .
	 * 
	 * @param output : File di output in formato .csv .
	 * @param intestazione : Ordine di scrittura dei campi di un contatto.
	 * @param recordContatti : Lista di contatti da scrivere nel file fileName di output.
	 * @throws IOException
	 */
	public static void writeContattiCsv(File output, String intestazione, List<Contatto> recordContatti) throws IOException {
		
		FileWriter writer = new FileWriter(output);
		
		writer.write(intestazione);
		for (Contatto contattoTemp : recordContatti) {
			writer.write("\n");
			writer.write(RubricaBuilder.rowBuilderCsv(contattoTemp));
		}
		
		writer.flush();
		writer.close();
	}
	
}
