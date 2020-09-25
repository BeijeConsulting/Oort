package it.beije.oort.file.rubrica.consoleapp;

import it.beije.oort.file.rubrica.Contatto;
import it.beije.oort.file.rubrica.consoleapp.utils.ConsoleAppUtils;
import it.beije.oort.file.rubrica.consoleapp.utils.DBConsoleAppUtils;
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

public class DBConsoleApp {
    ///////////////////////////////////////////////
    // GLOBAL STATIC VARS
    ///////////////////////////////////////////////
    private static final  Scanner sc = new Scanner(System.in);
    private static final List<Contatto> resultList = new ArrayList<>();
    private static final String[] columnNames = new String[5];
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
            line = sc.nextLine().toLowerCase();

            // Verify the input
            if (!DBConsoleAppUtils.isValidInput(line)) {
                System.out.println("Input non valido");
                continue;
            }

            // Do things depending on input
            switch (line) {
                case "help":
                    DBConsoleAppUtils.showHelp();
                    break;
                case "add":
                    add();
                    break;
                case "listall":
                    getAllFromDB(true);
                    break;
                case "search":
                    search();
                    break;
                case "modify":
                    modify();
                    break;
                case "delete":
                    delete();
                    break;
                case "export":
                    export();
                    break;
                case "import":
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
        System.out.println("Inserisci nome del file da importaee con l'estensione.");
        System.out.println("Il file si deve trovare in questa cartella: " + DBConsoleAppUtils.getOutputPath());
        String inputFile = sc.nextLine();
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
        String outputFile = sc.nextLine();
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
        String id = sc.nextLine();
        Contatto c = getContattoFromDB(id);
        if (c == null) {
            return;
        }
        System.out.println("Hai selezionato questo contatto: " + c.toString());
        System.out.println("Vuoi davvero cancellarlo? Scrivi [yes] per confermare.");
        if (sc.nextLine().equalsIgnoreCase("yes")){
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
        String id = sc.nextLine();
        Contatto c = getContattoFromDB(id);
        if (c == null) {
            return;
        }
        System.out.println("Hai selezionato questo contatto: " + c.toString());

        // Modify the contact here
        String s;
        do {
            System.out.println("Cosa vuoi modificare? [N]ome, [C]ognome, [T]elefono, [E]mail. [Done] per concludere");
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
     */
    private static void search(){
        System.out.println("In che campo vuoi cercare? Opzioni: ID, NOME, COGNOME, EMAIL, TELEFONO");
        String searchColumn = sc.nextLine();
        if (DBConsoleAppUtils.isValidColumn(searchColumn)){
            System.out.println("Cosa vuoi cercare?");
            String searchQuery = sc.nextLine();
            searchBy(searchColumn, searchQuery);
        } else {
            System.out.println("Nome del campo non valido.");
        }
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
     * Main method to paginate query results. It's basically used to increment or decrement the page.
     */
    private static void pageManager(){
        int page = 0;
        String command = "";
        // Loop until the user wants to exit
        while (!command.equals("quit")){
            listPage(page);
            command = sc.nextLine();
            switch (command){
                // Will only go next if there's actually some entries in the possible "next" page
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
        for (int i = 0; i < columnNames.length; i++){
            try{
                columnNames[i] = rs.getMetaData().getColumnName(i+1); // +1 perché saltiamo lo 0, inesistente. I DB partono da 1.
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    private static void resultSetToList(ResultSet rs) throws SQLException {
        saveColumnNames(rs);
        resultList.clear();
        while (rs.next()) {
            Contatto c = new Contatto();
            c.setNome(rs.getString("NOME"));
            c.setCognome(rs.getString("COGNOME"));
            c.setCell(rs.getString("TELEFONO"));
            c.setEmail(rs.getString("EMAIL"));
            c.setID(rs.getInt("ID"));
            resultList.add(c);
        }
    }

    private static Contatto getContattoFromDB(String id){
        try (Connection conn = DBManager.getDefaultConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM rubrica WHERE id = ?");
            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();
            Contatto c = new Contatto();
            if (!rs.isBeforeFirst() ) {
                System.out.println("ID non presente.");
                return null;
            } else {
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
        return null;
    }
}
