package model.BankOfLithuania;

import model.BankCurrencies;
import model.BankOfLithuania.generated.ExchangeRatesType;
import model.BankOfLithuania.generated.ItemType;
import model.BankOfLithuania.generated.ObjectFactory;
import model.Currency;
import model.WebServiceClient;
import util.GetCurrencyNames;
import util.HttpGet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class LithuaniaWSClient implements WebServiceClient{

    private String bankName = "Lithuanian bank";
    private static final String LITHUANIAN_BANK_URL = "http://webservices.lb.lt/ExchangeRates/ExchangeRates.asmx/getExchangeRatesByDate?Date=%REPLACE_TO_DATE%";
    private GetCurrencyNames cNamesDB = new GetCurrencyNames();
    private HttpGet client = new HttpGet();

    public BankCurrencies getExchangeRates(String date) {

        return convertToCurrencyList(xmlToObject(getCourses(date)));

    }

    private String getCourses(String date) {

        return client.getXml(LITHUANIAN_BANK_URL, date);

    }


    private List<ItemType> xmlToObject(String xml) {

        List<ItemType> itList = null;
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
            StringReader sr = new StringReader(xml);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            ExchangeRatesType obj = ((ExchangeRatesType) ((JAXBElement) unmarshaller.unmarshal(sr)).getValue());
            itList = obj.getItem();
        } catch (JAXBException e) {
            System.err.println("Parameter value is invalid! No data found.");
            itList = new ArrayList<ItemType>();
        }
        return itList;
    }

    private BankCurrencies convertToCurrencyList(List<ItemType> itList) {

        TreeMap<String, String> cNamesList = new TreeMap<String, String>();
        HashMap<String, Currency> currenciesList = new HashMap<String, Currency>();

        for (ItemType it : itList) {

            String fullName = cNamesDB.getCurrencyNames().get(it.getCurrency());
            cNamesList.put(it.getCurrency(), fullName);

            float exchangeRate = Float.parseFloat(it.getRate()) / Integer.parseInt(it.getQuantity());
            currenciesList.put(it.getCurrency(), new Currency(fullName, it.getCurrency(), exchangeRate));
        }

        return new BankCurrencies(bankName, cNamesList, currenciesList);
    }

}
