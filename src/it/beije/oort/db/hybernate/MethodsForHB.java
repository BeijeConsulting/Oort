package it.beije.oort.db.hybernate;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import it.beije.oort.rubrica.Contatto;
import it.beije.oort.rubrica.HybSessionFactory;

public class MethodsForHB {
	
	//Variabili utilizzate nei vari metodi per l'utilizzo del Client_Scanner_Hibernate
	static Scanner scan = new Scanner(System.in);
	static String value = "";
	static String col = "";
	static String id_values = "";
	static String parola = "";
	static String sc = "";
	static String nome = "";
	static String cognome = "";
	static String telefono = "";
	static String email = "";
	static boolean flag2 = true;
	static boolean flag3 = true;
	
	//Metodo per inserire un nuovo contatto nel DB
	public static Contatto insert() {
		Session session = HybSessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println("Session is open? " + session.isOpen());
		Contatto contatto = new Contatto();
		System.out.println("Inserire almeno uno dei seguenti campi: ");
		System.out.print("- Nome: ");
		contatto.setNome(scan.nextLine());
		System.out.print("- Cognome: ");
		contatto.setCognome(scan.nextLine());
		System.out.print("- Telefono: ");
		contatto.setTelefono(scan.nextLine());
		System.out.print("- Email: ");
		contatto.setEmail(scan.nextLine());
		session.save(contatto);
		transaction.commit();
		session.close();
		System.out.println("Session is open? " + session.isOpen());
		return contatto;
	}
	
	//Metodo per modificare il nome
	public static Contatto updateNome(int id_values) {
		Session session = HybSessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Contatto contatto = session.get(Contatto.class, id_values);
		System.out.println("Contatto da modificare: " + contatto);
		contatto.setNome(value);
		System.out.println("Contatto modificato: " + contatto);
		session.save(contatto);
		transaction.commit();
		session.close();
		System.out.println("Session is open? " + session.isOpen());
		return contatto;
	}
	
	//Metodo per modificare il cognome
	public static Contatto updateCognome(int id_values) {
		Session session = HybSessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Contatto contatto = session.get(Contatto.class, id_values);
		System.out.println("Contatto da modificare: " + contatto);
		contatto.setCognome(value);
		System.out.println("Contatto modificato: " + contatto);
		session.save(contatto);
		transaction.commit();
		session.close();
		System.out.println("Session is open? " + session.isOpen());
		return contatto;
	}
	
	//Metodo per modificare il telefono
	public static Contatto updateTelefono(int id_values) {
		Session session = HybSessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Contatto contatto = session.get(Contatto.class, id_values);
		System.out.println("Contatto da modificare: " + contatto);
		contatto.setTelefono(value);
		System.out.println("Contatto modificato: " + contatto);
		session.save(contatto);
		transaction.commit();
		session.close();
		System.out.println("Session is open? " + session.isOpen());
		return contatto;
	}
	
	//Metodo per modificare l'email
	public static Contatto updateEmail(int id_values) {
		Session session = HybSessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Contatto contatto = session.get(Contatto.class, id_values);
		System.out.println("Contatto da modificare: " + contatto);
		contatto.setEmail(value);
		System.out.println("Contatto modificato: " + contatto);
		session.save(contatto);
		transaction.commit();
		session.close();
		System.out.println("Session is open? " + session.isOpen());
		return contatto;
	}
		
	//Metodo per modificare un certo contatto inserendone l'ID 
	public static void edit() {
		System.out.println("Selezionare, indicando l'ID del contatto da modificare: ");
		System.out.print("ID: ");
		id_values = scan.nextLine();
		int s = Integer.parseInt(id_values);
		do {
		String choose = "";
		System.out.println("Scegliere quale campo modificare: ");
		System.out.println("1) Nome");
		System.out.println("2) Cognome");
		System.out.println("3) Telefono");
		System.out.println("4) Email");
		System.out.println("5) Termina modifiche e torna al menù precedente!");
		System.out.print("Inserire scelta: ");  choose = scan.nextLine();
		if(choose.equals("1")) {
			System.out.print("Inserire il nome da andare a sostituire (ID = "+ s + "): ");	
			value = scan.nextLine();			
			updateNome(s);
		}else {
			if(choose.equals("2")) {
				System.out.print("Inserire il cognome da andare a sostiture: "); value = scan.nextLine();
				updateCognome(s);
			}else {
				if(choose.equals("3")) {
					System.out.print("Inserire il numero di telefono da sostituire: "); value = scan.nextLine();
					updateTelefono(s);
				}else {
					if(choose.equals("4")) {
						System.out.print("Inserire la email da sostiture: "); value = scan.nextLine();
						updateEmail(s);
					}else {
						if(choose.equals("5")) {
							System.out.println();
							flag3 = false;
						}
					}
				}
			}
		}
		}while(flag3);
	}
	
