package it.beije.oort.madonia.biblioteca.db;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaEntityManagerFactory {
	
	private JpaEntityManagerFactory() {}
	
	private static Map<String, EntityManagerFactory> factoryMap;
	
	public static EntityManager createEntityManager(String persistenceUnitName) {
		
		if (factoryMap == null) {
			factoryMap = new HashMap<String, EntityManagerFactory>();
		}
		
		if (!factoryMap.containsKey(persistenceUnitName)) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory(persistenceUnitName);
			factoryMap.put(persistenceUnitName, factory);
		}
		
		return factoryMap.get(persistenceUnitName).createEntityManager();
	}
}
