package it.beije.oort.bm.library.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import it.beije.oort.bm.library.*;
import it.beije.oort.bm.library.database.*;

public class LibraryClient {
	
	private static Database db;
	private static BufferedReader in;
	
	public static void main(String[] args) throws IOException {
		db = ConcreteDatabase.getDatabase();
		String command;
		in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome to Brando's Library");
		while(true) {
			System.out.println();
			System.out.println("1 - Visualize records");
			System.out.println("2 - Search record");
			System.out.println("3 - Add record");
			System.out.println("4 - Update record");
			System.out.println("5 - Delete record");
			System.out.println("0 - Exit");
			command = in.readLine();
			switch(command) {
				case "1":
					visualizeRecords();
					break;
				case "2":
					searchRecord();
					break;
				case "3":
					break;
				case "4":
					break;
				case "5":
					break;
				case "0":
					System.out.println("See ya!");
					System.exit(0);
					break;
				default:
					System.out.println("What do you mean?");
			}
		}
	}

	private static void searchRecord() throws IOException {
		System.out.println("Which type of records do you want to search for?");
		System.out.println();
		System.out.println("1 - User record");
		System.out.println("2 - Book record");
		System.out.println("3 - Loan record");
		System.out.println("4 - Author record");
		System.out.println("5 - Pubisher record");
		System.out.println("0 - Back");
		String command;
		boolean ok;
		do{ 
			ok = true;
			command = in.readLine();
			switch(command) {
			case "1":
				System.out.println(searchByParameters(User.class));
				break;
			case "2":
				System.out.println(searchByParameters(Book.class));
				break;
			case "3":
				System.out.println(searchByParameters(Loan.class));
				break;
			case "4":
				System.out.println(searchByParameters(Author.class));
				break;
			case "5":
				System.out.println(searchByParameters(Publisher.class));
				break;
			case "0":
				break;
			default:
				System.out.println("What do you mean?");
				ok = false;
			}
		}while(!ok);
		
	}

	private static void visualizeRecords() throws IOException {
		System.out.println("Which type of records do you want to see?");
		System.out.println();
		System.out.println("1 - User records");
		System.out.println("2 - Book records");
		System.out.println("3 - Loan records");
		System.out.println("4 - Author records");
		System.out.println("5 - Pubisher records");
		System.out.println("0 - Back");
		String command;
		boolean ok;
		do{ 
			ok = true;
			command = in.readLine();
			switch(command) {
			case "1":
				pagedPrint(db.getAll(User.class));
				break;
			case "2":
				pagedPrint(db.getAll(Book.class));
				break;
			case "3":
				pagedPrint(db.getAll(Loan.class));
				break;
			case "4":
				pagedPrint(db.getAll(Author.class));
				break;
			case "5":
				pagedPrint(db.getAll(Publisher.class));
				break;
			case "0":
				break;
			default:
				System.out.println("What do you mean?");
				ok = false;
			}
		}while(!ok);
	}

	@SuppressWarnings("unchecked")
	private static <T> T searchByParameters(Class<T> beanType) throws IOException {
		T elem = null;
		String param;
		int index;
		switch(beanType.getSimpleName()) {
		case Database.USER:
			User u = new User();
			System.out.println("Insert surname");
			u.setSurname(in.readLine());
			System.out.println("Insert name");
			u.setName(in.readLine());
			System.out.println("Insert fiscal code");
			u.setFc(in.readLine());
			System.out.println("Insert address");
			u.setAddress(in.readLine());
			System.out.println("Insert email");
			u.setEmail(in.readLine());
			System.out.println("Insert phone");
			u.setPhone(in.readLine());
			elem = (T)db.searchRecord(User.class, u);
			break;
		case Database.BOOK:
			Book b = new Book();
			System.out.println("Insert title");
			b.setTitle(in.readLine());
			System.out.println("Insert year");
			b.setYear(in.readLine());
			System.out.println("Select an author");
			List<Author> authors = db.getAll(Author.class);
			for(int i = 1; i<=authors.size(); i++) {
				System.out.println(i + " - " + authors.get(i-1).getName() + " " + authors.get(i-1).getSurname());
			}
			param = in.readLine();
			index = Integer.parseInt(param);
			if(index > 0 && index <= authors.size()) {
				b.setAuthor(index);
			}else {
				//dopo
			}
			System.out.println("Select a publisher");
			List<Publisher> publishers = db.getAll(Publisher.class);
			for(int i = 1; i<=publishers.size(); i++) {
				System.out.println(i + " - " + publishers.get(i-1).getName());
			}
			param = in.readLine();
			index = Integer.parseInt(param);
			if(index > 0 && index <= publishers.size()) {
				b.setPublisher(index);
			}else {
				//dopo
			}
			elem = (T)db.searchRecord(Book.class, b);
			break;
		case Database.AUTHOR:
			Author a = new Author();
			System.out.println("Insert surname");
			a.setSurname(in.readLine());
			System.out.println("Insert name");
			a.setName(in.readLine());
			System.out.println("Insert date of birth (YYYY-MM-dd)");
			param = in.readLine();
//			if(!param.equals("")) {
//				LocalDateTime tmpDate = LocalDateTime.parse(param, DateTimeFormatter.ofPattern("YYYY-MM-dd"));
//				Date dob = new Date(tmpDate.toInstant(ZoneOffset.UTC).toEpochMilli());
//				a.setDate_of_birth(dob);
//			}else {
//				a.setDate_of_birth(null);
//			}
			System.out.println("Insert date of death (YYYY-MM-dd)");
			param = in.readLine();
//			if(!param.equals("")) {
//				LocalDateTime tmpDate = LocalDateTime.parse(param, DateTimeFormatter.ofPattern("YYYY-MM-dd"));
//				Date dod = new Date(tmpDate.toInstant(ZoneOffset.UTC).toEpochMilli());
//				a.setDate_of_death(dod);
//			}else {
//				a.setDate_of_death(null);
//			}
			elem = (T)db.searchRecord(Author.class, a);
			break;
		case Database.LOAN:
			Loan l = new Loan();
			elem = (T)db.searchRecord(Loan.class, l);
			break;
		case Database.PUBLISHER:
			Publisher p = new Publisher();
			elem = (T)db.searchRecord(Publisher.class, p);
			break;
		default:
			throw new IllegalArgumentException("Something went really wrong man.");
		}
		return elem;
	}
	
	private static <T> void pagedPrint(List<T> all) throws IOException {
		if(all.size() == 0) {
			System.out.println("Empty.");
			return;
		}
		int pages = all.size()/10 + (all.size()%10 > 0 ? 1 : 0);
		int page = 1;
		int start = 0;
		int end = all.size() < 10 ? all.size() : 10;
		String command;
		boolean previous, next;
		do {
			previous = page > 1;
			next = page < pages;
			for(int i = start; i<end; i++) {
				System.out.println(all.get(i));
			}
			System.out.println();
			System.out.println("Page " + page + "      " + start + " to " + end + " of " + all.size());
			System.out.println();
			if(pages<= 1) return;
			System.out.println((previous ? "1 - previous    " : "") + (next ? "2 - next    " : "") + "0 - back");
			command = in.readLine();
			switch(command) {
				case "0":
					return;
				case "2":
					if(next) {
						page++;
						start = end;
						end += 10;
						if(end > all.size()) {
							end = all.size();
						}
					}	
					break;
				case "1":
					if(previous) {
						page--;
						start -= 10;
						end = start + 10;
					}
					break;
				default:
					System.out.println("What do you mean?");
			}
		}while(true);
		
	}
}
