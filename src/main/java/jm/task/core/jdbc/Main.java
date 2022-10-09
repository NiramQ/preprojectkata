package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // реализуйте алгоритм здесь

        /*

        Connection connection;
        Util util = new Util();
        connection = util.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM Usersmod";
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            int uid = rs.getInt(1);
            String uName = rs.getString(2);
            String uLastName = rs.getString(3);
            int age = rs.getByte(4);
            System.out.printf("%d: %s, %s, возраст %d", uid, uName, uLastName, age);
        }

         */
        UserServiceImpl x = new UserServiceImpl();
        x.createUsersTable();
        x.saveUser("Олег","Вещий",(byte)10);
        x.saveUser("Ярослав","Мудрый",(byte)20);
        x.saveUser("Владимир","Мономах",(byte)30);
        x.saveUser("Юрий","Долгорукий",(byte)40);
        System.out.println(x.getAllUsers());
        x.cleanUsersTable();
        x.dropUsersTable();
    }
}
