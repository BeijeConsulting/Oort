package it.beije.oort.franceschi.biblioteca.controller;

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

    public static int getIntFromUserInput(String userInput){
        int result = -1;
        try{
            result = Integer.parseInt(userInput);
        } catch (NumberFormatException e){
            System.err.println("Devi inserire un input numerico.");
        }
        if (result >= 0 && result <= 5) return result;
        else {
            System.err.println("Errore. Non hai inserito un numero valido.");
            return -1;
        }
    }
}
