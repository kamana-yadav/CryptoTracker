package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * SaveLivePrice — CoinGecko se real-time price fetch karke DB mein save karta hai.
 *
 * Merge: SavePriceHistory.java (jo hardcoded price save karta tha) delete kar diya.
 * Ye class dono kaam karti hai — live API price save karta hai.
 */
public class SaveLivePrice {

    public static void saveLivePrice(String coinId, String currency) {
        try {
            double price = CryptoAPI.getCryptoPrice(coinId, currency);

            Connection con = DatabaseUtil.getConnection();

            String sql = "INSERT INTO price_history(coin_name, price) VALUES(?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, coinId);
            ps.setDouble(2, price);
            ps.executeUpdate();

            String symbol = currency.equalsIgnoreCase("inr") ? "₹" : "$";
            System.out.println(coinId.toUpperCase()
                    + " live price saved: " + symbol + price);

            con.close();

        } catch (Exception e) {
            System.err.println("Price save error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Coin ID enter karo (e.g. bitcoin, ethereum, dogecoin):");
        String coin = sc.nextLine().trim().toLowerCase();

        System.out.println("Currency (inr/usd):");
        String currency = sc.nextLine().trim().toLowerCase();
        if (!currency.equals("inr") && !currency.equals("usd")) {
            currency = "inr";
        }

        saveLivePrice(coin, currency);
    }
}
