package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {

        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

        userDaoHibernate.createUsersTable();

        userDaoHibernate.saveUser("samat", "kur", (byte) 22);
        userDaoHibernate.saveUser("Oskar", "miladze", (byte) 20);
        userDaoHibernate.saveUser("Uli", "Chaba", (byte) 23);
        userDaoHibernate.saveUser("Jokish", "kubanov", (byte) 22);

        userDaoHibernate.getAllUsers().forEach(System.out::println);

        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();

    }
}
