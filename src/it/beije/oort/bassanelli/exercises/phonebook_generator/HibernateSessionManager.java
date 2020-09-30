package it.beije.oort.bassanelli.exercises.phonebook_generator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionManager {

	private HibernateSessionManager() {
	}

	private static Configuration configuration;
	private static SessionFactory factory;

	public static Session openSession() {

		if (configuration == null && factory == null) {
			loadConfiguration();
			factory = configuration.buildSessionFactory();
			// System.out.println("SessionFactory is create? " + factory.isOpen());
		} else if (factory == null) {
			factory = configuration.buildSessionFactory();
		}

		return factory.openSession();
	}

	public static void loadConfiguration() {

		if (configuration == null) {
			configuration = new Configuration();
			configuration = configuration.configure().addAnnotatedClass(Contact.class);
		}

	}

}
