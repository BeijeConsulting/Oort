package it.beije.oort.franceschi.rubrica.consoleapp;

import it.beije.oort.franceschi.rubrica.Contatto;
import it.beije.oort.franceschi.rubrica.consoleapp.utils.ConsoleAppUtils;
import it.beije.oort.franceschi.rubrica.consoleapp.utils.ConsoleAppValues;
import it.beije.oort.franceschi.jdbcRubrica.DBValues;
import it.beije.oort.franceschi.jdbcRubrica.DBWriter;
import it.beije.oort.franceschi.csvToXml.CSVWriter;
import it.beije.oort.franceschi.csvToXml.XMLWriter;

import java.util.Scanner;

public class ConsoleApp {
    private final static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String line = "";

        // Shows possible inputs for the user
        System.out.println("Input possibili:");
        for (String s : ConsoleAppValues.getComandi())
            System.out.print(s + " ");
        System.out.println();

        // Loop to run the program until the user enters "quit"
        while (!line.equalsIgnoreCase("quit")) {
            // Get the input
            System.out.println("Cosa desideri fare?");
            line = sc.nextLine().toLowerCase();

            // Verify the input
            if (!ConsoleAppUtils.isValidInput(line)) {
                System.out.println("Input non valido");
                continue;
            }

            // Do things depending on input
            switch (line) {
                case "add":
                    addContatto();
                    continue;
                case "delete":
                    delete();
                    continue;
                case "list":
                    get();
                    continue;
                case "modify":
                    modify();
                    continue;
                case "sort":
                    ConsoleAppUtils.sort();
                    continue;
                case "load":
                    load();
                    continue;
                case "help":
                    ConsoleAppUtils.printHelp();
                    continue;
                case "clean":
                    ConsoleAppValues.contatti.clear();
                    System.out.println("Rubrica svuotata.");
                    continue;
                case "save":
                    save();
            }
        }
        System.out.println("Programma terminato");
        sc.close();
    }

    /**
     * Method to save the current List of Contact into a file or DB. At the moment the user can't choose a name
     * for the file.
     */
    private static void save() {
        System.out.println("Dove vuoi salvare la rubrica? [1] Database - [2] XML - [3] CSV");
        int in = sc.nextInt();
        if (in == 1) {
            try {
                DBWriter.writeListToDB(ConsoleAppValues.contatti,
                        DBValues.getDbUrl(), DBValues.getDbUser(), DBValues.getDbPassword());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Rubrica salvata su Database in URL: " + DBValues.getDbUrl());
        } else if (in == 2) {
            XMLWriter.writeList(ConsoleAppValues.contatti, ConsoleAppValues.getOutPath() + ".xml");
            System.out.println("Rubrica salvata su XML in questa directory: " + ConsoleAppValues.getOutPath() + ".xml");
        } else if (in == 3) {
            CSVWriter.writeCSV(ConsoleAppValues.contatti, ConsoleAppValues.getOutPath() + ".csv");
            System.out.println("Rubrica salvata su CSV in questa directory: " + ConsoleAppValues.getOutPath() + ".csv");
        } else {
            System.out.println("Input non valido.");
        }
    }

    /**
     * Load the content of a CSV or XML file into the List of contacts
     */
    private static void load() {
        System.out.println("Inserisci il nome del file CSV o XML con estensione che vuoi caricare. "
                + "Il file deve essere nella cartella \"Input\"");
        String in = sc.nextLine();
        if (in != null) {
            ConsoleAppValues.contatti.addAll(ConsoleAppUtils.load(in));
        } else {
            System.out.println("Non hai inserito nulla.");
        }
    }

    /**
     * Method to modify a contact. The contact to modify is chosen inside the method
     */
    private static void modify() {
        System.out.println("Quale contatto vuoi modificare? Inserisci l'indice");
        String s = sc.nextLine();
        int i;
        Contatto c;
        try {
            i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.out.println("Non hai inserito un numero.");
            delete();
            return;
        }
        if (i < 0 || i > ConsoleAppValues.contatti.size()) {
            System.out.println("Indice non valido");
        } else {
            c = ConsoleAppValues.contatti.get(i);
            modifyContact(c);
        }
    }

    /**
     * Method to modify a specific contact
     *
     * @param c The contact to modify
     */
    private static void modifyContact(Contatto c) {
        String s;
        do {
            System.out.print("Cosa vuoi modificare? [N]ome, [C]ognome, [T]elefono, [E]mail: ");
            s = sc.nextLine().toLowerCase();
            switch (s) {
                case "n":
                    System.out.println("Inserisci il nuovo nome:");
                    c.setNome(sc.nextLine());
                    break;
                case "c":
                    System.out.println("Inserisci il nuovo cognome:");
                    c.setCognome(sc.nextLine());
                    break;
                case "t":
                    System.out.println("Inserisci il nuovo telefono:");
                    ConsoleAppUtils.phoneInput(c);
                    break;
                case "e":
                    System.out.println("Inserisci la nuova email:");
                    c.setEmail(sc.nextLine());
                    break;
            }
        } while (!s.equalsIgnoreCase("c") && !s.equalsIgnoreCase("n") && !s.equalsIgnoreCase("t")
                && !s.equalsIgnoreCase("e"));

    }

    /**
     * Method to create and add to the static list a new contact
     */
    private static void addContatto() {
        Contatto c = new Contatto();
        System.out.print("Nome: ");
        c.setNome(sc.nextLine());
        System.out.print("Cognome: ");
        c.setCognome(sc.nextLine());
        System.out.print("Telefono: ");
        ConsoleAppUtils.phoneInput(c);
        System.out.print("Email: ");
        c.setEmail(sc.nextLine());

        if (ConsoleAppUtils.isEmpty(c)) {
            System.out.println("Input invalido. Inserire almeno un campo.");
        } else {
            System.out.println("Stai per aggiungere il seguente contatto:");
            System.out.println(c.toString());
            String v;
            do {
                System.out.print("Confermi? [S]i - [N]o:");
                v = sc.nextLine();
                if (v.equalsIgnoreCase("s")) {
                    ConsoleAppValues.contatti.add(c);
                    System.out.println("Contatto aggiunto.");
                } else if (v.equalsIgnoreCase("n")) {
                    System.out.println("Ok, elimino il contatto.");
                }
            } while (!v.equalsIgnoreCase("s") && !v.equalsIgnoreCase("n"));
        }
    }

    /**
     * Prints all the contacts in the static List with the index in front of them.
     */
    private static void get() {
        for (int i = 0; i < ConsoleAppValues.contatti.size(); i++) {
            System.out.println(i + ". " + ConsoleAppValues.contatti.get(i).toString());
        }
    }

    /**
     * Method to delete a contact
     */
    private static void delete() {
        if (!(ConsoleAppValues.contatti.size() > 0)) {
            System.out.println("Non puoi cancellare nulla se la lista è vuota. " + "Aggiungi qualcosa!");
            return;
        }
        System.out.println(
                "Inserisci l'indice del contatto che vuoi eliminare. " + "Scrivi \"last\" per eliminare l'ultimo");
        String s = sc.nextLine();
        if (s.equalsIgnoreCase("last")) {
            ConsoleAppValues.contatti.remove(ConsoleAppValues.contatti.size() - 1);
            System.out.println("Ultimo Contatto eliminato.");
        } else {
            int i;
            try {
                i = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Non hai inserito un numero.");
                delete();
                return;
            }
            if (i < 0 || i > ConsoleAppValues.contatti.size()) {
                System.out.println("Indice non valido");
            } else {
                ConsoleAppValues.contatti.remove(i);
                System.out.println("Contatto in posizione " + i + " eliminato.");
            }
        }
    }
}
