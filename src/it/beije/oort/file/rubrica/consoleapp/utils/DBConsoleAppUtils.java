package it.beije.oort.file.rubrica.consoleapp.utils;

import it.beije.oort.file.rubrica.Contatto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBConsoleAppUtils {
    public static List<Contatto> contatti = new ArrayList<>();
    private static final List<String> comandi = Arrays.asList(
            "add", "listAll", "quit");


    public static void showHelp(){
        System.out.println("Add:	Aggiungi un contatto al Database.");
        System.out.println("Quit:   Exit the program.");
        System.out.println();
    }
    public static boolean isValidNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }

    public static boolean isValidInput(String s) {
        for (String string : comandi) {
            if (string.equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Shows possible inputs for the user
     */
    public static void showInit(){
        System.out.println("Input possibili:");
        for (String s : ConsoleAppValues.getComandi())
            System.out.print(s + " ");
        System.out.println();
    }
}
