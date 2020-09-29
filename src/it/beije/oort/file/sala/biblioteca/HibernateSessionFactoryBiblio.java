package it.beije.oort.file.sala.biblioteca;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryBiblio {
	
	private static SessionFactory factory;
	
	public static Session openSession() {
		if (factory == null) {
			Configuration configuration = new Configuration();
			configuration = configuration.configure()
					.addAnnotatedClass(Libro.class)
					.addAnnotatedClass(Autore.class)
					.addAnnotatedClass(Editore.class)
					.addAnnotatedClass(Utente.class)
					.addAnnotatedClass(Prestito.class);

			factory = configuration.buildSessionFactory();
		}
		
		return factory.openSession();
	}
}
