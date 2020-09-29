package it.beije.oort.bm.dbclient.database;

import java.sql.SQLException;
import java.util.List;

import it.beije.oort.bm.dbclient.Contatto;

public abstract class Database {
	private static Database DB;
	
	static final String SELECT = "SELECT * FROM rubrica ";
	static final String WHERE = "WHERE ";
	static final String NAME_VAL = "nome = ? ";
	static final String SURNAME_VAL = "cognome = ? ";
	static final String PHONE_VAL = "telefono = ? ";
	static final String EMAIL_VAL = "email = ? ";
	static final String ID_VAL = "id_rubrica = ? ";
	static final String AND = "AND ";
//	static final String OR = "OR ";
	static final String UPDATE = "UPDATE rubrica SET ";
	static final String DELETE = "DELETE FROM rubrica ";
	static final String INSERT = "INSERT INTO rubrica (cognome, nome, telefono, email) VALUES (?,?,?,?)";
	
	public static Database getDatabase() {
		if(DB == null) DB = new ConcreteHibernateDatabase();
		return DB;
	}
	
	public void disconnect() throws SQLException {
		DB.close();
		DB = null;
	}
	
	public abstract void close() throws SQLException;
	
	public abstract List<Contatto> select(boolean[] selector, String[] vals) throws SQLException;
	
	public abstract boolean insert(Contatto c) throws SQLException;
	
	public abstract boolean delete(int id) throws SQLException;
	
	public abstract boolean update(int id, boolean[] selector, String[] vals) throws SQLException;
	
	public abstract List<Contatto> selectAll() throws SQLException;
}
