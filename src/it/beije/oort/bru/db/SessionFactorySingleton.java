package it.beije.oort.bru.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import it.beije.oort.biblioteca.Autore;
import it.beije.oort.biblioteca.Editore;
import it.beije.oort.biblioteca.Libro;
import it.beije.oort.files.Contatto;

public class SessionFactorySingleton {
	
	private SessionFactorySingleton() {}
	private static SessionFactory factory;
	public static Session openSession() {
		if (factory == null) {
			Configuration configuration = new Configuration();
			configuration.configure().addAnnotatedClass(Libro.class)
						.addAnnotatedClass(Autore.class)
						.addAnnotatedClass(Editore.class);
			factory = configuration.buildSessionFactory();
		}
		return factory.openSession();
	}
}
