
package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddPortfolio {

    public static void main(String[] args) {

        try {

            Connection con = DatabaseUtil.getConnection();

            String sql =
                    "INSERT INTO portfolio(coin_name, quantity, buy_price) VALUES(?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, "Ethereum");
            ps.setDouble(2, 2.0);
            ps.setDouble(3, 250000);

            ps.executeUpdate();

            System.out.println("Portfolio Added Successfully!");

            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}