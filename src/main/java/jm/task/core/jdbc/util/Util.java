package jm.task.core.jdbc.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    static private String hostName = "localhost";
    static private String dbName = "mydatabase";
    static private String userName = "root";
    static private String password = "root";

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        return getConnection(hostName, dbName, userName, password);
    }

    public Connection getConnection(String hostname, String dbName, String userName, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String connectionURL = "jdbc:mysql://" + hostname + ":3306/" + dbName;
        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }

    public class HibUtil {
        static private Properties propertiesHibernate() {
            Properties prop = new Properties();
            prop.setProperty("hibernate.connection.url", "jdbc:mysql://" + hostName + ":3306/" + dbName);
            prop.setProperty("dialect", "org.hibernate.dialect.MySQL");
            prop.setProperty("hibernate.connection.username", userName);
            prop.setProperty("hibernate.connection.password", password);
            prop.setProperty("hibernate.connection.driver_class", "org.MySQL.Driver");
            return prop;
        }

        private static final SessionFactory sessionFactory;

        static {
            try {
                sessionFactory = new Configuration().addProperties(propertiesHibernate()).configure().buildSessionFactory();
            } catch (Throwable ex) {
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        public static SessionFactory getSessionFactory() {
            return sessionFactory;
        }
    }
}
