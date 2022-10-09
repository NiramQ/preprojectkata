package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserServiceImpl x = new UserServiceImpl();
        x.createUsersTable();
        x.saveUser("Олег", "Вещий", (byte) 10);
        x.saveUser("Ярослав", "Мудрый", (byte) 20);
        x.saveUser("Владимир", "Мономах", (byte) 30);
        x.saveUser("Юрий", "Долгорукий", (byte) 40);
        System.out.println(x.getAllUsers());
        x.cleanUsersTable();
        x.dropUsersTable();
    }
}