	//Metodo per eliminare un contatto 
	public static Contatto delete() {
		String next;
		System.out.println("Selezionare, indicando l'ID del contatto da cancellare: ");
		System.out.print("ID: ");
		id_values = scan.nextLine();
		int s = Integer.parseInt(id_values);
		Session session = HybSessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Contatto contatto = session.get(Contatto.class,s);
		System.out.println("Contatto da cancellare: " + contatto);
		System.out.println("Confermare? (s/n)"); 
		next = scan.nextLine();
		if(next.contentEquals("s")) {
		session.delete(contatto);
		System.out.println("Concatto cancellato!");
		transaction.commit();
		session.close();
		return contatto;
		}else {
			System.out.println("Operazione annullata!");
			session.close();
			return contatto;
		}
	}
	
	//Metodo visualizzazione tramite ID
	public static Contatto selectID(int id_values) {
		Session session = HybSessionFactory.openSession();
		Contatto contatto = session.get(Contatto.class,id_values);
		String hql = "SELECT c FROM Contatto as c WHERE id = '" +  id_values + "'";
		Query<Contatto> query = session.createQuery(hql);		
		for (Contatto contatti : query.list()) {
			System.out.println("[ID : " + contatti.getId() + " - Nome : " + contatti.getNome() + " - Cognome : " + contatti.getCognome() 
			+ " - Telefono : " + contatti.getTelefono() + " - Email : " + contatti.getEmail());
			}
		session.close();
		System.out.println("Session is open? " + session.isOpen());
		return contatto;
	}
	
	//Metodo visualizzazione tramite nome
	public static void selectNome(String nome) {
		Session session = HybSessionFactory.openSession();
		String hql = "SELECT c FROM Contatto as c WHERE nome like '" + nome + "%'";
		Query<Contatto> query = session.createQuery(hql);		
		for (Contatto contatti : query.list()) {
			System.out.println("[ID : " + contatti.getId() + " - Nome : " + contatti.getNome() + " - Cognome : " + contatti.getCognome() 
			+ " - Telefono : " + contatti.getTelefono() + " - Email : " + contatti.getEmail());
			}
		session.close();
		System.out.println("Session is open? " + session.isOpen());
		
	}
	
	//Metodo visualizzazione tramite cognome
	public static void selectCognome(String cognome) {
		Session session = HybSessionFactory.openSession();
		String hql = "SELECT c FROM Contatto as c WHERE cognome like '" + cognome + "%'";
		Query<Contatto> query = session.createQuery(hql);		
		for (Contatto contatti : query.list()) {
			System.out.println("[ID : " + contatti.getId() + " - Nome : " + contatti.getNome() + " - Cognome : " + contatti.getCognome() 
			+ " - Telefono : " + contatti.getTelefono() + " - Email : " + contatti.getEmail());
			}
		session.close();
		System.out.println("Session is open? " + session.isOpen());
	}
	
