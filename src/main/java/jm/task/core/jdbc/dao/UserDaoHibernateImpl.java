package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.query.Query;

import java.sql.SQLException;
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
        String sql = "CREATE TABLE Users (" +
                "id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(30) NOT NULL," +
                "lastName VARCHAR(30) NOT NULL," +
                "age INT(3) NOT NULL" +
                ") ENGINE=InnoDB";
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery(sql);
        try {
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
        }
        session.close();
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP table Users";

        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery(sql);
        try {
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
        }
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        try {
            session.save(user);
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
        }
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from User where id= :id");
        query.setLong("id", id);
        try {
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
        }
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        Query<User> query  = session.createQuery("select e from User e");
        List<User> list = query.list();
        session.close();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from User");
        try {
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
        }
        session.close();
    }
}
