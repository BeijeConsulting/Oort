package it.beije.oort.file.sala.biblioteca;

import java.util.Scanner;
import java.util.List;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

public class ScannerBiblioteca {
	
	private List<Databasable> list;
	private Scanner s;
	private static final String EXIT_TOKEN = ":Q";
	StringBuilder sb = new StringBuilder("<----------Cosa desideri fare ?---------->\n");
	private String intro = sb.append("Digita 1 per inserire manualmente una nuova lista di libri.\n")
			.append("Digita 2 per inserire manualmente una nuova lista di autori.\n")
			.append("Digita 3 per inserire manualmente una nuova lista di editori.\n")
			.append("Digita 4 per inserire manualmente una nuova lista di utenti.\n")
			.append("Digita 5 per inserire manualmente una nuova lista di prestiti.\n")
			.append("Digita :q per scartare tutti i dati ed uscire dall'utility.").toString();
	
	public ScannerBiblioteca() {
		list = new ArrayList<Databasable>();
		s = new Scanner(System.in);
	}
	
	private void insertLibri() {
		String bufferTitolo, bufferDescrizione, bufferAnno;
		Integer id_autore, id_editore;
		boolean insertFlag = true;
		
		while(insertFlag) {
			try {
				System.out.println("Inserire l'id dell'autore del prossimo libro:");
				id_autore = new Integer(s.nextLine());
				System.out.println("Inserire l'id dell'editore del prossimo libro:");
				id_editore = new Integer(s.nextLine());
				System.out.println("Inserire il titolo per il prossimo libro:");
				bufferTitolo = s.nextLine();
				System.out.println("Inserire la descrizione per il prossimo libro:");
				bufferDescrizione = s.nextLine();
				System.out.println("Inserire l'anno di pubblicazione del prossimo libro(formato YYYY):");
				bufferAnno = s.nextLine();
				
				list.add(new Libro(null, id_autore, id_editore, bufferTitolo,
						bufferDescrizione, Year.parse(bufferAnno)));
			} catch(NumberFormatException e) {
				System.out.println("Qualcosa è andato storto nell'inserimento dei parametri. Riprova.");
				continue;
			}
		
			System.out.println("Digita 'save': inserire lista corrente nel database.");
			System.out.println("Digita 'more': aggiungere un altro libro alla lista.");
			String buffer = s.nextLine();
			while(!(buffer.equalsIgnoreCase("save")||
					buffer.equalsIgnoreCase("more")||
					buffer.equalsIgnoreCase("quit"))) {
				System.out.println("Input non riconosciuto, riprovare...");
				buffer=s.nextLine();
			}
			if(buffer.equalsIgnoreCase("save")) {
				insertFlag = false;
				HibernateToolsetBiblio.insertHibernate(list);
				list.clear();
			} else if(buffer.equalsIgnoreCase("quit")) {
				list.clear();
				System.out.println("Ritorno al menù...");
				insertFlag=false;

			}
		}
	}
	
	//sub-routine per inserire autori
	private void insertAutori() {
		String nome, cognome, biografia, nascita, morte;
		boolean insertFlag = true;
		
		while(insertFlag) {
			System.out.println("Inserire il nome dell'autore:");
			nome = s.nextLine();
			System.out.println("Inserire il cognome dell'autore:");
			cognome = s.nextLine();
			System.out.println("Inserire la data di nascita dell'autore:");
			nascita = s.nextLine();
			System.out.println("Inserire la data di morte dell'autore:");
			morte = s.nextLine();
			System.out.println("Inserire una breve biografia dell'autore:");
			biografia = s.nextLine();
				
			if(nome.equals("") && cognome.equals("")) {
				System.out.println("Inserire almeno il nome o il cognome!");
				continue;
			} else {
				list.add(new Autore(null, nome, cognome, biografia, LocalDate.parse(nascita),
						LocalDate.parse(morte)));	
			}
		
			System.out.println("Digita 'save': inserire lista corrente nel database.");
			System.out.println("Digita 'more': aggiungere un altro autore alla lista.");
			System.out.println("Digita 'quit': scartare tutti i dati e tornare al menù.");
			String buffer = s.nextLine();
			while(!(buffer.equalsIgnoreCase("save")||
					buffer.equalsIgnoreCase("more")||
					buffer.equalsIgnoreCase("quit"))) {
				System.out.println("Input non riconosciuto, riprovare...");
				buffer=s.nextLine();
			}
			if(buffer.equalsIgnoreCase("save")) {
				insertFlag = false;
				HibernateToolsetBiblio.insertHibernate(list);
				list.clear();
			} else if(buffer.equalsIgnoreCase("quit")) {
				list.clear();
				System.out.println("Ritorno al menù...");
				insertFlag=false;

			}
		}
	}
	
	//sub-routine per inserire editori
	private void insertEditori() {
		String denominazione, descrizione;
		boolean insertFlag = true;
		
		while(insertFlag) {
			System.out.println("Inserire la denominazione dell'editore:");
			denominazione = s.nextLine();
			System.out.println("Inserire una breve descrizione dell'editore:");
			descrizione = s.nextLine();
			
				
			if(denominazione.equals("")) {
				System.out.println("Inserire almeno la denominazione!");
				continue;
			} else {
				list.add(new Editore(null, denominazione, descrizione));
			}
		
			System.out.println("Digita 'save': inserire lista corrente nel database.");
			System.out.println("Digita 'more': aggiungere un altro editore alla lista.");
			System.out.println("Digita 'quit': scartare tutti i dati e tornare al menù.");
			String buffer = s.nextLine();
			while(!(buffer.equalsIgnoreCase("save")||
					buffer.equalsIgnoreCase("more")||
					buffer.equalsIgnoreCase("quit"))) {
				System.out.println("Input non riconosciuto, riprovare...");
				buffer=s.nextLine();
			}
			if(buffer.equalsIgnoreCase("save")) {
				insertFlag = false;
				HibernateToolsetBiblio.insertHibernate(list);
				list.clear();
			} else if(buffer.equalsIgnoreCase("quit")) {
				list.clear();
				System.out.println("Ritorno al menù...");
				insertFlag=false;

			}
		}
	}
	
