package org.example;

import java.sql.*;

public class SavePriceHistory {

    public static void main(String[] args) {

        try {

            Connection con = DatabaseUtil.getConnection();

            String sql =
                    "INSERT INTO price_history(coin_name,price) VALUES(?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1,"Bitcoin");
            ps.setDouble(2,9500000);

            ps.executeUpdate();

            System.out.println("Price Saved");

            con.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}