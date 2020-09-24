package it.beije.oort.file.rubrica.consoleapp;

import it.beije.oort.file.rubrica.consoleapp.utils.DBConsoleAppUtils;
import it.beije.oort.file.rubrica.jdbcRubrica.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DBConsoleApp {
    private final static Scanner sc = new Scanner(System.in);
    private static final int page = 0;

    // to get only from row 0 to 10: SELECT * FROM `table` LIMIT 0, 10
    // to get only from row 10 to 20: SELECT * FROM `table` LIMIT 10, 10

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
                    listAll();
                    break;
            }
        }
        System.out.println("Programma terminato");
        sc.close();
    }

    private static void listAll() {
        try (Connection conn = DBManager.getDefaultConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM rubrica");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("nome"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void listPage(int n) {
        DBConsoleAppUtils.showPageNumber(n);
        // show head of table

        // print all contacts in list
    }

    private static void getResultQuery(int n) {
        try (Connection conn = DBManager.getDefaultConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM rubrica LIMIT ?, 10");
            ps.setInt(1, (n * 10));
            ResultSet rs = ps.executeQuery();

            // crea una lista di 10 elementi con dentro gli elementi, poi la mandi a listpage da stampare
            int index = 0;
            while (rs.next()) {
                System.out.println(index + rs.getString("nome"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
