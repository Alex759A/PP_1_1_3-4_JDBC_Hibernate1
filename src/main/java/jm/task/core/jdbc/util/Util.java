package jm.task.core.jdbc.util;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
//    // реализуйте настройку соеденения с БД

    private static final String URL="jdbc:mysql://localhost:3306/users";
    private static final String USE="root";
    private static final String PASSWORD="957SQlStepic_A";

    private static Util instance = null; // переменная тип Util
    private static Connection conn = null; // переменная тип Connection

    private Util() {
        try {
            conn = DriverManager.getConnection(URL, USE, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Util getInstance() { // возврат объект класса Util
        if (null == instance) {
            instance = new Util();
        }
        return instance;
    }
    public static Connection getConnection() { //

        return conn;
    }
}
