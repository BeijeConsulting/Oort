package it.beije.oort.file.sala.biblioteca;

import java.time.LocalDate;
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
	
	public static void deleteJPA(String table, Integer id) {
		String jpql = "DELETE FROM "+table+" WHERE id_"+table.toLowerCase()+" = :id";
		EntityManager entityManager = JPAFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id).executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
		System.out.println("Delete done!");
	}
	
	public static void updateJPA(String table, String fieldToChange, Object newValue, Integer id) {
		String jpql ="UPDATE "+table+" SET "+fieldToChange+" = :value WHERE id_"+table.toLowerCase()+" = :id";
		EntityManager entityManager = JPAFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery(jpql);
		query.setParameter("value", newValue).setParameter("id", id).executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
		System.out.println("Update done!");
	}
	
//	Volendo rendere meno fragile il codice si possono differenziare le chiamate a update con degli 'ifs'
//	su 'table+fieldtochange' oppure su 'instanceof value' utilizzando un sistema di overload come qui sotto.
//
//	private static void updateLibroJPA(String jbQuery, Integer ids, Integer id) {}
//	private static void updateLibroJPA(String jbQuery, String infos, Integer id) {}
//	
//	private static void updateAutoreJPA(String jbQuery, String infos, Integer id) {}
//	private static void updateAutoreJPA(String jbQuery, LocalDate data, Integer id) {}
//	
//	private static void updateEditoreJPA(String jbQuery, String infos, Integer id) {}
//	
//	private static void updateUtenteJPA(String jbQuery, String infos, Integer id) {}
//	
//	private static void updatePrestitoJPA(String jbQuery, Integer ids, Integer id) {}
//	private static void updatePrestitoJPA(String jbQuery, LocalDate data, Integer id) {}
//	private static void updatePrestitoJPA(String jbQuery, String note, Integer id) {}
}