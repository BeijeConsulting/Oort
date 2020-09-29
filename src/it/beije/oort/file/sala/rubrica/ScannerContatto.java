package it.beije.oort.file.sala.rubrica;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.beije.oort.file.sala.db.HibernateToolset;

public class ScannerContatto {

	private List<Contatto> listContatti;
	private Scanner s;
	private static final String EXIT_TOKEN = ":Q";
	StringBuilder sb = new StringBuilder("Cosa desideri fare ?\n");
	private String intro = sb.append("Digita 1 per Inserire manualmente una nuova lista di contatti.\n")
			.append("Digita 2 per effettuare una ricerca sul database.\n")
			.append("Digita 3 per visualizzare a video il contenuto integrale della tabella")
			.append(" 'rubrica'(WARNING: potrebbe essere molto grande).\n")
			.append("Digita 4 per effettuare una modifica ad una riga del database o ")
			.append("cancellarla (WARNING: necessario l'id della riga).\n")
			.append("Digita 5 per selezionare ed esportare una lista contatti in un formato a scelta.\n")
			.append("Digita :q per scartare tutti i dati ed uscire dall'utility.").toString();
	//costruttore
	public ScannerContatto() {
		listContatti = new ArrayList<Contatto>();
		s = new Scanner(System.in);
	}
	//utility
	private String sanitizer(String s) {
		return s;
	}
	//sub-routines
	private void selectRoutine() {
		System.out.println("Inserisci il campo su cui effettuare la ricerca:(id_rubrica, nome, cognome, telefono o email)");
		String fieldName=s.nextLine();
		while(!fieldName.equals("id_rubrica")&&
				!fieldName.equals("nome")&&
				!fieldName.equals("cognome")&&
				!fieldName.equals("telefono")&&
				!fieldName.equals("email")) {
			System.out.println("Campo non valido! scegliere tra: id_rubrica, nome, cognome, telefono, email :");
			fieldName=s.nextLine();
		}
		System.out.println("Inserisci la parola chiave da usare per la ricerca:");
		String value=s.nextLine();
		System.out.println("-"+fieldName+"-"+value+"-");
		List<Contatto> temp = HibernateToolset.selectHibernate(fieldName, value);
		for(Contatto c : temp) {
			System.out.println(c.toStringFromDatabase());
		}
	}
	
	private void selectBulkRoutine() {
		List<Contatto> temp = HibernateToolset.selectHibernate(true);
		if(temp.size()<=20) {
			for(Contatto c : temp) {
				System.out.println(c.toStringFromDatabase());
			}
		} else if(temp.size()>20) {
			boolean pagFlag=true;
			int offset = temp.size()-20;
			int j = 0;
			while(pagFlag) {
				for(;j<temp.size()-offset;j++) {
					System.out.println(temp.get(j).toStringFromDatabase());
				}
				if(offset!=0) {
					System.out.println("Visualizzate righe da "+(j-20)+" a "+(temp.size()-offset)+" di "+temp.size()+
							". Premere un tasto qualsiasi per visualizzare le prossime 20");
					offset-=20;
					if(offset<0) offset=0;
					s.nextLine();
				} else {
					System.out.println("Non ci sono più righe da stampare.");
					pagFlag = false;
				}
			}
		} else {
			System.out.println("Nessun risultato dalla query, la tabella potrebbe essere vuota.");
		}
	}

	private void insertRoutine() {
		String bufferNome, bufferCognome, bufferTelefono, bufferEmail;
		boolean insertFlag = true;
		while(insertFlag) {
			System.out.println("Inserire il nome per il prossimo contatto:");
			bufferNome = sanitizer(s.nextLine());
			System.out.println("Inserire il cognome per il prossimo contatto:");
			bufferCognome = sanitizer(s.nextLine());
			System.out.println("Inserire il telefono per il prossimo contatto:");
			bufferTelefono = sanitizer(s.nextLine());
			System.out.println("Inserire l'email per il prossimo contatto:");
			bufferEmail = sanitizer(s.nextLine());
			
			if(bufferCognome.equalsIgnoreCase("") && bufferEmail.equalsIgnoreCase("") &&
					bufferNome.equalsIgnoreCase("") && bufferTelefono.equalsIgnoreCase("")) {
				System.out.println("Impossibile creare contatto con 4 campi vuoti, fornire almeno un campo non vuoto:");
				continue;
			} else listContatti.add(new Contatto(bufferNome, bufferCognome, bufferTelefono, bufferEmail));
		
			System.out.println("save: inserire lista corrente nel database.\nmore: aggiungere un altro contatto.");
			String buffer = s.nextLine();
			while(!(buffer.equalsIgnoreCase("save")||buffer.equalsIgnoreCase("more"))) {
				System.out.println("Input non riconosciuto, riprovare...");
				buffer=s.nextLine();
			}
			if(buffer.equalsIgnoreCase("save")) {
				insertFlag = false;
				HibernateToolset.insertHibernate(listContatti);
				listContatti.clear();
				continue;
			}
			else if(buffer.equalsIgnoreCase("more")) {
				continue;
			}		
		}
	}
	
