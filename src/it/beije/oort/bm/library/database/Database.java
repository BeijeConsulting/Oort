package it.beije.oort.bm.library.database;

import java.util.List;

public interface Database {
	
	static String USER = "User";
	static String AUTHOR = "Author";
	static String BOOK = "Book";
	static String LOAN = "Loan";
	static String PUBLISHER = "Publisher";
	
	boolean add(Object data);
	
	<T> boolean remove(Class<T> beanType, int id);
	
	<T> boolean update(Class<T> beanType, int id, Object data);
	
	<T> List<T> getAll(Class<T> beanType);
	
	<T> List<T> searchRecord(Class<T> beanType, T data);
	
	
}
