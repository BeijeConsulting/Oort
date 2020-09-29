package it.beije.oort.file.sala.biblioteca;

import java.util.Scanner;
import java.util.List;
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
		Integer id_libro, id_autore, id_editore;
		boolean insertFlag = true;
		
		while(insertFlag) {
			try {
				System.out.println("Inserire l'id per il prossimo libro:");
				id_libro = new Integer(s.nextLine());
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
				
				list.add(new Libro(id_libro, id_autore, id_editore, bufferTitolo,
						bufferDescrizione, Year.parse(bufferAnno)));
			} catch(NumberFormatException e) {
				System.out.println("Qualcosa è andato storto nell'iserimento dei parametri. Riprova.");
				continue;
			}
		
			System.out.println("Digita 'save': inserire lista corrente nel database.");
			System.out.println("Digita 'more': aggiungere un altro libro alla lista.");
			String buffer = s.nextLine();
			while(!(buffer.equalsIgnoreCase("save")||buffer.equalsIgnoreCase("more"))) {
				System.out.println("Input non riconosciuto, riprovare...");
				buffer=s.nextLine();
			}
			if(buffer.equalsIgnoreCase("save")) {
				insertFlag = false;
				HibernateToolsetBiblio.insertHibernate(list);
				list.clear();
				continue;
			}
			else if(buffer.equalsIgnoreCase("more")) {
				continue;
			}
		}
	}
	
	//sub-routine per inserire autori
	private void insertAutori() {
		String bufferTitolo, bufferDescrizione, bufferAnno;
		Integer id_libro, id_autore, id_editore;
		boolean insertFlag = true;
		
		while(insertFlag) {
			try {
				System.out.println("Inserire l'id per il prossimo libro:");
				id_libro = new Integer(s.nextLine());
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
				
				list.add(new Libro(id_libro, id_autore, id_editore, bufferTitolo,
						bufferDescrizione, Year.parse(bufferAnno)));
			} catch(NumberFormatException e) {
				System.out.println("Qualcosa è andato storto nell'iserimento dei parametri. Riprova.");
				continue;
			}
		
			System.out.println("Digita 'save': inserire lista corrente nel database.");
			System.out.println("Digita 'more': aggiungere un altro libro alla lista.");
			String buffer = s.nextLine();
			while(!(buffer.equalsIgnoreCase("save")||buffer.equalsIgnoreCase("more"))) {
				System.out.println("Input non riconosciuto, riprovare...");
				buffer=s.nextLine();
			}
			if(buffer.equalsIgnoreCase("save")) {
				insertFlag = false;
				HibernateToolsetBiblio.insertHibernate(list);
				list.clear();
				continue;
			}
			else if(buffer.equalsIgnoreCase("more")) {
				continue;
			}
		}
	}
	
	private void insertEditori() {
		//sub-routine per inserire editori
	}
	
	private void insertUtenti() {
		//sub-routine per inserire utenti
	}
		
	private void insertPrestiti() {
		//sub-routine per inserire prestiti
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
}