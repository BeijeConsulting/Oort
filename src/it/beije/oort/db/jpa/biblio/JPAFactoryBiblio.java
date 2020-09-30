package it.beije.oort.db.jpa.biblio;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAFactoryBiblio {

	private JPAFactoryBiblio() {}
	
	private static EntityManagerFactory factory;

	
	public static EntityManager openEntity() {
		if(factory == null) {
			factory = Persistence.createEntityManagerFactory("OortBiblioteca");
				System.out.println("EntityFactory is create? " + factory.isOpen());
		}
	return	 factory.createEntityManager();
	}
	
}
