package it.beije.oort.franceschi.biblioteca.utils;

import it.beije.oort.franceschi.biblioteca.controller.DatabaseManager;
import it.beije.oort.franceschi.biblioteca.model.Libro;

import java.sql.Date;
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
        System.out.println("6) Modifica Oggetti //Attenzione: Non funzionante");
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
        int input = -2;
        String in;
        if (sc.hasNext()){
            in = sc.nextLine();
            if (in.equalsIgnoreCase("")) return input;
            else try{
                input = Integer.parseInt(in);
            } catch (NumberFormatException e){
                System.err.println("Devi inserire un numero.");
            }
        }
        return input;
    }

    public static Date getDate(Scanner sc){
        String dateString = sc.nextLine();
        return getDate(dateString);
    }

    public static Date getDate(String dateString){
        if (dateString.equalsIgnoreCase("")){
            return null;
        } else if (dateString.length() == 4){
            return Date.valueOf(dateString + "-1-1");
        } else return Date.valueOf(dateString);
    }

    // Verifico che non sia vuoto
    public static boolean isEmpty(Libro l){
        nullRemover(l);
        return l.getAnno_pubblicazione() == Date.valueOf("0000-01-01")
                & l.getDescrizione().equalsIgnoreCase("")
                & l.getTitolo().equalsIgnoreCase("")
                & l.getId_editore() == 0
                & l.getId_autore() == 0;
    }

    // Evito NullPointerException
    public static void nullRemover(Libro l){
        if (l.getAnno_pubblicazione() == null) l.setAnno_pubblicazione(Date.valueOf("0000-01-01"));
        if (l.getTitolo() == null) l.setTitolo("");
        if (l.getDescrizione() == null) l.setDescrizione("");
    }
}
