package it.beije.oort.kirolosmater.biblioteca;



import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;



public class JPAEntityManagerSingleton {

	private JPAEntityManagerSingleton() {}
	
	private static Map<String, EntityManager> entityManagerDB =  new HashMap<String, EntityManager>();
	
	public static Map getJpaEntityManager (String persistence) {
		if (entityManagerDB == null) {			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory(persistence);
			EntityManager entityManager = factory.createEntityManager();
			entityManagerDB.put(persistence, entityManager);
		}
		return entityManagerDB;
	}
	
}
