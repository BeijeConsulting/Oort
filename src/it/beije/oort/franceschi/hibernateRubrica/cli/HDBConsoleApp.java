package it.beije.oort.franceschi.hibernateRubrica.cli;

import it.beije.oort.franceschi.hibernateRubrica.HDBReader;
import it.beije.oort.franceschi.hibernateRubrica.HDBWriter;
import it.beije.oort.franceschi.hibernateRubrica.SingletonSessionFactory;
import it.beije.oort.franceschi.jdbcRubrica.DBManager;
import it.beije.oort.franceschi.jdbcRubrica.DBReader;
import it.beije.oort.franceschi.jdbcRubrica.cli.DBConsoleApp;
import it.beije.oort.franceschi.jdbcRubrica.cli.DBConsoleAppUtils;
import it.beije.oort.franceschi.rubrica.Contatto;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HDBConsoleApp {
    ///////////////////////////////////////////////
    // GLOBAL STATIC VARS
    ///////////////////////////////////////////////
    /**
     * Global static scanner used in most of the methods.
     */
    private static final Scanner sc = new Scanner(System.in);
    /**
     * A List containing all the contacts returned by methods querying the Database.
     */
    private static List<Contatto> resultList = new ArrayList<>();
    /**
     * Array containing the names of the columns of the table.
     */
    private static final String[] columnNames = {"ID", "NOME", "COGNOME", "TELEFONO", "EMAIL"};
    /**
     * Maximum amount of entries per page.
     */
    private static final int MAX_ENTRIES_PER_PAGE = 10;

    private static Session session;


    public static void main(String[] args) {
        init();
        // Show instructions.
        DBConsoleAppUtils.showInit();
        String line = "";
        // Start the loop to get the user input
        while (!line.equalsIgnoreCase("9")) {
            DBConsoleAppUtils.showInit();
            // Get the input
            System.out.println("Cosa desideri fare?");
            line = sc.nextLine().trim().toLowerCase();
            // Verify the input
            if (!DBConsoleAppUtils.isValidInput(line)) {
                System.err.println("Input non valido");
                continue;
            }
            // Do things depending on input
            switch (line) {
                case "8":
                    DBConsoleAppUtils.showHelp();
                    break;
                case "1":
                    add();
                    break;
                case "7":
                    getAllFromDB(true);
                    break;
                case "2":
                    search();
                    break;
//                case "3":
//                    modify();
//                    break;
//                case "6":
//                    delete();
//                    break;
//                case "4":
//                    export();
//                    break;
//                case "5":
//                    importFile();
//                    break;
            }
        }
        System.out.println("Programma terminato");
        session.close();
        sc.close();
    }

    private static void init() {
        session = SingletonSessionFactory.openSession();
    }

    /**
     * Modify a contact chosen by ID.
     */
    private static void modify(){
        // Get the contact to modify
        System.out.println("Inserisci l'ID del contatto da modificare.");
        String idString = sc.nextLine().trim();
        int id;
        try{
            id = Integer.parseInt(idString);
        } catch (Exception e){
            System.err.println("Non hai inserito un numero.");
            return;
        }
        Contatto c = HDBReader.getContattoById(id);
        if (c == null) {
            return;
        }
        System.out.println("Hai selezionato questo contatto: " + c.toString());

        // Modify the contact here
        String s;
        do {
            System.out.println("Cosa vuoi modificare? [N]ome, [C]ognome, [T]elefono, [E]mail. [Done] per concludere");
            s = sc.nextLine().trim().toLowerCase();
            switch (s) {
                case "n":
                    System.out.println("Inserisci il nuovo nome:");
                    c.setNome(sc.nextLine().trim());
                    break;
                case "c":
                    System.out.println("Inserisci il nuovo cognome:");
                    c.setCognome(sc.nextLine().trim());
                    break;
                case "t":
                    System.out.println("Inserisci il nuovo telefono:");
                    DBConsoleAppUtils.phoneInput(c);
                    break;
                case "e":
                    System.out.println("Inserisci la nuova email:");
                    c.setEmail(sc.nextLine().trim());
                    break;
            }
        } while (!s.equalsIgnoreCase("done"));

        // Update the Database
    }

    /**
     * Add a contact to the DB. Parameters of the Contact object are written by the User in the console.
     * Needs at least one parameter not null.
     */
    private static void add(){
        Contatto c = new Contatto();
        System.out.print("Nome: ");
        c.setNome(sc.nextLine().trim());
        System.out.print("Cognome: ");
        c.setCognome(sc.nextLine().trim());
        System.out.print("Telefono: ");
        DBConsoleAppUtils.phoneInput(c);
        System.out.print("Email: ");
        c.setEmail(sc.nextLine().trim());
        if (DBConsoleAppUtils.isEmpty(c)) {
            System.out.println("Input invalido. Inserire almeno un campo.");
        } else {
            System.out.println("Stai per aggiungere il seguente contatto:");
            System.out.println(c.toString());
            String v;
            do {
                System.out.print("Confermi? [S]i - [N]o:");
                v = sc.nextLine().trim();
                if (v.equalsIgnoreCase("s")) {
                    if (c != null) HDBWriter.addContatto(c);
                    System.out.println("Contatto aggiunto.");
                } else if (v.equalsIgnoreCase("n")) {
                    System.out.println("Ok, elimino il contatto.");
                    c = null;
                }
            } while (!v.equalsIgnoreCase("s") && !v.equalsIgnoreCase("n"));
        }
    }

    /**
     * Search in the Database. User choose column to search into and what to search (of course).
     * Can choose up to 3 columns.
     */
    private static void search(){
        System.out.println("In che campo vuoi cercare? Opzioni: ID, NOME, COGNOME, EMAIL, TELEFONO.");
        String col = sc.nextLine().trim();
        System.out.println("Cosa vuoi cercare?");
        String searchQuery = sc.nextLine().trim();
        searchBy(col, searchQuery);
    }

    /**
     * Actual method to search the Database. This method uses the "LIKE" operator.
     * @param col The column to search
     * @param query The "thing" to search.
     */
    private static void searchBy(String col, String query){
        resultList = HDBReader.searchBy(col, query);
        pageManager();
    }

    /**
     * Returns every contact in the Database.
     * @param showPage if true will show the results. If false, it will only populate (or update) the List containing all entries.
     */
    private static void getAllFromDB(boolean showPage) {
        String selectAll = "SELECT c FROM Contatto AS c";
        resultList.addAll(session.createQuery(selectAll, Contatto.class).list());
        if (showPage) pageManager();
    }

    /**
     * Main method to paginate query results. It's basically used to increment or decrement the page.
     */
    private static void pageManager(){
        int page = 0;
        String command = "";
        // Loop until the user wants to exit
        while (!command.equals("quit")){
            listPage(page);
            command = sc.nextLine().trim();
            switch (command){
                // Will only go next if there's actually some entries in the possible "next" page
                case "": //Did this so i can also press ENTER to go to the next page. Saves time
                case "next":
                    if (page < (resultList.size()/MAX_ENTRIES_PER_PAGE)) page++;
                    else System.out.println("Sei all'ultima pagina.");
                    break;
                // Can't go lower than page 0 (1 to user).
                case "prev":
                    if (page>0) page--;
                    else System.out.println("Sei già alla prima pagina.");
                    break;
            }
        }
    }

    /**
     * Method which shows a specific page.
     * @param page The page to show.
     */
    private static void listPage(int page) {
        DBConsoleAppUtils.showPageNumber(page);
        // Show head of table
        System.out.format("%10s%20s%20s%20s%35s\n\n", columnNames[0], columnNames[1], columnNames[2], columnNames[3], columnNames[4]);
        // Print all contacts in list depending on the page we're at
        for (int i = (MAX_ENTRIES_PER_PAGE * page); i < (MAX_ENTRIES_PER_PAGE*(page+1)) && i < resultList.size(); i++) {
            System.out.format("%10d%20s%20s%20s%35s\n",
                    resultList.get(i).getId(),
                    resultList.get(i).getNome(),
                    resultList.get(i).getCognome(),
                    resultList.get(i).getCell(),
                    resultList.get(i).getEmail());
        }
        System.out.println();
        System.out.println("<- Prev --- [QUIT] --- Next ->");
    }
}
