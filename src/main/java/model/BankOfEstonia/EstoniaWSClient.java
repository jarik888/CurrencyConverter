package model.BankOfEstonia;

import model.BankCurrencies;
import model.BankOfEstonia.generated.BodyType;
import model.BankOfEstonia.generated.CurrencyType;
import model.BankOfEstonia.generated.ObjectFactory;
import model.BankOfEstonia.generated.ReportType;
import model.Currency;
import model.WebServiceClient;
import util.HttpGet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class EstoniaWSClient implements WebServiceClient{

    private String bankName = "Estonian bank";
    private static final String ESTONIAN_BANK_URL = "http://statistika.eestipank.ee/Reports?type=curd&format=xml&date1=%REPLACE_TO_DATE%&lng=eng&print=off";
    private HttpGet client = new HttpGet();

    public BankCurrencies getExchangeRates(String date) {

        return convertToBankCurrencies(xmlToObject(getCourses(date)));

    }


    private String getCourses(String date) {

        return client.getXml(ESTONIAN_BANK_URL, date);

    }

    private List<CurrencyType> xmlToObject(String xml) {

        List<CurrencyType> ctList = null;
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
            StringReader sr = new StringReader(xml);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            BodyType obj = ((ReportType) ((JAXBElement) unmarshaller.unmarshal(sr)).getValue()).getBody();
            ctList = obj.getCurrencies().getCurrency();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return ctList;
    }

    private BankCurrencies convertToBankCurrencies(List<CurrencyType> ctList) {

        TreeMap<String, String> cNamesList = new TreeMap<String, String>();
        HashMap<String, Currency> currenciesList = new HashMap<String, Currency>();

        for (CurrencyType ct : ctList) {

            if (ct.getName().equals("XAU")) {
                cNamesList.put("EEK", "Estonian kroon");
                currenciesList.put("EEK", new Currency("Estonian kroon", "EEK", 1f));
                continue; //Gold (EEK/oz)
            }

            cNamesList.put(ct.getName(), ct.getText());

            currenciesList.put(ct.getName(), new Currency(ct.getText(), ct.getName(), Float.parseFloat(ct.getRate())));
        }

        return new BankCurrencies(bankName, cNamesList, currenciesList);
    }


}
