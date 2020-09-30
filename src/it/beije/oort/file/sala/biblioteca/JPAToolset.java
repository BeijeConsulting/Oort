package it.beije.oort.file.sala.biblioteca;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class JPAToolset {

	private JPAToolset() {}
	
	public static void insertJPA(List<Databasable> insertList) {
		EntityManager entityManager = JPAFactory.createEntityManager();
		entityManager.getTransaction().begin();
	
		for(Databasable c : insertList) {
			entityManager.persist(c);
		}
		
		entityManager.getTransaction().commit();
		entityManager.close();
		System.out.println("Insert done!");
	}
	
	public static List<Databasable> selectJPA(String table, String field, String value) {
		String jpql = "SELECT c FROM "+table+" as c WHERE "+field+" like :value";
		EntityManager entityManager = JPAFactory.createEntityManager();
		Query query = entityManager.createQuery(jpql).setParameter("value", "%" +value + "%");
		List<Databasable> temp = query.getResultList();
		entityManager.close();
		return temp;
	}
	
	public static List<Databasable> selectJPA(String table) {
		String jpql = "SELECT c FROM "+table+" as c";
		EntityManager entityManager = JPAFactory.createEntityManager();
		Query query = entityManager.createQuery(jpql);
		List<Databasable> temp = query.getResultList();
		entityManager.close();
		return temp;
	}
}
