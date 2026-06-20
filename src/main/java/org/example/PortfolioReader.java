package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * PortfolioReader — DB se portfolio padhta hai.
 * Bug fix: pehle directly DriverManager.getConnection() call karta tha.
 * Ab DatabaseUtil.getConnection() use karta hai.
 */
public class PortfolioReader {

    public static void main(String[] args) {
        readPortfolio();
    }

    public static void readPortfolio() {
        try {
            Connection con = DatabaseUtil.getConnection();

            String sql = "SELECT * FROM portfolio";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("\n--- Aapka Portfolio ---");
            System.out.printf("%-15s %-10s %-12s%n", "Coin", "Quantity", "Buy Price");
            System.out.println("------------------------------------");

            boolean empty = true;
            while (rs.next()) {
                empty = false;
                System.out.printf("%-15s %-10.4f $%-12.2f%n",
                        rs.getString("coin_name"),
                        rs.getDouble("quantity"),
                        rs.getDouble("buy_price")
                );
            }

            if (empty) {
                System.out.println("Portfolio empty hai. Pehle coin add karo.");
            }

            con.close();

        } catch (Exception e) {
            System.err.println("Portfolio read error: " + e.getMessage());
        }
    }
}
