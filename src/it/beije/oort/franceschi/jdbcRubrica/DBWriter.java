package it.beije.oort.franceschi.jdbcRubrica;

import it.beije.oort.franceschi.rubrica.Contatto;
import it.beije.oort.franceschi.csvToXml.CSVParser;
import it.beije.oort.franceschi.csvToXml.XMLParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class DBWriter {
    public static void writeCSVToDB(String CSVPath, String dbUrl, String dbUser, String dbPass){
        writeListToDB(new CSVParser(CSVPath).creaListaContatti(), dbUrl, dbUser, dbPass);
    }

    public static void writeXMLToDB(String XMLPath, String dbUrl, String dbUser, String dbPass){
        List<Contatto> contatti = null;
        try {
            contatti = XMLParser.parseXML(XMLPath);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        writeListToDB(contatti, dbUrl, dbUser, dbPass);
    }

    public static void writeListToDB(List<Contatto> contatti, String dbUrl, String dbUser, String dbPass) {
        //Connect to DB
        PreparedStatement ps;
        try (Connection conn = DBManager.getConnection(dbUrl, dbUser, dbPass)) {
            System.out.println("Connected: " + !conn.isClosed());
            ps = conn.prepareStatement("INSERT INTO rubrica (nome, cognome, telefono, email) " +
                    "VALUES (?, ?, ?, ?)");

            // Write each contact in the list in the db
            if (contatti != null) {
                for (Contatto c : contatti) {
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
}
