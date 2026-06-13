import java.util.ArrayList;
public class PortfolioManager {
    ArrayList<CryptoData> portfolio = new ArrayList<>();

    public void addCoin(CryptoData coin) {
        portfolio.add(coin);
    }

    public void removeCoin(CryptoData coin) {
        portfolio.remove(coin);
    }

    public void displayPortfolio() {
        for (CryptoData coin : portfolio) {
            coin.displayInfo();
        }

    }

    public double calculateTotal() {
        double total = 0;
        for (CryptoData coin : portfolio) {
            total = total + coin.getprice();
        }
        return total;
    }
}