	private void modifyRoutine() {
		boolean flagUpdate = false;
		boolean flagDelete = false;
		System.out.println("Digitare 'modifica' per effettuare una modifica o 'cancella' per eliminare una riga dal db...");
		String sc = s.nextLine();
		if(sc.equalsIgnoreCase("modifica")) flagUpdate = true;
		else if(sc.equalsIgnoreCase("cancella")) flagDelete = true;

		while(flagUpdate) {
			int id;
			String field, value, check;
			System.out.println("Inserisci l'id del campo che vuoi modificare:");
			id = Integer.parseInt(s.nextLine());
			System.out.println("Inserisci il nome del campo da modificare (nome, cognome, telefono o email):");
			field = s.nextLine();
			System.out.println("Inserisci il nuovo valore da assegnare al campo:");
			value = s.nextLine();
			//RubricaToolset.updateWithId(id, field, value);
			HibernateToolset.updateHibernate(id, field, value);
			System.out.println(":q per tornare indietro, qualsiasi altra cosa per effettuare un'altra modifica.");
			check = s.nextLine();
			if(check.equalsIgnoreCase(EXIT_TOKEN)) flagUpdate=false;
		}
		
		while(flagDelete) {
			String check;
			int id;
			System.out.println("Inserisci l'id del campo che vuoi eliminare");
			id = Integer.parseInt(s.nextLine());
			//RubricaToolset.deleteWithId(id);
			HibernateToolset.deleteHibernate(id);
			System.out.println(":q per tornare indietro, qualsiasi altra cosa per cancellare un altra riga.");
			check = s.nextLine();
			if(check.equalsIgnoreCase(EXIT_TOKEN)) flagDelete=false;
		}
	}
	
	private void exportRoutine() {
		String fieldName, value, filename;
		System.out.println("Inserire il campo della tabella su cui effettuare la ricerca:");
		fieldName=s.nextLine();
		System.out.println("Inserire la parola chiave che si vuole usare nella ricerca:");
		value=s.nextLine();
		List<Contatto> temp = HibernateToolset.selectHibernate(fieldName, value);
		System.out.println("Inserire il nome del file di destinazione (con formato .csv o .xml):");
		filename = s.nextLine();
		if(filename.endsWith(".csv")) RubricaToolset.contattoToCsv(temp, filename);
		else if(filename.endsWith(".xml")) RubricaToolset.contattoToXml(temp, filename);
		else System.out.println("Impossibile esportare il file.");
	}

	//main routine
	public void mainRoutine() {
		boolean flag = true;
		
		System.out.println("|----------------------------------|");
		System.out.println("|Utility di interfaccia al database|");
		System.out.println("|----------------------------------|");
		
		while(flag) {
			System.out.println(intro);
			String buffer = s.nextLine();
			if(buffer.equalsIgnoreCase(EXIT_TOKEN)) {
				flag=false;
				System.out.println("Terminating.");
				continue;
			}
			else if(buffer.equals("1")) insertRoutine();
			else if(buffer.equals("2")) selectRoutine();
			else if(buffer.equals("3")) selectBulkRoutine();
			else if(buffer.equals("4")) modifyRoutine();
			else if(buffer.equals("5")) exportRoutine();
			else {
				flag=false;
				System.out.println("Input non riconosciuto. Termino.");
				continue;
			}
		}
		s.close();
	}
	
	public static void main(String[] args) {
		new ScannerContatto().mainRoutine();
	}
}