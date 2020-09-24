package it.beije.oort.file.rubrica.jdbcRubrica;

import it.beije.oort.file.rubrica.Contatto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBReader {
    public static List<Contatto> getListFromDB(String dbUrl, String dbUser, String dbPass) {
        List<Contatto> contattiDaDB = new ArrayList<>();
        //Connect to DB
        Statement s;
        ResultSet rs;
        try (Connection conn = DBManager.getConnection(dbUrl, dbUser, dbPass)) {
            System.out.println("Connection active: " + !conn.isClosed());
            s = conn.createStatement();
            rs = s.executeQuery("SELECT * FROM rubrica");

            // For each row in resultset, create a new contact and add it to the List
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
