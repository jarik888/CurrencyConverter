package service;

import model.BankCurrencies;
import model.BankOfEstonia.EstoniaWSClient;
import model.BankOfLithuania.LithuaniaWSClient;
import model.WebServiceClient;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CurrencyService {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private WebServiceClient[] wsClientsList = new WebServiceClient[]{
            new EstoniaWSClient(),
            new LithuaniaWSClient(),
    };

    private static HashMap<Date, List<BankCurrencies>> cache = new HashMap<Date, List<BankCurrencies>>();


    public HashMap<String, String> exchangeCurrency(Date date, String from, String to, float amount) {

        List<BankCurrencies> bc;
        HashMap<String, String> exchangeValues = new HashMap<String, String>();


        if (!cache.containsKey(date)) { // use web services
            getBankCurrencies(date);
        } else {
            System.out.println("USE CACHE: ");
        }

        bc = cache.get(date);

        for (BankCurrencies b : bc) {

            if (b.getCurrenciesList().containsKey(from) & b.getCurrenciesList().containsKey(to)) {
                float currencyFrom = b.getCurrenciesList().get(from).getExchangeRate();
                float currencyTo = b.getCurrenciesList().get(to).getExchangeRate();

                float value = amount * currencyFrom / currencyTo;


                value = (float) (Math.round(value * Math.pow(10, 2)) / Math.pow(10, 2));// round to two decimal places

                DecimalFormat df = new DecimalFormat("#########################.##");
                String val = Float.toString(value);

                Number n = 0;
                try {
                    val = df.parse(val).toString();
                } catch (ParseException e) {
                    System.err.println(val + " -> parsing error!");
                }

                exchangeValues.put(b.getBankName(), val);
            } /*else {
                exchangeValues.put(b.getBankName(), " - ");
            }*/
        }

        if (exchangeValues.size() < 1) {
            exchangeValues.put("Nothing found", "Please enter another date!");
        }

        return  exchangeValues;
    }


    public TreeMap<String, String> getBankCurrencies(Date date) {

        // Merge two TreeMaps without duplicate entries
        TreeMap<String, String> currencyNames = new TreeMap<String, String>();

        String sDate = sdf.format(date);

        if (cache.get(date) != null) { // get from cache

            System.out.println("USE CACHE: ");

            for (BankCurrencies bc : cache.get(date)) {
                currencyNames.putAll(bc.getCurrencyNames());
//              System.out.println(bc.getBankName() + " " + bc.getCurrencyNames() + " " + bc.getCurrenciesList());
            }

        } else { // get from web services

            System.out.println("USE WS: ");

            List<BankCurrencies> bcList = new ArrayList<BankCurrencies>();

            for (WebServiceClient c : wsClientsList) {

                BankCurrencies bc = c.getExchangeRates(sDate);

                if (bc.getCurrencyNames().size() > 0) { // if data is there
                    bcList.add(bc);
                }
                currencyNames.putAll(bc.getCurrencyNames());
//              System.out.println(bc.getBankName() + " " + bc.getCurrencyNames() + " " + bc.getCurrenciesList());

            }
            cache.put(date, bcList);
        }

        return currencyNames;
    }

}
