package org.example;

import java.sql.*;

public class PortfolioReader {

    public static void main(String[] args) {

        try {

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/crypto_tracker",
                    "root",
                    "MyNewPassword123"
            );

            String sql = "SELECT * FROM portfolio";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                System.out.println(
                        rs.getString("coin_name")
                                + " "
                                + rs.getDouble("quantity")
                                + " "
                                + rs.getDouble("buy_price")
                );
            }

            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}