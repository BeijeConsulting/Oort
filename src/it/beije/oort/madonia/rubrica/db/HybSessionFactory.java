package it.beije.oort.madonia.rubrica.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import it.beije.oort.madonia.rubrica.Contatto;

public class HybSessionFactory {
	private HybSessionFactory() {}
	
	private static SessionFactory factory;
	
	public static Session getSession() {
		if (factory == null) {
			Configuration configuration = new Configuration();
			configuration = configuration.configure("/hibernate.cfg.xml")
					.addAnnotatedClass(Contatto.class);
			
			factory = configuration.buildSessionFactory();
		}
		
		return factory.openSession();
	}
}
