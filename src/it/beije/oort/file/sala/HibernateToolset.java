package it.beije.oort.file.sala;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class HibernateToolset {

	private HibernateToolset() {}
	
	public static List<Contatto> selectHibernate(String field, String value) {
		String hql = "SELECT c FROM Contatto as c WHERE "+field+" like :value";
		Session session = HibernateSessionFactory.openSession();
		Query<Contatto> query = session.createQuery(hql).setParameter("value", "%" +value + "%");
		List<Contatto> temp = query.list();
		session.close();
		return temp;
	}
	
	public static List<Contatto> selectHibernate(boolean star) {
		String hql = "SELECT c FROM Contatto as c";
		Session session = HibernateSessionFactory.openSession();
		Query<Contatto> query = session.createQuery(hql);
		List<Contatto> temp = query.list();
		session.close();
		return temp;
	}
	
	public static void insertHibernate(List<Contatto> contatti) {
		Session session = HibernateSessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		for(Contatto c : contatti) {
			session.save(c);
		}
		transaction.commit();
		session.close();
		System.out.println("Insert done!");
	}

	public static void updateHibernate(int id, String field, String value) {
		Session session = HibernateSessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql ="UPDATE Contatto SET "+field+" = :value WHERE id = :id";
		session.createQuery(hql).setParameter("value", value).setParameter("id", id).executeUpdate();
		transaction.commit();
		session.close();
		System.out.println("Update done!");
	}
	
	public static void deleteHibernate(int id) {
		Session session = HibernateSessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "DELETE FROM Contatto WHERE id = :id";
		session.createQuery(hql).setParameter("id", id).executeUpdate();
		transaction.commit();
		session.close();
		System.out.println("Delete done!");
	}
}
