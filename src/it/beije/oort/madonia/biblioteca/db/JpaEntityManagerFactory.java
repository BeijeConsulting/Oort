package it.beije.oort.madonia.biblioteca.db;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

public class JpaEntityManagerFactory {
	
	private JpaEntityManagerFactory() {}
	
	Map<String, EntityManagerFactory> factoryMap = new HashMap<String, EntityManagerFactory>();
	
	
}
