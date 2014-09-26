package model;


import java.util.HashMap;
import java.util.TreeMap;

public class BankCurrencies {

    private String bankName;
    private TreeMap<String, String> currencyNames;
    private HashMap<String,Currency> currenciesList;

    public BankCurrencies(String bankName, TreeMap<String, String> currencyNames, HashMap<String,Currency> currenciesList) {

        this.bankName = bankName;
        this.currencyNames = currencyNames;
        this.currenciesList = currenciesList;

    }

    public String getBankName() {

        return bankName;
    }

    public TreeMap<String, String> getCurrencyNames() {

        return currencyNames;
    }

    public HashMap<String, Currency> getCurrenciesList() {

        return currenciesList;
    }

}
