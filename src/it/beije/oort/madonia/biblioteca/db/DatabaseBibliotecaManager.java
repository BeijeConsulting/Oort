package it.beije.oort.madonia.biblioteca.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import it.beije.oort.madonia.biblioteca.ebeans.Autore;
import it.beije.oort.madonia.biblioteca.ebeans.Editore;
import it.beije.oort.madonia.biblioteca.ebeans.Libro;
import it.beije.oort.madonia.biblioteca.ebeans.Utente;

public class DatabaseBibliotecaManager {
	private DatabaseBibliotecaManager () {}
	
	private static String persistenceUnitName = "OortBiblioteca";
	
	public static void inserisciEntity(Autore autore) {
		inserisciEntity((Object) autore);
	}
	
	public static void inserisciEntity(Editore editore) {
		inserisciEntity((Object) editore);
	}
	
	public static void inserisciEntity(Libro libro) {
		inserisciEntity((Object) libro);
	}
	
	public static void inserisciEntity(Utente utente) {
		inserisciEntity((Object) utente);
	}
	
	private static void inserisciEntity(Object o) {
		EntityManager eManager = JpaEntityManagerFactory.createEntityManager(persistenceUnitName);
		try {
			eManager.getTransaction().begin();
			eManager.persist(o);
			eManager.getTransaction().commit();
		} finally {
			eManager.close();
		}
	}
	
	public static Map<Integer, Autore> lookUp(Autore autore) {
		String jpql = "SELECT a FROM Autore as a ORDER BY a.cognome, a.nome";
		// Ma davvero bisogna castare così?!
		Map<Integer, Autore> mappa = (Map<Integer, Autore>) (Map<Integer, ?>) lookUp(jpql);
		return mappa;
	}
	
	public static Map<Integer, Editore> lookUp(Editore autore) {
		String jpql = "SELECT e FROM Editore as e ORDER BY e.denominazione";
		Map<Integer, Editore> mappa = (Map<Integer, Editore>) (Map<Integer, ?>) lookUp(jpql);
		return mappa;
	}
	
	private static Map<Integer, Object> lookUp(String jpql) {
		EntityManager eManager = JpaEntityManagerFactory.createEntityManager(persistenceUnitName);
		Map<Integer, Object> mappa = new HashMap<Integer, Object>();
		try {
			Query query = eManager.createQuery(jpql);
			List<Object> lista = query.getResultList();
			for (int i = 0; i < lista.size(); i++) {
				mappa.put(i+1,lista.get(i));
			}
		} finally {
			eManager.close();
		}
		return mappa;
	}
}
