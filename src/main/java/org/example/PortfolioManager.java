package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * PortfolioManager — in-memory portfolio manage karta hai (Swing UI ke liye).
 * Fix: getName(), getPrice() etc. correct getters use karta hai.
 */
public class PortfolioManager {

    private List<CryptoData> portfolio = new ArrayList<>();

    public void addCoin(CryptoData coin) {
        portfolio.add(coin);
    }

    public void removeCoin(String name) {
        portfolio.removeIf(c -> c.getName().equalsIgnoreCase(name));
    }

    public List<CryptoData> getPortfolio() {
        return portfolio;
    }

    public void displayPortfolio() {
        if (portfolio.isEmpty()) {
            System.out.println("Portfolio empty hai.");
            return;
        }
        for (CryptoData coin : portfolio) {
            coin.displayInfo();
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (CryptoData coin : portfolio) {
            total += coin.getPrice();
        }
        return total;
    }
}
