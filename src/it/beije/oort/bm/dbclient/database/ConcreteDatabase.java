package it.beije.oort.bm.dbclient.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import it.beije.oort.bm.dbclient.Contatto;

class ConcreteDatabase extends Database {

	private Connection connection;
	
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET";
	
	private static final String SELECT = "SELECT * FROM rubrica ";
	private static final String WHERE = "WHERE ";
	private static final String NAME_VAL = "nome = ? ";
	private static final String SURNAME_VAL = "cognome = ? ";
	private static final String PHONE_VAL = "telefono = ? ";
	private static final String EMAIL_VAL = "email = ? ";
	private static final String ID_VAL = "id_rubrica = ? ";
	private static final String AND = "AND ";
//	private static final String OR = "OR ";
	private static final String UPDATE = "UPDATE rubrica SET ";
	private static final String DELETE = "DELETE FROM rubrica WHERE ";
	private static final String INSERT = "INSERT INTO rubrica (cognome, nome, telefono, email) VALUES (?,?,?,?)";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	ConcreteDatabase(){
		try {
			getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void getConnection() throws SQLException {
		if(connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		}
	}
	
	private void close() throws SQLException {
		connection.close();
	}
	
	@Override
	public void finalize() {
		try {
			close();
		} catch (SQLException e) {}
	}
	
	@Override
	public List<Contatto> select(boolean[] selector, String[] vals) throws SQLException {
		if(vals.length != selector.length) throw new IllegalArgumentException();
		List<Contatto> result = new ArrayList<>();
		StringBuilder query = new StringBuilder();
		boolean requireAnd = false;
		int param = 1;
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
		getConnection();
		PreparedStatement stmt = connection.prepareStatement(query.toString());
		for(int i = 0; i<vals.length;i++) {
			if(selector[i]) stmt.setString(param++, vals[i]);
		}
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Contatto c = new Contatto(rs.getInt("id_rubrica"));
			c.setCognome(rs.getString("cognome"));
			c.setCognome(rs.getString("nome"));
			c.setCognome(rs.getString("telefono"));
			c.setCognome(rs.getString("email"));
			result.add(c);
		}
		rs.close();
		stmt.close();
		close();
		return result;
	}

	@Override
	public boolean insert(Contatto c) throws SQLException {
		boolean result;
		getConnection();
		PreparedStatement stmt = connection.prepareStatement(INSERT);
		stmt.setString(1, c.getCognome()==null ? "" : c.getCognome());
		stmt.setString(2, c.getNome()==null ? "" : c.getNome());
		stmt.setString(3, c.getTelefono()==null ? "" : c.getTelefono());
		stmt.setString(4, c.getEmail()==null ? "" : c.getEmail());
		if(stmt.executeUpdate() == 0) result = false;
		else result = true;
		stmt.close();
		close();
		return result;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		boolean result;
		getConnection();
		StringBuilder query = new StringBuilder();
		query.append(DELETE).append(WHERE).append(ID_VAL);
		PreparedStatement stmt = connection.prepareStatement(query.toString());
		stmt.setInt(1, id);
		if(stmt.executeUpdate() == 0) result = false;
		else result = true;
		stmt.close();
		close();
		return result;
	}

	@Override
	public boolean update(int id, boolean[] selector, String[] vals) throws SQLException {
		if(vals.length != selector.length) throw new IllegalArgumentException();
		boolean result;
		getConnection();
		StringBuilder query = new StringBuilder();
		query.append(UPDATE);
		if(selector[0]) {
			query.append(SURNAME_VAL).append(", ");
		}
		if(selector[1]) {
			query.append(NAME_VAL).append(", ");
		}
		if(selector[2]) {
			query.append(PHONE_VAL).append(", ");
		}
		if(selector[3]) {
			query.append(EMAIL_VAL);
		}
		query.append(WHERE).append(ID_VAL);
		PreparedStatement stmt = connection.prepareStatement(query.toString());
		int param = 1;
		for(int i = 0; i<vals.length;i++) {
			if(selector[i]) stmt.setString(param++, vals[i]);
		}
		stmt.setInt(param, id);
		if(stmt.executeUpdate() == 0) result = false;
		else result = true;
		stmt.close();
		close();
		return result;
	}

	@Override
	public List<Contatto> selectAll() throws SQLException {
		List<Contatto> result = new ArrayList<>();
		getConnection();
		PreparedStatement stmt = connection.prepareStatement(SELECT);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Contatto c = new Contatto(rs.getInt("id_rubrica"));
			c.setCognome(rs.getString("cognome"));
			c.setCognome(rs.getString("nome"));
			c.setCognome(rs.getString("telefono"));
			c.setCognome(rs.getString("email"));
			result.add(c);
		}
		rs.close();
		stmt.close();
		close();
		return result;
	}
}
