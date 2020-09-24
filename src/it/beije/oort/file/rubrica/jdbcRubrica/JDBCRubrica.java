package it.beije.oort.file.rubrica.jdbcRubrica;

import it.beije.oort.file.rubrica.Contatto;
import it.beije.oort.franceschi.csvToXml.XMLParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCRubrica {
    private static final String XML_PATH = "C:\\Code\\Oort\\xml\\rubriche\\rubrica_sala.xml";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Beije09";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET";


    public static void main(String[] args) {
        writeXMLtoDB(XML_PATH, DB_URL, DB_USER, DB_PASSWORD);
        List<Contatto> contattiDaDB = readXMLfromDB(DB_URL, DB_USER, DB_PASSWORD);

        System.out.println("Ho letto: " + contattiDaDB.size());
    }

    public static void writeXMLtoDB(String xmlPath, String dbUrl, String dbUser, String dbPass) {
        //First get the list
        List<Contatto> contattiDaXML = null;
        try {
            contattiDaXML = XMLParser.parseXML(xmlPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Connect to DB
        PreparedStatement ps;
        try (Connection conn = DBManager.getConnection(dbUrl, dbUser, dbPass)) {
            System.out.println("Connection active: " + !conn.isClosed());
            ps = conn.prepareStatement("INSERT INTO rubrica (nome, cognome, telefono, email) " +
                    "VALUES (?, ?, ?, ?)");
            if (contattiDaXML != null) {
                for (Contatto c : contattiDaXML) {
                    ps.setString(1, c.getNome());
                    ps.setString(2, c.getCognome());
                    ps.setString(3, c.getCell());
                    ps.setString(4, c.getEmail());

                    ps.execute();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Contatto> readXMLfromDB(String dbUrl, String dbUser, String dbPass) {
        List<Contatto> contattiDaDB = new ArrayList<>();
        //Connect to DB
        Statement s;
        ResultSet rs;
        try (Connection conn = DBManager.getConnection(dbUrl, dbUser, dbPass)) {
            System.out.println("Connection active: " + !conn.isClosed());
            s = conn.createStatement();
            rs = s.executeQuery("SELECT * FROM rubrica");

            while (rs.next()) {
                Contatto c = new Contatto();
                c.setNome(rs.getString("NOME"));
                c.setCognome(rs.getString("COGNOME"));
                c.setCell(rs.getString("TELEFONO"));
                c.setEmail(rs.getString("EMAIL"));

                contattiDaDB.add(c);
            }
        } catch (Exception e) {
            // Catch generico perché per ora sono fiducioso
            e.printStackTrace();
        }
        return contattiDaDB;
    }
}
