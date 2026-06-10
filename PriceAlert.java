public class PriceAlert {
    private String coinName;
    private Double targetPrice;
    PriceAlert(String coinName,Double targetPrice) {
        this.coinName = coinName;
        this.targetPrice = targetPrice;
    }
    public void checkAlert(Double currentPrice){
        if(currentPrice<targetPrice){
            System.out.println("Alert");
        }

    }
}