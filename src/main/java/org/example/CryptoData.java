package org.example;

/**
 * CryptoData — ek coin ka data hold karta hai.
 * Bug fix: getters ab Java convention follow karte hain (getName, getPrice etc.)
 */
public class CryptoData {

    private String name;
    private String symbol;
    private double price;
    private double change;
    private double marketCap;

    public CryptoData(String name, String symbol,
                      double price, double change, double marketCap) {
        this.name      = name;
        this.symbol    = symbol;
        this.price     = price;
        this.change    = change;
        this.marketCap = marketCap;
    }

    public String getName()      { return name; }
    public String getSymbol()    { return symbol; }
    public double getPrice()     { return price; }
    public double getChange()    { return change; }
    public double getMarketCap() { return marketCap; }

    public void setPrice(double price)   { this.price = price; }
    public void setChange(double change) { this.change = change; }

    public void displayInfo() {
        System.out.println("Coin      : " + name);
        System.out.println("Symbol    : " + symbol);
        System.out.println("Price     : " + price);
        System.out.println("Change %  : " + change);
        System.out.println("Market Cap: " + marketCap);
        System.out.println("----------------------------");
    }
}
