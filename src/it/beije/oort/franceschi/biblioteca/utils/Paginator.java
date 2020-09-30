package it.beije.oort.franceschi.biblioteca.utils;

import it.beije.oort.franceschi.biblioteca.controller.Main;
import it.beije.oort.franceschi.jdbcRubrica.cli.DBConsoleAppUtils;

import java.util.Scanner;

/**
 * Per avere la paginazione bella ordinata, devo fare metodi custom a seconda degli oggetti da paginare
 */
public class Paginator {
    private final static Scanner sc = new Scanner(System.in);
    ///////////////////////////////////////////////
    // PAGINAZIONE
    ///////////////////////////////////////////////

    /**
     * Main method to paginate query results. It's basically used to increment or decrement the page.
     */
    public static void pageManager(){
        int page = 0;
        String command = "";

        if (Main.resultList.size() <= 10){
            listPage(page);
            return;
        }
        while (!command.equals("esci")){
            listPage(page);
            command = sc.nextLine().trim();
            switch (command){
                // Will only go next if there's actually some entries in the possible "next" page
                case "": //Did this so i can also press ENTER to go to the next page. Saves time
                case "next":
                    if (page < (Main.resultList.size() / Config.getMaxEntriesPerPage())) page++;
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
        for (int i = (Config.getMaxEntriesPerPage() * page);
             i < (Config.getMaxEntriesPerPage()*(page+1)) && i < Main.resultList.size();
             i++) {
            System.out.println(Main.resultList.get(i));
        }
        if (Main.resultList.size() > 10){
            System.out.println("[Prev] per la pagina precedente, [Next] o INVIO per la successiva. [ESCI] per uscire.");
            System.out.println("<- Prev --- [ESCI] --- Next ->");
        }
        System.out.println();
    }
}
