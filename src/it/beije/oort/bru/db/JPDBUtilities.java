package it.beije.oort.bru.db;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import it.beije.oort.bru.db.biblioteca.Autore;
import it.beije.oort.bru.db.biblioteca.Editore;
import it.beije.oort.bru.db.biblioteca.Libro;
import it.beije.oort.bru.db.biblioteca.Prestito;
import it.beije.oort.bru.db.biblioteca.Utente;

public class JPDBUtilities {
	
	public static Libro exportBook(String titolo) {
		EntityManager entityManager = JPDBEntityManagerFactory.createEntityManager();
		String jpql = "SELECT l FROM Libro AS l WHERE titolo = '" + titolo + "'";
		Query query = entityManager.createQuery(jpql);
		Libro book = (Libro) query.getSingleResult();
		return book;
	}
	
	public static List<Prestito> exportLoans() {
		EntityManager entityManager = JPDBEntityManagerFactory.createEntityManager();
		String jpql2 = "SELECT p FROM Prestito p";
		Query query = entityManager.createQuery(jpql2);
		List<Prestito> loans = query.getResultList();
		entityManager.close();
		return loans;
	}
	
	public static boolean isAvalaible(int idUtente, int idLibro) {
		List<Prestito> loans = exportLoans();
		for (int i = 0; i < loans.size(); i++) {
			if (loans.get(i).getId_libro() == idLibro) {
				return false;
			}
		}
		return true;
	}
	
	public static List<Autore> exportAuthors() {
		EntityManager entityManager = JPDBEntityManagerFactory.createEntityManager();
		String jpql = "SELECT a FROM Autore a";
		Query query = entityManager.createQuery(jpql);
		List<Autore> authors = query.getResultList();
		entityManager.close();
		return authors;
	}
	
	public static List<Editore> exportPublishers() {
		EntityManager entityManager = JPDBEntityManagerFactory.createEntityManager();
		String jpql = "SELECT e from Editore e";
		Query query = entityManager.createQuery(jpql);
		List <Editore> publishers = query.getResultList();
		entityManager.close();
		return publishers;
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
		loan.setData_inizio(data_inizio);
		loan.setData_fine(data_fine);
		loan.setNote(note);
		entityManager.persist(loan);
		transaction.commit();
		entityManager.close();
	}
}
