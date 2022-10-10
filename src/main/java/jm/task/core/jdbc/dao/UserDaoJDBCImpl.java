package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private List<User> listUser = new ArrayList<>();


    public UserDaoJDBCImpl() {

    }

    Util util = new Util();

    public void createUsersTable() {
        String sql = "CREATE TABLE if not exists Usersmod (id BIGINT UNSIGNED auto_increment, name VARCHAR(50), lastName VARCHAR(50), age TINYINT UNSIGNED, PRIMARY KEY (id));";
        try (PreparedStatement ps = util.getConnection().prepareStatement(sql)) {
            ps.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE if exists Usersmod;";
        try (PreparedStatement ps = util.getConnection().prepareStatement(sql)) {
            ps.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO Usersmod (name, lastName, age) VALUES ('" + name + "', '" + lastName + "', " + age + ");";
        try (PreparedStatement ps = util.getConnection().prepareStatement(sql)) {
            ps.executeUpdate(sql);
            System.out.println("User с именем – " + name + " " + lastName + " добавлен в базу данных");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM usersmod WHERE id = " + id + ";";
        try (PreparedStatement ps = util.getConnection().prepareStatement(sql)) {
            ps.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM Usersmod";
        try (PreparedStatement ps = util.getConnection().prepareStatement(sql)) {
            ps.executeQuery(sql);
            while (ps.getResultSet().next()) {
                long uid = ps.getResultSet().getLong(1);
                String uName = ps.getResultSet().getString(2);
                String uLastName = ps.getResultSet().getString(3);
                byte age = ps.getResultSet().getByte(4);
                User user = new User(uName, uLastName, age);
                user.setId(uid);
                listUser.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return listUser;
    }

    public void cleanUsersTable() {
        String sql = "DROP TABLE if exists usersmod";
        try (PreparedStatement ps = util.getConnection().prepareStatement(sql)) {
            ps.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
