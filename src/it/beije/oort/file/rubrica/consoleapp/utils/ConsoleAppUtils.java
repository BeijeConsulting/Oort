package it.beije.oort.file.rubrica.consoleapp.utils;

import it.beije.oort.file.rubrica.Contatto;
import it.beije.oort.file.rubrica.comparators.ContattoNomeComparator;
import it.beije.oort.franceschi.csvToXml.CSVParser;
import it.beije.oort.franceschi.csvToXml.InputManager;
import it.beije.oort.franceschi.csvToXml.XMLParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleAppUtils {
    private static final Scanner sc = new Scanner(System.in);

    public static boolean isEmpty(Contatto c) {
        return c.getNome().equals("") & c.getCognome().equals("") & c.getCell().equals("") & c.getEmail().equals("");
    }

    public static boolean isValidNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }

    public static boolean isValidInput(String s) {
        for (String string : ConsoleAppValues.getComandi()) {
            if (string.equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;

    }

    public static void printHelp() {
        System.out.println("Add:	Aggiungi un contatto.");
        System.out.println("Delete:	Elimini un contatto.");
        System.out.println("List:	Stampi tutti i contatti nella rubrica.");
        System.out.println("Load:	Carica un file da disco..");
        System.out.println("Save:   Salva il file.");
        System.out.println("Modify:	Modifica un contatto.");
        System.out.println("Sort:	Ordina alfabeticamente i contatti nella rubrica.");
        System.out.println();
    }

    public static void sort() {
        ConsoleAppValues.contatti.sort(new ContattoNomeComparator());
        System.out.println("Lista ordinata con successo.");
    }

    public static void phoneInput(Contatto c) {
        boolean valid;
        do {
            String cell = sc.nextLine();
            valid = ConsoleAppUtils.isValidNumber(cell);
            if (!valid) {
                System.out.println("Input non valido. Reinserisci il numero di telefono.");
            } else {
                c.setCell(cell);
            }
        } while (!valid);
    }

    /**
     * Given a filename, it will load it and parse its content into a List of Contatto
     *
     * @param fileName the name of the file including the extension
     * @return a List containing all Contatto objects parsed from the file.
     */
    public static List<Contatto> load(String fileName) {
        List<Contatto> contatti = new ArrayList<>();
        String path = ConsoleAppValues.getPath() + fileName;
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
}
