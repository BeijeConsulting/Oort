package it.beije.oort.girardi.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import it.beije.oort.rubrica.Contatto;
import it.beije.oort.rubrica.HybSessionFactory;

public class MyHDBtools {

	public static void main(String[] args) {
		System.out.println("INIZIO");
	
		//apro sessione
		Session session = MyHibSessionFactory.openSession();
		System.out.println("session is open? " + session.isOpen());
		
		//esempio lettura tramite id
//		Contatto c = session.find(Contatto.class, 6);
//		System.out.println(c);

		//esempio query HQL
//		String hql = "SELECT c FROM Contatto as c ";
//		Query<Contatto> query = session.createQuery(hql);
//		System.out.println("# contatti presenti: " + query.list().size());
//		System.out.println("ID, COGNOME, NOME, TELEFONO, EMAIL");
//		for (Contatto c : query.list()) {
//			System.out.println(c.getId()+",  "+c.getCognome()+",  "+c.getNome() 
//									+",  "+c.getTelefono()+",  "+c.getEmail());
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
		Transaction transaction = session.beginTransaction();
//		
//		//esempio INSERT
		Contatto contatto = new Contatto();
		contatto.setId(3);
		contatto.setNome("Fiorenzo");
		contatto.setCognome("Belvedere");
		contatto.setEmail("fiore@belvi.it");
		contatto.setTelefono("345777666");
		System.out.println("id : " + contatto.getId());
		session.save(contatto);
		System.out.println("id : " + contatto.getId());

		//esempio UPDATE
//		Contatto contatto = session.get(Contatto.class, 6);
//		System.out.println(contatto);
//		contatto.setNome("Johnny");
//		contatto.setCognome("Bravo");
//		contatto.setTelefono("333");
//		contatto.setEmail("johnny@be.good");
//		System.out.println(contatto);
//		session.save(contatto);
		
		//esempio DELETE
//		Contatto contatto = session.get(Contatto.class, 9);
//		System.out.println(contatto);
//		session.delete(contatto);

//		//confermo aggiornamento su DB
		transaction.commit();
		
		//annullo aggiornamento su DB
		//transaction.rollback();
		
//		chiudo la sessione
		session.close();
		System.out.println("session is open? " + session.isOpen());

		
		//test HybSessionFactory
//		session = MyHibSessionFactory.openSession();
//		System.out.println("new session is open? " + session.isOpen());
//		session.close();
		
		System.out.println("FINE");
	}

}
