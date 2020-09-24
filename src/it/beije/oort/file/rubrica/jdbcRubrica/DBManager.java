package it.beije.oort.file.rubrica.jdbcRubrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    public static Connection getConnection(String url, String user, String pw) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(url, user, pw);
    }

    public static Connection getDefaultConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(DBValues.getDbUrl(), DBValues.getDbUser(), DBValues.getDbPassword());
    }
}
