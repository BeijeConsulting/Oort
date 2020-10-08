package it.beije.oort.lauria.biblioteca;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class JPADBtools {

	public static final EntityManager entityManager = JEntityManagerFactory.openEntityManager("OortBiblioteca");
	
//	public static void main(String[] args) {
		
//	EntityManager entityManager = JEntityManagerFactory.openEntityManager("OortBiblioteca");
		
	//esempio SELECT
//	Libro contatto = entityManager.find(Libro.class, 1);
//	
//	System.out.println(contatto);
	
	//esempio query JPQL
//	String jpql = "SELECT c FROM Contatto as c WHERE cognome = 'rossi'";
//	Query query = entityManager.createQuery(jpql);
//	List<Contatto> contatti = query.getResultList();
//	System.out.println(contatti.size());
//	for (Contatto contatto : contatti) {
//		System.out.println("id : " + contatto.getId());
//		System.out.println("nome : " + contatto.getNome());
//		System.out.println("cognome : " + contatto.getCognome());
//		System.out.println("telefono : " + contatto.getTelefono());
//		System.out.println("email : " + contatto.getEmail());
//	}
	
	//esempio INSERT
//	Contatto contatto = new Contatto();
//	contatto.setNome("Gianna");
//	contatto.setCognome("Nanni");
//	contatto.setEmail("gianna@nannino.it");
//	contatto.setTelefono("3455661634");
//	
//	//entityManager.getTransaction().begin();
//	EntityTransaction entityTransaction = entityManager.getTransaction();
//	entityTransaction.begin();
//	
//	System.out.println("contatto id : " + contatto.getId());
//	entityManager.persist(contatto);
//	System.out.println("contatto id : " + contatto.getId());
//	entityManager.getTransaction().commit();
//	//entityManager.getTransaction().rollback();
//	
//	entityManager.close();
//	}
	
	public JPADBtools() {}

	public static boolean selectContact(int i, int j) {
		//EntityManager entityManager = JEntityManagerFactory.openEntityManager("OortBiblioteca");
		String jpql = "SELECT l FROM Libro as l WHERE id > "+ i +" and id < "+ j;
		Query query = entityManager.createQuery(jpql);
		List<Libro> libri = query.getResultList();
		if(libri.size() == 0) {
			return false;
		}
		System.out.println("In biblioteca sono presenti "+ libri.size() +" libri.");
		for (Libro libro : libri) {			
			Autore a = JPADBtools.entityManager.find(Autore.class, libro.getId_autore());
			Editore e = JPADBtools.entityManager.find(Editore.class, libro.getId_editore()); 
			System.out.println("id : " + libro.getId());
			System.out.println("titolo : " + libro.getTitolo());
			System.out.println("descrizione : " + libro.getDescrizione());
			System.out.println("id autore : " + libro.getId_autore() + " ("+ a.getNome() +" "+a.getCognome() +")" );
			System.out.println("id editore : " + libro.getId_editore() + " ("+ e.getDenominazione() +")" );
			System.out.println("anno : " + libro.getAnno());
		}
		//entityManager.close();
		return true;
	}
	
	public static boolean selectLibri(int i, int j) {
		//EntityManager entityManager = JEntityManagerFactory.openEntityManager("OortBiblioteca");
		String jpql = "SELECT l FROM Libro as l WHERE id > "+ i +" and id < "+ j;
		Query query = entityManager.createQuery(jpql);
		List<Libro> libri = query.getResultList();
		if(libri.size() == 0) {
			return false;
		}
		System.out.println("In biblioteca sono presenti "+ libri.size() +" libri.");
		for (Libro libro : libri) {			
			Autore a = JPADBtools.entityManager.find(Autore.class, libro.getId_autore());
			Editore e = JPADBtools.entityManager.find(Editore.class, libro.getId_editore()); 
			System.out.println("id : " + libro.getId());
			System.out.println("titolo : " + libro.getTitolo());
			System.out.println("descrizione : " + libro.getDescrizione());
			System.out.println("id autore : " + libro.getId_autore() + " ("+ a.getNome() +" "+a.getCognome() +")" );
			System.out.println("id editore : " + libro.getId_editore() + " ("+ e.getDenominazione() +")" );
			System.out.println("anno : " + libro.getAnno());
		}
		//entityManager.close();
		return true;
	}
	
	public static boolean selectAutori(int i, int j) {
		//EntityManager entityManager = JEntityManagerFactory.openEntityManager("OortBiblioteca");
		String jpql = "SELECT a FROM Autore as a WHERE id > "+ i +" and id < "+ j;
		Query query = entityManager.createQuery(jpql);
		List<Autore> autori = query.getResultList();
		if(autori.size() == 0) {
			return false;
		}
		System.out.println("In biblioteca sono presenti libri di "+ autori.size() +" autori diversi.");
		for (Autore autore : autori) {				
			System.out.println("id : " + autore.getId());
			System.out.println("cognome : " + autore.getCognome());
			System.out.println("nome : " + autore.getNome());
			System.out.println("data_nascita : " + autore.getData_nascita().toString());
			System.out.println("data_morte : " + autore.getData_morte().toString());
			System.out.println("biografia : " + autore.getBiografia());
		}
		//entityManager.close();
		return true;
	}

	public static boolean selectEditori(int i, int j) {
		//EntityManager entityManager = JEntityManagerFactory.openEntityManager("OortBiblioteca");
		String jpql = "SELECT e FROM Editore as e WHERE id > "+ i +" and id < "+ j;
		Query query = entityManager.createQuery(jpql);
		List<Editore> editori = query.getResultList();
		if(editori.size() == 0) {
			return false;
		}
		System.out.println("In biblioteca sono presenti libri di "+ editori.size() +" editori diversi.");
		for (Editore editore : editori) {				
			System.out.println("id : " + editore.getId());
			System.out.println("denominazione : " + editore.getDenominazione());
			System.out.println("descrizione : " + editore.getDescrizione());
			
		}
		//entityManager.close();
		return true;
	}

	public static boolean selectUtenti(int i, int j) {
		//EntityManager entityManager = JEntityManagerFactory.openEntityManager("OortBiblioteca");
		String jpql = "SELECT u FROM Utente as u WHERE id > "+ i +" and id < "+ j;
		Query query = entityManager.createQuery(jpql);
		List<Utente> utenti = query.getResultList();
		if(utenti.size() == 0) {
			return false;
		}
		System.out.println("In biblioteca sono presenti "+ utenti.size() +" utenti registrati.");
		for (Utente utente : utenti) {				
			System.out.println("id : " + utente.getId());
			System.out.println("nome : " + utente.getNome());
			System.out.println("cognome : " + utente.getCognome());
			System.out.println("codice fiscale : " + utente.getCodice_fiscale());
			System.out.println("email : " + utente.getEmail());
			System.out.println("telefono : " + utente.getTelefono());
			System.out.println("indirizzo : " + utente.getIndirizzo());
			
		}
		//entityManager.close();
		return true;
	}
	
	public static boolean selectPrestiti(int i, int j) {
		//EntityManager entityManager = JEntityManagerFactory.openEntityManager("OortBiblioteca");
		String jpql = "SELECT p FROM Prestito as p WHERE id > "+ i +" and id < "+ j;
		Query query = entityManager.createQuery(jpql);
		List<Prestito> prestiti = query.getResultList();
		if(prestiti.size() == 0) {
			return false;
		}
		//System.out.println(prestiti.size());
		for (Prestito prestito : prestiti) {		
			Libro l = JPADBtools.entityManager.find(Libro.class, prestito.getId_libro());
			Utente u = JPADBtools.entityManager.find(Utente.class, prestito.getId_utente());
			System.out.println("id : " + prestito.getId());
			System.out.println("id_libro : " + prestito.getId_libro() + " ("+ l.getTitolo() +")" );
			System.out.println("id_utente : " + prestito.getId_utente() + " ("+ u.getNome() +" "+u.getCognome() +")" );
			System.out.println("data inizio prestito : " + prestito.getData_inizio().toString());
			System.out.println("data fine prestito : " + prestito.getData_fine().toString());
			System.out.println("note : " + prestito.getNote());
			
		}
		//entityManager.close();
		return true;
	}
	
	
	
	
	
	public static void insertLibro(String titolo, String descrizione, int id_autore, int id_editore, String anno) {
		//EntityManager entityManager = JEntityManagerFactory.openEntityManager("OortBiblioteca");
		Libro libro = new Libro();
		libro.setTitolo(titolo);
		libro.setDescrizione(descrizione);
		libro.setId_autore(id_autore);
		libro.setId_editore(id_editore);
		libro.setAnno(anno);
		
		// apro la transaction
		entityManager.getTransaction().begin();
		// salvo il libro
		entityManager.persist(libro);
		// chiudo la transaction
		entityManager.getTransaction().commit();
					
		//entityManager.close();
	}
	
	public static void insertAutore(String nome, String cognome, LocalDate data_nascita, LocalDate data_morte, String biografia) {
		//EntityManager entityManager = JEntityManagerFactory.openEntityManager("OortBiblioteca");
		Autore autore = new Autore();
		autore.setNome(nome);
		autore.setCognome(cognome);
		autore.setData_nascita(data_nascita);
		autore.setData_morte(data_morte);
		autore.setBiografia(biografia);
		
		// apro la transaction
		entityManager.getTransaction().begin();
		// salvo il libro
		entityManager.persist(autore);
		// chiudo la transaction
		entityManager.getTransaction().commit();
					
		//entityManager.close();
	}
	
	public static void insertEditore(String denominazione, String descrizione) {
		//EntityManager entityManager = JEntityManagerFactory.openEntityManager("OortBiblioteca");
		Editore editore = new Editore();
		editore.setDenominazione(denominazione);
		editore.setDescrizione(descrizione);
		
		
		// apro la transaction
		entityManager.getTransaction().begin();
		// salvo il libro
		entityManager.persist(editore);
		// chiudo la transaction
		entityManager.getTransaction().commit();
					
		//entityManager.close();
	}

	public static void insertUtente(String nome, String cognome, String codice_fiscale, String email, String telefono, String indirizzo) {
		//EntityManager entityManager = JEntityManagerFactory.openEntityManager("OortBiblioteca");
		Utente utente = new Utente();
		utente.setNome(nome);
		utente.setCognome(cognome);
		utente.setCodice_fiscale(codice_fiscale);
		utente.setEmail(email);
		utente.setTelefono(telefono);
		utente.setIndirizzo(indirizzo);
		
		// apro la transaction
		entityManager.getTransaction().begin();
		// salvo il libro
		entityManager.persist(utente);
		// chiudo la transaction
		entityManager.getTransaction().commit();
					
		//entityManager.close();
	}
	
	public static void insertPrestito(int id_libro, int id_utente, LocalDate data_inizio, LocalDate data_fine, String note) {
		//EntityManager entityManager = JEntityManagerFactory.openEntityManager("OortBiblioteca");
		Prestito prestito = new Prestito();
		prestito.setId_libro(id_libro);
		prestito.setId_utente(id_utente);
		prestito.setData_inizio(data_inizio);
		prestito.setData_fine(data_fine);
		prestito.setNote(note);
		
		// apro la transaction
		entityManager.getTransaction().begin();
		// salvo il libro
		entityManager.persist(prestito);
		// chiudo la transaction
		entityManager.getTransaction().commit();
					
		//entityManager.close();
	}
}
