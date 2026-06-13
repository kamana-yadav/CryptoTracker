class CryptoData {
    private String name;
    private String symbol;
    private double price;
    private double change;
    private double marketCap;

    CryptoData(String name, String symbol, double price, double change, double marketCap) {
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.change = change;
        this.marketCap = marketCap;

    }

    public String getname() {
        return name;
    }

    public String getsymbol() {
        return symbol;
    }

    public double getprice() {
        return price;
    }

    public double getchange() {
        return change;
    }

    public double getmarketCap() {
        return marketCap;
    }

    public void displayInfo() {
        System.out.println("Coin: " + name);
        System.out.println("Price: " + price);
        System.out.println("Change: " + change);
        System.out.println("MarketCap: " + marketCap);
    }
}
