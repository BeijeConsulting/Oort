package it.beije.oort.db;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExportDB {
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "simone9810";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET";
	
	public static Connection getMySqlConnection(String url, String user, String password) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(url, user, password);
		return connection;
	}
	
	public static void preparedSelect() throws IOException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		FileWriter fileWriter = new FileWriter("/tempDB/export_from_DB.csv");
		
		try {
			connection = ExportDB.getMySqlConnection(ExportDB.DB_URL, ExportDB.DB_USER, ExportDB.DB_PASSWORD);
			System.out.println("connection is open? " + !connection.isClosed());
			ps = connection.prepareStatement("SELECT * FROM contatti");
			rs = ps.executeQuery();
			fileWriter.append("NOME;COGNOME;TELEFONO;EMAIL"+ "\n");
			while (rs.next()) {
				fileWriter.append(rs.getString(2)).append(";").append(rs.getString(3)).append(";")
				.append(rs.getString(4)).append(";").append(rs.getString(5)).append("\n");
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				fileWriter.flush();
				fileWriter.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		Connection connection = getMySqlConnection(DB_URL, DB_USER, DB_PASSWORD);
		preparedSelect();
	}
}
