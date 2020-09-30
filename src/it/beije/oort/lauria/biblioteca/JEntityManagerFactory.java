package it.beije.oort.lauria.biblioteca;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JEntityManagerFactory {
	
	private JEntityManagerFactory() {}
	
	private static Map<String, EntityManager> persistenceMap = new HashMap<String, EntityManager>();;	
	
	public static EntityManager openEntityManager(String persistence) {		
		
		if (persistenceMap.get(persistence) == null) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory(persistence);
			EntityManager entityManager = factory.createEntityManager();
			persistenceMap.put(persistence, entityManager);
		}
		
		return persistenceMap.get(persistence);
	}
	
	
	/*
	 * chiedo all'utente quale persistence vuole. La cerco nella mappa: 
	 * se la mappa è vuota o comunque non la contiene, la aggiungo, 
	 * altrimenti vado a prendere la corrispondente entity manager
	 * 
	 * Map<String, EntityManager> persistenceMap = new HashMap<String, EntityManager>();
	 * 
	 * EntityManagerFactory factory = Persistence.createEntityManagerFactory(persistenceUtente);
	 * EntityManager entityManager = factory.createEntityManager();
	 * 
	 * if(persistenceMap.get(persistenceUtente) == null) // quindi non ho una persistence 
	 * 		EntityManagerFactory factory = Persistence.createEntityManagerFactory(persistenceUtente);
	 * 		EntityManager entityManager = factory.createEntityManager();
	 * 		persistenceMap.put(persistenceUtente, entityManager)
	 * 
	 * 
	 */
	
}
