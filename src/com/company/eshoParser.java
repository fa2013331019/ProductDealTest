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


public class eshoParser {

    public static void main(String[] args) {

        String url = "http://esho.com/offer-zone";
        try {

            Document doc = Jsoup.connect(url).get();

            Elements product_name = doc.select("div.preview-image-outer > a");

            ArrayList<String> product_url_prefixes = new ArrayList<String>();

            for (Element e : product_name) {
                String prefix = e.attr("href");
                product_url_prefixes.add(prefix);
            }

            for (String s : product_url_prefixes) {
                Document product_doc = Jsoup.connect("http://esho.com/" + s).get();

                Element title = product_doc.select("h3.title").first();
                Element new_prices = product_doc.select("span.price.new").first();
                Element old_prices = product_doc.select("span.price.old").first();
                String image_url = product_doc.select("div.carousel-item > img").first().attr("src");

                System.out.println("Titles: " + title.text());
                System.out.println("Product URL: " + "http://esho.com/" + s);
                System.out.println("New Prices" + new_prices.text());



                try {
                    System.out.println("Old Prices" + old_prices.text());
                } catch (NullPointerException e) {
                    System.out.println("No Old Price for this item");
                }

                try {
                    System.out.println("Image URL: " + "http://esho.com/" + image_url);
                }
                catch (NullPointerException e) {
                    System.out.println("No image for this product");
                }

            }
            System.out.println(product_url_prefixes);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


