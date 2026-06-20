package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * PriceGraph — DB mein saved price history ka line chart dikhata hai.
 */
public class PriceGraph {

    public static void showGraph(String coin) {
        try {
            Connection con = DatabaseUtil.getConnection();

            String sql = "SELECT price FROM price_history WHERE LOWER(coin_name)=LOWER(?) ORDER BY id ASC";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, coin);
            ResultSet rs = ps.executeQuery();

            DefaultCategoryDataset data = new DefaultCategoryDataset();
            int day = 1;

            while (rs.next()) {
                double price = rs.getDouble("price");
                data.addValue(price, coin.toUpperCase(), "Day " + day);
                day++;
            }

            con.close();

            if (day == 1) {
                System.out.println(coin + " ke liye koi price history nahi mili. Pehle 'Save Live Price' chalao.");
                return;
            }

            JFreeChart chart = ChartFactory.createLineChart(
                    coin.toUpperCase() + " Price History",
                    "Time",
                    "Price (USD)",
                    data
            );

            ChartFrame frame = new ChartFrame("Crypto Tracker — " + coin.toUpperCase(), chart);
            frame.setSize(800, 500);
            frame.setVisible(true);

        } catch (Exception e) {
            System.err.println("Graph error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Coin choose karo:");
        System.out.println("1. Bitcoin   2. Ethereum   3. Dogecoin   4. Solana   5. Cardano");

        int choice = sc.nextInt();
        String coin = switch (choice) {
            case 1 -> "bitcoin";
            case 2 -> "ethereum";
            case 3 -> "dogecoin";
            case 4 -> "solana";
            case 5 -> "cardano";
            default -> {
                System.out.println("Galat choice");
                yield null;
            }
        };

        if (coin != null) showGraph(coin);
    }
}
