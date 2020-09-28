package it.beije.oort.franceschi.jdbcRubrica;

import it.beije.oort.franceschi.rubrica.Contatto;

import java.util.List;

public class JDBCRubrica {
    private static final String XML_PATH = "C:\\Code\\Oort\\xml\\rubriche\\rubrica_sala.xml";
    private static final String CSV_PATH = "C:\\Code\\Oort\\csv\\rubriche\\rubrica_sala.csv";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Beije09";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET";


    public static void main(String[] args) {
        List<Contatto> contatti = DBReader.getListFromDB(DB_URL, DB_USER, DB_PASSWORD);
        System.out.printf("Ho letto: %d contatti", contatti.size());
    }
}
