package it.beije.oort.db;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ScannerDB {
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "simone9810";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET";
	public static String pathFile = "/tempDB/rubricaCSV_from_DB.csv";
	static Scanner scan = new Scanner(System.in);
	static String value = "";
	static String col = "";
	static String id_values = "";
	static String parola = "";
	static String sc = "";
	static String scelta = "";
	static String nome = "";
	static String cognome = "";
	static String telefono = "";
	static String email = "";
	static boolean flag = true;
	static boolean flag2 = true;
	static boolean flag3 = true;
	
	public static Connection getMySqlConnection(String url, String user, String password) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(url, user, password);
		return connection;
	}
	
	public void selezionaScelta() throws IOException {
		System.out.println("Scanner per DataBase a linea di comando!");
		System.out.println("Selezionare tramite inserimento del numero l'opzione desiderata tra le seguenti: ");
		do {
		System.out.println("1) Inserire un nuovo contatto! (Inserire almeno un campo)");
		System.out.println("2) Aggiornare un contatto già esistente!");
		System.out.println("3) Cancellare un contatto già esistente!");
		System.out.println("4) Ricerca utenza nel DB!");
		System.out.println("5) Export del DB in un file! (CSV)");
		System.out.println("6) Uscire dallo scanner!");
		System.out.println("N.B. " + " Per poter aggiornare (2) o cancellare (3) bisogna conoscere l'ID del contatto, si consiglia di fare prima una ricerca(4)");
		System.out.print("Digitare la scelta: ");
		scelta = scan.nextLine();
		if(scelta.equals("1")) {
			System.out.println("Inserire almeno uno dei seguenti campi: ");
			System.out.print("- Nome: ");
			nome = scan.nextLine();
			System.out.print("- Cognome: ");
			cognome = scan.nextLine();
			System.out.print("- Telefono: ");
			telefono = scan.nextLine();
			System.out.print("- Email: ");
			email = scan.nextLine();
			insert(nome, cognome, telefono, email);
		}else {
			if(scelta.equals("2")) {
				System.out.println("Selezionare, indicando l'ID il contatto da modificare: ");
				System.out.print("ID: ");
				id_values = scan.nextLine();
				do {
				String choose = "";
				System.out.println("Scegliere quale campo modificare: ");
				System.out.println("1) Nome");
				System.out.println("2) Cognome");
				System.out.println("3) Telefono");
				System.out.println("4) Email");
				System.out.println("5) Annulla e torna al menù precedente!");
				System.out.print("Inserire scelta: ");  choose = scan.nextLine();
				if(choose.equals("1")) {
					System.out.print("Inserire il nome da andare a sostituire (ID = "+ id_values + "): ");	value = scan.nextLine();
					col = "nome";
					update(col,value,id_values);
				}else {
					if(choose.equals("2")) {
						System.out.print("Inserire il cognome da andare a sostiture: "); value = scan.nextLine();
						col = "cognome";
					}else {
						if(choose.equals("3")) {
							System.out.print("Inserire il numero di telefono da sostituire: "); value = scan.nextLine();
							col = "telefono";
						}else {
							if(choose.equals("4")) {
								System.out.print("Inserire la email da sostiture: "); value = scan.nextLine();
								col = "email";
							}else {
								if(choose.equals("5"))
									System.out.println();
										flag3 = false;
							}
						}
					}
				}
				}while(flag3);
			}else {
				if(scelta.equals("3")) {
					System.out.println("delete");
				}else {
					if(scelta.equals("4")) {
						do {
						System.out.println();
						System.out.println("Scegliere per quale campo si vuole effettuare la ricerca: ");
						System.out.println("1) Ricerca per ID!");
						System.out.println("2) Ricerca per Nome!");
						System.out.println("3) Ricerca per Cognome!");
						System.out.println("4) Stampa tutti i contatti!");
						System.out.print("Scegliere l'opzione di ricerca: ");
						sc = scan.nextLine();
						if(sc.equals("1")) {
							sc = "id";
							System.out.print("Inserire l'ID da cercare: ");
							parola = scan.nextLine();
							flag2 = false;
						}else {
							if(sc.equals("2")) {
								sc = "nome";
								System.out.print("Inserire il nome da cercare: ");
								parola = scan.nextLine();
								flag2 = false;
							}else {
								if(sc.equals("3")) {
									sc = "cognome";
									System.out.print("Inserire il cognome da cercare: ");
									parola = scan.nextLine();
									flag2 = false;
								}else {
									if(sc.equals("4")) {
										selectAll(sc);
										flag2 = false;
									}else
										flag2 = true;
								}
							}
						}
						select(sc, parola);
						}while(flag2);
					}else {
						if(scelta.equals("5")) {
							System.out.println("Export in corso..");
							exportForDB(pathFile);
							System.out.println("Export completato!");
						}else {
							if(scelta.contentEquals("6")) {
								System.out.println("Chiusura in corso..");
								System.out.println("Scanner closed!");
								flag = false;
							}else {
							System.out.println("ATTENZIONE! Scelta non valida! Ripetere l'operazione..");
							flag = true;
							}
						}
					}
				}
			}
		}
		System.out.println();
		}while(flag);
	}
	
	public static void update(String col, String value, String id_values) {
		Connection connection = null;
		PreparedStatement ps = null;
		Statement statement = null;
		ResultSet rs = null;
		
		StringBuilder sbuilder = new StringBuilder("UPDATE contatti set" + col + "='" + value + "'  where id = '" + id_values + "'");
		try {
			connection = ScannerDB.getMySqlConnection(ScannerDB.DB_URL, ScannerDB.DB_USER, ScannerDB.DB_PASSWORD);
			ps = connection.prepareStatement(sbuilder.toString());
			ps.execute();
			System.out.println("Update eseguito!");
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM contatti where id = '" + id_values + "'");
			System.out.println("[ID: " + rs.getString("id")+ " - Nome: " + rs.getString("nome") + " - Cognome: " + rs.getString("cognome") +
					" - Telefono: " + rs.getString("telefono") + " - Email: " + rs.getString("email") + "]");
											
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
			
	
	
	public static void exportForDB(String pathFile) throws IOException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		FileWriter fileWriter = new FileWriter(pathFile);
		
		try {
			connection = ExportDB.getMySqlConnection(ExportDB.DB_URL, ExportDB.DB_USER, ExportDB.DB_PASSWORD);
			ps = connection.prepareStatement("SELECT * FROM contatti");
			rs = ps.executeQuery();
			fileWriter.append("ID;NOME;COGNOME;TELEFONO;EMAIL"+ "\n");
			while (rs.next()) {
				fileWriter.append(rs.getString(1)).append(";").append(rs.getString(2)).append(";").append(rs.getString(3)).append(";")
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
		
	public void selectAll(String sc) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		try {
			connection = ScannerDB.getMySqlConnection(ScannerDB.DB_URL, ScannerDB.DB_USER, ScannerDB.DB_PASSWORD);
			ps = connection.prepareStatement("SELECT * FROM contatti");
			rs = ps.executeQuery();
			while (rs.next()){
				System.out.println("[ID: " + rs.getString("id")+ " - Nome: " + rs.getString("nome") + " - Cognome: " + rs.getString("cognome") +
						" - Telefono: " + rs.getString("telefono") + " - Email: " + rs.getString("email") + "]");
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
	
	public void select(String sc, String parola) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		try {
			connection = ScannerDB.getMySqlConnection(ScannerDB.DB_URL, ScannerDB.DB_USER, ScannerDB.DB_PASSWORD);
			ps = connection.prepareStatement("SELECT * FROM contatti where "+ sc + " like ? ");
			ps.setString(1, parola + '%');
			rs = ps.executeQuery();
			while (rs.next()){
				System.out.println("[ID: " + rs.getString("id")+ " - Nome: " + rs.getString("nome") + " - Cognome: " + rs.getString("cognome") +
						" - Telefono: " + rs.getString("telefono") + " - Email: " + rs.getString("email") + "]");
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
		
	
	public static void insert(String nome, String cognome, String telefono, String email) {
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ScannerDB.getMySqlConnection(ScannerDB.DB_URL, ScannerDB.DB_USER, ScannerDB.DB_PASSWORD);
					
			ps = connection.prepareStatement("INSERT INTO contatti (nome, cognome, telefono, email) VALUES (?, ?, ?, ?)");
			ps.setString(1, nome);
			ps.setString(2, cognome);
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
	

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		Connection connection = getMySqlConnection(DB_URL, DB_USER, DB_PASSWORD);
		ScannerDB sdb = new ScannerDB();
		sdb.selezionaScelta();
		
		
	}
}
