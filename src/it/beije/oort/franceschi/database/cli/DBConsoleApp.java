package it.beije.oort.franceschi.database.cli;

import it.beije.oort.file.rubrica.Contatto;
import it.beije.oort.file.rubrica.jdbcRubrica.DBManager;
import it.beije.oort.franceschi.csvToXml.CSVWriter;
import it.beije.oort.franceschi.csvToXml.XMLWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * CLI for a Database
 */
public class DBConsoleApp {
    ///////////////////////////////////////////////
    // GLOBAL STATIC VARS
    ///////////////////////////////////////////////
    /**
     * Global static scanner used in most of the methods.
     */
    private static final  Scanner sc = new Scanner(System.in);
    /**
     * A List containing all the contacts returned by methods querying the Database.
     */
    private static final List<Contatto> resultList = new ArrayList<>();
    /**
     * Array containing the names of the columns of the table.
     */
    private static final String[] columnNames = new String[5];
    /**
     * Maximum amount of entries per page.
     */
    private static final int MAX_ENTRIES_PER_PAGE = 10;


    ///////////////////////////////////////////////
    // MAIN LOOP
    ///////////////////////////////////////////////
    public static void main(String[] args) {
        // Show instructions.
        DBConsoleAppUtils.showInit();
        String line = "";
        // Start the loop to get the user input
        while (!line.equalsIgnoreCase("quit")) {
            // Get the input
            System.out.println("Cosa desideri fare?");
            line = sc.nextLine().trim().toLowerCase();
            // Verify the input
            if (!DBConsoleAppUtils.isValidInput(line)) {
                System.out.println("Input non valido");
                continue;
            }
            // Do things depending on input
            switch (line) {
                case "aiuto":
                    DBConsoleAppUtils.showHelp();
                    break;
                case "aggiungi":
                    add();
                    break;
                case "mostra tutto":
                    getAllFromDB(true);
                    break;
                case "cerca":
                    search();
                    break;
                case "modifica":
                    modify();
                    break;
                case "cancella":
                    delete();
                    break;
                case "esporta":
                    export();
                    break;
                case "importa":
                    importFile();
                    break;
            }
        }
        System.out.println("Programma terminato");
        sc.close();
    }

