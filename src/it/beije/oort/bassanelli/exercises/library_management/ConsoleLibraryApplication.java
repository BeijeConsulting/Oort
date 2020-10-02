package it.beije.oort.bassanelli.exercises.library_management;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.persistence.EntityManagerFactory;

import org.hibernate.mapping.Array;

public class ConsoleLibraryApplication {

	public static void main(String[] args) {

		final Scanner scanner = new Scanner(System.in);

		String command;

		Book book;
		Author author;
		Publisher publisher;
		User user;
		Borrow borrow;

		List<Book> books;
		List<Author> authors;
		List<Publisher> publishers;
		List<User> users;
		List<Borrow> borrows;

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

		boolean exitFromApplication = false;

		while (!exitFromApplication) {
			
			System.out.println("- LIBRARY APPLICATION -");
			System.out.println("1) Add book");
			System.out.println("2) Add author");
			System.out.println("3) Add publisher");
			System.out.println("4) Add user");
			System.out.println("5) Add borrow");
			System.out.println("6) View records");
			System.out.println("0) Exit");

			System.out.print("Command: ");
			command = scanner.nextLine();

			switch (command) {

			case "1":

				// authors = HibernateDBManager.getAllAuthors();
				// publishers = HibernateDBManager.getAllPublishers();

				authors = JavaPersistenceDBManager.getAllAuthors();
				publishers = JavaPersistenceDBManager.getAllPublishers();

				if (authors == null || authors.isEmpty() || publishers == null || publishers.isEmpty()) {
					System.out.println("Not found authors and/or publishers into database");
				} else {

					boolean exitFromAddBook = false;

					do {
						book = new Book();

						System.out.println("- ADD BOOK - ");
						System.out.print("Title: ");
						book.setTitle(scanner.nextLine());
						System.out.print("Description: ");
						book.setDescription(scanner.nextLine());
						System.out.println("Author (list):");
						for (int i = 0; i < authors.size(); i++) {
							System.out.println(authors.get(i).toString("ID;NAME;SURNAME"));
						}
						System.out.print("Choose author ID: ");
						book.setAuthorId(Integer.parseInt(scanner.nextLine()));
						System.out.println("Publisher (list):");
						for (int i = 0; i < publishers.size(); i++) {
							System.out.println(publishers.get(i).toString("ID;NAME"));
						}
						System.out.print("Choose publisher ID: ");
						book.setPublisherId(Integer.parseInt(scanner.nextLine()));
						System.out.println("Year: ");
						book.setYear(Integer.parseInt(scanner.nextLine()));

						System.out.println("1) Ok");
						System.out.println("2) Cancel");

						System.out.print("Command: ");
						command = scanner.nextLine();

						switch (command) {
						case "1":
							// HibernateDBManager.addBook(book);
							JavaPersistenceDBManager.addBook(book);
							System.out.println("Book saved");
							break;
						case "2":
							exitFromAddBook = true;
							System.out.println("Cancel");
							break;
						default:
							System.out.println("Command not valid");
							break;
						}

					} while (!exitFromAddBook);
				}

				break;

			case "2":

				boolean exitFromAddAuthor = false;

				do {

					author = new Author();

					System.out.println("- ADD AUTHOR - ");
					System.out.print("Name: ");
					author.setName(scanner.nextLine());
					System.out.print("Surname: ");
					author.setSurname(scanner.nextLine());

					try {
						System.out.print("Date of birth [MM/DD/YYYY]:");
						author.setDateOfBirth(LocalDate.parse(scanner.nextLine(), dateTimeFormatter));
					} catch (DateTimeParseException e) {
						author.setDateOfBirth(null);
						// e.printStackTrace();
					}

					try {
						System.out.print("Date of death [MM/DD/YYYY]:");
						author.setDateOfDeath(LocalDate.parse(scanner.nextLine(), dateTimeFormatter));
					} catch (DateTimeParseException e) {
						author.setDateOfDeath(null);
						// e.printStackTrace();
					}

					System.out.print("Biography:");
					author.setBiography(scanner.nextLine());

					System.out.println("1) Ok");
					System.out.println("2) Cancel");

					System.out.print("Command: ");
					command = scanner.nextLine();

					switch (command) {
					case "1":
						// HibernateDBManager.addAuthor(author);
						JavaPersistenceDBManager.addAuthor(author);
						System.out.println("Author saved");
						break;
					case "2":
						exitFromAddAuthor = true;
						System.out.println("Cancel");
						break;
					default:
						System.out.println("Command not valid");
						break;
					}

				} while (!exitFromAddAuthor);

				break;

			case "3":

				boolean exitFromAddPublisher = false;

				do {

					publisher = new Publisher();

					System.out.println("- ADD PUBLISHER - ");
					System.out.print("Name: ");
					publisher.setName(scanner.nextLine());
					System.out.print("Description: ");
					publisher.setDescription(scanner.nextLine());

					System.out.println("1) Ok");
					System.out.println("2) Cancel");

					System.out.print("Command: ");
					command = scanner.nextLine();

					switch (command) {
					case "1":
						// HibernateDBManager.addPublisher(publisher);
						JavaPersistenceDBManager.addPublisher(publisher);
						System.out.println("Publisher saved");
						break;
					case "2":
						exitFromAddPublisher = true;
						System.out.println("Cancel");
						break;
					default:
						System.out.println("Command not valid");
						break;
					}

				} while (!exitFromAddPublisher);

				break;

			case "4":

				boolean exitFromAddUser = false;

				do {

					user = new User();

					System.out.println("- ADD USER - ");
					System.out.print("Name: ");
					user.setName(scanner.nextLine());
					System.out.print("Surname: ");
					user.setSurname(scanner.nextLine());
					System.out.print("Fiscal code:");
					user.setFiscalCode(scanner.nextLine());
					System.out.print("Email:");
					user.setEmail(scanner.nextLine());
					System.out.print("Mobile:");
					user.setMobile(scanner.nextLine());
					System.out.print("Address:");
					user.setAddress(scanner.nextLine());

					System.out.println("1) Ok");
					System.out.println("2) Cancel");

					System.out.print("Command: ");
					command = scanner.nextLine();

					switch (command) {
					case "1":
						// HibernateDBManager.addUser(user);
						JavaPersistenceDBManager.addUser(user);
						System.out.println("User saved");
						break;
					case "2":
						exitFromAddUser = true;
						System.out.println("Cancel");
						break;
					default:
						System.out.println("Command not valid");
						break;
					}

				} while (!exitFromAddUser);

				break;

			case "5":

				// books = HibernateDBManager.getAllBooks();
				// users = HibernateDBManager.getAllUsers();

				books = JavaPersistenceDBManager.getAllBooks();
				users = JavaPersistenceDBManager.getAllUsers();

				if (books == null || books.isEmpty() || users == null || users.isEmpty()) {
					System.out.println("Not found books and/or users into database");
				} else {

					boolean exitFromAddBorrow = false;

					do {

						borrow = new Borrow();

						System.out.println("- ADD BORROW - ");
						System.out.println("Book (list): ");
						for (int i = 0; i < books.size(); i++) {
							System.out.println(books.get(i).toString("ID;TITLE"));
						}
						System.out.print("Choose book ID: ");
						borrow.setBookId(Integer.parseInt(scanner.nextLine()));
						System.out.println("User (list): ");
						for (int i = 0; i < users.size(); i++) {
							System.out
									.println(users.get(i).toString("ID;NAME;SURNAME;FISCALCODE;EMAIL;MOBILE;ADDRESS"));
						}
						System.out.print("Choose user ID: ");
						borrow.setUserId(Integer.parseInt(scanner.nextLine()));

						try {
							System.out.print("Date of start [MM/DD/YYYY]:");
							borrow.setDateOfStart(LocalDate.parse(scanner.nextLine(), dateTimeFormatter));
						} catch (DateTimeParseException e) {
							System.out.println("Date is not valid");
							break;
							// e.printStackTrace();
						}

						try {
							System.out.print("Date of end [MM/DD/YYYY]:");
							borrow.setDateOfEnd(LocalDate.parse(scanner.nextLine(), dateTimeFormatter));
						} catch (DateTimeParseException e) {
							borrow.setDateOfEnd(null);
							// e.printStackTrace();
						}

						System.out.print("Note:");
						borrow.setNote(scanner.nextLine());

						System.out.println("1) Ok");
						System.out.println("2) Cancel");

						System.out.print("Command: ");
						command = scanner.nextLine();

						switch (command) {
						case "1":
							// HibernateDBManager.addBorrow(borrow);
							JavaPersistenceDBManager.addBorrow(borrow);
							System.out.println("Borrow saved");
							break;
						case "2":
							exitFromAddBorrow = true;
							System.out.println("Cancel");
							break;
						default:
							System.out.println("Command not valid");
							break;
						}

					} while (!exitFromAddBorrow);
				}

				break;

			case "6":

				boolean exitFromViewPages = false;

				do {

					System.out.println("- VIEW PAGES -");
					System.out.println("1) Books");
					System.out.println("2) Authors");
					System.out.println("3) Publishers");
					System.out.println("4) Users");
					System.out.println("5) Borrows");
					System.out.println("0) Back");

					System.out.print("Command: ");
					command = scanner.nextLine();

					switch (command) {

					case "1":

						books = JavaPersistenceDBManager.getAllBooks();
						
						printRecords(books);
						
						break;
						
					case "2":

						authors = JavaPersistenceDBManager.getAllAuthors();
						
						printRecords(authors);
						
						break;
						
					case "3":

						publishers = JavaPersistenceDBManager.getAllPublishers();
						
						printRecords(publishers);
						
						break;
						
					case "4":

						users = JavaPersistenceDBManager.getAllUsers();
						
						printRecords(users);
						
						break;

					case "5":

						borrows = JavaPersistenceDBManager.getAllBorrows();
						
						printRecords(borrows);
						
						break;
						
					case "0":
						exitFromViewPages = true;
						System.out.println("Cancel");
						break;

					default:
						System.out.println("Command not valid");
						break;
					}

				} while (!exitFromViewPages);

				break;

			case "0":
				System.out.println("- EXIT -");
				exitFromApplication = true;
				break;

			default:
				System.out.println("Command not valid");
				break;
			}

		}

		scanner.close();
		System.gc();
		System.out.println("BYE!!");
		System.exit(0);
	}
	
	public static void printRecords(List<?> list) {
		
		if(list == null || list.isEmpty()) {
			System.out.println("Not found records");
		} else {
			for(Object o : list) {
				System.out.println(o.toString());
			}
		}
	}
	
}
