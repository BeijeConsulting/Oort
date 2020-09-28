package it.beije.oort.file.sala;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HibernateToolset {

	private static Configuration configuration;
	private static SessionFactory factory;
	
	public HibernateToolset() {
		configuration = new Configuration().configure();
		factory = configuration.buildSessionFactory();
		
	}
	
	public List<Contatto> selectHibernate(String field, String value) {
		String hql = "SELECT c FROM Contatto WHERE ? = ?";
		Session session = factory.openSession();
		Query<Contatto> query = session.createQuery(hql).setParameter(0, field).setParameter(1, value);
		return query.list();
	}
	
	public void insertHibernate(List<Contatto> contatti) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		for(Contatto c : contatti) {
			session.save(c);
		}
		transaction.commit();
		session.close();
	}

	public void updateHibernate() {
		
	}
}
