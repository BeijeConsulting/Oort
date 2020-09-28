package it.beije.oort.franceschi.rubrica.consoleapp.utils;

import it.beije.oort.franceschi.rubrica.Contatto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsoleAppValues {
    private static final List<String> comandi = Arrays.asList("add", "delete", "clean", "list", "load", "save", "getFile", "modify", "sort", "help", "quit");
    private static final String path = "C:\\Code\\Oort\\input\\";
    private static final String out = "consoleOut";
    public static List<Contatto> contatti = new ArrayList<>();

    public static List<String> getComandi() {
        return comandi;
    }

    public static String getOutPath() {
        return path + out;
    }

    public static String getPath() {
        return path;
    }
}
