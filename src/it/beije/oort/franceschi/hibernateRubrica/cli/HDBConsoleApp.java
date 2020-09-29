package it.beije.oort.franceschi.hibernateRubrica.cli;

import it.beije.oort.franceschi.csvToXml.CSVWriter;
import it.beije.oort.franceschi.csvToXml.XMLWriter;
import it.beije.oort.franceschi.hibernateRubrica.HDBReader;
import it.beije.oort.franceschi.hibernateRubrica.HDBWriter;
import it.beije.oort.franceschi.hibernateRubrica.SingletonSessionFactory;
import it.beije.oort.franceschi.jdbcRubrica.cli.DBConsoleAppUtils;
import it.beije.oort.franceschi.rubrica.Contatto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * CLI app for a Database using Hibernate.
 * This app recycles some util methods from the previous one.
 */
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
        String line = "";
        // Start the loop to get the user input
        while (!line.equalsIgnoreCase("9")) {
            // Show instructions
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
                case "3":
                    modify();
                    break;
                case "6":
                    delete();
                    break;
                case "4":
                    export();
                    break;
                case "5":
                    importFile();
                    break;
            }
        }
        System.out.println("Programma terminato");
        session.close();
        sc.close();
    }

    /**
     * Export all the Database content into a CSV or XML file.
     * Filename and extension chosen by the user.
     */
    private static void export(){
        System.out.println("Inserisci nome del file con l'estensione desiderata.");
        String outputFile = sc.nextLine().trim();
        String ext = DBConsoleAppUtils.getFileExt(outputFile);

        // Get all entries of DB into the List, without showing them.
        getAllFromDB(false);
        if (ext.equalsIgnoreCase("csv")){
            CSVWriter.writeCSV(resultList, DBConsoleAppUtils.getOutputPath() + outputFile);
            System.out.println("Scritto file CSV a questo indirizzo: " + DBConsoleAppUtils.getOutputPath() + outputFile);
        } else if (ext.equalsIgnoreCase("xml")){
            XMLWriter.writeList(resultList, DBConsoleAppUtils.getOutputPath() + outputFile);
            System.out.println("Scritto file XML a questo indirizzo: " + DBConsoleAppUtils.getOutputPath() + outputFile);
        } else{
            System.out.println("Errore, input non valido.");
        }
    }

    /**
     * Import a file into the Database. File must be CSV or XML and in a specific path.
     * User insert the file name and extension.
     */
    private static void importFile(){
        System.out.println("Inserisci nome del file da importare con l'estensione.");
        System.out.println("Il file si deve trovare in questa cartella: " + DBConsoleAppUtils.getOutputPath());
        String inputFile = sc.nextLine().trim();
        List<Contatto> contattiImport = DBConsoleAppUtils.loadFile(inputFile);
        if (contattiImport != null){
            for (Contatto c : contattiImport){
                HDBWriter.addContatto(c);
            }
            HDBWriter.addContatti((Contatto) contattiImport);
            System.out.println("Importazione completata.");
        } else {
            System.out.println("Errore. Importazione fallita.");
        }
    }

    /**
     * Delete a contact chosen by ID.
     */
    private static void delete(){
        System.out.println("Inserisci l'ID del contatto da cancellare.");
        int id;
        try{
            id = sc.nextInt();
        } catch (InputMismatchException inputMismatchException){
            System.err.println("Devi inserire un numero.");
            delete();
            return;
        }
        Contatto c = HDBReader.getContattoById(id);
        if (c == null) {
            return;
        }
        System.out.println("Hai selezionato questo contatto: " + c.toString());
        System.out.println("Vuoi davvero cancellarlo? Scrivi [yes] per confermare.");
        if (sc.nextLine().trim().equalsIgnoreCase("yes")){
            // Delete
            session = SingletonSessionFactory.openSession();
            Transaction ts = session.beginTransaction();
            session.delete(c);
            ts.commit();
        }
    }

    /**
     * Initializaton method.
     */
    private static void init() {
        session = SingletonSessionFactory.openSession();
    }

    /**
     * Modify a contact chosen by ID.
     */
    private static void modify(){
        // Get the contact to modify
        System.out.println("Inserisci l'ID del contatto da modificare.");
        // Convert it to int
        int id;
        try{
            id = sc.nextInt();
        } catch (InputMismatchException inputMismatchException){
            System.err.println("Devi inserire un numero.");
            modify();
            return;
        }
        // begin transaction and get the contact to modify
        Transaction ts = session.beginTransaction();
        Contatto c = HDBReader.getContattoById(id);
        if (c == null) {
            return;
        }
        System.out.println("Hai selezionato questo contatto: " + c.toString());

        // Modify the contact here
        String s = "";
        while (!s.equalsIgnoreCase("done")) {
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
        }
        // Update the Database
        session.save(c);
        ts.commit();
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
        try{
            searchBy(col, searchQuery);
        } catch (NoResultException e){
            System.err.println("Nessun risultato.");
        }
    }

    /**
     * Actual method to search the Database
     * @param col The column to search
     * @param query The "thing" to search.
     */
    private static void searchBy(String col, String query){
        if (col.equalsIgnoreCase("id")){
            int id;
            try {
                id = Integer.parseInt(query);
            } catch (Exception e){
                System.err.println("Non hai inserito un numero!");
                return;
            }
            resultList.add(HDBReader.getContattoById(id));
        } else {
            resultList = HDBReader.searchBy(col, query);
        }
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
