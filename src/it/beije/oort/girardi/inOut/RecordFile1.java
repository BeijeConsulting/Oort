package it.beije.oort.girardi.inOut;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecordFile1 {
		
	private static final String PATH_FILES = "C:\\Users\\Padawan05\\Desktop\\file_testo\\";
	
// -------------- METODI ------------------
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
	

// estrarre metodi per migliorare leggibilità
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
			
			// Sostituzione delle vocali, la parte commentata è meno efficiente (4 secondi 
			//risparmiati generando 1 milione di righe)
			if (randomAbbreviaNome >= 6 && randomSeparatore < 4) {
				int randomVocale = (int) (Math.random() * 5) + 1;
				if (randomVocale == 1) {
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

	
// scrittura record sui file
	private static String costruisciRiga(String... campi) {
		StringBuilder riga = new StringBuilder();
		for(String campo : campi) {
			riga.append(campo).append(';');
		}
		riga.deleteCharAt(riga.length() - 1);
		return riga.toString();
	}




// --------------- MAIN ---------------	
	public static void main(String[] args) throws IOException {
		
		File fileNomi = new File(PATH_FILES + "nomiNoIntro.txt");
		File fileCognomi = new File(PATH_FILES + "cognomiNoIntro.txt");
		
		// Dichiarazione variabili 
		List<String> recordNomitemp = new ArrayList<>();
		List<String> recordCognomitemp = new ArrayList<>();
		
		List<String> recordNomi = new ArrayList<>();
		List<String> recordCognomi = new ArrayList<>();	
		List<String> recordTel = new ArrayList<>();
		List<String> recordMail = new ArrayList<>();
		
		// lettura e memorizzazione dei record di nomi e cognomi
		recordNomitemp = RecordFile1.memContent(fileNomi, recordNomitemp);
		recordCognomitemp = RecordFile1.memContent(fileCognomi, recordCognomitemp); 
		
		for(int k = 0; k < 1000; k++) {
			int i = (int) (Math.random()*recordNomitemp.size()); 
			int j = (int) (Math.random()*recordCognomitemp.size());
			recordNomi.add(recordNomitemp.get(i));
			recordCognomi.add(recordCognomitemp.get(j));
			recordTel.add(RecordFile1.generaNumero());
			recordMail.add(RecordFile1.generaMail(recordNomi.get(k), recordCognomi.get(k)));
		}
		
		// fase di scrittura	
		File output = new File(PATH_FILES + "rubrica.txt");
		FileWriter writer = new FileWriter(output);
		
		writer.write("COGNOME;NOME;E-MAIL;TELEFONO");
		for (int arrayIndex = 0; arrayIndex < recordNomi.size(); arrayIndex++) {
			writer.write("\n");
			writer.write(RecordFile1.costruisciRiga(
					recordCognomi.get(arrayIndex),
					recordNomi.get(arrayIndex),
					recordMail.get(arrayIndex),
					recordTel.get(arrayIndex)
					));
		}
		
		writer.flush();
		writer.close();
		System.out.println("Rubrica completata!");
	}
	
}
	