package util;

import java.io.*;
import java.util.HashMap;

public class GetCurrencyNames {

    private static String FILE_NAME = "CurrencyCodes.txt";
    private HashMap<String,String> currencyNames;

    public GetCurrencyNames() {
        currencyNames = new HashMap<String, String>();
        readFromTXT(FILE_NAME);

    }

    private void readFromTXT(String fileName) {

        InputStream input = GetCurrencyNames.class.getClassLoader().getResourceAsStream(fileName);

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            String line = "";

            while ((line = br.readLine()) != null) {
                int tab = line.indexOf("\t");
                currencyNames.put(line.substring(0, tab), line.substring(tab + 1));
            }

        } catch (IOException e) {
            System.err.println("Txt file reading error!");
            e.printStackTrace();
        }
    }

    public HashMap<String, String> getCurrencyNames() {
        return currencyNames;
    }

}
