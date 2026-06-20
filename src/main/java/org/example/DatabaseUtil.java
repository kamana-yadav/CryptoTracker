package org.example;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * DatabaseUtil — ek jagah se sab DB connections milte hain.
 * Password config.properties se padhta hai, hardcode nahi.
 */
public class DatabaseUtil {

    private static String url;
    private static String username;
    private static String password;

    static {
        try {
            Properties props = new Properties();
            InputStream input = DatabaseUtil.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            if (input == null) {
                throw new RuntimeException(
                        "config.properties nahi mila! " +
                        "src/main/resources/ mein check karo."
                );
            }

            props.load(input);
            url      = props.getProperty("db.url");
            username = props.getProperty("db.username");
            password = props.getProperty("db.password");

        } catch (Exception e) {
            System.err.println("Config load error: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(url, username, password);
    }
}
