package org.example;

import java.sql.*;

public class ReadData {

    public static void main(String[] args) {

        try {

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/crypto_tracker",
                    "root",
                    "MyNewPassword123"
            );

            String sql = "SELECT * FROM favourites";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()) {

                System.out.println(
                        rs.getInt("id")
                                + " "
                                + rs.getString("coin_name")
                );
            }

            con.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}