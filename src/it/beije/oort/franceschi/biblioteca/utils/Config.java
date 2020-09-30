package it.beije.oort.franceschi.biblioteca.utils;

public class Config {
    private final static String PERSISTENCE_UNIT_NAME = "Biblioorteca";
    private final static int MAX_ENTRIES_PER_PAGE = 10;

    public static String getPersistenceUnitName() {
        return PERSISTENCE_UNIT_NAME;
    }

    public static int getMaxEntriesPerPage(){
        return MAX_ENTRIES_PER_PAGE;
    }
}
