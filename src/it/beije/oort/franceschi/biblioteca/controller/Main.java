package it.beije.oort.franceschi.biblioteca.controller;

import java.util.Scanner;

public class Main {
    static final Scanner sc = new Scanner(System.in);


    public void run(){
        // Preparo le cose basilari, come mostrare le istruzioni e inizializzare le variabili
        int userChoice = -1;
        Utils.showWelcome();

        // Loop principale del programma
        while (userChoice != 0){
            Utils.showInstructions();
            /* Ottengo la selezione dell'utente e la converto in int
               Preferisco gestire la conversione in un metodo a parte piuttosto che
               usare nextInt così posso gestire le eccezioni a parte, senza intasare questo metodo.
               Potrei forse prendere l'input in un altro metodo. Da testare: TODO
               Inoltre, il metodo mi valida l'input
            */
            userChoice = Utils.getIntFromUserInput(sc.nextLine());
            if (userChoice < 0) continue;

            switch (userChoice){

            }
        }
    }
}
