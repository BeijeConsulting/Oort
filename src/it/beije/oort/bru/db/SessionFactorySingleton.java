package it.beije.oort.bru.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import it.beije.oort.bru.db.biblioteca.Autore;
import it.beije.oort.bru.db.biblioteca.Editore;
import it.beije.oort.bru.db.biblioteca.Libro;
import it.beije.oort.bru.db.biblioteca.Prestito;
import it.beije.oort.bru.db.biblioteca.Utente;

public class SessionFactorySingleton {
	
	private SessionFactorySingleton() {}
	private static SessionFactory factory;
	public static Session openSession() {
		if (factory == null) {
			Configuration configuration = new Configuration();
			configuration.configure().addAnnotatedClass(Libro.class)
						.addAnnotatedClass(Autore.class)
						.addAnnotatedClass(Editore.class)
						.addAnnotatedClass(Utente.class)
						.addAnnotatedClass(Prestito.class);
			factory = configuration.buildSessionFactory();
		}
		return factory.openSession();
	}
}
