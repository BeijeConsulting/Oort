package it.beije.oort.sb.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import it.beije.oort.rubrica.Contatto;
import it.beije.oort.sb.biblioteca.*;
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
	
	public static Session openSessionBib() {
		if (factory == null) {
			//inizializzo configurazione
			Configuration configuration = new Configuration();
			configuration = configuration.configure("/hibernateBiblio.cfg.xml")
					.addAnnotatedClass(Autori.class)
					.addAnnotatedClass(Editori.class)
					.addAnnotatedClass(Libri.class)
					.addAnnotatedClass(Prestiti.class)
					.addAnnotatedClass(Utenti.class);
			
			//chiedo generatore di sessioni
			factory = configuration.buildSessionFactory();

		}
		
		return factory.openSession();
	}

}
