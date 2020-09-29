package it.beije.oort.file.sala.biblioteca;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class HibernateToolsetBiblio {

private HibernateToolsetBiblio() {}
	
	public static List<Databasable> selectHibernate(String table, String field, String value) {
		String hql = "SELECT c FROM "+table+" as c WHERE "+field+" like :value";
		Session session = HibernateSessionFactoryBiblio.openSession();
		Query<Databasable> query = session.createQuery(hql).setParameter("value", "%" +value + "%");
		List<Databasable> temp = query.list();
		session.close();
		return temp;
	}
	
	public static List<Databasable> selectHibernate(String table) {
		String hql = "SELECT c FROM "+table+" as c";
		Session session = HibernateSessionFactoryBiblio.openSession();
		Query<Databasable> query = session.createQuery(hql);
		List<Databasable> temp = query.list();
		session.close();
		return temp;
	}
	
	public static void insertHibernate(List<Databasable> insertList) {
		Session session = HibernateSessionFactoryBiblio.openSession();
		Transaction transaction = session.beginTransaction();
		for(Databasable c : insertList) {
			session.save(c);
		}
		transaction.commit();
		session.close();
		System.out.println("Insert done!");
	}
}
