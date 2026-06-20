package org.example;

import javax.swing.SwingUtilities;
import java.util.Scanner;

/**
 * Main — ek hi entry point.
 *
 * Merge: Root ka Main (Swing launcher) aur src ka Main (console menu) dono yahan hain.
 * Startup pe choice milti hai: GUI chalao ya console mode.
 *
 * CoinPrice.java delete kar diya — wo logic yahan case 3 mein absorb ho gaya.
 */
public class Main {

    private static final String[] COIN_IDS = {
            "bitcoin", "ethereum", "dogecoin", "solana", "cardano"
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("============================");
        System.out.println("   Crypto Tracker v2.0");
        System.out.println("============================");
        System.out.println("Mode choose karo:");
        System.out.println("1. GUI Mode (Swing Window)");
        System.out.println("2. Console Mode");
        System.out.print("Choice: ");

        String input = sc.nextLine().trim();

        if (input.equals("1")) {
            SwingUtilities.invokeLater(MainFrame::new);
        } else {
            runConsole(sc);
        }
    }

    private static void runConsole(Scanner sc) {
        while (true) {
            System.out.println("\n---- Crypto Tracker ----");
            System.out.println("1. Portfolio add karo");
            System.out.println("2. Portfolio dekho");
            System.out.println("3. Portfolio update karo");
            System.out.println("4. Portfolio se coin delete karo");
            System.out.println("5. Crypto price check karo");
            System.out.println("6. Live price DB mein save karo");
            System.out.println("7. Price graph dekho");
            System.out.println("8. Profit/Loss calculate karo");
            System.out.println("9. Exit");
            System.out.print("Choice: ");

            String line = sc.nextLine().trim();
            int choice;
            try { choice = Integer.parseInt(line); }
            catch (NumberFormatException e) { System.out.println("Sahi number dalo."); continue; }

            switch (choice) {

                case 1 -> AddPortfolio.main(null);

                case 2 -> PortfolioReader.readPortfolio();

                case 3 -> UpdatePortfolio.main(null);

                case 4 -> DeletePortfolio.main(null);

                case 5 -> {
                    try {
                        System.out.println("\nCoin choose karo:");
                        printCoinMenu();
                        int coinChoice = Integer.parseInt(sc.nextLine().trim());

                        if (coinChoice < 1 || coinChoice > COIN_IDS.length) {
                            System.out.println("Galat choice.");
                            break;
                        }
                        String coin = COIN_IDS[coinChoice - 1];

                        System.out.println("Currency: 1. INR   2. USD");
                        int c = Integer.parseInt(sc.nextLine().trim());
                        String currency = (c == 1) ? "inr" : "usd";

                        double price = CryptoAPI.getCryptoPrice(coin, currency);
                        String symbol = currency.equals("inr") ? "₹" : "$";
                        System.out.println(coin.toUpperCase() + " Price = " + symbol + price);

                    } catch (Exception e) {
                        System.err.println("Price fetch error: " + e.getMessage());
                    }
                }

                case 6 -> SaveLivePrice.main(null);

                case 7 -> PriceGraph.main(null);

                case 8 -> ProfitCalculator.main(null);

                case 9 -> {
                    System.out.println("Bye!");
                    System.exit(0);
                }

                default -> System.out.println("1-9 ke beech choice dalo.");
            }
        }
    }

    private static void printCoinMenu() {
        for (int i = 0; i < COIN_IDS.length; i++) {
            System.out.println((i + 1) + ". " + COIN_IDS[i]);
        }
    }
}
