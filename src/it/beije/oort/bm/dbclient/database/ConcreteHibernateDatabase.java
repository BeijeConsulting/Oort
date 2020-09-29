package it.beije.oort.bm.dbclient.database;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import it.beije.oort.bm.dbclient.Contatto;

public class ConcreteHibernateDatabase extends Database {
	private Session session;
	
	private static final String SELECT = "SELECT c FROM Contatto as c ";
	private static final String NAME_VAL = "nome = ?2 ";
	private static final String SURNAME_VAL = "cognome = ?1 ";
	private static final String PHONE_VAL = "telefono = ?3 ";
	private static final String EMAIL_VAL = "email = ?4 ";
	
	ConcreteHibernateDatabase() {
		session = new Configuration().configure().addAnnotatedClass(Contatto.class).buildSessionFactory().openSession();
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Contatto> select(boolean[] selector, String[] vals) throws SQLException {
		if(vals.length != selector.length) throw new IllegalArgumentException();
		StringBuilder query = new StringBuilder();
		boolean requireAnd = false;
		query.append(SELECT).append(WHERE);
		if(selector[0]) {
			query.append(SURNAME_VAL);
			requireAnd = true;
		}
		if(selector[1]) {
			if(requireAnd) query.append(AND);
			query.append(NAME_VAL);
			requireAnd = true;
		}
		if(selector[2]) {
			if(requireAnd) query.append(AND);
			query.append(PHONE_VAL);
			requireAnd = true;
		}
		if(selector[3]) {
			if(requireAnd) query.append(AND);
			query.append(EMAIL_VAL);
		}

		Query<Contatto> results = session.createQuery(query.toString());
		for(int i = 0; i<vals.length;i++) {
			if(selector[i]) results.setParameter(i+1, vals[i]);
		}
		
		return results.getResultList();
	}

	@Override
	public boolean insert(Contatto c) throws SQLException {
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();
		return true;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		Contatto c = session.find(Contatto.class, id);
		if(c != null) {
			session.beginTransaction();
			session.delete(c);
			session.getTransaction().commit();
		}
		return true;
	}

	@Override
	public boolean update(int id, boolean[] selector, String[] vals) throws SQLException {
		if(vals.length != selector.length) throw new IllegalArgumentException();
		Contatto c = session.find(Contatto.class, id);
		if(selector[0]) {
			c.setCognome(vals[0]);
		}
		if(selector[1]) {
			c.setNome(vals[1]);
		}
		if(selector[2]) {
			c.setTelefono(vals[2]);
		}
		if(selector[3]) {
			c.setEmail(vals[3]);
		}
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contatto> selectAll() throws SQLException {
		Query<Contatto> results = session.createQuery(SELECT);
		return results.getResultList();
	}

	@Override
	public void close() throws SQLException {
		session.close();
	}

}
