package it.beije.oort.lauria.hb;

import it.beije.oort.lauria.Contatto;

import java.util.List;

//import org.hibernate.Criteria;
import org.hibernate.Session;
//import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;


public class HDBtools {
	
	public static void main(String[] args) {
		System.out.println("INIZIO");
		//apro sessione
		Session session = HybSessionFactory.openSession();
		//chiudo la sessione
		session.close();		
		
		
		
		
		//apro sessione
//		Session session = HybSessionFactory.openSession();
//		System.out.println("session is open? " + session.isOpen());

		//esempio lettura tramite id
		// session.find cerca SOLO E SOLTANTO tramite id perchè è la sola primary key della table
//		Contatto c = session.find(Contatto.class, 1);
//		System.out.println(c);
		
		//esempio query HQL
		//HDBtools.selectContacts();
//		String hql = "SELECT c FROM Contatto as c WHERE nome = 'fusco'";
//		Query<Contatto> query = session.createQuery(hql);
//		System.out.println(query.list().size());
//		for (Contatto contatto : query.list()) {
//			System.out.println("id : " + contatto.getId());
//			System.out.println("nome : " + contatto.getNome());
//			System.out.println("cognome : " + contatto.getCognome());
//			System.out.println("telefono : " + contatto.getTelefono());
//			System.out.println("email : " + contatto.getEmail());
//		}

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
		
		//esempio INSERT
		//HDBtools.insert();		
//		System.out.println("INSERT\n");
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
		//HDBtools.update();
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
		
		//chiudo la sessione
//		session.close();
//		System.out.println("session is open? " + session.isOpen());
		
		System.out.println("FINE");
	}
	
	public static void selectContacts(List<Contatto> recordContatti) {

		String hql = "SELECT c FROM Contatto as c WHERE id > 0";
		
		//apro sessione
		Session session = HybSessionFactory.openSession();
				
		Query<Contatto> query = session.createQuery(hql);
		//System.out.println("Contatti trovati : " + query.list().size());		
		for (Contatto contatto : query.list()) {
			Contatto c = new Contatto();						
			c.setId(contatto.getId());
			c.setNome(contatto.getNome());
			c.setCognome(contatto.getCognome());
			c.setTelefono(contatto.getTelefono());
			c.setEmail(contatto.getEmail());
			
			recordContatti.add(c);			
		}
		
		//chiudo la sessione
		session.close();
	}
	
	public static void selectContacts(int id) {
		//apro sessione
		Session session = HybSessionFactory.openSession();
		Contatto c = session.find(Contatto.class, id);
		System.out.println(c);
		//chiudo la sessione
		session.close();
		}
	
	
	public static void selectContacts(String cognome, String nome, String telefono, String email) {

		String hql = "SELECT c FROM Contatto as c WHERE cognome like '%"+ cognome +"%' and nome like '%"+ nome +"%' and telefono like '%"+ telefono +"%' and email like '%"+ email +"%'";
		//String hql = "SELECT c FROM Contatto as c WHERE email like '%@hotmail.it%'";
		
		//apro sessione
		Session session = HybSessionFactory.openSession();
				
		Query<Contatto> query = session.createQuery(hql);
		System.out.println("Contatti trovati : " + query.list().size());		
		for (Contatto contatto : query.list()) {
			System.out.print("id : " + contatto.getId());
			System.out.print(" - nome : " + contatto.getNome());
			System.out.print(" - cognome : " + contatto.getCognome());
			System.out.print(" - telefono : " + contatto.getTelefono());
			System.out.println(" - email : " + contatto.getEmail());
		}
		
		//chiudo la sessione
		session.close();
	}

	public static void insert() {
		
		Contatto contatto = new Contatto();
		//contatto.setId(3);
		contatto.setNome("Fiorenza");
		contatto.setCognome("Volpe");
		contatto.setEmail("fiore@volpe.it");
		contatto.setTelefono("34556616");
		//System.out.println("id : " + contatto.getId());
		
		//apro sessione
		Session session = HybSessionFactory.openSession();
		//apro transazione
		Transaction transaction = session.beginTransaction();		
		session.save(contatto);
		//System.out.println("id : " + contatto.getId());
		System.out.println("Contatto inserito : "+ contatto.getId() + " - " + contatto);
		//confermo aggiornamento su DB
		transaction.commit();
		//chiudo la sessione
		session.close();
	}
	
	public static void insertContact(String cognome, String nome, String telefono, String email) {
		
		Contatto contatto = new Contatto();
		contatto.setNome(nome);
		contatto.setCognome(cognome);
		contatto.setTelefono(telefono);
		contatto.setEmail(email);
		//System.out.println("id : " + contatto.getId());
		
		//apro sessione
		Session session = HybSessionFactory.openSession();
		//apro transazione
		Transaction transaction = session.beginTransaction();		
		session.save(contatto);
		//System.out.println("id : " + contatto.getId());
		System.out.println("Contatto inserito : "+ contatto.getId() + " - " + contatto);
		//confermo aggiornamento su DB
		transaction.commit();
		//chiudo la sessione
		session.close();
	}
	
	public static void update() {
		
		//apro sessione
		Session session = HybSessionFactory.openSession();
		
		Contatto contatto = session.get(Contatto.class, 1005);
		System.out.println("Contatto corrente da modificare : "+ contatto);
		contatto.setNome("clara");
		contatto.setTelefono("432432421243");
		System.out.println("Contatto modificato : "+ contatto);
		
		//apro transazione
		Transaction transaction = session.beginTransaction();
		session.save(contatto);
		
		//confermo aggiornamento su DB
		transaction.commit();
		//chiudo la sessione
		session.close();
	}
	
	public static void update(int id, String cognome, String nome, String telefono, String email) {
		
		//apro sessione
		Session session = HybSessionFactory.openSession();
		
		Contatto contatto = session.get(Contatto.class, id);
		System.out.println("Contatto corrente da modificare : "+ + contatto.getId() + " - " + contatto);
		contatto.setNome(nome);
		contatto.setCognome(cognome);
		contatto.setTelefono(telefono);
		contatto.setEmail(email);
		System.out.println("Contatto modificato : "+ + contatto.getId() + " - " + contatto);
		
		//apro transazione
		Transaction transaction = session.beginTransaction();
		session.save(contatto);
		
		//confermo aggiornamento su DB
		transaction.commit();
		//chiudo la sessione
		session.close();
	}
	
	public static void deleteContact(int id){
		//apro sessione
		Session session = HybSessionFactory.openSession();
		
		Contatto contatto = session.get(Contatto.class, id);		
		
		//apro transazione
		Transaction transaction = session.beginTransaction();
		session.delete(contatto);
		
		//confermo aggiornamento su DB
		transaction.commit();
		//chiudo la sessione
		session.close();
		
	}
}
