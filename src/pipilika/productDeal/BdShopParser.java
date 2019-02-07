package pipilika.productDeal;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class BdShopParser {

    public void parseApage(String url) {
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
                Element product_image = product_doc.select("div.fotorama__loaded--img > img").first();
                String image_url = product_image.attr("src");

                System.out.println("Title: " + title.text());
                System.out.println("Product Url:" + s);
                System.out.println("New Price: " + new_price.text());
                System.out.println("Old Price: " + old_price.text());
                System.out.println("Image Url: " + image_url);

            }

        } catch (Exception e) {
            System.out.println("No more page available to parse...");
        }
    }

    public static void main(String[] args) {

        System.out.println("Running...");

        BdShopParser bds = new BdShopParser();

        try {
            String base_url = "https://www.bdshop.com/highlight/onsale.html";
            //bds.parseApage(base_url);

            String newUrl = base_url;

            while(newUrl != null) {

                Document doc = Jsoup.connect(base_url).get();
                Element nextPage = doc.select("a.action.next").first();

                try {
                    newUrl = nextPage.attr("href");
                    bds.parseApage(newUrl);
                }

                catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Done!");

    }
}
