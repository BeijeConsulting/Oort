package it.beije.oort.file.sala.biblioteca;

import java.util.Scanner;

import it.beije.oort.file.sala.db.HibernateToolset;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
	
	private String sublist (String table) {
			List<Databasable> temp = JPAToolset.selectJPA(table);
			if(temp.size()<=20) {
				for(Databasable d : temp) {
					System.out.println(d.toString());
				}
			} else if(temp.size()>20) {
				boolean pagFlag=true;
				int offset = temp.size()-20;
				int j = 0;
				while(pagFlag) {
					for(;j<temp.size()-offset;j++) {
						System.out.println(temp.get(j).toString());
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
			System.out.println("Digitare id "+table+" desiderato:");

		return s.nextLine();
	}
	
	private void insertLibri() {
		String titolo, desc;
		Integer id_autore, id_editore;
		Short anno;
		boolean insertFlag = true;
		
		while(insertFlag) {
			try {
				System.out.println("Inserire l'id dell'autore del prossimo libro scegliendo dalla lista degli autori:");
				id_autore = new Integer(sublist("Autore"));
				System.out.println("Inserire l'id dell'editore del prossimo libro:");
				id_editore = new Integer(sublist("Editore"));
				System.out.println("Inserire il titolo per il prossimo libro:");
				titolo = s.nextLine();
				System.out.println("Inserire la descrizione per il prossimo libro:");
				desc = s.nextLine();
				System.out.println("Inserire l'anno di pubblicazione del prossimo libro(formato YYYY):");
				anno = Short.parseShort(s.nextLine());
				
				list.add(new Libro(null, id_autore, id_editore, titolo,	desc, anno));
			} catch(NumberFormatException e) {
				System.out.println("Qualcosa è andato storto nell'inserimento dei parametri. Riprova.");
				continue;
			}
		
			System.out.println("Digita 'save': inserire lista corrente nel database.");
			System.out.println("Digita 'more': aggiungere un altro libro alla lista.");
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
				JPAToolset.insertJPA(list);
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
		String nome, cognome, biografia, nascita, morte;
		boolean insertFlag = true;
		
		while(insertFlag) {
			System.out.println("Inserire il nome dell'autore:");
			nome = s.nextLine();
			System.out.println("Inserire il cognome dell'autore:");
			cognome = s.nextLine();
			System.out.println("Inserire la data di nascita dell'autore(DD-MM-YYYY):");
			nascita = s.nextLine();
			System.out.println("Inserire la data di morte dell'autore(DD-MM-YYYY):");
			morte = s.nextLine();
			System.out.println("Inserire una breve biografia dell'autore:");
			biografia = s.nextLine();
				
			if(nome.equals("") && cognome.equals("")) {
				System.out.println("Inserire almeno il nome o il cognome!");
				continue;
			} else {
				list.add(new Autore(null, nome, cognome, biografia, (nascita.equals("")?null:LocalDate.parse(nascita, formatter)),
						(morte.equals("")?null:LocalDate.parse(morte, formatter))));
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
				JPAToolset.insertJPA(list);
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
				JPAToolset.insertJPA(list);
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
				JPAToolset.insertJPA(list);
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
				id_utente = new Integer(sublist("Utente"));
				System.out.println("Inserire l'id del libro per cui si sta richiedendo il prestito:");
				id_libro = new Integer(sublist("Libro"));
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
				JPAToolset.insertJPA(list);
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