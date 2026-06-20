package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * UpdatePortfolio — portfolio mein coin ki quantity update karta hai.
 */
public class UpdatePortfolio {

    public static void updateQuantity(String coinName, double newQuantity) {
        try {
            Connection con = DatabaseUtil.getConnection();

            String sql = "UPDATE portfolio SET quantity=? WHERE LOWER(coin_name)=LOWER(?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDouble(1, newQuantity);
            ps.setString(2, coinName);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println(coinName + " ki quantity update ho gayi: " + newQuantity);
            } else {
                System.out.println(coinName + " portfolio mein nahi mila.");
            }

            con.close();

        } catch (Exception e) {
            System.err.println("Update error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Coin name: ");
        String coinName = sc.nextLine().trim();

        System.out.print("Nayi quantity: ");
        double qty = sc.nextDouble();

        updateQuantity(coinName, qty);
    }
}
