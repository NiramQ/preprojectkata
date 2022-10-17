package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Util {
    private static String hostName = "localhost";
    private static String dbName = "mydatabase";
    private static String userName = "root";
    private static String password = "root";

    public Util() {
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        return this.getConnection(hostName, dbName, userName, password);
    }

    public Connection getConnection(String hostname, String dbName, String userName, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String connectionURL = "jdbc:mysql://" + hostname + ":3306/" + dbName;
        return DriverManager.getConnection(connectionURL, userName, password);
    }

    public SessionFactory getSessionFactory() {
        try {
            return (new Configuration()).addAnnotatedClass(User.class).addProperties(this.propertiesHibernate()).buildSessionFactory();
        } catch (Throwable var2) {
            System.err.println("SessionFactory creation failed" + String.valueOf(var2));
            throw new ExceptionInInitializerError(var2);
        }
    }

    private Properties propertiesHibernate() {
        Properties prop = new Properties();
        prop.setProperty("hibernate.connection.url", "jdbc:mysql://" + hostName + ":3306/" + dbName);
        prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        prop.setProperty("hibernate.connection.username", userName);
        prop.setProperty("hibernate.connection.password", password);
        prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        prop.setProperty("hibernate.hbm2ddl.auto", "none");
        return prop;
    }
}