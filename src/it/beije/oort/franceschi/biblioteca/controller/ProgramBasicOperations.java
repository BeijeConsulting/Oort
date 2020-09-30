package it.beije.oort.franceschi.biblioteca.controller;

import it.beije.oort.db.JPAEntityManager;
import it.beije.oort.franceschi.biblioteca.model.*;
import it.beije.oort.franceschi.biblioteca.utils.Config;
import it.beije.oort.franceschi.biblioteca.utils.Paginator;
import it.beije.oort.franceschi.biblioteca.utils.Utils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Scanner;

public class ProgramBasicOperations {
    private final static Scanner sc = new Scanner(System.in);

    private final static DatabaseManager dbBasic = new DatabaseManager(Config.getPersistenceUnitName());
    public final static EntityManager em = JPAEntityManager.getEntityManager(Config.getPersistenceUnitName());


    private ProgramBasicOperations(){}

    public static void showAuthorDetails(){
        System.out.println("Inserisci l'ID dell'autore di cui vuoi vedere i dettagli.");
        int idAutore = Utils.getUserInput(sc);
        if (!dbBasic.exist(Autore.class, idAutore)) {
            System.err.println("ID autore non presente.");
            return;
        }
        Autore autore = (Autore) dbBasic.select(Autore.class, idAutore);

        System.out.println(autore);
        System.out.println("Libri dell'Autore: ");

        getBooksFromAuthor(idAutore);
        Paginator.pageManager();
    }


    public static void getBooksFromAuthor(int idAutore){
        String jpql = "SELECT b FROM Libro as b WHERE b.id_autore = :idAutore";
        Query query = em.createQuery(jpql, Libro.class)
                .setParameter("idAutore", idAutore);
        Main.resultList = query.getResultList();
    }

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
                updateObject(Libro.class, id);
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
            case "Libro":
                Libro libro = (Libro) dbBasic.select(obj, id);
                if (libro == null){
                    System.err.println("Libro inesistente.");
                    break;
                }
                String in;
                System.out.println("Modifica i campi. Premi invio senza scrivere nulla per NON modificare il campo.");
                System.out.println("Titolo:");
                in = sc.nextLine();
                if (!in.equalsIgnoreCase("")) libro.setTitolo(in);
                break;
            case "Autore":
                break;
        }
    }
}
