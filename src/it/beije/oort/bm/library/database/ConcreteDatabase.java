package it.beije.oort.bm.library.database;

import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import it.beije.oort.bm.library.*;

public class ConcreteDatabase implements Database {
	private static ConcreteDatabase istance;
	private EntityManagerFactory factory;
	
	
	private ConcreteDatabase() {
		factory = Persistence.createEntityManagerFactory("library");
	}
	
	@Override
	public boolean add(Object data) {
//		switch(table) {
//			case USER:
//				data = (User)data;
//				break;
//			case BOOK:
//				data = (Book)data;
//				break;
//			case AUTHOR:
//				data = (Author)data;
//				break;
//			case LOAN:
//				data = (Loan)data;
//				break;
//			case PUBLISHER:
//				data = (Publisher)data;
//				break;
//			default:
//				throw new IllegalArgumentException("Table " + table + " not found.");
//		}
		EntityManager s = getEntityManager();
		try {
			s.getTransaction().begin();
			s.persist(data);
			s.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			s.close();
		}
		return true;
	}

	@Override
	public <T> boolean remove(Class<T> table, int id) {
		EntityManager s = getEntityManager();
		try {
			s.getTransaction().begin();
			T elem = s.find(table, id);
			s.remove(elem);
			s.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			s.close();
		}
		return true;
	}

