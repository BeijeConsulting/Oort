package it.beije.oort.bm.rubrica.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import it.beije.oort.bm.rubrica.Contatto;

public class JPADatabase implements Database{
	private static JPADatabase istance;
	private EntityManagerFactory factory;
	
	
	private JPADatabase() {
		factory = Persistence.createEntityManagerFactory("rubrica");
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Contatto> select(boolean[] selector, String[] vals) {
		if(vals.length != selector.length) throw new IllegalArgumentException();
		if(!(selector[0] || selector[1] || selector[2] || selector[3])) return null;
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
		EntityManager s = getEntityManager();
		List<Contatto> ret = null;
		try {
			Query results = s.createQuery(query.toString());
			for(int i = 0; i<vals.length;i++) {
				if(selector[i]) results.setParameter(i+1, vals[i]);
			}
			ret = results.getResultList();
		} catch(RuntimeException re){
			re.printStackTrace();
		} finally {
			s.close();
		}
		return ret;
	}
	
	@Override
	public boolean insert(Contatto c) {
		EntityManager s = getEntityManager();
		try {
			s.getTransaction().begin();;
			s.persist(c);
			s.getTransaction().commit();
		} catch(RuntimeException re) {
			return false;
		}finally {
			s.close();
		}
		return true;
	}
	
	@Override
	public boolean delete(int id) {
		EntityManager s = getEntityManager();
		try {
			Contatto c = s.find(Contatto.class, id);
			if(c != null) {
				s.getTransaction().begin();
				s.remove(c);
				s.getTransaction().commit();
			}
		}catch(RuntimeException re) {
			return false;
		}finally {
			s.close();
		}
		return true;
	}
	
	@Override
	public boolean update(int id, boolean[] selector, String[] vals) {
		if(vals.length != selector.length) throw new IllegalArgumentException();
		EntityManager s = getEntityManager();
		try {
			Contatto c = s.find(Contatto.class, id);
			s.getTransaction().begin();
			if(c != null) {
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
				s.getTransaction().commit();
			} else {
				return false;
			}
		}catch(RuntimeException re) {
			re.printStackTrace();
			return false;
		}finally {
			s.close();
		}
		return true;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Contatto> selectAll() {
		EntityManager s = getEntityManager();
		List<Contatto> ret = null;
		try {
			Query results = s.createQuery(SELECT);
			ret = results.getResultList();
		} catch(RuntimeException re) {
			re.printStackTrace();
		}finally {
			s.close();
		}
		return ret;
	}
	
	private EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
	public static JPADatabase getDatabase() {
		if(istance == null) istance = new JPADatabase();
		return istance;
	}

}
