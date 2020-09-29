package it.beije.oort.madonia.rubrica.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HDBtools {
	private Configuration configuration;
	private SessionFactory factory;
	private Session session;
	
	public HDBtools() {
		configuration = new Configuration();
		configuration = configuration.configure();
		factory = configuration.buildSessionFactory();
		session = factory.openSession();
	}
	
	public Session getSession() {
		return session;
	}
	
	public void close() {
		session.close();
	}
}
