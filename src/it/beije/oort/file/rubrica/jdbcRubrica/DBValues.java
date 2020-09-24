package it.beije.oort.file.rubrica.jdbcRubrica;

public class DBValues {
    private static final String XML_PATH = "C:\\Code\\Oort\\xml\\rubriche\\rubrica_sala.xml";
    private static final String CSV_PATH = "C:\\Code\\Oort\\csv\\rubriche\\rubrica_sala.csv";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Beije09";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET";

    public static String getXmlPath() {
        return XML_PATH;
    }

    public static String getCsvPath() {
        return CSV_PATH;
    }

    public static String getDbUser() {
        return DB_USER;
    }

    public static String getDbPassword() {
        return DB_PASSWORD;
    }

    public static String getDbUrl() {
        return DB_URL;
    }
}
