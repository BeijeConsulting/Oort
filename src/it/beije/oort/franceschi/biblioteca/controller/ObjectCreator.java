package it.beije.oort.franceschi.biblioteca.controller;

import it.beije.oort.franceschi.biblioteca.model.*;
import it.beije.oort.franceschi.biblioteca.utils.Paginator;
import it.beije.oort.franceschi.biblioteca.utils.Utils;

import java.util.Scanner;

public class ObjectCreator {
    private final static Scanner sc = new Scanner(System.in);

    private ObjectCreator(){}

    public static void creaLibro(){
        Libro libro = new Libro();
        System.out.println("Inserisci i dati del libro che vuoi creare.");

        // Sezione Titolo
        do{
            System.out.println("Titolo: [Obbligatorio]");
            libro.setTitolo(sc.nextLine().trim());
        } while (libro.getTitolo().equalsIgnoreCase("") || libro.getTitolo() == null);

        // Sezione ID Autore
        boolean autoreSetted = false;
        do{
            System.out.println("ID Autore: [Obbligatorio. Scrivi \"-1\" per avere una lista di Autori]");
            int idAutore = Utils.getUserInput(sc);
            if (idAutore == -1){
                Main.resultList = Utils.db.findAll(Autore.class);
                Paginator.pageManager();
            } else {
                if (Utils.db.exist(Autore.class, idAutore)){
                    libro.setId_autore(idAutore);
                    autoreSetted = true;
                } else {
                    System.err.println("Non hai inserito un ID valido.");
                }
            }
        } while (!autoreSetted);

        // Descrizione
        System.out.println("Descrizione:");
        libro.setDescrizione(sc.nextLine().trim());

        System.out.println("Anno di Pubblicazione:");
        libro.setAnno_pubblicazione(Utils.getDate(sc));

        System.out.println("ID Editore: [Scrivi -1 per avere una lista di Editori]");
        int idEditore = Utils.getUserInput(sc);
        if (idEditore == -1){
            Main.resultList = Utils.db.findAll(Editore.class);
            Paginator.pageManager();
            System.out.println("ID Editore:");
            idEditore = Utils.getUserInput(sc);
        }
        if (Utils.db.exist(Autore.class, idEditore)){
            libro.setId_autore(idEditore);
        } else {
            System.err.println("Non hai inserito un ID valido.");
        }

        System.out.println("Hai creato il seguente Libro:");
        System.out.println(libro);
        addObjectToDb(libro);
    }

    public static void creaAutore(){
        Autore autore = new Autore();
        System.out.println("Inserisci i dati dell'autore che vuoi creare.");
        do{
            System.out.println("Nome: [Obbligatorio]");
            autore.setNome(sc.nextLine().trim());
        } while (autore.getNome().equalsIgnoreCase("") || autore.getNome() == null);

        System.out.println("Cognome:");
        autore.setCognome(sc.nextLine().trim());

        System.out.println("Data di nascita: [Formato ANNO-MESE-GIORNO]");
        autore.setData_nascita(Utils.getDate(sc));

        System.out.println("Data di morte: [Formato ANNO-MESE-GIORNO]");
        autore.setData_morte(Utils.getDate(sc));

        System.out.println("Biografia:");
        autore.setBiografia(sc.nextLine().trim());

        System.out.println("Hai creato il seguente Autore:");
        System.out.println(autore);

        addObjectToDb(autore);
    }

    public static void creaEditore(){
        Editore editore = new Editore();
        System.out.println("Inserisci i dati dell'editore che vuoi creare.");
        do{
            System.out.println("Nome: [Obbligatorio]");
            editore.setNome(sc.nextLine().trim());
        } while (editore.getNome().equalsIgnoreCase("") || editore.getNome() == null);

        System.out.println("Descrizione:");
        editore.setDescrizione(sc.nextLine().trim());

        System.out.println("Hai creato il seguente Editore:");
        System.out.println(editore);

        addObjectToDb(editore);
    }

    public static void creaUtente(){
        Utente utente = new Utente();
        System.out.println("Inserisci i dati dell'utente che vuoi creare.");
        do{
            System.out.println("Nome: [Obbligatorio]");
            utente.setNome(sc.nextLine().trim());
        } while (utente.getNome().equalsIgnoreCase("") || utente.getNome() == null);

        System.out.println("Cognome:");
        utente.setCognome(sc.nextLine().trim());

        do{
            System.out.println("Codice Fiscale: [Obbligatorio]");
            utente.setCodice_fiscale(sc.nextLine().trim());
        } while (utente.getCodice_fiscale().equalsIgnoreCase("") || utente.getCodice_fiscale() == null);

        System.out.println("Cellulare:");
        utente.setCellulare(sc.nextLine().trim());

        System.out.println("Email:");
        utente.setEmail(sc.nextLine().trim());

        System.out.println("Indirizzo:");
        utente.setIndirizzo(sc.nextLine().trim());

        System.out.println("Hai creato il seguente Utente:");
        System.out.println(utente);

        addObjectToDb(utente);
    }

    public static void creaPrestito(){
        Prestito prestito = new Prestito();
        System.out.println("Inserisci i dati del prestito che vuoi creare.");
        do{
            System.out.println("Data di Inizio Prestito: [Obbligatoria]");
            prestito.setDataInizio(Utils.getDate(sc));
        } while (prestito.getDataInizio() == null);

        System.out.println("Data di Fine Prestito:");
        prestito.setDataFine(Utils.getDate(sc));

        // Sezione CF
        boolean cfSetted = false;
        do{
            System.out.println("Codice Fiscale Utente: [Obbligatorio. Scrivi \"-1\" per avere una lista di Utenti]");
            String cfUtente = sc.nextLine();
            if (cfUtente.equalsIgnoreCase("-1")){
                Main.resultList = Utils.db.findAll(Utente.class);
                Paginator.pageManager();
            } else {
                if (DatabaseManager.getUtenteFromCF(cfUtente) != null){
                    prestito.setCfUtente(cfUtente);
                    cfSetted = true;
                } else {
                    System.err.println("Non hai inserito un Codice Fiscale valido.");
                }
            }
        } while (!cfSetted);

        boolean idLibro = false;
        do{
            System.out.println("ID Libro: [Obbligatorio. Scrivi \"-1\" per avere una lista di Libri]");
            int idLibroPrestato = Utils.getUserInput(sc);
            if (idLibroPrestato == -1){
                Main.resultList = Utils.db.findAll(Utente.class);
                Paginator.pageManager();
            } else {
                if (Utils.db.exist(Libro.class, idLibroPrestato)){
                    prestito.setIdLibro(idLibroPrestato);
                    idLibro = true;
                } else {
                    System.err.println("Non hai inserito un ID Libro valido.");
                }
            }
        } while (!idLibro);

        System.out.println("Note:");
        prestito.setNote(sc.nextLine().trim());

        System.out.println("Hai creato il seguente Prestito:");
        System.out.println(prestito);

        addObjectToDb(prestito);
    }

    private static void addObjectToDb(IBibliotecaModel obj){
        String selection = "";
        while (!selection.equalsIgnoreCase("s") && !selection.equalsIgnoreCase("n")) {
            System.out.println("Vuoi aggiungerlo al Database? [S]i - [N]o");
            selection = sc.nextLine().trim();
            if (selection.equalsIgnoreCase("s")) {
                Utils.db.insert(obj);
            } else if (selection.equalsIgnoreCase("n")) {
                System.out.println("Ok, non aggiungo l'oggetto' al database.");
                obj = null;
            }
        }
    }
}