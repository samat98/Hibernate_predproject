package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

//    Session session = sessionFactory.openSession();
//    session.beginTransaction();
//    Customer user = new Customer(); //Note customer is a POJO maps to the customer table in the database.
//
//    user.setName("test");
//    user.setisActive(true);
//    session.save(user);
//    session.getTransaction().commit();

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Users (" +
                "id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(30) NOT NULL," +
                "lastName VARCHAR(30) NOT NULL," +
                "age INT(3) NOT NULL" +
                ") ENGINE=InnoDB";
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();
        session.getTransaction().commit();

    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP table IF EXISTS Users";

        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from User where id= :id");
        query.setLong("id", id);
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        Query<User> query  = session.createQuery("select e from User e");
        List<User> list = query.list();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from User");
        query.executeUpdate();
        session.getTransaction().commit();
    }
}
