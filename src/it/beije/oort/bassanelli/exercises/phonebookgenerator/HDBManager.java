package it.beije.oort.bassanelli.exercises.phonebookgenerator;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HDBManager {

	public static final String TABLE_PHONEBOOK = "phonebook";
	public static final String FIELD_ID = "id";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_SURNAME = "surname";
	public static final String FIELD_MOBILE = "mobile";
	public static final String FIELD_EMAIL = "email";

	public static List<Contact> readFromDatabase() {

		List<Contact> list = new ArrayList<Contact>();

		Configuration configuration = new Configuration();
		configuration = configuration.configure().addAnnotatedClass(Contact.class);

		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();

		StringBuilder builder = new StringBuilder("FROM ").append(Contact.class.getSimpleName()); // hql

		// System.out.println(Contact.class.getSimpleName());

		Query<Contact> query = session.createQuery(builder.toString());

		for (Contact contact : query.list()) {

			/*
			 * System.out.println("Id : " + contact.getId()); System.out.println("Name : " +
			 * contact.getName()); System.out.println("Surname : " + contact.getSurname());
			 * System.out.println("Mobile : " + contact.getMobile());
			 * System.out.println("Email : " + contact.getEmail());
			 */

			list.add(contact);

		}

		return list;
	}

}
