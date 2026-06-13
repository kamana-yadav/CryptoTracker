package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class SaveLivePrice {

    public static void main(String[] args) {

        try {

            Scanner sc = new Scanner(System.in);


            System.out.println("Enter coin name:");
            System.out.println("bitcoin");
            System.out.println("ethereum");
            System.out.println("dogecoin");
            System.out.println("solana");
            System.out.println("cardano");


            String coin = sc.nextLine();



            String currency = "inr";


            double price =
                    CryptoAPI.getCryptoPrice(
                            coin,
                            currency
                    );



            Connection con =
                    DatabaseUtil.getConnection();



            String sql =
                    "INSERT INTO price_history(coin_name,price) VALUES(?,?)";



            PreparedStatement ps =
                    con.prepareStatement(sql);



            ps.setString(1, coin);

            ps.setDouble(2, price);



            ps.executeUpdate();



            System.out.println(
                    coin.toUpperCase()
                            +" Live Price Saved: ₹"
                            +price
            );



            con.close();



        } catch(Exception e){

            e.printStackTrace();

        }

    }
}