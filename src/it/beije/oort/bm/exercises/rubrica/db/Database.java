package it.beije.oort.bm.exercises.rubrica.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	private Connection connection;
	
	private static Database db;
	
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "root";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Database() {
		
	}
	
	public Connection getConnection() throws SQLException {
		if(connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		}
		return connection;
	}
	
	public void closeConnection() throws SQLException {
		connection.close();
	}
	
	@Override
	public void finalize() {
		try {
			connection.close();
		} catch (SQLException e) {}
	}
	
	
	public static Database getDatabase() {
		if(db == null) {
			db = new Database();
		}
		return db;
	}
}
