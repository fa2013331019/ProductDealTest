package pipilika.productDeal;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class RokomariParser {

    public void parseApage(String url) {
        try {
            Document doc = Jsoup.connect(url).header("Accept-Encoding", "gzip, deflate").userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0").followRedirects(true).maxBodySize(0).timeout(0).get();

            //Elements product_name = doc.select("div.details-book-main-info__header");
            System.out.println(doc);
            //System.out.println(product_name.text());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(url);
    }

    public static void main(String[] args) {

        System.out.println("Running...");

        try {
            String baseUrl = "https://www.rokomari.com/book/category/1983/";

            RokomariParser rp = new RokomariParser();

            String newURl = baseUrl;

            while(newURl != null) {

                Document doc = Jsoup.connect(newURl).header("Accept-Encoding", "gzip, deflate").userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0").followRedirects(true).maxBodySize(0).timeout(0).get();
                Element nextPage = doc.select("span.current ~ a").first();
                String nextPageUrl = nextPage.attr("href");
                newURl = "https://www.rokomari.com" + nextPageUrl;

                try {
                    rp.parseApage(newURl);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Done!");

    }
}
