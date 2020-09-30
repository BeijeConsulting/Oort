package it.beije.oort.franceschi.biblioteca.utils;

import it.beije.oort.franceschi.biblioteca.controller.DatabaseManager;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Utils {
    public final static DatabaseManager db = new DatabaseManager(Config.getPersistenceUnitName());

    public static void showInstructions(){
        System.out.println("Comandi disponibili:");
        System.out.println("1) Aggiungi Libro");
        System.out.println("2) Aggiungi Autore");
        System.out.println("3) Aggiungi Editore");
        System.out.println("4) Aggiungi Utente");
        System.out.println("5) Aggiungi Prestito");
        System.out.println("6) Modifica Oggetti //WIP");
        System.out.println("15) Mostra dettagli Autore");
        System.out.println("0) Esci");
        System.out.println("Scrivi il numero dell'operazione che intendi fare " +
                "e poi premi INVIO.");
    }

    public static void showWelcome(){
        System.out.println("Applicazione da linea di comando per la gestione di un sistema " +
                "bibliotecario.");
    }

    public static int getUserInput(Scanner sc){
        int input = -1;
        try{
            if (sc.hasNext()){
                input = sc.nextInt();
            }
        } catch (InputMismatchException inputMismatchException){
           System.err.println("Devi inserire un input numerico.");
        } catch (NoSuchElementException e){
            System.err.println("Input vuoto.");
        }
        return input;
    }

    public static Date getDate(Scanner sc){
        String dateString = sc.nextLine();
        if (dateString.equalsIgnoreCase("")){
            return null;
        } else if (dateString.length() == 4){
            return Date.valueOf(dateString + "-1-1");
        } else return Date.valueOf(dateString);
    }
}
