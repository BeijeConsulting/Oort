package it.beije.oort.bassanelli.exercises.phonebookgenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "Beije02";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET";

	public static final String TABLE_PHONEBOOK = "phonebook";
	public static final String FIELD_ID = "id";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_SURNAME = "surname";
	public static final String FIELD_MOBILE = "mobile";
	public static final String FIELD_EMAIL = "email";

	public static Connection getMySqlConnection(String url, String user, String password)
			throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection connection = DriverManager.getConnection(url, user, password);

		return connection;
	}

	public static void writeIntoDatabase(List<Contact> list) {

		Connection connection = null;
		PreparedStatement ps = null;

		try {

			connection = getMySqlConnection(DB_URL, DB_USER, DB_PASSWORD);

			for (Contact contact : list) {

				StringBuilder builder = new StringBuilder("INSERT INTO ").append(TABLE_PHONEBOOK).append("(")
						.append(FIELD_NAME).append(", ").append(FIELD_SURNAME).append(", ").append(FIELD_MOBILE)
						.append(", ").append(FIELD_EMAIL).append(")").append(" VALUES ").append("(?, ?, ?, ?)");

				// System.out.println(builder.toString());
				ps = connection.prepareStatement(builder.toString());

				ps.setString(1, contact.getName());
				ps.setString(2, contact.getSurname());
				ps.setString(3, contact.getMobile());
				ps.setString(4, contact.getEmail());

				ps.execute();

				// System.out.println("Insert contact: " + ps.getUpdateCount());
			}

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cfnException) {
			cfnException.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static List<Contact> readFromDatabase() {

		List<Contact> list = new ArrayList<Contact>();

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);

			StringBuilder builder = new StringBuilder("SELECT * FROM ").append(TABLE_PHONEBOOK);

			ps = connection.prepareStatement(builder.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				Contact contact = new Contact();
				contact.setId(rs.getInt(FIELD_ID));
				contact.setName(rs.getString(FIELD_NAME));
				contact.setSurname(rs.getString(FIELD_SURNAME));
				contact.setMobile(rs.getString(FIELD_MOBILE));
				contact.setEmail(rs.getString(FIELD_EMAIL));
				list.add(contact);
			}

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cfnException) {
			cfnException.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public static List<Contact> searchContact(String field, String word) {

		List<Contact> list = new ArrayList<Contact>();

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);

			switch (field.toLowerCase()) {
			default:
			case "name":
				field = FIELD_NAME;
				break;
			case "surname":
				field = FIELD_SURNAME;
				break;
			case "mobile":
				field = FIELD_MOBILE;
				break;
			case "email":
				field = FIELD_EMAIL;
				break;
			}

			StringBuilder builder = new StringBuilder("SELECT * FROM ").append(TABLE_PHONEBOOK).append(" WHERE ")
					.append(field).append(" LIKE ?");

			ps = connection.prepareStatement(builder.toString());

			ps.setString(1, word + "%");

			rs = ps.executeQuery();

			while (rs.next()) {
				Contact contact = new Contact();
				contact.setId(rs.getInt(FIELD_ID));
				contact.setName(rs.getString(FIELD_NAME));
				contact.setSurname(rs.getString(FIELD_SURNAME));
				contact.setMobile(rs.getString(FIELD_MOBILE));
				contact.setEmail(rs.getString(FIELD_EMAIL));
				list.add(contact);
			}

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cfnException) {
			cfnException.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public static void addContact(Contact contact) {
		Connection connection = null;
		PreparedStatement ps = null;

		try {

			connection = getMySqlConnection(DB_URL, DB_USER, DB_PASSWORD);

			StringBuilder builder = new StringBuilder("INSERT INTO ").append(TABLE_PHONEBOOK).append("(")
					.append(FIELD_NAME).append(", ").append(FIELD_SURNAME).append(", ").append(FIELD_MOBILE)
					.append(", ").append(FIELD_EMAIL).append(")").append(" VALUES ").append("(?, ?, ?, ?)");

			// System.out.println(builder.toString());
			ps = connection.prepareStatement(builder.toString());

			ps.setString(1, contact.getName());
			ps.setString(2, contact.getSurname());
			ps.setString(3, contact.getMobile());
			ps.setString(4, contact.getEmail());

			ps.execute();

			// System.out.println("Insert contact: " + ps.getUpdateCount());

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cfnException) {
			cfnException.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static Contact getContactById(Integer id) {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Contact contact = null;

		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);

			StringBuilder builder = new StringBuilder("SELECT * FROM ").append(TABLE_PHONEBOOK).append(" WHERE ")
					.append("ID").append(" = ?");

			ps = connection.prepareStatement(builder.toString());
			ps.setString(1, id.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				contact = new Contact();
				contact.setId(rs.getInt(FIELD_ID));
				contact.setName(rs.getString(FIELD_NAME));
				contact.setSurname(rs.getString(FIELD_SURNAME));
				contact.setMobile(rs.getString(FIELD_MOBILE));
				contact.setEmail(rs.getString(FIELD_EMAIL));
			}

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cfnException) {
			cfnException.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return contact;
	}

	public static void editContact(Contact contact) {
		Connection connection = null;
		PreparedStatement ps = null;

		try {

			connection = getMySqlConnection(DB_URL, DB_USER, DB_PASSWORD);

			StringBuilder builder = new StringBuilder("UPDATE ").append(TABLE_PHONEBOOK).append(" SET ")
					.append(FIELD_NAME).append(" = ?,").append(FIELD_SURNAME).append(" = ?,").append(FIELD_MOBILE)
					.append(" = ?,").append(FIELD_EMAIL).append(" = ?").append(" WHERE ").append(FIELD_ID)
					.append(" = ?");

			// System.out.println(builder.toString());
			ps = connection.prepareStatement(builder.toString());

			ps.setString(1, contact.getName());
			ps.setString(2, contact.getSurname());
			ps.setString(3, contact.getMobile());
			ps.setString(4, contact.getEmail());
			ps.setString(5, contact.getId().toString());

			ps.execute();

			// System.out.println("Insert contact: " + ps.getUpdateCount());

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cfnException) {
			cfnException.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void deleteById(Integer id) {

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);

			StringBuilder builder = new StringBuilder("DELETE FROM ").append(TABLE_PHONEBOOK).append(" WHERE ")
					.append("ID").append(" = ?");

			ps = connection.prepareStatement(builder.toString());
			ps.setString(1, id.toString());

			ps.execute();


		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cfnException) {
			cfnException.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
