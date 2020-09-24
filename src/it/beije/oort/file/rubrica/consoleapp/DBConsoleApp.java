package it.beije.oort.file.rubrica.consoleapp;

import it.beije.oort.file.rubrica.consoleapp.utils.DBConsoleAppUtils;
import it.beije.oort.file.rubrica.jdbcRubrica.DBManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class DBConsoleApp {
    private final static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        DBConsoleAppUtils.showInit();
        String line = "";

        while (!line.equalsIgnoreCase("quit")) {
            // Get the input
            System.out.println("Cosa desideri fare?");
            line = sc.nextLine().toLowerCase();

            // Verify the input
            if (!DBConsoleAppUtils.isValidInput(line)) {
                System.out.println("Input non valido");
                continue;
            }

            // Do things depending on input
            switch (line) {
                case "help":
                    DBConsoleAppUtils.showHelp();
                    break;
                case "add":
                    //todo: add method
                    break;
                case "listall":
                    //todo list every contatto with pagination
                    break;
            }
        }
        System.out.println("Programma terminato");
        sc.close();
    }

    private static void listAll() {
        try (Connection conn = DBManager.getDefaultConnection()) {
            //todo
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
