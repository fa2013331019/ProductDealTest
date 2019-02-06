package com.company;

import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONObject;

public class bdShopParser {
    public static void main(String[] args) {

        System.out.println("Running...");

        String url = "https://www.bdshop.com/highlight/onsale.html";

        try {
            Document doc = Jsoup.connect(url).get();

            Elements product_name = doc.select("div.prolabels-wrapper > a");

            ArrayList<String> product_urls = new ArrayList<String>();

            for(Element e : product_name) {
                String s = e.attr("href");
                product_urls.add(s);
            }

            for(String s : product_urls) {
                Document product_doc = Jsoup.connect(s).get();

                Element title = product_doc.select("h1.page-title").first();
                Element new_price = product_doc.select("span.special-price").select("span.price").first();
                Element old_price = product_doc.select("span.old-price").select("span.price").first();
                System.out.println(title.text());
                System.out.println(new_price.text());
                System.out.println(old_price.text());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Done!");

    }
}
