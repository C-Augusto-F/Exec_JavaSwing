package com.mycompany.conversormoedas;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    private static final String API_URL = "https://economia.awesomeapi.com.br/json/last/USD-BRL,EUR-BRL";

    public static double convertToUSD(double amountBRL) throws IOException {
        JSONObject rates = fetchExchangeRates();
        double usdRate = rates.getJSONObject("USDBRL").getDouble("bid");
        return amountBRL / usdRate;
    }

    public static double convertToEUR(double amountBRL) throws IOException {
        JSONObject rates = fetchExchangeRates();
        double eurRate = rates.getJSONObject("EURBRL").getDouble("bid");
        return amountBRL / eurRate;
    }

    private static JSONObject fetchExchangeRates() throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        Scanner scanner = new Scanner(url.openStream());
        StringBuilder json = new StringBuilder();
        while (scanner.hasNext()) {
            json.append(scanner.nextLine());
        }
        scanner.close();

        return new JSONObject(json.toString());
    }
}
