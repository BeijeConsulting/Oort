package it.beije.oort.bassanelli.exercises.phonebookgenerator;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.Transaction;

public class HDBManager {

	public static final String TABLE_PHONEBOOK = "phonebook";
	public static final String FIELD_PHONEBOOK_ID = "id";
	public static final String FIELD_PHONEBOOK_NAME = "name";
	public static final String FIELD_PHONEBOOK_SURNAME = "surname";
	public static final String FIELD_PHONEBOOK_MOBILE = "mobile";
	public static final String FIELD_PHONEBOOK_EMAIL = "email";

	public static final String CLASS_NAME_CONTACT = Contact.class.getSimpleName();

	public static List<Contact> readRecordsPhonebook() {

		List<Contact> list = new ArrayList<Contact>();

		Session session = HibernateSessionManager.openSession();

		// HQL query string
		StringBuilder builder = new StringBuilder("FROM ").append(CLASS_NAME_CONTACT);

		Query<Contact> query = session.createQuery(builder.toString());

		for (Contact contact : query.list()) {

//			 System.out.println("Id : " + contact.getId()); System.out.println("Name : " +
//			 contact.getName()); System.out.println("Surname : " + contact.getSurname());
//			 System.out.println("Mobile : " + contact.getMobile());
//			 System.out.println("Email : " + contact.getEmail());

			list.add(contact);
		}

		session.close();

		return list;
	}
	
	public static void writeRecordsPhonebook(List<Contact> list) {

		Session session = HibernateSessionManager.openSession();
		
		Transaction transaction = session.beginTransaction();

		for (Contact contact : list) {
			session.save(contact);
		}

		transaction.commit();
		
		session.close();

	}

	public static List<Contact> searchByFilterPhonebook(String field, String word) {

		List<Contact> list = new ArrayList<Contact>();

		Session session = HibernateSessionManager.openSession();

		switch (field.toLowerCase()) {
		default:
		case "name":
			field = FIELD_PHONEBOOK_NAME;
			break;
		case "surname":
			field = FIELD_PHONEBOOK_SURNAME;
			break;
		case "mobile":
			field = FIELD_PHONEBOOK_MOBILE;
			break;
		case "email":
			field = FIELD_PHONEBOOK_EMAIL;
			break;
		}

		// HQL query string
		StringBuilder builder = new StringBuilder("FROM ").append(CLASS_NAME_CONTACT).append(" WHERE ").append(field)
				.append(" LIKE :word");

		Query<Contact> query = session.createQuery(builder.toString());

		list = query.setParameter("word", word + "%").list();

		session.close();

		return list;
	}

	public static void addRecordPhonebook(Contact contact) {

		Session session = HibernateSessionManager.openSession();

		Transaction transaction = session.beginTransaction();

		session.save(contact);

		transaction.commit();

		session.close();

	}
	
	public static Contact searchByIdPhonebook(String id) {
		return searchByIdPhonebook(Integer.parseInt(id));
	}

	public static Contact searchByIdPhonebook(int id) {

		Session session = HibernateSessionManager.openSession();

//		StringBuilder builder = new StringBuilder("FROM ").append(CLASS_NAME_CONTACT).append(" WHERE ")
//				.append(FIELD_PHONEBOOK_ID).append(" = :id");
//		Query<Contact> query = session.createQuery(builder.toString());
//		Contact contact = query.setParameter("id", id).list().get(0);
		
		Contact contact = session.get(Contact.class, id);

		session.close();

		return contact;

	}
	
	public static void updateRecordPhonebook(Contact contact, String id) {
		updateRecordPhonebook(contact, Integer.parseInt(id));
	}

	public static void updateRecordPhonebook(Contact contact, int id) {

		Session session = HibernateSessionManager.openSession();

		Transaction transaction = session.beginTransaction();

		Contact temp = session.get(Contact.class, id);

		temp.setName(contact.getName());
		temp.setSurname(contact.getSurname());
		temp.setMobile(contact.getMobile());
		temp.setEmail(contact.getEmail());

		session.save(temp);

		transaction.commit();

		session.close();

	}
	
	public static void deleteRecordPhonebook(String id) {
		deleteRecordPhonebook(Integer.parseInt(id));
	}

	public static void deleteRecordPhonebook(int id) {

		Session session = HibernateSessionManager.openSession();

		Transaction transaction = session.beginTransaction();

		Contact temp = session.get(Contact.class, id);

		session.delete(temp);
		
		transaction.commit();

		session.close();

	}

}
