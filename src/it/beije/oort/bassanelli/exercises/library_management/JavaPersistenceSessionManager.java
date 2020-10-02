package it.beije.oort.bassanelli.exercises.library_management;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JavaPersistenceSessionManager {

	private JavaPersistenceSessionManager() {}
	
	// private static EntityManagerFactory entityManagerFactory;
	
	private static Map<String, EntityManagerFactory> factory;
	
	public static EntityManager getEntityManager(String name) {
		
		if(factory == null) {
			
			factory = new HashMap<String, EntityManagerFactory>();
			factory.put(name, Persistence.createEntityManagerFactory(name));
			
		} else if (!factory.containsKey(name)){
			
			factory.put(name, Persistence.createEntityManagerFactory(name));
			
		}
		
		return factory.get(name).createEntityManager();
	
	}
	
}
