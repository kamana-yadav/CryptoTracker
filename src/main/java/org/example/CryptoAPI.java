package org.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CryptoAPI {


    public static double getCryptoPrice(String coin, String currency) throws Exception {


        URL url = new URL(
                "https://api.coingecko.com/api/v3/simple/price?ids="
                        + coin +
                        "&vs_currencies="
                        + currency
        );


        HttpURLConnection con =
                (HttpURLConnection) url.openConnection();


        BufferedReader br =
                new BufferedReader(
                        new InputStreamReader(
                                con.getInputStream()
                        )
                );


        String response = br.readLine();


        JsonObject obj =
                JsonParser.parseString(response)
                        .getAsJsonObject();


        return obj.getAsJsonObject(coin)
                .get(currency)
                .getAsDouble();

    }


}