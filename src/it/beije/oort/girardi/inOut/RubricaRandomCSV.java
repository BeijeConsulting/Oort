package it.beije.oort.girardi.inOut;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.beije.oort.rubrica.Contatto;

public class RubricaRandomCSV {
	
// class variable:		
	private static final String PATH_FILES = "C:\\Users\\Padawan05\\Desktop\\file_testo\\";
	private static final int NUMERO_CONTATTI = 1000; 
	//recordTelefono e recordEmail potrebbero essere più corti di NUMERO_CONTATTI 
	private static List<String> recordTelefono = new ArrayList<>();
	private static List<String> recordEmail = new ArrayList<>();
	
	
// -------------- METODI ------------------
// lettura e memorizzazione record da file csv
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
			s.append((int) (Math.random() * 10));
		}
		
		return s.toString();
	}
	
	
// genera numero di telefono secondo l'aggiornamento
	private static String generaNumeroAgg() {
		StringBuilder strb = new StringBuilder();
		int index = (int) ((Math.random() * 8) + 1);
		switch (index) {
		case 1:
			break;
		case 2:
			if (recordTelefono.size() > 0) {
				int i = (int) (Math.random() * recordTelefono.size());
				strb.append(recordTelefono.get(i));
			}
			break;
		case 3:
		case 4:
			strb.append("+39" + generaNumero());
			recordTelefono.add(strb.toString());
			break;
		case 5:
		case 6:
		case 7:
		case 8:
			strb.append(generaNumero());
			recordTelefono.add(strb.toString());
			break;
		default: 
			System.out.println("something wrong happened during number update ");
		}
		return strb.toString();		
	}
	

// genera mail secondo delle specifiche random
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
	


// genera mail secondo l'aggiornamento
	private static String generaMailAgg(String nome, String cognome) {
		StringBuilder strb = new StringBuilder();
		int index = (int) ((Math.random() * 10) + 1);
		switch (index) {
		case 1:
		case 2:
			break;
		case 3:
		case 4:
		case 5:
			if (recordEmail.size() > 0) {
				int i = (int) (Math.random() * recordEmail.size());
				strb.append(recordEmail.get(i));
			}
			break;
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
			strb.append(generaMail(nome,cognome));
			recordEmail.add(strb.toString());
			break;
		default: 
			System.out.println("something wrong happened during e-mail update ");
		}
		return strb.toString();	
	}
	

	
// scrittura delle righe di un file csv dato un array di stringhe 
	private static String costruisciRiga(String... campi) {
		StringBuilder riga = new StringBuilder();
		for(String campo : campi) {
			riga.append(campo).append(';');
		}
		riga.deleteCharAt(riga.length() - 1);
		return riga.toString();
	}

	
// scrittura delle righe di un file csv dato un Contatto
private static String costruisciRiga(Contatto contatto) {
	return costruisciRiga(contatto.getNome(), contatto.getCognome(), 
						  contatto.getTelefono(), contatto.getEmail());
}



// --------------- MAIN ---------------	
	public static void main(String[] args) throws IOException {
		
		File fileNomi = new File(PATH_FILES + "nomiNoIntro.txt");
		File fileCognomi = new File(PATH_FILES + "cognomiNoIntro.txt");
		
		// Dichiarazione variabili 
		List<String> recordNomi = new ArrayList<>();
		List<String> recordCognomi = new ArrayList<>();
	
		List<Contatto> recordContatti = new ArrayList<>();
		
		// lettura e memorizzazione dei record di nomi e cognomi
		recordNomi = RubricaRandomCSV.memContent(fileNomi, recordNomi);
		recordCognomi = RubricaRandomCSV.memContent(fileCognomi, recordCognomi); 
		
		// riempio il recordContatti generando i numeri e le e-mail
		for(int k = 0; k < NUMERO_CONTATTI; k++) {
			int i = (int) (Math.random()*recordNomi.size()); 
			int j = (int) (Math.random()*recordCognomi.size());
			Contatto contatto = new Contatto(
			recordNomi.get(i),
			recordCognomi.get(j),
		//	RubricaRandomCSV.generaNumero(),
			RubricaRandomCSV.generaNumeroAgg(),
		//	RubricaRandomCSV.generaMail(recordNomi.get(i), recordCognomi.get(j)) );
			RubricaRandomCSV.generaMailAgg(recordNomi.get(i), recordCognomi.get(j)) );
			
			recordContatti.add(contatto);
		}		
		
		// fase di scrittura	
		File output = new File(PATH_FILES + "rubrica.csv");
		FileWriter writer = new FileWriter(output);
		
		//scrivo direttamente il contatto
//		writer.write("NOME;COGNOME;TELEFONO;E-MAIL");
//		for (Contatto contattoTemp : recordContatti) {
//			writer.write("\n");
//			writer.write(RubricaRandomCSV.costruisciRiga(contattoTemp));
//		}
		
		// scrivo il contatto con l'ordine prescelto
		writer.write("COGNOME;NOME;E-MAIL;TELEFONO");
		for (Contatto contattoTemp : recordContatti) {
			writer.write("\n");
			writer.write(RubricaRandomCSV.costruisciRiga(
				contattoTemp.getCognome(),
				contattoTemp.getNome(),
				contattoTemp.getEmail(),
				contattoTemp.getTelefono() ) );
		}
		
		writer.flush();
		writer.close();
		System.out.println("Rubrica completata!");
		System.out.println(recordTelefono.size());
		System.out.println(recordEmail.size());
	}
	
}
	