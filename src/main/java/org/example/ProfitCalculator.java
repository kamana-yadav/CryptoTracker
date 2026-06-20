package org.example;

import java.util.Scanner;

/**
 * ProfitCalculator — buy price aur current price se profit/loss calculate karta hai.
 */
public class ProfitCalculator {

    public static double calculate(double quantity, double buyPrice, double currentPrice) {
        return (currentPrice - buyPrice) * quantity;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Coin quantity (e.g. 0.5): ");
        double quantity = sc.nextDouble();

        System.out.print("Buy price (jis price pe khareeda tha): ");
        double buyPrice = sc.nextDouble();

        System.out.print("Current price (aaj ka): ");
        double currentPrice = sc.nextDouble();

        double profit = calculate(quantity, buyPrice, currentPrice);

        if (profit >= 0) {
            System.out.printf("Profit: $%.2f%n", profit);
        } else {
            System.out.printf("Loss: $%.2f%n", Math.abs(profit));
        }
    }
}
