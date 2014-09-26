package model;

public class Currency {

    private String name;
    private String shortName;
    private float exchangeRate;

    public Currency(String name, String shortName, float exchangeRate) {

        this.name = name;
        this.shortName = shortName;
        this.exchangeRate = exchangeRate;

    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public float getExchangeRate() {
        return exchangeRate;
    }


}
