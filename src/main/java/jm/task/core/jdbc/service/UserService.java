package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;

import java.util.List;

public interface UserService {
    void createUsersTable(); // создание таблицы

    void dropUsersTable(); // удаление записи о пользователе

    void saveUser(String name, String lastName, byte age); // сохранение данных в таблице

    void removeUserById(long id); // изменение данных по ID

    List<User> getAllUsers(); // вывод всех Users

    void cleanUsersTable();  // очистить базу
}
