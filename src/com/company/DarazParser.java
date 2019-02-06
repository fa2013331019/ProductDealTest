package com.company;

import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class DarazParser {
    public static void main(String args[]) throws IOException, JSONException {

        print("running...");

        URL url = new URL("https://acs-m.daraz.com.bd/h5/mtop.lazada.pegasus.service.business.fs/1.0/?jsv=2.4.5&appKey=24677475&t=1548656230076&sign=77ee04dc9362f045573c55c91a698a38&api=mtop.lazada.pegasus.service.business.fs&v=1.0&timeout=8000&x-i18n-language=en&x-i18n-regionID=BD&dataType=json&type=originaljson&data=%7B%22language%22%3A%22en-BD%22%2C%22regionID%22%3A%22BD%22%2C%22appId%22%3A%22101102%22%2C%22platform%22%3A%22pc%22%2C%22deviceID%22%3A%22%22%2C%22backupParams%22%3A%22language%2CregionID%22%2C%22isbackup%22%3Atrue%2C%22_pvuuid%22%3A1548656230056%2C%22terminalType%22%3A1%7D");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");

        int status = con.getResponseCode();

        System.out.println(status);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;

        StringBuffer content = new StringBuffer();

        while((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        System.out.println(content);

        System.out.println(content.length());

        //JSONObject jsonObject = new JSONObject(content.toString());

        in.close();

        JSONObject response = new JSONObject(content.toString());

        System.out.println(response);
        System.out.println(response.getClass());

        con.disconnect();

        String link = "https://www.daraz.com.bd/flash-sale/";

        Document doc = Jsoup.connect(link).get();

        //Elements item_list = doc.select("div.item-list-content > a[href]");

        //System.out.println(doc);

       /* for(Element item : item_list) {
            System.out.println(item);
        }*/
       

        print("done!");
    }




    public static void print(String str) {
        System.out.println(str);
    }
}
