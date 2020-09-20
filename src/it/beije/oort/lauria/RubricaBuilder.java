package it.beije.oort.lauria;

import java.io.*;
import java.util.List;


public class RubricaBuilder {
	
	public static void builderRandomContacts(int size, 
												List<String> listaNomi,
												List<String> listaCognomi,
												List<String> listaTelefoni,
												List<String> listaEmail,
												List<Contatto> recordContatti) {
		
		//List<Contatto> recordContatti = new ArrayList<>();
		for(int k = 0; k < size; k++) {
			int i = (int) (Math.random()*listaNomi.size()); 
			int j = (int) (Math.random()*listaCognomi.size());
			
//			Contatto contatto = new Contatto(
//					recordNomitemp.get(i),
//					recordCognomitemp.get(j),
//					RecordFile.generaNumero(),
//					RecordFile.generaMail(recordNomitemp.get(i), recordCognomitemp.get(j)));
			Contatto contatto = new Contatto();
			contatto.setNome( listaNomi.get(i) );
			contatto.setCognome( listaCognomi.get(j) );
			contatto.setTelefono( RubricaBuilder.generaNumero(listaTelefoni) );
			contatto.setEmail( RubricaBuilder.generaMail( listaEmail, contatto.getNome(), contatto.getCognome() ) );
			
			recordContatti.add(contatto);
			
		}
		//return recordContatti;		
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
	

	private static String generaNumero(List<String>  listaTelefoni) {
		StringBuilder telefono = new StringBuilder();
		
		int randomTel = (int) (Math.random() * 8) + 1;
		
		if(randomTel==1) {
			telefono.append("");
		}else if(randomTel==2){
			if(listaTelefoni.size() > 0) {
				int randomLista = (int) (Math.random() * listaTelefoni.size());
				telefono.append(listaTelefoni.get(randomLista));
			}
			else {
				telefono.append("");
				//listaTelefoni.add(telefono.toString());
			}			
		}else if(randomTel==3 || randomTel==4 ){
			telefono.append("+39").append(RubricaBuilder.generaNumero());
			listaTelefoni.add(telefono.toString());
		}else{
			telefono.append(RubricaBuilder.generaNumero());
			listaTelefoni.add(telefono.toString());
		}
		
		return telefono.toString();
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
	
	private static String generaMail(List<String>  listaEmail, String nome, String cognome) {	
		StringBuilder email = new StringBuilder();
		
		int randomMail = (int) (Math.random() * 10) + 1;
				
		if(randomMail < 3) {
			email.append("");
		}else if(3 <= randomMail && randomMail <= 5){
			if(listaEmail.size() > 0) {
				int randomLista = (int) (Math.random() * listaEmail.size());			
				email.append(listaEmail.get(randomLista));
				//listaEmail.add(email.toString());
			}else {
				email.append("");
				//listaEmail.add(email.toString());
			}
		}else{
			email.append(RubricaBuilder.generaMail(nome, cognome));
			listaEmail.add(email.toString());
		}
		
		return email.toString();
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
	
	
	public static void writeRubricaCsv(File output, String intestazione, List<Contatto> recordContatti) throws IOException {
		
		FileWriter writer = new FileWriter(output);
		
		writer.write(intestazione);
		for (Contatto contattoTemp : recordContatti) {
			writer.write("\n");
			if((int) (Math.random() * 5) + 1 == 1) contattoTemp.setNome("");
			if((int) (Math.random() * 3) + 1 == 1) contattoTemp.setCognome("");
			writer.write(RubricaBuilder.rowBuilderCsv(contattoTemp));
		}
		
		writer.flush();
		writer.close();
	}
	
	public static void writeRubricaCsv(String fileName, String intestazione, List<Contatto> recordContatti) throws IOException {		
		File output = new File(fileName);
		RubricaBuilder.writeRubricaCsv(output, intestazione, recordContatti);
	}

	
	// scrittura record sui file
	public static String rowBuilderCsv(Contatto contatto) {
		return RubricaBuilder.rowBuilderCsv(contatto.getCognome(), contatto.getNome(), 
								contatto.getEmail(),  contatto.getTelefono());
	}
	
	public static String rowBuilderCsv(String... campi) {
		StringBuilder riga = new StringBuilder();
		for(String campo : campi) {
			riga.append(campo).append(';');
		}
		riga.deleteCharAt(riga.length() - 1);
		return riga.toString();
	}

}