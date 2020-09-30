package it.beije.oort.bru.db;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import it.beije.oort.biblioteca.Autore;
import it.beije.oort.biblioteca.Editore;
import it.beije.oort.biblioteca.Libro;
import it.beije.oort.biblioteca.Prestito;
import it.beije.oort.biblioteca.Utente;

public class JPDBUtilities {
	
	private static Libro exportBook(int idLibro) {
		Libro book = new Libro();
		EntityManager entityManager = JPDBEntityManagerFactory.createEntityManager();
		String jpql = "SELECT l FROM Libro as l WHERE id = " + idLibro;
		Query query = entityManager.createQuery(jpql);
		book = (Libro) query.getSingleResult();
		return book;
	}
	
	private static Utente exportUser(int idUtente) {
		Utente user = new Utente();
		EntityManager entityManager = JPDBEntityManagerFactory.createEntityManager();
		String jpql = "SELECT u FROM Utente as u WHERE id = " + idUtente;
		Query query = entityManager.createQuery(jpql);
		user = (Utente) query.getSingleResult();
		return user;
	}
	
	//METODO PER SAPERE SE UN LIBRO E' DISPONIBILE O SE E' PRESO IN PRESTITO
	
	public static List<Autore> exportAuthors(List<Autore> authors) {
		EntityManager entityManager = JPDBEntityManagerFactory.createEntityManager();
		String jpql = "SELECT a from Autore a";
		Query query = entityManager.createQuery(jpql);
		List<Autore> exportedAuthors = query.getResultList();
		entityManager.close();
		return exportedAuthors;
	}
	
	public static List<Editore> exportPublishers(List<Editore> publishers) {
		EntityManager entityManager = JPDBEntityManagerFactory.createEntityManager();
		String jpql = "SELECT e from Editore e";
		Query query = entityManager.createQuery(jpql);
		List <Editore> exportedPublishers = query.getResultList();
		entityManager.close();
		return exportedPublishers;
	}
	
	public static void insertAuthor(String cognome, String nome, String data_nascita, String data_morte, String biografia) {
		EntityManager entityManager = JPDBEntityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Autore author = new Autore();
		author.setCognome(cognome);
		author.setNome(nome);
		author.setData_nascita(data_nascita);
		author.setData_morte(data_morte);
		author.setBiografia(biografia);
		entityManager.persist(author);
		transaction.commit();
		entityManager.close();
	}
	
	public static void insertPublisher(String denominazione, String descrizione) {
		EntityManager entityManager = JPDBEntityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Editore publisher = new Editore();
		publisher.setDenominazione(denominazione);
		publisher.setDescrizione(descrizione);
		entityManager.persist(publisher);
		transaction.commit();
		entityManager.close();
	}
	
	public static void insertBook(String titolo, String descrizione, int id_autore, int id_editore, int anno) {
		EntityManager entityManager = JPDBEntityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Libro book = new Libro();
		book.setTitolo(titolo);
		book.setDescrizione(descrizione);
		book.setId_autore(id_autore);
		book.setId_editore(id_editore);
		book.setAnno(anno);
		entityManager.persist(book);
		transaction.commit();
		entityManager.close();
	}
	
	public static void insertUser(String nome, String cognome, String telefono, String email, String codice_fiscale, String indirizzo) {
		EntityManager entityManager = JPDBEntityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Utente user = new Utente();
		user.setNome(nome);
		user.setCognome(cognome);
		user.setTelefono(telefono);
		user.setEmail(email);
		user.setCodice_fiscale(codice_fiscale);
		user.setIndirizzo(indirizzo);
		entityManager.persist(user);
		transaction.commit();
		entityManager.close();
	}
	
	public static void insertLoan(int idLibro, int idUtente, String data_inizio, String data_fine, String note) {
		EntityManager entityManager = JPDBEntityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Prestito loan = new Prestito();
		loan.setId_libro(idLibro);
		loan.setId_utente(idUtente);
		loan.setData_inzio(data_inizio);
		loan.setData_fine(data_fine);
		loan.setNote(note);
		entityManager.persist(loan);
		transaction.commit();
		entityManager.close();
	}
}
