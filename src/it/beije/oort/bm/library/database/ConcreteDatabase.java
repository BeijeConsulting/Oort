package it.beije.oort.bm.library.database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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
	public boolean addTo(String table, Object data) {
		switch(table) {
			case USER:
				data = (User)data;
				break;
			case BOOK:
				data = (Book)data;
				break;
			case AUTHOR:
				data = (Author)data;
				break;
			case LOAN:
				data = (Loan)data;
				break;
			case PUBLISHER:
				data = (Publisher)data;
				break;
			default:
				throw new IllegalArgumentException("Table " + table + " not found.");
		}
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
	public boolean removeFrom(String table, int id) {
		Session s = getSession();
		try {
			s.beginTransaction();
			Object o = s.get(table, id);
			s.delete(o);
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
			String t_name = type.getTypeName().toLowerCase();
			switch(t_name) {
			case USER:
				User newU = (User) elem;
				User oldU = (User) data;
				if(!newU.getSurname().equals("")) oldU.setSurname(newU.getSurname());
				if(!newU.getName().equals("")) oldU.setName(newU.getName());
				if(!newU.getFc().equals("")) oldU.setFc(newU.getFc());
				if(!newU.getPhone().equals("")) oldU.setPhone(newU.getPhone());
				if(!newU.getEmail().equals("")) oldU.setEmail(newU.getEmail());
				if(!newU.getAddress().equals("")) oldU.setAddress(newU.getAddress());
				break;
			case BOOK:
				Book newB = (Book) elem;
				Book oldB = (Book) data;
				if(!newB.getTitle().equals("")) oldB.setTitle(newB.getTitle());
				if(!newB.getDescription().equals("")) oldB.setDescription(newB.getDescription());
				if(!(newB.getAuthor() == 0)) oldB.setAuthor(newB.getAuthor());
				if(!(newB.getPublisher() == 0)) oldB.setPublisher(newB.getPublisher());
				if(!newB.getYear().equals("")) oldB.setYear(newB.getYear());
				break;
			case AUTHOR:
				elem = (Author) elem;
				data = (Author) data;
				break;
			case LOAN:
				elem = (Loan) elem;
				data = (Loan) data;
				break;
			case PUBLISHER:
				elem = (Publisher) elem;
				data = (Publisher) data;
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

	@Override
	public <T> List<T> getAll(Class<T> beanType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getRecord(int id, Class<T> beanType) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Session getSession() {
		return factory.openSession();
	}
	
	public static ConcreteDatabase getDatabase() {
		if(istance == null) istance = new ConcreteDatabase();
		return istance;
	}

}
