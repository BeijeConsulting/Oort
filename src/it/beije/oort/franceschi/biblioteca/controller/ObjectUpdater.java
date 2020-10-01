package it.beije.oort.franceschi.biblioteca.controller;

import it.beije.oort.franceschi.biblioteca.model.*;
import it.beije.oort.franceschi.biblioteca.utils.Utils;

import java.util.Scanner;

// TODO tutti i metodi di update
public class ObjectUpdater {
    private final static Scanner sc = new Scanner(System.in);

    private ObjectUpdater(){}

    public static void update(){
        System.out.println("Che cosa vuoi modificare?");
        System.out.println("1) Libro");
        System.out.println("2) Autore");
        System.out.println("3) Editore");
        System.out.println("4) Utente");
        System.out.println("5) Prestito");
        System.out.println("0) Esci");
        int input = Utils.getUserInput(sc);
        int id;
        switch (input){
            case 0:
                return;
            case 1:
                System.out.println("Inserisci l'ID del Libro da modificare.");
                id = Utils.getUserInput(sc);
                //updateObject(Libro.class, id);
                TestMethodsHere.mergeBooks(id);
                break;
            case 2:
                System.out.println("Inserisci l'ID dell'Autore da modificare.");
                id = Utils.getUserInput(sc);
                updateObject(Autore.class, id);
                break;
            case 3:
                System.out.println("Inserisci l'ID dell'Editore da modificare.");
                id = Utils.getUserInput(sc);
                updateObject(Editore.class, id);
                break;
            case 4:
                System.out.println("Inserisci l'ID dell'Utente da modificare.");
                id = Utils.getUserInput(sc);
                updateObject(Utente.class, id);
                break;
            case 5:
                System.out.println("Inserisci l'ID del Prestito da modificare.");
                id = Utils.getUserInput(sc);
                updateObject(Prestito.class, id);
                break;
            default:
                System.err.println("Input inserito non valido.");
        }

    }

    private static void updateObject(Class<? extends IBibliotecaModel> obj, int id){
        switch (obj.getSimpleName()){
            // LIBRO
            case "Libro":
                Libro libro = (Libro) DatabaseManager.select(obj, id);
                if (libro == null){
                    System.err.println("Libro inesistente.");
                    break;
                }
                String in;
                System.out.println("Modifica i campi. Premi invio senza scrivere nulla per lasciare il campo invariato.");
                // titolo
                System.out.println("Titolo: [" + libro.getTitolo() + "]");
                in = sc.nextLine();
                if (!in.equalsIgnoreCase("")) libro.setTitolo(in);
                // descrizione
                System.out.println("Descrizione: [" + libro.getDescrizione() + "]");
                in = sc.nextLine();
                if (!in.equalsIgnoreCase("")) libro.setDescrizione(in);
                // anno pubblicazione
                System.out.println("Anno di Pubblicazione: [" + libro.getAnno_pubblicazione() + "]");
                in = sc.nextLine();
                if (!in.equalsIgnoreCase("")) libro.setAnno_pubblicazione(Utils.getDate(in));
                // id autore
                System.out.println("ID Autore: [" + libro.getId_autore() + "]");
                in = sc.nextLine();
                if (!in.equalsIgnoreCase("")){
                    try{
                        libro.setId_autore(Integer.parseInt(in));
                    } catch (NumberFormatException e){
                        System.err.println("Non hai inserito un numero valido.");
                    }
                }

                break;
            case "Autore":
                break;
        }
    }
}