	//sub-routine per inserire utenti
	private void insertUtenti() {
		String nome, cognome, codicef, email, telefono, indirizzo;
		boolean insertFlag = true;
		
		while(insertFlag) {
			System.out.println("Inserire il nome utente:");
			nome = s.nextLine();
			System.out.println("Inserire il cognome utente:");
			cognome = s.nextLine();
			System.out.println("Inserire il codice fiscale dell'utente:");
			codicef = s.nextLine();
			System.out.println("Inserire l'email dell'utente:");
			email = s.nextLine();
			System.out.println("Inserire il numero di telefono dell'utente:");
			telefono = s.nextLine();
			System.out.println("Inserire l'indirizzo di residenza dell'utente:");
			indirizzo = s.nextLine();
				
			if(nome.equals("") || cognome.equals("") || codicef.equals("")) {
				System.out.println("Inserire almeno il nome, cognome e codice fiscale!");
				continue;
			} else {
				list.add(new Utente(null, nome, cognome, codicef, email, telefono, indirizzo));	
			}
		
			System.out.println("Digita 'save': inserire lista corrente nel database.");
			System.out.println("Digita 'more': aggiungere un altro utente alla lista.");
			System.out.println("Digita 'quit': scartare tutti i dati e tornare al menù.");
			String buffer = s.nextLine();
			while(!(buffer.equalsIgnoreCase("save")||
					buffer.equalsIgnoreCase("more")||
					buffer.equalsIgnoreCase("quit"))) {
				System.out.println("Input non riconosciuto, riprovare...");
				buffer=s.nextLine();
			}
			if(buffer.equalsIgnoreCase("save")) {
				insertFlag = false;
				HibernateToolsetBiblio.insertHibernate(list);
				list.clear();
			} else if(buffer.equalsIgnoreCase("quit")) {
				list.clear();
				System.out.println("Ritorno al menù...");
				insertFlag=false;
			}
		}
	}
	
	//sub-routine per inserire prestiti
	private void insertPrestiti() {
		String  inizio, fine, note;
		Integer id_utente,id_libro;
		LocalDate ini, fin;
		boolean insertFlag = true;
		
		while(insertFlag) {
			try {
				System.out.println("Inserire l'id dell'utente che sta richiedendo il prestito:");
				id_utente = new Integer(s.nextLine());
				System.out.println("Inserire l'id del libro per cui si sta richiedendo il prestito:");
				id_libro = new Integer(s.nextLine());
				System.out.println("Inserire la data di inizio prestito (lasciare vuoto per inserire data di oggi)");
				inizio = s.nextLine();
				System.out.println("Inserire la data di fine se il prestito ha durata non standard(1 mese):");
				fine = s.nextLine();
				System.out.println("Inserire note riguardanti il prestito:");
				note = s.nextLine();
				
				if(inizio.equals("")) ini=LocalDate.now();
				else ini = LocalDate.parse(inizio);
				if(fine.equals("")) fin=ini.plusDays(30);
				else fin = LocalDate.parse(fine);
				
				list.add(new Prestito(null, id_utente, id_libro, ini, fin, note));
				
			} catch(NumberFormatException e) {
				System.out.println("id utente o id libro non validi, riprovare...");
				continue;
			}
		
			System.out.println("Digita 'save': inserire lista corrente nel database.");
			System.out.println("Digita 'more': aggiungere un altro prestito alla lista.");
			System.out.println("Digita 'quit': scartare tutti i dati e tornare al menù.");
			String buffer = s.nextLine();
			while(!(buffer.equalsIgnoreCase("save")||
					buffer.equalsIgnoreCase("more")||
					buffer.equalsIgnoreCase("quit"))) {
				System.out.println("Input non riconosciuto, riprovare...");
				buffer=s.nextLine();
			}
			if(buffer.equalsIgnoreCase("save")) {
				insertFlag = false;
				HibernateToolsetBiblio.insertHibernate(list);
				list.clear();
			} else if(buffer.equalsIgnoreCase("quit")) {
				list.clear();
				System.out.println("Ritorno al menù...");
				insertFlag=false;
			}
		}
	}
	
	//main routine
	public void mainRoutine() {
		boolean flag = true;
		
		System.out.println("|-----------------------------------------------|");
		System.out.println("|-Utility di interfaccia al database Biblioteca-|");
		System.out.println("|-----------------------------------------------|");
		
		while(flag) {
			System.out.println(intro);
			String buffer = s.nextLine();
			if(buffer.equalsIgnoreCase(EXIT_TOKEN)) {
				flag=false;
				System.out.println("Terminating.");
				continue;
			} else if(buffer.equals("1")) insertLibri();
			else if(buffer.equals("2")) insertAutori();
			else if(buffer.equals("3")) insertEditori();
			else if(buffer.equals("4")) insertUtenti();
			else if(buffer.equals("5")) insertPrestiti();
			else {
				flag=false;
				System.out.println("Input non riconosciuto. Termino.");
				continue;
			}
		}
		s.close();
	}
	
	public static void main(String[] args) {
		new ScannerBiblioteca().mainRoutine();
	}
}