package org.example;

import java.util.Scanner;

public class CoinPrice {


    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);


        System.out.println("Choose Coin:");

        System.out.println("1. Bitcoin");
        System.out.println("2. Ethereum");
        System.out.println("3. Dogecoin");
        System.out.println("4. Solana");
        System.out.println("5. Cardano");


        int choice = sc.nextInt();


        String coin;


        switch(choice){

            case 1:
                coin = "bitcoin";
                break;

            case 2:
                coin = "ethereum";
                break;

            case 3:
                coin = "dogecoin";
                break;

            case 4:
                coin = "solana";
                break;

            case 5:
                coin = "cardano";
                break;

            default:
                System.out.println("Wrong choice");
                return;
        }



        System.out.println("Choose Currency:");

        System.out.println("1. INR");
        System.out.println("2. USD");


        int c = sc.nextInt();


        String currency;


        if(c==1){
            currency="inr";
        }
        else{
            currency="usd";
        }



        try{


            double price =
                    CryptoAPI.getCryptoPrice(
                            coin,
                            currency
                    );


            System.out.println(
                    coin.toUpperCase()
                            +" Price = "
                            + price
            );


        }
        catch(Exception e){

            e.printStackTrace();

        }

    }
}