package it.beije.oort.bassanelli.exercises.library_management;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class JavaPersistenceDBManager {

	public static final String SCHEMA_OORT_LIBRARY = "OortLibrary";

	public static List<Book> getAllBooks() {

		List<Book> books = new ArrayList<Book>();
		EntityManager entityManager = JavaPersistenceSessionManager.getEntityManager(SCHEMA_OORT_LIBRARY);
		StringBuilder builder = new StringBuilder("FROM ").append(Book.TABLE_BOOK);
		Query query = entityManager.createQuery(builder.toString());
		books = query.getResultList();
		entityManager.close();
		return books;

	}

	public static Book searchBookById(int id) {

		EntityManager entityManager = JavaPersistenceSessionManager.getEntityManager(SCHEMA_OORT_LIBRARY);
		Book book = entityManager.find(Book.class, id);
		entityManager.close();
		return book;

	}

	public static void addBook(Book book) {

		EntityManager entityManager = JavaPersistenceSessionManager.getEntityManager(SCHEMA_OORT_LIBRARY);
		entityManager.getTransaction().begin();
		entityManager.persist(book);
		entityManager.close();

	}

	public static List<Author> getAllAuthors() {

		List<Author> authors = new ArrayList<Author>();
		EntityManager entityManager = JavaPersistenceSessionManager.getEntityManager(SCHEMA_OORT_LIBRARY);
		StringBuilder builder = new StringBuilder("FROM ").append(Author.TABLE_AUTHOR);
		Query query = entityManager.createQuery(builder.toString());
		authors = query.getResultList();
		entityManager.close();
		return authors;

	}

	public static Author searchAuthorById(int id) {

		EntityManager entityManager = JavaPersistenceSessionManager.getEntityManager(SCHEMA_OORT_LIBRARY);
		Author author = entityManager.find(Author.class, id);
		entityManager.close();
		return author;

	}

	public static void addAuthor(Author author) {

		EntityManager entityManager = JavaPersistenceSessionManager.getEntityManager(SCHEMA_OORT_LIBRARY);
		entityManager.getTransaction().begin();
		entityManager.persist(author);
		entityManager.close();

	}

	public static List<Publisher> getAllPublishers() {

		List<Publisher> publishers = new ArrayList<Publisher>();
		EntityManager entityManager = JavaPersistenceSessionManager.getEntityManager(SCHEMA_OORT_LIBRARY);
		StringBuilder builder = new StringBuilder("FROM ").append(Publisher.TABLE_PUBLISHER);
		Query query = entityManager.createQuery(builder.toString());
		publishers = query.getResultList();
		entityManager.close();
		return publishers;

	}
	
	public static Publisher searchPublisherById(int id) {

		EntityManager entityManager = JavaPersistenceSessionManager.getEntityManager(SCHEMA_OORT_LIBRARY);
		Publisher publisher = entityManager.find(Publisher.class, id);
		entityManager.close();
		return publisher;

	}

	public static void addPublisher(Publisher publisher) {

		EntityManager entityManager = JavaPersistenceSessionManager.getEntityManager(SCHEMA_OORT_LIBRARY);
		entityManager.getTransaction().begin();
		entityManager.persist(publisher);
		entityManager.close();

	}

	public static List<User> getAllUsers() {

		List<User> users = new ArrayList<User>();
		EntityManager entityManager = JavaPersistenceSessionManager.getEntityManager(SCHEMA_OORT_LIBRARY);
		StringBuilder builder = new StringBuilder("FROM ").append(User.TABLE_USER);
		Query query = entityManager.createQuery(builder.toString());
		users = query.getResultList();
		entityManager.close();
		return users;

	}
	
	public static User searchUserById(int id) {

		EntityManager entityManager = JavaPersistenceSessionManager.getEntityManager(SCHEMA_OORT_LIBRARY);
		User user = entityManager.find(User.class, id);
		entityManager.close();
		return user;

	}

	public static void addUser(User user) {

		EntityManager entityManager = JavaPersistenceSessionManager.getEntityManager(SCHEMA_OORT_LIBRARY);
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.close();

	}

	public static List<Borrow> getAllBorrows() {

		List<Borrow> borrows = new ArrayList<Borrow>();
		EntityManager entityManager = JavaPersistenceSessionManager.getEntityManager(SCHEMA_OORT_LIBRARY);
		StringBuilder builder = new StringBuilder("FROM ").append(Borrow.TABLE_BORROW);
		Query query = entityManager.createQuery(builder.toString());
		borrows = query.getResultList();
		entityManager.close();
		return borrows;

	}
	
	public static Borrow searchBorrowById(int id) {

		EntityManager entityManager = JavaPersistenceSessionManager.getEntityManager(SCHEMA_OORT_LIBRARY);
		Borrow borrow = entityManager.find(Borrow.class, id);
		entityManager.close();
		return borrow;

	}

	public static void addBorrow(Borrow borrow) {

		EntityManager entityManager = JavaPersistenceSessionManager.getEntityManager(SCHEMA_OORT_LIBRARY);
		entityManager.getTransaction().begin();
		entityManager.persist(borrow);
		entityManager.close();

	}
}
