package it.beije.oort.franceschi.database.cli;

import it.beije.oort.file.rubrica.Contatto;
import it.beije.oort.file.rubrica.jdbcRubrica.DBManager;
import it.beije.oort.franceschi.csvToXml.CSVParser;
import it.beije.oort.franceschi.csvToXml.InputManager;
import it.beije.oort.franceschi.csvToXml.XMLParser;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBConsoleAppUtils {
    public static List<Contatto> contatti = new ArrayList<>();
    private static final List<String> comandi = Arrays.asList(
            "add", "search", "modify", "export", "delete", "import", "listAll", "help", "quit");

    public static String getOutputPath() {
        return OUTPUT_PATH;
    }

    private static final String OUTPUT_PATH = "C:\\Code\\Oort\\output\\db\\";


    public static void showHelp(){
        System.out.println("Add:	Aggiungi un contatto al Database.");
        System.out.println("Search: Cerca nel DB.");
        System.out.println("Modify: Modifica un contatto nel DB.");
        System.out.println("Export: Export DB to file.");
        System.out.println("Import: Import a file into the DB.");
        System.out.println("Delete: Delete a Contatto from the DB.");
        System.out.println("ListAll:Stampa tutti gli elementi presenti nel DB.");
        System.out.println("Quit:   Exit the program.");
        System.out.println();
    }

    public static boolean isValidInput(String s) {
        for (String string : comandi) {
            if (string.equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidColumn(String s){
        return s != null &&
                (s.equalsIgnoreCase("NOME") ||
                        s.equalsIgnoreCase("ID") ||
                        s.equalsIgnoreCase("cognome") ||
                        s.equalsIgnoreCase("telefono") ||
                        s.equalsIgnoreCase("email"));
    }

    /**
     * Shows possible inputs for the user
     */
    public static void showInit() {
        System.out.println("Input possibili:");
        for (String s : comandi)
            System.out.print(s + " ");
        System.out.println();
    }

    public static void showPageNumber(int n) {
        System.out.println("\t\t\t\t\t\t//////////////////////////////////////////////////////////////////");
        System.out.println("\t\t\t\t\t\t/////////////////////////\t PAGINA " + ++n + "\t//////////////////////////");
        System.out.println("\t\t\t\t\t\t//////////////////////////////////////////////////////////////////");
        System.out.println();
    }

    public static String getFileExt(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf + 1);
    }

    public static String getFileExt(String filePath) {
        int lastIndexOf = filePath.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return filePath.substring(lastIndexOf + 1);
    }

    public static List<Contatto> loadFile(String fileName) {
        List<Contatto> contatti = new ArrayList<>();
        String path = DBConsoleAppUtils.getOutputPath() + fileName;
        File file = new File(path);
        if (!file.exists() || file.isDirectory()) {
            System.out.println("Input non valido.");
            return null;
        }
        String ext = InputManager.getFileExt(file);
        if (ext.equalsIgnoreCase("csv")) {
            contatti = new CSVParser(path).creaListaContatti();
        } else if (ext.equalsIgnoreCase("xml")) {
            try {
                contatti = XMLParser.parseXML(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return contatti;
    }
    private static int getTotalRowsInDB(){
        try (Connection conn = DBManager.getDefaultConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT COUNT(id) FROM rubrica");
            ResultSet a = statement.executeQuery();
            return a.getInt(1);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
