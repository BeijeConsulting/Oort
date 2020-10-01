package it.beije.oort.kirolosmater.biblioteca;



import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;



public class JPAEntityManagerSingleton {

	private JPAEntityManagerSingleton() {}
	
	private static EntityManager entityManager;
	
	private static Map<String, EntityManager> entityManagerDB =  new HashMap<String, EntityManager>();
	
	public static Map getJpaEntityManager (String persistence) {			
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(persistence);
		EntityManager entityManager = factory.createEntityManager();
		entityManagerDB.put(persistence, entityManager);
		return entityManagerDB;
	}
	
	public static EntityManager getEntityManager (String persistence) {
		if (entityManagerDB.get(persistence) == null) {			
			entityManagerDB = getJpaEntityManager (persistence);
			
		}
		entityManager = entityManagerDB.get(persistence);
//		System.out.println("entityManager is null? " + entityManager == null);
		return entityManager;
	}
	
}
