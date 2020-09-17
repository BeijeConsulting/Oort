package it.beije.oort.madonia.rubrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecordFile {
	
	private static final String PATH_FILES = "/temp/rubrica/";
	private static final int NUMERO_CONTATTI = 100;
	
//	// lettura file
//	public static String getContent(File file) throws IOException {
//		FileReader fileReader = new FileReader(file);
//		StringBuilder builder = new StringBuilder();		
//		BufferedReader bufferedReader = new BufferedReader(fileReader);
//		
//		while (bufferedReader.ready()) {
//			builder.append(bufferedReader.readLine()).append('\n');
//		}
//		
//		return builder.toString();	
//	}
	
	public static void main(String[] args) throws IOException {
		
		File fileNomi = new File(PATH_FILES + "nomi_italiani.txt");
		File fileCognomi = new File(PATH_FILES + "cognomi_italiani.txt");
		
		// Dichiarazione variabili 
		List<String> recordNomitemp = new ArrayList<>();
		List<String> recordCognomitemp = new ArrayList<>();
		
		List<Contatto> recordContatti = new ArrayList<>();
		
		// lettura e memorizzazione dei record di nomi e cognomi
		recordNomitemp = RecordFile.memContent(fileNomi, recordNomitemp);
		recordCognomitemp = RecordFile.memContent(fileCognomi, recordCognomitemp); 
		
		for(int k = 0; k < NUMERO_CONTATTI; k++) {
			int i = (int) (Math.random()*recordNomitemp.size()); 
			int j = (int) (Math.random()*recordCognomitemp.size());
			
			Contatto contatto = new Contatto(
					recordNomitemp.get(i),
					recordCognomitemp.get(j),
					RecordFile.generaNumero(),
					RecordFile.generaMail(recordNomitemp.get(i), recordCognomitemp.get(j)));
			recordContatti.add(contatto);
		}
		
		WriterCsvRubrica.writeCsvFile(new String[] {"COGNOME","NOME","TELEFONO", "EMAIL"}, recordContatti, PATH_FILES + "rubrica_prova.txt");
		
	}
	
	// lettura e memorizzazione record	
	public static List<String> memContent(File file, List<String> record ) throws IOException {			
		FileReader fileReader = new FileReader(file);			
		BufferedReader bufferedReader = new BufferedReader(fileReader);			
		
		while (bufferedReader.ready()) {
			record.add(bufferedReader.readLine());
		}
		
		fileReader.close();
		bufferedReader.close();

		return record;
	}
	
	// genera Numero di telefono
	private static String generaNumero() {
		String[] prefissi = {"345", "346", "347", "348", "349"};
		StringBuilder s = new StringBuilder();
		
		int indexPrefisso = (int) (Math.random() * prefissi.length);
		s.append(prefissi[indexPrefisso]);
		
		for(int i = 0; i < 7; i++) {
			s.append((int) (Math.random() * 9));
		}
		
		return s.toString();
	}
	
//	private static String generaMail(String nome, String cognome) {
//		String[] dominio = {"gmail.com", "hotmail.com", "hotmail.it", "libero.it", "yahoo.com", "virgilio.it", "tim.it", "alice.it"};
//		StringBuilder s = new StringBuilder();
//		
//		nome = nome.replace(" ", "").replace("'", "").toLowerCase();
//		cognome = cognome.replace(" ", "").replace("'", "").toLowerCase();
//		
//		s.append(nome).append('.').append(cognome).append('@');
//		
//		int indexDominio = (int) (Math.random() * dominio.length);
//		s.append(dominio[indexDominio]);
//		
//		return s.toString();
//	}
	
	// TODO estrarre metodi per migliorare leggibilità
	private static String generaMail(String nome, String cognome) {
		// Preparazione variabili da utilizzare
		String[] dominio = {"gmail.com", "hotmail.com", "hotmail.it", "libero.it", "yahoo.com", "virgilio.it", "tim.it", "alice.it"};
		StringBuilder s = new StringBuilder();
		
		nome = nome.replace(" ", "").replace("'", "").toLowerCase();
		cognome = cognome.replace(" ", "").replace("'", "").toLowerCase();
		
		boolean putNome = (int) (Math.random() * 5) + 1 != 1;
		boolean putCognome = (int) (Math.random() * 3) + 1 != 1;
		
		// tre casistiche mutualmente esclusive
		
		// No nome, no cognome
		if ( !putNome && !putCognome ) { // ! (putNome || putCognome)
			// Mettere da 6 a 20 caratteri
			int numero = (int) (Math.random() * 14) + 6;
			for(char c = 'a'; c < 'a' + numero; c++) {
				s.append(c);
			}
			
			// O nome, o cognome
		} else if (putNome ^ putCognome) {
			// Inserire un numero da 00 a 100
			if (putNome) {
				s.append(nome);
			} else {
				s.append(cognome);
			}
			if ((int) (Math.random() * 10) + 1 > 1) {
				int numeroFinale = (int) (Math.random() * 101);
				if(numeroFinale < 10) { s.append("0"); }
				
				s.append(numeroFinale);
			}
			
			// Nome e cognome
		} else {
			int randomAbbreviaNome = (int) (Math.random() * 10) + 1;
			int randomSeparatore = (int) (Math.random() * 10) + 1;
			int randomInversione = (int) (Math.random() * 4) + 1;
			String separatore = "";
			
			// Nome solo prima lettera
			if(randomAbbreviaNome < 3) {
				nome = nome.substring(0,1);
				
				// Nome fino alla prima vocale (seconda per nomi che cominciano per vocale)
			} else if (randomAbbreviaNome >= 3 && randomAbbreviaNome < 6) {
				char[] vocali = {'a','e','i','o','u'};
				int indexStop = -1;
				for(char vocale : vocali) {
					int indexTemp = nome.indexOf(vocale,1);
					if(indexStop < 0) {
						indexStop = indexTemp;
					} else if(indexTemp > 0) {
						indexStop = indexStop < indexTemp ? indexStop : indexTemp;
					}
				}
				
				if(indexStop > 0) {
					nome = nome.substring(0, indexStop + 1);
				}
			}
			
			// Quale separatore usare
			if(randomSeparatore>= 4 && randomSeparatore < 8) {
				separatore = ".";
			} else if (randomSeparatore>= 8) {
				separatore = "-";
			}
			
			// Se fare inversione tra nome e cognome
			if(randomInversione == 1) {
				s.append(cognome).append(separatore).append(nome);
			} else {
				s.append(nome).append(separatore).append(cognome);
			}
			
			// Sostituzione delle vocali, la parte commentata è meno efficiente (4 secondi risparmiati generando 1 milione di righe)
			if (randomAbbreviaNome >= 6 && randomSeparatore < 4) {
				int randomVocale = (int) (Math.random() * 5) + 1;
				if (randomVocale == 1) {
//					String nomeTemp = s.toString();
//					s = new StringBuilder(nomeTemp.replace('a', '4').replace('e', '3').replace('i', '1').replace('o', '0'));
					for(int i = 0; i < s.length(); i++) {
						switch(s.charAt(i)) {
						case 'a':
							s.deleteCharAt(i).insert(i, '4');
							break;
						case 'e':
							s.deleteCharAt(i).insert(i, '3');
							break;
						case 'i':
							s.deleteCharAt(i).insert(i, '1');
							break;
						case 'o':
							s.deleteCharAt(i).insert(i, '0');
							break;
						}
					}
				}
			}
		}
		
		s.append("@");
		
		int indexDominio = (int) (Math.random() * dominio.length);
		s.append(dominio[indexDominio]);
		
		return s.toString();
	}
}