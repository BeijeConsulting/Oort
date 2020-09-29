package it.beije.oort.bm.library.database;

import java.util.List;

public interface Database {
	
	static String USER = "user";
	static String AUTHOR = "author";
	static String BOOK = "book";
	static String LOAN = "loan";
	static String PUBLISHER = "publisher";
	
	boolean addTo(String table, Object data);
	
	boolean removeFrom(String table, int id);
	
	<T> boolean update(Class<T> beanType, int id, Object data);
	
	<T> List<T> getAll(Class<T> beanType);
	
	<T> T getRecord(int id, Class<T> beanType);
	
	
}
