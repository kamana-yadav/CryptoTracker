package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.sql.*;
import java.util.Scanner;


public class PriceGraph {


    public static void showGraph(String coin) {


        try {


            Connection con =
                    DatabaseUtil.getConnection();


            String sql =
                    "SELECT * FROM price_history WHERE coin_name=?";


            PreparedStatement ps =
                    con.prepareStatement(sql);


            ps.setString(1, coin);


            ResultSet rs =
                    ps.executeQuery();



            DefaultCategoryDataset data =
                    new DefaultCategoryDataset();



            int day = 1;



            while(rs.next()){


                double price =
                        rs.getDouble("price");


                data.addValue(
                        price,
                        coin.toUpperCase(),
                        "Day " + day
                );


                day++;

            }



            JFreeChart chart =
                    ChartFactory.createLineChart(
                            coin.toUpperCase()
                                    +" Price History",
                            "Time",
                            "Price",
                            data
                    );



            ChartFrame frame =
                    new ChartFrame(
                            "Crypto Tracker Graph",
                            chart
                    );


            frame.setSize(700,500);

            frame.setVisible(true);



        }
        catch(Exception e){

            e.printStackTrace();

        }

    }





    public static void main(String[] args){


        Scanner sc = new Scanner(System.in);


        System.out.println("Choose Coin:");

        System.out.println("1. Bitcoin");
        System.out.println("2. Ethereum");
        System.out.println("3. Dogecoin");
        System.out.println("4. Solana");
        System.out.println("5. Cardano");


        int choice =
                sc.nextInt();



        String coin;


        switch(choice){


            case 1:
                coin="bitcoin";
                break;


            case 2:
                coin="ethereum";
                break;


            case 3:
                coin="dogecoin";
                break;


            case 4:
                coin="solana";
                break;


            case 5:
                coin="cardano";
                break;


            default:
                System.out.println("Wrong choice");
                return;

        }



        showGraph(coin);

    }
}