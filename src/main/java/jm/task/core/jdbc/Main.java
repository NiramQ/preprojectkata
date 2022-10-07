package jm.task.core.jdbc;

import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // реализуйте алгоритм здесь
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
    }
}
