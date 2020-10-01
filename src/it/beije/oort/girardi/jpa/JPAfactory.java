package it.beije.oort.girardi.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAfactory {

	private JPAfactory() {}
	
	private static EntityManagerFactory factory;
	private static EntityManager entityManager;
	
	public static EntityManager openEntityFactory() {
		if (entityManager == null) {
//			factory = Persistence.createEntityManagerFactory("OortBiblioteca");
			factory = Persistence.createEntityManagerFactory("OortRubrica");
			entityManager = factory.createEntityManager();
			System.out.println("EntityFactory is created? " + factory.isOpen());
		}
		return entityManager;
	}
	
}
