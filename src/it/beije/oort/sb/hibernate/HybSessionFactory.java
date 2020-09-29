package it.beije.oort.sb.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import it.beije.oort.rubrica.Contatto;

public class HybSessionFactory {
	
	private HybSessionFactory() {}
	
	private static SessionFactory factory;
	
	public static Session openSession() {
		if (factory == null) {
			//inizializzo configurazione
			Configuration configuration = new Configuration();
			configuration = configuration.configure()
					.addAnnotatedClass(Contatto.class);
					//.addAnnotatedClass(AltraClasse.class);
			
			//chiedo generatore di sessioni
			factory = configuration.buildSessionFactory();

		}
		
		return factory.openSession();
	}

}
