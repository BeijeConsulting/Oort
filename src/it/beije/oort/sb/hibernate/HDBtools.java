package it.beije.oort.sb.hibernate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import it.beije.oort.rubrica.Contatto;
import it.beije.oort.rubrica.RubricaCsvXml;
import it.beije.oort.sb.db.ClientRubricaDB;
import it.beije.oort.sb.db.DBSupport;


public class HDBtools {
	static Scanner sc = new Scanner(System.in);
	//metodo che cancella i dati tramite l'indice
	public static void delete() {
		System.out.println("Scegli quale contatto cancellare tramite l'indice\n");
		visualizzaAll();
		System.out.println("Inserisci l'indice del contatto da cancellare");
		int ind = sc.nextInt();
		Session session = HybSessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(session.get(Contatto.class, ind));
		System.out.println("Contatto eliminato!\n");
		transaction.commit();
		session.close();
	}
	//metodo costruito solo per avere un messaggio iniziale nel Menu e far partire la config
	public static int numElementi() {
		Session session = HybSessionFactory.openSession();
		String hql = "SELECT c FROM Contatto as c where id > 0";
		Query<Contatto> query = session.createQuery(hql);
		int a = query.list().size();
		session.close();
		return a;	
	}
	
	//metodo per importare una rubrica da locale, si avvale di metodi di lettura file scritti precedentemente
	//si possono passare file sia csv che xml
	public static void importer() throws IOException, ParserConfigurationException, SAXException {
		File file = new File(DBSupport.importer().toString());
		if(!file.exists()) {
			System.out.println("Non esiste il file selezionato");
			return;
		}
		Session session = HybSessionFactory.openSession();
		List<Contatto> contatti = RubricaCsvXml.LetturaFile(file);
		for(Contatto c : contatti) {
			Transaction transaction = session.beginTransaction();
			session.save(c);
			transaction.commit();
		} 
		System.out.println("Rubrica importata!\n");
	}
	//metodo per inserire un nuovo contatto in fondo al db
	public static void insert() {
		Session session = HybSessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
//		Contatto contatto = ClientRubricaDB.cWriter();
		session.save(ClientRubricaDB.cWriter());
		transaction.commit();
		session.close();
	}
	//metodo che viualizza tutti i contatti presenti nel db
	//l'utente può scegliere il numero di record per pagina che vuole visualizzare
	public static void visualizzaAll() {
		int numPag=1;
		Session session = HybSessionFactory.openSession();
		String hql = "SELECT c FROM Contatto as c where id > 0";
		Query<Contatto> query = session.createQuery(hql);
		List<Contatto> list  = query.list();
		System.out.println("Quanti record vuoi per pagina?");
		int rec = sc.nextInt();
		CICLO: for(int i = 0; i< list.size();i+=rec) {
					for(int count = i; count< i+rec; count++) {
			            System.out.format("%-10s%-30s%-30s%-30s%-55s\n",
			            		"Id : "+ list.get(count).getId(),
			            "Nome: " + list.get(count).getNome(),
			            "Cognome: " + list.get(count).getCognome(),
			            "Telefono : " + list.get(count).getTelefono(),
			            "Email : " + list.get(count).getEmail());
						if(count==list.size()-1) {
							System.out.println("numero della pagina: " + numPag);
							System.out.println("\nterminato il numero dei contatti");
							return;
						}		
					}
					System.out.println("numero della pagina: " + numPag);
					numPag++;
					System.out.println("\nse vuoi vedere la prossima pagina contatti digita next. In caso contrario digita stop");
					String temp = "";
					while(temp.toLowerCase()!="next" || temp.toLowerCase()!= "stop") {
						temp = sc.next();
						if(temp.equalsIgnoreCase("next")) continue CICLO;
						else if(temp.equalsIgnoreCase("stop")) break CICLO;
						else System.out.println("Non ho compreso il comando inserito, digita stop o next");
						}
		}
		session.close();
	}
	
