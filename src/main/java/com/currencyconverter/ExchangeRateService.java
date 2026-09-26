package com.currencyconverter;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Talks to the Frankfurter API (https://www.frankfurter.app) to fetch live
 * currency exchange rates. Frankfurter is free, requires no API key, and is
 * backed by the European Central Bank's published rates.
 */
public class ExchangeRateService {

    private static final String BASE_URL = "https://api.frankfurter.app/latest";
    private final HttpClient httpClient;

    public ExchangeRateService() {
        this.httpClient = HttpClient.newHttpClient();
    }

    /**
     * Converts an amount from one currency to another using the latest
     * published exchange rate.
     *
     * @param amount amount to convert
     * @param from   3-letter source currency code, e.g. "USD"
     * @param to     3-letter target currency code, e.g. "INR"
     * @return the converted amount
     * @throws IOException          if the network request fails
     * @throws InterruptedException if the request is interrupted
     */
    public double convert(double amount, String from, String to) throws IOException, InterruptedException {
        if (from.equalsIgnoreCase(to)) {
            return amount;
        }

        String url = String.format("%s?amount=%s&from=%s&to=%s", BASE_URL, amount, from, to);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Exchange rate API returned status code: " + response.statusCode());
        }

        JSONObject json = new JSONObject(response.body());
        JSONObject rates = json.getJSONObject("rates");

        if (!rates.has(to)) {
            throw new IOException("Currency code not found in response: " + to);
        }

        return rates.getDouble(to);
    }

    /**
     * Fetches the current 1-unit exchange rate between two currencies,
     * useful for displaying "1 USD = 83.12 INR" style labels.
     */
    public double getRate(String from, String to) throws IOException, InterruptedException {
        return convert(1.0, from, to);
    }
}
