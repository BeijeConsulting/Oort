package it.beije.oort.franceschi.jdbcRubrica.cli;

import it.beije.oort.franceschi.csvToXml.CSVParser;
import it.beije.oort.franceschi.csvToXml.InputManager;
import it.beije.oort.franceschi.csvToXml.XMLParser;
import it.beije.oort.franceschi.rubrica.Contatto;

import java.io.File;
import java.util.ArrayList;
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
        System.out.println("1) Aggiungi:           Aggiungi un contatto al Database.");
        System.out.println("2) Cerca:              Cerca nel Database.");
        System.out.println("3) Modifica:           Modifica un contatto nel Database.");
        System.out.println("4) Esporta:            Esporta il Database su file.");
        System.out.println("5) Importa:            Importa un file nel Database.");
        System.out.println("6) Cancella:           Cancella un contatto dal Database.");
        System.out.println("7) Mostra Tutto:       Stampa tutti gli elementi presenti nel Database.");
        System.out.println("8) Aiuto:              Stampa le istruzioni a schermo.");
        System.out.println("9) Esci:               Esci dal programma.");
        System.out.println();
    }

    /**
     * Checks against the available commands if the input is a valid command.
     * @param s The string to check
     * @return true if the string is a valid command
     */
    public static boolean isValidInput(String s) {
        try {
            return Integer.parseInt(s) < 10 && Integer.parseInt(s) > 0;
        } catch (NumberFormatException e){
            System.err.println("Devi inserire un numero.");
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
        System.out.println("Comandi disponibili:");
        showHelp();
        System.out.println("Per scegliere, scrivi il numero relativo al comando desiderato e premi INVIO.");
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