	//Metodo per la visualizzazione dei contatti 
	public static void selectAll() {
		System.out.println("Scegliere una delle seguenti opzioni: ");
		System.out.println("1) Stampa tutto il DataBase!");
		System.out.println("2) Stampa un numero di contatti a scelta!");
		System.out.println("3) Stampa un numero di contatti divisi per pagine!");
		System.out.print("Inserire la scelta: ");  
		String options = scan.nextLine();
		if(options.contentEquals("1")) {
		Session session = HybSessionFactory.openSession();
		String hql = "SELECT c FROM Contatto as c";
		Query<Contatto> query = session.createQuery(hql);		
		for (Contatto contatti : query.list()) {
			System.out.println("[ID : " + contatti.getId() + " - Nome : " + contatti.getNome() + " - Cognome : " + contatti.getCognome() 
			+ " - Telefono : " + contatti.getTelefono() + " - Email : " + contatti.getEmail() + "]");
			}
		session.close();
		System.out.println("Session is open? " + session.isOpen());
	}else {
		if(options.contentEquals("2")) {
			List<Contatto> list1;
			Contatto contatto = new Contatto();
			String num;
			Session session = HybSessionFactory.openSession();
			String hql = "SELECT c FROM Contatto as c";
			Query<Contatto> query = session.createQuery(hql);
			System.out.println("Il DB contiente " + query.list().size() + " contatti. Quanti se ne vogliono stampare? ");
			System.out.print("Inserire il numero scelto: ");
			num = scan.nextLine();
			int cont = Integer.parseInt(num);
			list1 = query.list();
			for (int i = 0; i<cont;i++){
				System.out.println("[ID : " + list1.get(i).getId() + " - Nome : " + list1.get(i).getNome() + " - Cognome : " + list1.get(i).getCognome() 
				+ " - Telefono : " + list1.get(i).getTelefono() + " - Email : " + list1.get(i).getEmail() + "]");
			}
			session.close();
		}
			else {
				if(options.contentEquals("3")) {
					List<Contatto> list2;
					Contatto contatto = new Contatto();
					String pag;
					Session session = HybSessionFactory.openSession();
					String hql = "SELECT c FROM Contatto as c where id > 0";
					Query<Contatto> query = session.createQuery(hql);
					System.out.println("Il DB contiente " + query.list().size() + " contatti. Quanti se ne vogliono stampare in una pagina? ");
					System.out.print("Inserire il numero scelto: ");
					pag = scan.nextLine();
					int cont = Integer.parseInt(pag);
					list2 = query.list();
					int pagcont = 1;
					
					LOOP: for (int i = 0; i<list2.size();i+=cont)
					for (int j = i; j<i+cont;j++) {
						System.out.format("%-10s%-30s%-30s%-30s%-55s\n",
			            		"Id : "+ list2.get(j).getId(),
			            "Nome: " + list2.get(j).getNome(),
			            "Cognome: " + list2.get(j).getCognome(),
			            "Telefono : " + list2.get(j).getTelefono(),
			            "Email : " + list2.get(j).getEmail());
						if(j==list2.size()-1) {
							System.out.println("Pagina n° " + pagcont);
							System.out.println("\nNumero dei contatti terminato");
							return;
						}	
					
						System.out.println("Pagina n° " + pagcont);
						pagcont++;
						System.out.println("\nSe vuoi vedere la prossima pagina contatti digita next , in caso contrario digita stop");
						String temp = "";
						while(temp.toLowerCase()!="next" || temp.toLowerCase()!= "stop") {
							temp = scan.next();
							if(temp.equalsIgnoreCase("next")) continue LOOP;
							else if(temp.equalsIgnoreCase("stop")) break LOOP;
							else System.out.println("Non ho compreso il comando inserito, digita stop o next");
						}
					}
				}
			}
		}
	}
	
	//Metodo che richiama i metodi per la visualizzazione.
	public static void show() {
		do {
			System.out.println();
			System.out.println("Scegliere per quale campo si vuole effettuare la ricerca: ");
			System.out.println("Se effettuando la ricerca non viene stampato nessuno contatto, il contatto non esiste! (Es. Ricerca per ID : 105, se 105 non è presente nel DB l'operazione non stamperà nulla!");
			System.out.println("1) Ricerca per ID! ");
			System.out.println("2) Ricerca per Nome!(E' possibile inserire anche solo una lettera, verrano stampati tutti i nomi che iniziano con la lettera o le lettere inserite)");
			System.out.println("3) Ricerca per Cognome!(E' possibile inserire anche solo una lettera, verrano stampati tutti i cognomi che iniziano con la lettera o le lettere inserite)");
			System.out.println("4) Stampa tutti i contatti / Stampa un numero scelto di contatti!");
			System.out.print("Scegliere l'opzione di ricerca: ");
			sc = scan.nextLine();
			if(sc.equals("1")) {
				System.out.print("Inserire l'ID da cercare: ");
				parola = scan.nextLine();
				int id_valore = Integer.parseInt(parola);
				selectID(id_valore);
				flag2 = false;
			}else {
				if(sc.equals("2")) {
					System.out.print("Inserire il nome da cercare: ");
					parola = scan.nextLine();
					selectNome(parola);
					flag2 = false;
				}else {
					if(sc.equals("3")) {
						System.out.print("Inserire il cognome da cercare: ");
						parola = scan.nextLine();
						selectCognome(parola);
						flag2 = false;
					}else {
						if(sc.equals("4")) {
							selectAll();
							flag2 = false;
						}else
							flag2 = true;
					}
				}
			}
		}while(flag2);
	}
	
	//Metodo per l'EXPORT del DB in un file CSV con destinazione Path prestabilito
	public static void ExportDB(String pathFile) throws IOException, SQLException {
		FileWriter fileWriter = new FileWriter(pathFile);
		Session session = HybSessionFactory.openSession();
		try {
		
		String hql = "SELECT c FROM Contatto as c";
		Query<Contatto> query = session.createQuery(hql);	
		System.out.println("EXPORT START: ");
		fileWriter.append("ID;NOME;COGNOME;TELEFONO;EMAIL"+ "\n");
		for (Contatto contatto : query.list()) {
			System.out.println(contatto.toString());
		fileWriter.append(contatto.toFormattString()).append("\n");
		}
		System.out.println("EXPORT END.");
	} 
		finally {
		try {
			session.close();
			fileWriter.flush();
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	}
}