	@Override
	public <T> boolean update(Class<T> type, int id, Object data) {
		EntityManager s = getEntityManager();
		try {
			s.getTransaction().begin();;
			Object elem = s.find(type, id);
			String t_name = type.getSimpleName();
			switch(t_name) {
			case USER:
				User newU = (User) data;
				User oldU = (User) elem;
				if(!newU.getSurname().equals("")) oldU.setSurname(newU.getSurname());
				if(!newU.getName().equals("")) oldU.setName(newU.getName());
				if(!newU.getFc().equals("")) oldU.setFc(newU.getFc());
				if(!newU.getPhone().equals("")) oldU.setPhone(newU.getPhone());
				if(!newU.getEmail().equals("")) oldU.setEmail(newU.getEmail());
				if(!newU.getAddress().equals("")) oldU.setAddress(newU.getAddress());
				break;
			case BOOK:
				Book newB = (Book) data;
				Book oldB = (Book) elem;
				if(!newB.getTitle().equals("")) oldB.setTitle(newB.getTitle());
				if(!newB.getDescription().equals("")) oldB.setDescription(newB.getDescription());
				if(!(newB.getAuthor() == 0)) oldB.setAuthor(newB.getAuthor());
				if(!(newB.getPublisher() == 0)) oldB.setPublisher(newB.getPublisher());
				if(!newB.getYear().equals("")) oldB.setYear(newB.getYear());
				break;
			case AUTHOR:
				Author newA = (Author) data;
				Author oldA = (Author) elem;
				if(!newA.getSurname().equals("")) oldA.setSurname(newA.getSurname());
				if(!newA.getName().equals("")) oldA.setName(newA.getName());
				if(!newA.getDate_of_birth().equals("")) oldA.setDate_of_birth(Date.valueOf(newA.getDate_of_birth()));
				if(!newA.getDate_of_death().equals("")) oldA.setDate_of_death(Date.valueOf(newA.getDate_of_death()));
				if(!newA.getBiography().equals("")) oldA.setBiography(newA.getBiography());
				break;
			case LOAN:
				Loan newL = (Loan) data;
				Loan oldL = (Loan) elem;
				if(newL.getUser() != 0) oldL.setUser(newL.getUser());
				if(newL.getBook() != 0) oldL.setBook(newL.getBook());
				if(!newL.getStart_date().equals("")) oldL.setStart_date(Date.valueOf(newL.getStart_date()));
				if(!newL.getEnd_date().equals("")) oldL.setEnd_date(Date.valueOf(newL.getEnd_date()));
				if(!newL.getNotes().equals("")) oldL.setNotes(newL.getNotes());
				break;
			case PUBLISHER:
				Publisher newP = (Publisher) data;
				Publisher oldP = (Publisher) elem;
				if(!newP.getName().equals("")) oldP.setName(newP.getName());
				if(!newP.getDescription().equals("")) oldP.setDescription(newP.getDescription());
				break;
			default:
				throw new IllegalArgumentException("");
			}
			s.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			s.close();
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getAll(Class<T> beanType) {
		String query = "SELECT x FROM " + beanType.getSimpleName() + " AS x";
		List<T> ret;
		EntityManager s = getEntityManager();
		try {
			Query results = s.createQuery(query);
			ret = results.getResultList();
		}catch(RuntimeException re) {
			re.printStackTrace();
			return null;
		}finally {
			s.close();
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> searchRecord(Class<T> beanType, T data) {
		String type = data.getClass().getSimpleName();
		EntityManager s = getEntityManager();
		List<T> ret = null;
		StringBuilder query = new StringBuilder();
		query.append("SELECT x FROM ").append(type).append(" AS x WHERE ");
		boolean requireAnd = false;
		switch(type) {
		case USER:
			User user = (User) data;
			if(!user.getSurname().equals("")) {
				query.append("surname = ").append("\'").append(user.getSurname()).append("\'").append(" ");
				requireAnd = true;
			}
			if(!user.getName().equals("")) {
				if(requireAnd) query.append("AND ");
				query.append("name = ").append("\'").append(user.getName()).append("\'").append(" ");
				requireAnd = true;
			}
			if(!user.getFc().equals("")) {
				if(requireAnd) query.append("AND ");
				query.append("fc = ").append("\'").append(user.getFc()).append("\'").append(" ");
				requireAnd = true;
			}
			if(!user.getAddress().equals("")) {
				if(requireAnd) query.append("AND ");
				query.append("address = ").append("\'").append(user.getAddress()).append("\'").append(" ");
				requireAnd = true;
			}
			if(!user.getPhone().equals("")) {
				if(requireAnd) query.append("AND ");
				query.append("phone = ").append("\'").append(user.getPhone()).append("\'").append(" ");
				requireAnd = true;
			}
			if(!user.getEmail().equals("")) {
				if(requireAnd) query.append("AND ");
				query.append("email = ").append("\'").append(user.getEmail()).append("\'").append(" ");
			}
			break;
		case BOOK:
			Book book = (Book) data;
			if(!book.getTitle().equals("")) {
				query.append("title = ").append("\'").append(book.getTitle()).append("\'").append(" ");
				requireAnd = true;
			}
			if(book.getPublisher() != 0) {
				if(requireAnd) query.append("AND ");
				query.append("publisher = ").append(book.getPublisher()).append(" ");
				requireAnd = true;
			}
			if(book.getAuthor() != 0) {
				if(requireAnd) query.append("AND ");
				query.append("author = ").append(book.getAuthor()).append(" ");
				requireAnd = true;
			}
			if(!book.getYear().equals("")) {
				if(requireAnd) query.append("AND ");
				query.append("year = ").append("\'").append(book.getYear()).append("\'").append(" ");
			}			
			break;
		case AUTHOR:
			Author author = (Author) data;
			if(!author.getSurname().equals("")) {
				query.append("surname = ").append("\'").append(author.getSurname()).append("\'").append(" ");
				requireAnd = true;
			}
			if(!author.getName().equals("")) {
				if(requireAnd) query.append("AND ");
				query.append("name = ").append("\'").append(author.getName()).append("\'").append(" ");
				requireAnd = true;
			}
			if(!author.getDate_of_birth().equals("")) {
				if(requireAnd) query.append("AND ");
				query.append("date_of_birth = ").append("\'").append(author.getDate_of_birth()).append("\'").append(" ");
				requireAnd = true;
			}
			if(!author.getDate_of_death().equals("")) {
				if(requireAnd) query.append("AND ");
				query.append("date_of_death = ").append("\'").append(author.getDate_of_death()).append("\'").append(" ");
			}
			break;
		case LOAN:
			Loan loan = (Loan) data;
			if(loan.getBook() != 0) {
				query.append("book = ").append(loan.getBook()).append(" ");
				requireAnd = true;
			}
			if(loan.getUser() != 0) {
				if(requireAnd) query.append("AND ");
				query.append("user = ").append(loan.getUser()).append(" ");
				requireAnd = true;
			}
			if(loan.getStart_date() != null) {
				if(requireAnd) query.append("AND ");
				query.append("start_date = ").append("\'").append(loan.getStart_date().toString()).append("\'").append(" ");
				requireAnd = true;
			}
			if(loan.getEnd_date() != null) {
				if(requireAnd) query.append("AND ");
				query.append("end_date = ").append("\'").append(loan.getEnd_date()).append("\'").append(" ");
			}	
			break;
		case PUBLISHER:
			Publisher p = (Publisher) data;
			if(!p.getName().equals("")) {
				query.append("name = ").append("\'").append(p.getName()).append("\'").append(" ");
			}
			break;
		default:
			throw new IllegalArgumentException("Something went really wrong man.");
		}
		Query results = s.createQuery(query.toString());
		ret = results.getResultList();
		s.close();
		return ret;
	}
	
	private EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
	public static ConcreteDatabase getDatabase() {
		if(istance == null) istance = new ConcreteDatabase();
		return istance;
	}
}