	//metodo di appoggio che serve per avere un campo e un valore di quel campo da ricercare
	public static String[] campi(){
		String[]campi = new String[2];
		System.out.println("Per quale campo vuoi ricercare tra Id, Nome, Cognome, Telefono e Email?");
		String campo = sc.next().toLowerCase();
		List<String> campi2 = new ArrayList<String>();
		campi2.add("id");
		campi2.add("nome");
		campi2.add("cognome");
		campi2.add("email");
		campi2.add("telefono");
		while(!campi2.contains(campo)) {
			System.out.println("Inserisci un campo valido tra Id, Nome, Cognome, Email, e Telefono");
			campo = sc.next().toLowerCase();
		}
		System.out.println("Inserisci il valore di " + campo + " per cui vuoi effettuare la ricerca");
		String att = sc.next();	
		campi[0]= campo;
		campi[1]= att;
		return campi;
	}
	
	//metodo che ricerca tutti i contatti presenti nel db tramite il campo e il valore dati dal metodo campi()
	public static void ricerca() {
		String[] campi = campi();
		int numPag=1;
		Session session = HybSessionFactory.openSession();
		String hql;
		if(campi[0].equalsIgnoreCase("id")) { 
			hql = "SELECT c FROM Contatto as c where " +campi[0]+" = "+campi[1];
			}
		else {
				System.out.println("Puoi ricercare i valori che \n1)Iniziano per "+campi[1]+
						"\n2)Finiscono per "+campi[1]+"\n3)Contengono "+campi[1]);
				System.out.println("Digita il numero corrispondente alla tua preferenza e premi INVIO");
				int c=0;
				c = sc.nextInt();
				if(c==1)	hql = "SELECT c FROM Contatto as c where " +campi[0]+" like '"+campi[1]+"%'";
				else if(c==2)	hql = "SELECT c FROM Contatto as c where " +campi[0]+" like '%"+campi[1]+"'";
				else if(c==3) hql = "SELECT c FROM Contatto as c where " +campi[0]+" like '%"+campi[1]+"%'";
				else {
						System.out.println("non ho trovato l'opzione che cerchi");
						return;
				}
			}
		Query<Contatto> query = session.createQuery(hql);
		List<Contatto> list = query.list();
		if (list.size()==0) System.out.println("Non ho trovato nessun contatto con queste caratteristiche");
		else {
		System.out.println("Ho trovato "+list.size()+" record");
		System.out.println("Quanti record vuoi visualizzare per pagina?");
		int rec = sc.nextInt();
		CICLO: for(int i = 0; i< list.size();i+=rec) {					 
					for(int count = i; count< i+rec; count++) {
						System.out.format("%-10s%-30s%-30s%-30s%-55s\n",
			            		"Id : "+ list.get(count).getId(),
			            "Nome: " + list.get(count).getNome(),
			            "Cognome: " + list.get(count).getCognome(),
			            "Telefono : " + list.get(count).getTelefono(),
			            "Email : " + list.get(count).getEmail());
//						System.out.print("Id : "+ list.get(count).getId());
//						System.out.print("    Nome: " + list.get(count).getNome());
//						System.out.print("    Cognome: " + list.get(count).getCognome());
//						System.out.print("    Telefono : " + list.get(count).getTelefono());
//						System.out.println("    Email : " + list.get(count).getEmail());
						if(count==list.size()-1) {
							System.out.println("numero della pagina: " + numPag);
							System.out.println("\nterminato il numero dei contatti");
							return;
						}		
					}
					System.out.println("numero della pagina: " + numPag);
					numPag++;
					System.out.println("\nse vuoi vedere la prossima pagina contatti digita next. In caso contrario digita stop");
					String temp = "";
					while(temp.toLowerCase()!="next" || temp.toLowerCase()!= "stop") {
						temp = sc.next();
						if(temp.equalsIgnoreCase("next")) continue CICLO;
						else if(temp.equalsIgnoreCase("stop")) break CICLO;
						else System.out.println("Non ho compreso il comando inserito, digita stop o next");
						}
		}
		}
		session.close();
	}
	//metodo che prima fa vedere i vari record e poi chiede quale record modificare
	public static void update() {
		System.out.println("Scegli quale contatto modificare tramite l'indice\n");
		visualizzaAll();
		System.out.println("Inserisci l'indice del contatto da modificare");
		int ind = sc.nextInt();
		Session session = HybSessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Contatto contatto = session.get(Contatto.class, ind);
		System.out.println("Quale campo vuoi modificare tra Nome, Cognome, Telefono e Email?");
		String campo = sc.next().toLowerCase();
		List<String> campi2 = new ArrayList<String>();
		campi2.add("nome");
		campi2.add("cognome");
		campi2.add("email");
		campi2.add("telefono");
		while(!campi2.contains(campo)) {
			System.out.println("Inserisci un campo valido tra Nome Cognome Email e Telefono");
			campo = sc.next().toLowerCase();
		}
		System.out.println("Inserisci il nuovo valore di " + campo);
		String att = sc.next();	
		
		switch(campo.toLowerCase()) {
		case "nome":
			contatto.setNome(att);
			break;
		case "cognome":
			contatto.setCognome(att);
			break;
		case "email":
			contatto.setTelefono(att);
			break;
		case "telefono":
			contatto.setEmail(att);
			break;
		}
		session.save(contatto);
		transaction.commit();
		System.out.println("Digita Ancora se vuoi modificare un altro campo di questo contatto");
		if(sc.next().equalsIgnoreCase("ancora")) update();
		else {session.close(); return; }
	}
	//metodo per salvare su file csv o xml la rubrica contenuta nel database
	public static void export() throws DOMException, IOException, ParserConfigurationException, TransformerException {
		File file = new File(DBSupport.export().toString());
		Session session = HybSessionFactory.openSession();
		String hql = "SELECT c FROM Contatto as c where id > 0";
		Query<Contatto> query = session.createQuery(hql);
		RubricaCsvXml.rubricaWriter(file, query.list());
		session.close();
	}
	
	
	/* SAREBBE IL MAIN DEL MERGE
	public static void main(String[] args) {
		System.out.println("INIZIO");

//		//inizializzo configurazione
//		Configuration configuration = new Configuration();
//		configuration = configuration.configure()
//				.addAnnotatedClass(Contatto.class);
//				//.addAnnotatedClass(AltraClasse.class);
//		
//		//chiedo generatore di sessioni
//		SessionFactory factory = configuration.buildSessionFactory();
//		
//		System.out.println("is open? " + factory.isOpen());
		
		//apro sessione
		Session session = HybSessionFactory.openSession();
		System.out.println("session is open? " + session.isOpen());

		//esempio lettura tramite id
//		Contatto c = session.find(Contatto.class, 1);
//		System.out.println(c);
		
		//esempio query HQL
		String hql = "SELECT c FROM Contatto as c WHERE cognome = 'rossi'";
		Query<Contatto> query = session.createQuery(hql);
		System.out.println(query.list().size());
		for (Contatto contatto : query.list()) {
			System.out.println("id : " + contatto.getId());
			System.out.println("nome : " + contatto.getNome());
			System.out.println("cognome : " + contatto.getCognome());
			System.out.println("telefono : " + contatto.getTelefono());
			System.out.println("email : " + contatto.getEmail());
		}

		//esempio Criteria
//		Criteria criteria = session.createCriteria(Contatto.class);
//		criteria.add(Restrictions.eq("cognome", "rossi"));
//		List<Contatto> contatti = criteria.list();
//		for (Contatto contatto : contatti) {
//			System.out.println("id : " + contatto.getId());
//			System.out.println("nome : " + contatto.getNome());
//			System.out.println("cognome : " + contatto.getCognome());
//			System.out.println("telefono : " + contatto.getTelefono());
//			System.out.println("email : " + contatto.getEmail());
//		}
		
		//apro transazione
//		Transaction transaction = session.beginTransaction();
//		
//		//esempio INSERT
//		Contatto contatto = new Contatto();
//		contatto.setId(3);
//		contatto.setNome("Fiorenza");
//		contatto.setCognome("Volpe");
//		contatto.setEmail("fiore@volpe.it");
//		contatto.setTelefono("34556616");
//		System.out.println("id : " + contatto.getId());
//		session.save(contatto);
//		System.out.println("id : " + contatto.getId());

		//esempio UPDATE
//		Contatto contatto = session.get(Contatto.class, 5);
//		System.out.println(contatto);
//		contatto.setNome("clara");
//		contatto.setTelefono("432432421243");
//		System.out.println(contatto);
//		session.save(contatto);
		
//		//confermo aggiornamento su DB
//		transaction.commit();
		
		//annullo aggiornamento su DB
		//transaction.rollback();
		
//		//chiudo la sessione
		session.close();
		System.out.println("session is open? " + session.isOpen());

		
		//test HybSessionFactory
		session = HybSessionFactory.openSession();
		System.out.println("new session is open? " + session.isOpen());
		session.close();
		
		System.out.println("FINE");
	} */

}
