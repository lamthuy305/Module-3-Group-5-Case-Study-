package com.codegym.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/stone_management";
    public static final String JDBC_USERNAME = "root";
<<<<<<< HEAD
    public static final String JDBC_PASSWORD = "anhthuy12";
=======
    public static final String JDBC_PASSWORD = "123456";
<<<<<<< HEAD
=======
    public static final String JDBC_PASSWORD1 = "anhtinh9x";

>>>>>>> 9b0f5c7d929f1a0893f7c21860c78619ec320bfc
>>>>>>> devcuong

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
