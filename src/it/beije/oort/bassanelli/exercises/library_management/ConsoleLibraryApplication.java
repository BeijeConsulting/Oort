package it.beije.oort.bassanelli.exercises.library_management;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

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
				
				authors = HibernateDBManager.getAllAuthors();
				publishers = HibernateDBManager.getAllPublishers();
				
				if(authors == null || authors.isEmpty() || publishers == null || publishers.isEmpty()) {
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
						for(int i = 0; i < authors.size(); i++) {
							System.out.println(authors.get(i).toString("ID;NAME;SURNAME"));
						}
						System.out.print("Choose author ID: ");
						book.setAuthorId(Integer.parseInt(scanner.nextLine()));
						System.out.println("Publisher (list):");
						for(int i = 0; i < publishers.size(); i++) {
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
							HibernateDBManager.addBook(book);
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
						
					} while(!exitFromAddBook);
				}

				break;

			case "2":
				
				boolean exitFromAddAuthor = false;
				
				do {
					
					author = new Author();
					
					DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
					
					System.out.println("- ADD AUTHOR - ");
					System.out.print("Name: ");
					author.setName(scanner.nextLine());
					System.out.print("Surname: ");
					author.setSurname(scanner.nextLine());
					System.out.print("Date of birth [MM/DD/YYYY]:");
					author.setDateOfBirth(LocalDate.parse(scanner.nextLine(), dateTimeFormatter));
					System.out.print("Date of death [MM/DD/YYYY]:");
					author.setDateOfDeath(LocalDate.parse(scanner.nextLine(), dateTimeFormatter));
					System.out.print("Biography:");
					author.setBiography(scanner.nextLine());
					
					System.out.println("1) Ok");
					System.out.println("2) Cancel");

					System.out.print("Command: ");
					command = scanner.nextLine();
					
					switch (command) {
					case "1":
						HibernateDBManager.addAuthor(author);
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
					
				} while(!exitFromAddAuthor);
				
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
						HibernateDBManager.addPublisher(publisher);
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
						HibernateDBManager.addUser(user);
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
				
				books = HibernateDBManager.getAllBooks();
				users = HibernateDBManager.getAllUsers();
				
				if(books == null || books.isEmpty() || users == null || users.isEmpty()) {
					System.out.println("Not found books and/or users into database");
				} else {
					
					boolean exitFromAddBorrow = false;
					
					do {
						
						borrow = new Borrow();

						DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
						
						System.out.println("- ADD BORROW - ");
						System.out.println("Book (list): ");
						for(int i = 0; i < books.size(); i++) {
							System.out.println(books.get(i).toString("ID;TITLE"));
						}
						System.out.print("Choose book ID: ");
						borrow.setBookId(Integer.parseInt(scanner.nextLine()));
						System.out.println("User (list): ");
						for(int i = 0; i < users.size(); i++) {
							System.out.println(users.get(i).toString("ID;NAME;SURNAME;FISCALCODE;EMAIL;MOBILE;ADDRESS"));
						}
						System.out.print("Choose user ID: ");
						borrow.setUserId(Integer.parseInt(scanner.nextLine()));
						System.out.print("Date of start [MM/DD/YYYY]:");
						borrow.setDateOfStart(LocalDate.parse(scanner.nextLine(), dateTimeFormatter));
						System.out.print("Date of end [MM/DD/YYYY]:");
						borrow.setDateOfEnd(LocalDate.parse(scanner.nextLine(), dateTimeFormatter));
						System.out.print("Note:");
						borrow.setNote(scanner.nextLine());
						
						System.out.println("1) Ok");
						System.out.println("2) Cancel");

						System.out.print("Command: ");
						command = scanner.nextLine();
						
						switch (command) {
						case "1":
							HibernateDBManager.addBorrow(borrow);
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
						
					} while(!exitFromAddBorrow);
				}
				
				break;
				
			case "6":
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
	
}
