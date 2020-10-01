package it.beije.oort.franceschi.biblioteca.controller;

import it.beije.oort.franceschi.biblioteca.model.IBibliotecaModel;
import it.beije.oort.franceschi.biblioteca.utils.Utils;

import java.util.List;
import java.util.Scanner;

// todo DELETE E LIST
public class Main {
    static final Scanner sc = new Scanner(System.in);

    public static List<? extends IBibliotecaModel> resultList;

    public void run(){
        // Preparo le cose basilari, come mostrare le istruzioni e inizializzare le variabili
        int userChoice = -1;

        Utils.showWelcome();

        // Loop principale del programma
        while (userChoice != 0){
            Utils.showInstructions();
            userChoice = Utils.getUserInput(sc);
            if (userChoice < 0) continue;

            switch (userChoice){
                case 1:
                    ObjectCreator.addObjectToDb(ObjectCreator.creaLibroNuovo());
                    break;
                case 2:  // Aggiungi autore
                    ObjectCreator.creaAutore();
                    break;
                case 3:
                    ObjectCreator.creaEditore();
                    break;
                case 4:
                    ObjectCreator.creaUtente();
                    break;
                case 5:
                    ObjectCreator.creaPrestito();
                    break;
                case 6:
                    ObjectUpdater.update();
                    break;
                case 15:
                    ProgramBasicOperations.showAuthorDetails();
                    break;
                default:
                    System.err.println("Hai inserito un numero non presente nella lista di operazioni valide.");
                    break;
            }
        }
    }


}
