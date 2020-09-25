package it.beije.oort.franceschi.database.cli;

import it.beije.oort.file.rubrica.Contatto;

import it.beije.oort.franceschi.csvToXml.CSVParser;
import it.beije.oort.franceschi.csvToXml.InputManager;
import it.beije.oort.franceschi.csvToXml.XMLParser;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Utility class with support methods.
 */
public class DBConsoleAppUtils {
    /**
     * A static scanner
     */
    private static final Scanner sc = new Scanner(System.in);
    /**
     * List of commands available.
     */
    private static final List<String> comandi = Arrays.asList(
            "add", "search", "modify", "export", "import", "delete", "listAll", "help", "quit");

    /**
     * The path were the program will export CSV and XML files. Also used for imports.
     */
    private static final String OUTPUT_PATH = "C:\\Code\\Oort\\output\\db\\";

    /**
     * Getter for the path where I store exported files.
     * @return the path with ending slashes.
     */
    public static String getOutputPath() {
        return OUTPUT_PATH;
    }

    /**
     * Outputs the possible commands and a short explanation for them.
     */
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

    /**
     * Checks against the available commands if the input is a valid command.
     * @param s The string to check
     * @return true if the string is a valid command
     */
    public static boolean isValidInput(String s) {
        for (String string : comandi) {
            if (string.equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the parameter is a valid column name.
     * @param s The string to check.
     * @return true if the parameter is a valid column name.
     */
    public static boolean isValidColumn(String s){
        return s != null && (s.equalsIgnoreCase("NOME") ||
                            s.equalsIgnoreCase("ID") ||
                            s.equalsIgnoreCase("COGNOME") ||
                            s.equalsIgnoreCase("TELEFONO") ||
                            s.equalsIgnoreCase("EMAIL"));
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

    /**
     * Prints a nice header for the pagination system
     * @param n the page we're at
     */
    public static void showPageNumber(int n) {
        System.out.println("\t\t\t\t\t\t//////////////////////////////////////////////////////////////////");
        System.out.println("\t\t\t\t\t\t/////////////////////////\t PAGINA " + ++n + "\t//////////////////////////");
        System.out.println("\t\t\t\t\t\t//////////////////////////////////////////////////////////////////");
        System.out.println();
    }

    /**
     * Returns the file extension.
     * @param filePath The path of the file
     * @return the extension of the file
     */
    public static String getFileExt(String filePath) {
        int lastIndexOf = filePath.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return filePath.substring(lastIndexOf + 1);
    }

    /**
     * Load a CSV or XML file into a List.
     * @param fileName the name of the file, including the extension.
     * @return a List containing all the Contatti from the file.
     */
    public static List<Contatto> loadFile(String fileName) {
        List<Contatto> contatti = new ArrayList<>();
        String path = DBConsoleAppUtils.getOutputPath() + fileName;
        File file = new File(path);
        // Check if the file exist and if it's actually a file and not a directory
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

    /**
     * Check if the given Contatto is empty
     * @param c the Contatto to check
     * @return true if it's empty
     */
    public static boolean isEmpty(Contatto c) {
        return c.getNome().equals("") &&
                c.getCognome().equals("") &&
                c.getCell().equals("") &&
                c.getEmail().equals("");
    }

    /**
     * Util method to add a cellphone number to a Contatto.
     * @param c the Contatto to work on
     */
    public static void phoneInput(Contatto c) {
        boolean valid;
        do {
            String cell = sc.nextLine();
            valid = isValidNumber(cell);
            if (!valid) {
                System.out.println("Input non valido. Reinserisci il numero di telefono.");
            } else {
                c.setCell(cell);
            }
        } while (!valid);
    }

    /**
     * Util method to check if a String is a valid number.
     * @param s The String to check.
     * @return true if s is a valid number.
     */
    private static boolean isValidNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }
}
