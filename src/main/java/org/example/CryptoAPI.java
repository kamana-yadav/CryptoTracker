package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * CryptoAPI — CoinGecko API se data fetch karta hai.
 *
 * Merge: Root ka fetchData() aur src ka getCryptoPrice() dono combine kiye.
 *   - getCryptoPrice()  → sirf price chahiye to
 *   - getMarketData()   → poora CryptoData object chahiye to (name, symbol, price, change, marketcap)
 */
public class CryptoAPI {

    private static final String BASE = "https://api.coingecko.com/api/v3";

    /**
     * Sirf price return karta hai (double).
     * Example: getCryptoPrice("bitcoin", "inr")
     */
    public static double getCryptoPrice(String coin, String currency) throws Exception {

        String endpoint = BASE + "/simple/price?ids="
                + coin + "&vs_currencies=" + currency;

        String response = fetchRaw(endpoint);

        JsonObject obj = JsonParser.parseString(response).getAsJsonObject();
        return obj.getAsJsonObject(coin).get(currency).getAsDouble();
    }

    /**
     * Poora market data return karta hai — name, symbol, price, 24h change, marketcap.
     * Currency default USD hai.
     */
    public static CryptoData getMarketData(String coinId) throws Exception {

        String endpoint = BASE + "/coins/markets?vs_currency=usd&ids=" + coinId;

        String response = fetchRaw(endpoint);

        JsonArray arr = JsonParser.parseString(response).getAsJsonArray();
        if (arr.size() == 0) {
            throw new Exception("Coin nahi mila: " + coinId);
        }

        JsonObject coin = arr.get(0).getAsJsonObject();

        String name      = coin.get("name").getAsString();
        String symbol    = coin.get("symbol").getAsString().toUpperCase();
        double price     = coin.get("current_price").getAsDouble();
        double change    = coin.get("price_change_percentage_24h").getAsDouble();
        double marketCap = coin.get("market_cap").getAsDouble();

        return new CryptoData(name, symbol, price, change, marketCap);
    }

    /**
     * Internal helper — URL se raw JSON string laata hai.
     */
    private static String fetchRaw(String endpoint) throws Exception {

        URL url = new URL(endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(10000);
        con.setReadTimeout(10000);

        int status = con.getResponseCode();
        if (status != 200) {
            throw new Exception("API error, HTTP status: " + status);
        }

        BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream())
        );

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();

        return sb.toString();
    }
}
