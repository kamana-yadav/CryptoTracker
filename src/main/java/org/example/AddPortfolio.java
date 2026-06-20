package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * AddPortfolio — DB mein naya coin add karta hai.
 * Bug fix: pehle hardcoded "Ethereum" tha. Ab user se input leta hai.
 */
public class AddPortfolio {

    public static void addCoin(String coinName, double quantity, double buyPrice) {
        try {
            Connection con = DatabaseUtil.getConnection();

            String sql = "INSERT INTO portfolio(coin_name, quantity, buy_price) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, coinName);
            ps.setDouble(2, quantity);
            ps.setDouble(3, buyPrice);

            ps.executeUpdate();
            System.out.println(coinName + " portfolio mein add ho gaya!");

            con.close();

        } catch (Exception e) {
            System.err.println("Portfolio add error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Coin name (e.g. Bitcoin): ");
        String coinName = sc.nextLine().trim();

        System.out.print("Quantity (e.g. 0.5): ");
        double quantity = sc.nextDouble();

        System.out.print("Buy price (USD mein): ");
        double buyPrice = sc.nextDouble();

        addCoin(coinName, quantity, buyPrice);
    }
}
