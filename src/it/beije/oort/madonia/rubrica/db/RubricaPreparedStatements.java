package it.beije.oort.madonia.rubrica.db;

public class RubricaPreparedStatements {
	public static final String INSERT_CONTATTO_IN_RUBRICA = "INSERT INTO rubrica (nome, cognome, telefono, email) VALUES (?, ?, ?, ?)";
	public static final String SELECT_FROM_RUBRICA = "SELECT nome, cognome, telefono, email FROM rubrica";
}
