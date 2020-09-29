package it.beije.oort.file.sala;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
	
	private static SessionFactory factory;
	
	public static Session openSession() {
		if (factory == null) {
			Configuration configuration = new Configuration();
			configuration = configuration.configure()
					.addAnnotatedClass(Contatto.class);

			factory = configuration.buildSessionFactory();
		}
		
		return factory.openSession();
	}
}
