package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBConnection {

    public static void main(String[] args) {

        try {

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/crypto_tracker",
                    "root",
                    "MyNewPassword123"
            );

            String sql =
                    "INSERT INTO favourites(coin_name) VALUES(?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, "Bitcoin");

            ps.executeUpdate();

            System.out.println("Data Inserted!");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}