    ///////////////////////////////////////////////
    // MAIN METHODS (THOSE CALLED INSIDE THE SWITCH
    ///////////////////////////////////////////////

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
                addToDB(c);
            }
            System.out.println("Importazione completata.");
        } else {
            System.out.println("Errore. Importazione fallita.");
        }
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
     * Delete a contact chosen by ID.
     */
    private static void delete(){
        System.out.println("Inserisci l'ID del contatto da cancellare.");
        String id = sc.nextLine().trim();
        Contatto c = getContattoFromDB(id);
        if (c == null) {
            return;
        }
        System.out.println("Hai selezionato questo contatto: " + c.toString());
        System.out.println("Vuoi davvero cancellarlo? Scrivi [yes] per confermare.");
        if (sc.nextLine().trim().equalsIgnoreCase("yes")){
            // Delete
            try (Connection conn = DBManager.getDefaultConnection()) {
                PreparedStatement ps = conn.prepareStatement("DELETE FROM rubrica WHERE id = ?");
                ps.setString(1, id);

                ps.executeUpdate();
                System.out.println("Contatto eliminato con successo.");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Modify a contact chosen by ID.
     */
    private static void modify(){
        // Get the contact to modify
        System.out.println("Inserisci l'ID del contatto da modificare.");
        String id = sc.nextLine().trim();
        Contatto c = getContattoFromDB(id);
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

        // Update
        try (Connection conn = DBManager.getDefaultConnection()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE rubrica SET nome = ?, cognome = ?, email = ?, telefono = ? WHERE id = ?");
            ps.setString(1, c.getNome());
            ps.setString(2, c.getCognome());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getCell());
            ps.setString(5, id);

            ps.executeUpdate();
            System.out.println("Contatto aggiornato con successo.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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
                    if (c != null) addToDB(c);
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
        System.out.println("In che campo vuoi cercare? Opzioni: ID, NOME, COGNOME, EMAIL, TELEFONO. Scegli 1 o 2 opzioni.");
        String s = sc.nextLine().trim();
        String[] searchColumns = s.split(" ");
        if (searchColumns.length < 1){
            System.out.println("Errore nell'input.");
        } else if (searchColumns.length == 1 && DBConsoleAppUtils.isValidColumn(searchColumns[0])){
            System.out.println("Cosa vuoi cercare?");
            String searchQuery = sc.nextLine().trim();
            searchBy(searchColumns[0], searchQuery);
        } else if (searchColumns.length == 2 && DBConsoleAppUtils.isValidColumn(searchColumns[0])
                && DBConsoleAppUtils.isValidColumn(searchColumns[1])){
            System.out.println("Cosa vuoi cercare?");
            String searchQuery = sc.nextLine().trim();
            searchBy(searchColumns[0], searchColumns[1], searchQuery);
        } else if (searchColumns.length == 3 && DBConsoleAppUtils.isValidColumn(searchColumns[0]) &&
                DBConsoleAppUtils.isValidColumn(searchColumns[1]) &&
                DBConsoleAppUtils.isValidColumn(searchColumns[2])){
            System.out.println("Cosa vuoi cercare?");
            String searchQuery = sc.nextLine().trim();
            searchBy(searchColumns[0], searchColumns[1], searchColumns[2], searchQuery);
        }
    }

    /**
     * Actual method to search the Database. This method uses the "LIKE" operator.
     * @param col The column to search
     * @param query The "thing" to search.
     */
    private static void searchBy(String col, String query){
        try (Connection conn = DBManager.getDefaultConnection()) {
            String preparedQuery = "SELECT * FROM rubrica WHERE " + col + " LIKE ?";
            PreparedStatement ps = conn.prepareStatement(preparedQuery);
            ps.setString(1, "%" + query + "%");

            ResultSet rs = ps.executeQuery();

            resultSetToList(rs);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        pageManager();
    }

    /**
     * Actual method to search the Database. This method uses the "LIKE" operator.
     * @param col The column to search
     * @param col2 The second column to search
     * @param query The "thing" to search.
     */
    private static void searchBy(String col, String col2, String query){
        try (Connection conn = DBManager.getDefaultConnection()) {
            String preparedQuery = "SELECT * FROM rubrica WHERE " + col + " LIKE ? OR " + col2 + " LIKE ?";
            PreparedStatement ps = conn.prepareStatement(preparedQuery);
            ps.setString(1, "%" + query + "%");
            ps.setString(2, "%" + query + "%");

            ResultSet rs = ps.executeQuery();

            resultSetToList(rs);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        pageManager();
    }

    /**
     * Actual method to search the Database. This method uses the "LIKE" operator.
     * @param col The column to search
     * @param col2 The second column to search
     * @param col3 The third column to search
     * @param query The "thing" to search.
     */
    private static void searchBy(String col, String col2, String col3, String query){
        try (Connection conn = DBManager.getDefaultConnection()) {
            String preparedQuery = "SELECT * FROM rubrica WHERE " + col + " LIKE ? OR " + col2 + " LIKE ? OR " + col3 + " LIKE ?";
            PreparedStatement ps = conn.prepareStatement(preparedQuery);
            ps.setString(1, "%" + query + "%");
            ps.setString(2, "%" + query + "%");
            ps.setString(3, "%" + query + "%");

            ResultSet rs = ps.executeQuery();

            resultSetToList(rs);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        pageManager();
    }


    /**
     * Returns every contact in the Database.
     * @param showPage if true will show the results. If false, it will only populate (or update) the List containing all entries.
     */
    private static void getAllFromDB(boolean showPage) {
        try (Connection conn = DBManager.getDefaultConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM rubrica");
            /*
            There's also this query:
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM rubrica LIMIT ?, 10");
            But I think it costs more to query the database every time than to create a List and
            loop that.
             */
            ResultSet rs = ps.executeQuery();
            resultSetToList(rs);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (showPage) pageManager();
    }

    ///////////////////////////////////////////////
    // SUPPORT METHODS (USED BY MAIN METHODS)
    ///////////////////////////////////////////////

    /**
     * Add a single contact to the Database
     * @param c the contact to add
     */
    private static void addToDB(Contatto c){
        try (Connection conn = DBManager.getDefaultConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO rubrica (nome, cognome, telefono, email) VALUES (?, ?, ?, ?)");
            ps.setString(1, c.getNome());
            ps.setString(2, c.getCognome());
            ps.setString(3, c.getCell());
            ps.setString(4, c.getEmail());

            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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
        for (int i = (MAX_ENTRIES_PER_PAGE * page); i < (MAX_ENTRIES_PER_PAGE*(page+1)) && i < DBConsoleApp.resultList.size(); i++) {
            System.out.format("%10d%20s%20s%20s%35s\n",
                    DBConsoleApp.resultList.get(i).getID(),
                    DBConsoleApp.resultList.get(i).getNome(),
                    DBConsoleApp.resultList.get(i).getCognome(),
                    DBConsoleApp.resultList.get(i).getCell(),
                    DBConsoleApp.resultList.get(i).getEmail());
        }
        System.out.println();
        System.out.println("<- Prev --- [QUIT] --- Next ->");
    }

    /**
     * Save the table's column names.
     * @param rs The ResultSet from which we will take the column's names.
     */
    private static void saveColumnNames(ResultSet rs) {
        for (int i = 0; i < columnNames.length; ){
            try{
                columnNames[i] = rs.getMetaData().getColumnName(++i); // Faccio subito qui l'aumento perché almeno mi prende la colonna 1 nel DB (la 0 non esiste)
            } catch (SQLException e){
                System.err.println("Eccezione SQL cercando di salvare i nomi delle colonne.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Save the ResultSet to the global list.
     * @param rs The ResultSet returned from the DB.
     */
    private static void resultSetToList(ResultSet rs){
        saveColumnNames(rs);
        resultList.clear();
        try{
            while (rs.next()) {
                Contatto c = new Contatto();
                c.setNome(rs.getString("NOME"));
                c.setCognome(rs.getString("COGNOME"));
                c.setCell(rs.getString("TELEFONO"));
                c.setEmail(rs.getString("EMAIL"));
                c.setID(rs.getInt("ID"));
                resultList.add(c);
            }
        } catch (SQLException e){
            System.err.println("Eccezione SQL cercando di salvare il ResultSet nella Lista");
            e.printStackTrace();
        }
    }

    /**
     * Returns a single contact from the DB, chosen by ID.
     * @param id The id of the desired contact.
     * @return A Contatto object from the DB.
     */
    private static Contatto getContattoFromDB(String id){
        // Connette al DB
        try (Connection conn = DBManager.getDefaultConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM rubrica WHERE id = ?");
            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();
            Contatto c = new Contatto();

            // Controllo che ci sia una riga oltre all'intestazione. Se sì, so che qualcosa ha trovato.
            if (!rs.isBeforeFirst() ) {
                System.out.println("ID non presente.");
                return null;
            } else {
                // Se la query ha trovato qualcosa, so che è il contatto desiderato (avendo fatto per ID non posso avere più di un risultato.
                rs.next();
                c.setNome(rs.getString("NOME"));
                c.setCognome(rs.getString("COGNOME"));
                c.setCell(rs.getString("TELEFONO"));
                c.setEmail(rs.getString("EMAIL"));
                return c;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // In caso di eccezione o altro errore, ritorno un oggetto null.
        return null;
    }

    private DBConsoleApp(){}
}