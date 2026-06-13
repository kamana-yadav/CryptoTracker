package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/crypto_tracker",
                    "root",
                    "MyNewPassword123"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}