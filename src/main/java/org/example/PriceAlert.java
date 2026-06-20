package org.example;

/**
 * PriceAlert — jab coin ek price pe pahunche to alert karta hai.
 *
 * Bug fix: pehle checkAlert(target - 100) call hota tha (fake price).
 * Ab real API se current price fetch karke check karta hai.
 */
public class PriceAlert {

    private String coinName;
    private double targetPrice;

    public PriceAlert(String coinName, double targetPrice) {
        this.coinName    = coinName;
        this.targetPrice = targetPrice;
    }

    public String getCoinName()    { return coinName; }
    public double getTargetPrice() { return targetPrice; }

    /**
     * API se real price fetch karta hai aur check karta hai.
     * @return true agar current price target pe ya usse neeche aa gaya
     */
    public boolean checkAlert() {
        try {
            double currentPrice = CryptoAPI.getCryptoPrice(coinName, "usd");
            return checkAlert(currentPrice);
        } catch (Exception e) {
            System.err.println("Alert check failed for " + coinName + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * Direct price pass karke check karo (testing ke liye useful).
     */
    public boolean checkAlert(double currentPrice) {
        if (currentPrice <= targetPrice) {
            System.out.println("ALERT! " + coinName.toUpperCase()
                    + " current price $" + currentPrice
                    + " target $" + targetPrice + " pe ya neeche aa gaya!");
            return true;
        }
        return false;
    }
}
