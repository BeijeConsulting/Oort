package it.beije.oort.bassanelli.exercises.library_management;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class HibernateDBManager {

	public static List<Book> getAllBooks() {

		List<Book> books = new ArrayList<Book>();
		Session session = HibernateSessionManager.openSession();
		StringBuilder builder = new StringBuilder("FROM ").append(Book.TABLE_BOOK);
		Query<Book> query = session.createQuery(builder.toString());
		books = query.list();
		return books;

	}

	public static void addBook(Book book) {

		Session session = HibernateSessionManager.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(book);
		transaction.commit();
		session.close();

	}

	public static List<Author> getAllAuthors() {

		List<Author> authors = new ArrayList<Author>();
		Session session = HibernateSessionManager.openSession();
		StringBuilder builder = new StringBuilder("FROM ").append(Author.TABLE_AUTHOR);
		Query<Author> query = session.createQuery(builder.toString());
		authors = query.list();
		return authors;

	}

	public static void addAuthor(Author author) {

		Session session = HibernateSessionManager.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(author);
		transaction.commit();
		session.close();

	}

	public static List<Publisher> getAllPublishers() {

		List<Publisher> publishers = new ArrayList<Publisher>();
		Session session = HibernateSessionManager.openSession();
		StringBuilder builder = new StringBuilder("FROM ").append(Publisher.TABLE_PUBLISHER);
		Query<Publisher> query = session.createQuery(builder.toString());
		publishers = query.list();
		return publishers;

	}

	public static void addPublisher(Publisher publisher) {

		Session session = HibernateSessionManager.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(publisher);
		transaction.commit();
		session.close();

	}

	public static List<User> getAllUsers() {

		List<User> users = new ArrayList<User>();
		Session session = HibernateSessionManager.openSession();
		StringBuilder builder = new StringBuilder("FROM ").append(User.TABLE_USER);
		Query<User> query = session.createQuery(builder.toString());
		users = query.list();
		return users;

	}

	public static void addUser(User user) {

		Session session = HibernateSessionManager.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();
		session.close();

	}

	public static List<Borrow> getAllBorrows() {

		List<Borrow> borrows = new ArrayList<Borrow>();
		Session session = HibernateSessionManager.openSession();
		StringBuilder builder = new StringBuilder("FROM ").append(Borrow.TABLE_BORROW);
		Query<Borrow> query = session.createQuery(builder.toString());
		borrows = query.list();
		return borrows;

	}

	public static void addBorrow(Borrow borrow) {

		Session session = HibernateSessionManager.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(borrow);
		transaction.commit();
		session.close();

	}
	
}
