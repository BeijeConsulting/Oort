package it.beije.oort.file.rubrica.consoleapp;

import it.beije.oort.file.rubrica.Contatto;
import it.beije.oort.file.rubrica.consoleapp.utils.ConsoleAppUtils;
import it.beije.oort.file.rubrica.consoleapp.utils.ConsoleAppValues;

import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "";

        // Shows possible inputs for the user
        System.out.println("Input possibili:");
        for (String s : ConsoleAppValues.getComandi())
            System.out.print(s + " ");
        System.out.println();

        // Loop to run the program untils the user enters "quit"
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
                    addContatto(sc);
                    continue;
                case "delete":
                    delete(sc);
                    continue;
                case "list":
                    get();
                    continue;
                case "modify":
                    modify(sc);
                    continue;
                case "getfile":
                    ConsoleAppUtils.getFile();
                    continue;
                case "sort":
                    ConsoleAppUtils.sort();
                    continue;
                case "load":
                    load(sc);
                    continue;
                case "help":
                    ConsoleAppUtils.printHelp();
                    continue;
                case "clean":
                    ConsoleAppValues.contatti.clear();
                    System.out.println("Rubrica svuotata.");
                    continue;
            }
        }
        System.out.println("Programma terminato");
        sc.close();
    }

    /**
     * Load the content of a CSV or XML file into the List of contacts
     *
     * @param sc A scanner object
     */
    private static void load(Scanner sc) {
        System.out.println("Inserisci il nome del file CSV o XML con estensione che vuoi caricare. "
                + "Il file deve essere nella cartell \"Input\"");
        ConsoleAppValues.contatti.addAll(ConsoleAppUtils.load(sc.nextLine()));
    }

    /**
     * Method to modify a contact. The contact to modify is chosen inside the method
     *
     * @param sc A scanner object
     */
    private static void modify(Scanner sc) {
        System.out.println("Quale contatto vuoi modificare? Inserisci l'indice");
        String s = sc.nextLine();
        int i = 0;
        Contatto c;
        try {
            i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.out.println("Non hai inserito un numero.");
            delete(sc);
            return;
        }
        if (i < 0 || i > ConsoleAppValues.contatti.size()) {
            System.out.println("Indice non valido");
            return;
        } else {
            c = ConsoleAppValues.contatti.get(i);
            modifyContact(sc, c);
        }
    }

    /**
     * Method to modify a specific contact
     *
     * @param sc A scanner object
     * @param c  The contact to modify
     */
    private static void modifyContact(Scanner sc, Contatto c) {
        String s = "";
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
                    ConsoleAppUtils.phoneInput(sc, c);
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
     *
     * @param sc A scanner object
     */
    private static void addContatto(Scanner sc) {
        Contatto c = new Contatto();
        System.out.print("Nome: ");
        c.setNome(sc.nextLine());
        System.out.print("Cognome: ");
        c.setCognome(sc.nextLine());
        System.out.print("Telefono: ");
        ConsoleAppUtils.phoneInput(sc, c);
        System.out.print("Email: ");
        c.setEmail(sc.nextLine());

        if (ConsoleAppUtils.isEmpty(c)) {
            System.out.println("Input invalido. Inserire almeno un campo.");
        } else {
            System.out.println("Stai per aggiungere il seguente contatto:");
            System.out.println(c.toString());
            String v = "";
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
     *
     * @param sc A scanner object
     */
    private static void delete(Scanner sc) {
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
            int i = 0;
            try {
                i = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Non hai inserito un numero.");
                delete(sc);
                return;
            }
            if (i < 0 || i > ConsoleAppValues.contatti.size()) {
                System.out.println("Indice non valido");
                return;
            } else {
                ConsoleAppValues.contatti.remove(i);
                System.out.println("Contatto in posizione " + i + " eliminato.");
            }
        }
    }
}
