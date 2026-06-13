package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while(true){

            System.out.println("\n---- Crypto Tracker ----");
            System.out.println("1. Add Portfolio");
            System.out.println("2. View Portfolio");
            System.out.println("3. Check Crypto Price");
            System.out.println("4. Show Graph");
            System.out.println("5. Save Live Price");
            System.out.println("6. Exit");


            int choice = sc.nextInt();


            switch(choice){


                case 1:
                    AddPortfolio.main(null);
                    break;


                case 2:
                    PortfolioReader.main(null);
                    break;



                case 3:

                    try{


                        System.out.println("Choose Coin:");

                        System.out.println("1. Bitcoin");
                        System.out.println("2. Ethereum");
                        System.out.println("3. Dogecoin");
                        System.out.println("4. Solana");
                        System.out.println("5. Cardano");


                        int coinChoice = sc.nextInt();


                        String coin;


                        switch(coinChoice){

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
                                continue;
                        }



                        System.out.println("Choose Currency:");

                        System.out.println("1. INR");
                        System.out.println("2. USD");


                        int c = sc.nextInt();


                        String currency;


                        if(c == 1){
                            currency = "inr";
                        }
                        else{
                            currency = "usd";
                        }



                        double price =
                                CryptoAPI.getCryptoPrice(
                                        coin,
                                        currency
                                );


                        if(currency.equals("inr")){

                            System.out.println(
                                    coin.toUpperCase()
                                            +" Price = ₹"
                                            + price
                            );

                        }
                        else{

                            System.out.println(
                                    coin.toUpperCase()
                                            +" Price = $"
                                            + price
                            );
                        }



                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                    break;




                case 4:
                    PriceGraph.main(null);
                    break;



                case 5:
                    SaveLivePrice.main(null);
                    break;



                case 6:
                    System.exit(0);

            }

        }
    }
}