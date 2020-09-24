package it.beije.oort.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBManager {

	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "Beije05";
	
//	public static final String DB_URL = "jdbc:mysql://localhost:3306/oort?serverTimezone=CET";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET";
	
	public static Connection getMySqlConnection(String url, String user, String password) 
			throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection connection = DriverManager.getConnection(url, user, password);
		
		return connection;
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Connection connection = getMySqlConnection(DB_URL, DB_USER, DB_PASSWORD);
		System.out.println("connection is open? " + !connection.isClosed());
	}

}
