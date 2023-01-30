package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;


public class Main {
    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) throws SQLException {
        userService.createUsersTable();

        userService.saveUser("Ivan", "Ivanov", (byte) 33);
        userService.saveUser("Petr", "Petrov", (byte) 25);
        userService.saveUser("Egor", "Egorov", (byte) 55);
        userService.saveUser("Sergey", "Sergeev", (byte) 44);

        userService.removeUserById(2);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }

}
