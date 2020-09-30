package it.beije.oort.bm.library.database;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import it.beije.oort.bm.library.*;

public class ConcreteDatabase implements Database {
	private static ConcreteDatabase istance;
	private SessionFactory factory;
	
	
	private ConcreteDatabase() {
		factory = new Configuration().configure("hibernate-library.cfg.xml")
				.addAnnotatedClass(Author.class)
				.addAnnotatedClass(Book.class)
				.addAnnotatedClass(Loan.class)
				.addAnnotatedClass(Publisher.class)
				.addAnnotatedClass(User.class)
				.buildSessionFactory();
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
		Session s = getSession();
		try {
			s.beginTransaction();
			s.save(data);
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
		Session s = getSession();
		try {
			s.beginTransaction();
			T elem = s.get(table, id);
			s.delete(elem);
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
		Session s = getSession();
		try {
			s.beginTransaction();
			Object elem = s.get(type, id);
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
				if(newA.getDate_of_birth() != null) oldA.setDate_of_birth(newA.getDate_of_birth());
				if(newA.getDate_of_death() != null) oldA.setDate_of_death(newA.getDate_of_death());
				if(!newA.getBiography().equals("")) oldA.setBiography(newA.getBiography());
				break;
			case LOAN:
				Loan newL = (Loan) data;
				Loan oldL = (Loan) elem;
				if(newL.getUser() != 0) oldL.setUser(newL.getUser());
				if(newL.getBook() != 0) oldL.setBook(newL.getBook());
				if(newL.getStart_date() != null) oldL.setStart_date(newL.getStart_date());
				if(newL.getEnd_date() != null) oldL.setEnd_date(newL.getEnd_date());
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
			s.save(elem);
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
		Session s = getSession();
		try {
			Query<T> results = s.createQuery(query);
			ret = results.getResultList();
		}catch(RuntimeException re) {
			re.printStackTrace();
			return null;
		}finally {
			s.close();
		}
		return ret;
	}

	@Override
	public <T> T getRecord(int id, Class<T> beanType) {
		Session s = getSession();
		T elem = null;
		try {
			elem = s.find(beanType, id);
		} catch(RuntimeException re){
			re.printStackTrace();
		}finally {
			s.close();
		}
		return elem;
	}
	
	private Session getSession() {
		return factory.openSession();
	}
	
	public static ConcreteDatabase getDatabase() {
		if(istance == null) istance = new ConcreteDatabase();
		return istance;
	}
}
