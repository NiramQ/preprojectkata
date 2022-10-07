package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String hostName = "localhost";
        String dbName = "mydatabase";
        String userName = "root";
        String password = "root";
        return getConnection(hostName, dbName, userName, password);
    }
    public Connection getConnection(String hostname, String dbName, String userName, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String connectionURL = "jdbc:mysql://" + hostname + ":3306/" + dbName;
        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }
}
