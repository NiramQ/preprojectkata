package jm.task.core.jdbc.dao;

import java.util.ArrayList;
import java.util.List;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDaoHibernateImpl implements UserDao {
    private List<User> users = new ArrayList();
    private final Util util = new Util();
    private Transaction tx;

    public UserDaoHibernateImpl() {
    }

    public void createUsersTable() {
        Session session = this.util.getSessionFactory().openSession();

        try {
            this.tx = session.beginTransaction();
            String sql = "create table IF not exists Usersmod (id BIGINT auto_increment , name VARCHAR(50), lastName VARCHAR(50), age TINYINT UNSIGNED, PRIMARY KEY (id));";
            session.createSQLQuery(sql).executeUpdate();
            this.tx.commit();
        } catch (Exception e) {
            if (this.tx != null) {
                this.tx.rollback();
            }

            throw e;
        } finally {
            session.close();
        }

    }

    public void dropUsersTable() {
        Session session = this.util.getSessionFactory().openSession();

        try {
            this.tx = session.beginTransaction();
            String sql = "drop table if exists Usersmod;";
            session.createSQLQuery(sql).executeUpdate();
            this.tx.commit();
        } catch (Exception e) {
            if (this.tx != null) {
                this.tx.rollback();
            }

            throw e;
        } finally {
            session.close();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        Session session = this.util.getSessionFactory().openSession();

        try {
            this.tx = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            this.tx.commit();
        } catch (Exception e) {
            if (this.tx != null) {
                this.tx.rollback();
            }

            throw e;
        } finally {
            session.close();
        }

    }

    public void removeUserById(long id) {
        Session session = this.util.getSessionFactory().openSession();

        try {
            this.tx = session.beginTransaction();
            User user = (User) session.load(User.class, id);
            session.delete(user);
            this.tx.commit();
        } catch (Exception e) {
            if (this.tx != null) {
                this.tx.rollback();
            }

            throw e;
        } finally {
            session.close();
        }

    }

    public List<User> getAllUsers() {
        Session session = this.util.getSessionFactory().openSession();

        try {
            this.tx = session.beginTransaction();
            this.users = session.createSQLQuery("SELECT * FROM Usersmod").addEntity(User.class).list();
            this.tx.commit();
        } catch (Exception e) {
            if (this.tx != null) {
                this.tx.rollback();
            }

            throw e;
        } finally {
            session.close();
        }

        return this.users;
    }

    public void cleanUsersTable() {
        Session session = this.util.getSessionFactory().openSession();

        try {
            this.tx = session.beginTransaction();
            String sql = "truncate TABLE Usersmod;";
            session.createSQLQuery(sql).executeUpdate();
            this.tx.commit();
        } catch (Exception e) {
            if (this.tx != null) {
                this.tx.rollback();
            }

            throw e;
        } finally {
            session.close();
        }

    }
}