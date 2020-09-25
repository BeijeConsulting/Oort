package it.beije.oort.lauria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DBtools {

	public static void select(String editore) {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
			System.out.println("connection is open? " + !connection.isClosed());
			
			statement = connection.createStatement();
			
			rs = statement.executeQuery("SELECT * FROM libri l where editore = '" + editore + "'");
//			rs = statement.executeQuery("SELECT * FROM libri l, autore a where l.id_autore = a.id");
			
			while (rs.next()) {
				System.out.println("titolo : " + rs.getString("titolo"));
				System.out.println("desc : " + rs.getString("descrizione"));
				System.out.println("editore : " + rs.getString("editore"));
				System.out.println("anno : " + rs.getString("anno"));
				//System.out.println("autore : " + rs.getString("nome"));
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			try {
				rs.close();
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	}
	
	public static void preparedSelect(String editore) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
			System.out.println("connection is open? " + !connection.isClosed());
			
			ps = connection.prepareStatement("SELECT * FROM libri l where editore = ?");
			ps.setString(1, editore);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				System.out.println("titolo : " + rs.getString("titolo"));
				System.out.println("desc : " + rs.getString("descrizione"));
				System.out.println("editore : " + rs.getString("editore"));
				System.out.println("anno : " + rs.getString("anno"));
				//System.out.println("autore : " + rs.getString("nome"));
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	}

	public static void insert(String titolo, String descrizione, String editore) {
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
			System.out.println("connection is open? " + !connection.isClosed());
			
			statement = connection.createStatement();
			
			statement.execute("INSERT INTO libri (titolo, descrizione, editore) VALUES ('"+titolo+"', '"+descrizione+"', '"+editore+"')");
//			statement.execute("INSERT INTO libri (titolo, descrizione, editore) VALUES ('OCA manual', 'testo certificazione', 'wiley')");
			
			System.out.println("insert record : " + statement.getUpdateCount());
			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void preparedInsert(String table, String cognome, String nome, String telefono, String email) {
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
			
			ps = connection.prepareStatement("INSERT INTO "+ table +" (cognome, nome, telefono, email) VALUES (?, ?, ?, ?)");
			ps.setString(1, cognome);
			ps.setString(2, nome);
			ps.setString(3, telefono);
			ps.setString(4, email);
			
			ps.execute();

			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void preparedInsert(String table, List<Contatto> recordContatti) {
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
			//System.out.println("connection is open? " + !connection.isClosed());
			
			for(Contatto contattoTemp : recordContatti) {
				ps = connection.prepareStatement("INSERT INTO "+ table +" (cognome, nome, telefono, email) VALUES (?, ?, ?, ?)");
				ps.setString(1, contattoTemp.getCognome());
				ps.setString(2, contattoTemp.getNome());
				ps.setString(3, contattoTemp.getTelefono());
				ps.setString(4, contattoTemp.getEmail());
				
				ps.execute();
				
				//System.out.println("insert record : " + ps.getUpdateCount());
			}
			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

//	public static void main(String[] args) {
//		preparedInsert("OCP", "manuale OCP", "mondadori");
//		preparedSelect("mondadori");
//	}

	
	public static List<Contatto> preparedSelect(String table, List<Contatto> recordContatti) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
			//System.out.println("connection is open? " + !connection.isClosed());
			
			ps = connection.prepareStatement("SELECT * FROM " + table);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Contatto c = new Contatto();
				c.setId(rs.getString("id"));
				c.setCognome(rs.getString("cognome"));
				c.setNome(rs.getString("nome"));
				c.setTelefono(rs.getString("telefono"));
				c.setEmail(rs.getString("email"));
				
				recordContatti.add(c);
				
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return recordContatti;
	}
	
	public static void preparedSelect(String table, String cognome, String nome, String telefono, String email) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
						
//			ps = connection.prepareStatement("SELECT * FROM "+ table +" where cognome like ? and nome like ? and telefono like ? and email like ?");
			ps = connection.prepareStatement("SELECT * FROM "+ table +" where cognome like '%"+ cognome +"%' and nome like '%"+ nome +"%' and telefono like '%"+ telefono +"%' and email like '%"+ email +"%'");
//			ps.setString(1, cognome);
//			ps.setString(2, nome);
//			ps.setString(3, telefono);
//			ps.setString(4, email);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				System.out.print( rs.getString("id") + " - ");
				System.out.print("cognome : "+ rs.getString("cognome") + " - ");
				System.out.print("nome : "+ rs.getString("nome") + " - ");
				System.out.print("telefono : "+ rs.getString("telefono") + " - ");
				System.out.println("email : "+ rs.getString("email"));
			}

			
			//System.out.println("insert record : " + ps.getUpdateCount());
			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void updateSelect(String table, String cognome, String nome, String telefono, String email, int id) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
						
			ps = connection.prepareStatement("UPDATE "+ table +" set cognome = ? , nome = ? , telefono = ? , email = ? where id = ?");
			ps.setString(1, cognome);
			ps.setString(2, nome);
			ps.setString(3, telefono);
			ps.setString(4, email);
			ps.setInt(5, id);
			
			ps.execute();
			
			ps = connection.prepareStatement("SELECT * FROM "+ table +" where id = ? ");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				System.out.print( rs.getString("id") + " - ");
				System.out.print("cognome : "+ rs.getString("cognome") + " - ");
				System.out.print("nome : "+ rs.getString("nome") + " - ");
				System.out.print("telefono : "+ rs.getString("telefono") + " - ");
				System.out.println("email : "+ rs.getString("email"));
			}
			
			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void deleteSelect(String table, int id) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
						
			ps = connection.prepareStatement("DELETE FROM "+ table +" where id = ?");
			ps.setInt(1, id);
			
			ps.execute();
			
			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
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
