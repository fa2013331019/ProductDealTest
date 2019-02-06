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

public class ushopParser {
    public static void main(String[] args) {

        System.out.println("Running!");

        String url = "http://www.ushop.com.bd/Big-Discount/";

        try {
            Document doc = Jsoup.connect(url).get();

            Elements product_name = doc.select("div.image-wrapper > a");

            ArrayList<String> product_urls = new ArrayList<String>();

            for(Element e : product_name) {
                String product_url = e.attr("href");
                product_urls.add(product_url);
            }

            //System.out.println(product_urls);

            for(String s : product_urls) {
                Document product_doc = Jsoup.connect(s).get();

                Element title = product_doc.select("h1").first();
                Element old_price = product_doc.select("span.currency").first();
                Element new_price = product_doc.select("span#product_price").first();
                Element image_url = product_doc.select("div.minWidth > a").first();
                System.out.println("Title: " + title.text());
                System.out.println("Product URL: " + s);
                System.out.println("Old Price: " + old_price.text());
                System.out.println("New Price: " + new_price.text());
                try {
                    System.out.println("Image URL: " + image_url.attr("href"));
                } catch (NullPointerException e) {
                    System.out.println("No Image found");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Done!");
    }
}
