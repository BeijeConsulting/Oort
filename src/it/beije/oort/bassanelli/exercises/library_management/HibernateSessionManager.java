package it.beije.oort.bassanelli.exercises.library_management;

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
			configuration = configuration.configure("hibernate_library.cfg.xml").addAnnotatedClass(Book.class)
					.addAnnotatedClass(Author.class).addAnnotatedClass(Publisher.class).addAnnotatedClass(User.class)
					.addAnnotatedClass(Borrow.class);
		}

	}

}
