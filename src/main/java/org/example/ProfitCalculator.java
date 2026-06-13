package org.example;

public class ProfitCalculator {

    public static void main(String[] args) {

        double quantity = 0.5;
        double buyPrice = 8000000;
        double currentPrice = 9500000;

        double profit = (currentPrice - buyPrice) * quantity;

        System.out.println("Profit = ₹" + profit);
    }
}