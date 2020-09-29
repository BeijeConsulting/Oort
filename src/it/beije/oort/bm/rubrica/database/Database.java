package it.beije.oort.bm.rubrica.database;

import java.util.List;

import it.beije.oort.bm.rubrica.Contatto;

public interface Database {
	
	static final String SELECT = "SELECT c FROM Contatto as c ";
	static final String WHERE = "WHERE ";
	static final String SURNAME_VAL = "cognome = ?1 ";
	static final String NAME_VAL = "nome = ?2 ";
	static final String PHONE_VAL = "telefono = ?3 ";
	static final String EMAIL_VAL = "email = ?4 ";
	static final String AND = "AND ";
	
	public abstract List<Contatto> select(boolean[] selector, String[] vals);
	
	public abstract boolean insert(Contatto c);
	
	public abstract boolean delete(int id);
	
	public abstract boolean update(int id, boolean[] selector, String[] vals);
	
	public abstract List<Contatto> selectAll();
}
