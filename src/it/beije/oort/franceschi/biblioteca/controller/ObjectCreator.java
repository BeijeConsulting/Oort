package it.beije.oort.franceschi.biblioteca.controller;

import it.beije.oort.franceschi.biblioteca.model.Autore;

import java.util.Scanner;

public class ObjectCreator {
    private final static Scanner sc = new Scanner(System.in);

    private ObjectCreator(){}

    public static Autore creaAutore(){
        Autore autore = new Autore();
        System.out.println("Inserisci i dati dell'autore che vuoi creare. Il nome è un campo obbligatorio.");

    }
}
