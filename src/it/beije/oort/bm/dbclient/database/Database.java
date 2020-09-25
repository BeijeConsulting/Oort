package it.beije.oort.bm.dbclient.database;

import java.sql.SQLException;
import java.util.List;

import it.beije.oort.bm.dbclient.Contatto;

public abstract class Database {
	private static Database DB;
	
	public static Database getDatabase() {
		if(DB == null) DB = new ConcreteDatabase();
		return DB;
	}
	
	public abstract List<Contatto> select(boolean[] selector, String[] vals) throws SQLException;
	
	public abstract boolean insert(Contatto c) throws SQLException;
	
	public abstract boolean delete(int id) throws SQLException;
	
	public abstract boolean update(int id, boolean[] selector, String[] vals) throws SQLException;
	
	public abstract List<Contatto> selectAll() throws SQLException;
}
