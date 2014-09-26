package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpGet {


    public String getXml(String webUrl, String date) {

        try {

            java.net.URL url = new URL(webUrl.replace("%REPLACE_TO_DATE%", date));
            URLConnection c = url.openConnection();
            c.setRequestProperty("User-agent", "Mozilla/5.0");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            c.getInputStream()));
            String line;
            String xml = "";
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                xml += line;
            }
            //System.out.println(xml);
            return xml;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Check the Internet connection!");
            System.exit(0);
        }
        return null;
    }


}
