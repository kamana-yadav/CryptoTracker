public class Main{
    public static void main(String[] args){
        PriceAlert Alert=new PriceAlert("Bitcoin", 45000.0);
        Alert.checkAlert(40000.0);

    }
}