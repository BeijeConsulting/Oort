package it.beije.oort.sb.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class JpaEntityManagerFactory {

	private JpaEntityManagerFactory() {}
	 
	private static Map<String, EntityManagerFactory> factorymap;
	
	public static EntityManager entityManager(String persistentUnitName){
		if(factorymap == null) {
			factorymap = new HashMap<String, EntityManagerFactory>();
		} 
		if(!factorymap.containsKey(persistentUnitName)) {
			factorymap.put(persistentUnitName, Persistence.createEntityManagerFactory(persistentUnitName));
		}
		return factorymap.get(persistentUnitName).createEntityManager();		
	}
}
