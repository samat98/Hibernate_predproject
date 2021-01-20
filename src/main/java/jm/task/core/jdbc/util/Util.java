package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_mentor?useSSL=false",
                "root", "12345");
        return con;
    }

    public static SessionFactory getSessionFactory() {
        Properties prop= new Properties();
        prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/java_mentor");

        prop.setProperty("dialect", "org.hibernate.dialect.MySQL8Dialect");

        prop.setProperty("hibernate.connection.username", "root");
        prop.setProperty("hibernate.connection.password", "12345");
        prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");

        Configuration configuration = new Configuration();
        configuration.setProperties(prop);
        configuration.addAnnotatedClass(User.class);
        SessionFactory sessionFactory =  configuration.buildSessionFactory();
        return sessionFactory;
    }
}
