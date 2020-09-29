package it.beije.oort.bru.db;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import it.beije.oort.files.Contatto;

public class HDBUtilities {
	
	public static void exportDB(List<Contatto> contacts) {
		Session session = SessionFactorySingleton.openSession();
		String hql = "FROM Contatto";
		Query<Contatto> query = session.createQuery(hql);
		for (Contatto c : query.list()) {
			contacts.add(c);
		}
		session.close();
	}
	
	public static Contatto exportContact(List<Contatto> contacts, int id) {
		Contatto contact = new Contatto();
		Session session = SessionFactorySingleton.openSession();
		String hql = "SELECT c FROM Contatto as c WHERE id = " + id;
		Query<Contatto> query = session.createQuery(hql);
		contact = query.list().get(0);
		return contact;
	}
	
	public static List<Contatto> searchContact(String column, String value) {
		Session session = SessionFactorySingleton.openSession();
		List<Contatto> contacts = new ArrayList<Contatto>();
		String hql = "SELECT c FROM Contatto as c WHERE " + column + " = '" + value + "'";
		Query<Contatto> query = session.createQuery(hql);
		for (Contatto c : query.list()) {
			contacts.add(c);
		}
		session.close();
		return contacts;
	}
	
	public static void insertRecord(String cognome, String nome, String telefono, String email) {
		Session session = SessionFactorySingleton.openSession();
		Transaction transaction = session.beginTransaction();
		Contatto contact = new Contatto();
		contact.setCognome(cognome);
		contact.setNome(nome);
		contact.setTelefono(telefono);
		contact.setEmail(email);
		session.save(contact);
		transaction.commit();
		session.close();
	}
	
	public static void editContact(String paramMod, String value, int id) {
		Session session = SessionFactorySingleton.openSession();
		Transaction transaction = session.beginTransaction();
		Contatto contatto = session.get(Contatto.class, id);
		switch (paramMod) {
		case "nome":
			contatto.setNome(value);
			break;
		case "cognome":
			contatto.setCognome(value);
			break;
		case "telefono":
			contatto.setTelefono(value);
			break;
		case "email":	
			contatto.setEmail(value);
			break;
		}
		session.save(contatto);
		transaction.commit();
		session.close();
	}
	
	public static void deleteContact(int id) {
		Session session = SessionFactorySingleton.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "DELETE FROM Contatto WHERE id = " + id;
		Query query = session.createQuery(hql);
		query.executeUpdate();
		transaction.commit();
		session.close();
	}
}
