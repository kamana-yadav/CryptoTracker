package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * DeletePortfolio — portfolio se coin delete karta hai.
 */
public class DeletePortfolio {

    public static void deleteCoin(String coinName) {
        try {
            Connection con = DatabaseUtil.getConnection();

            String sql = "DELETE FROM portfolio WHERE LOWER(coin_name) = LOWER(?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, coinName);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println(coinName + " portfolio se delete ho gaya.");
            } else {
                System.out.println(coinName + " portfolio mein nahi mila.");
            }

            con.close();

        } catch (Exception e) {
            System.err.println("Delete error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Kaun sa coin delete karna hai? ");
        String coinName = sc.nextLine().trim();
        deleteCoin(coinName);
    }
}
