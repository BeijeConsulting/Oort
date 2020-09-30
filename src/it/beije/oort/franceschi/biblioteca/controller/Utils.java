package it.beije.oort.franceschi.biblioteca.controller;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Utils {
    //TODO: Anche lettura, modifica e cancellazione
    public static void showInstructions(){
        System.out.println("Comandi disponibili:");
        System.out.println("1) Aggiungi Libro");
        System.out.println("2) Aggiungi Autore");
        System.out.println("3) Aggiungi Editore");
        System.out.println("4) Aggiungi Utente");
        System.out.println("5) Aggiungi Prestito");
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
        if (input >= 0 && input <= 5){
            return input;
        }
        else {
            System.err.println("Errore. Non hai inserito un numero nel range valido.");
            return -1;
        }
    }
}
