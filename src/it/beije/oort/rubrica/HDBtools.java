package it.beije.oort.rubrica;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;


public class HDBtools {
	
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
	}

}
