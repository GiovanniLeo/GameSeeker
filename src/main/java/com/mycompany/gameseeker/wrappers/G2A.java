/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gameseeker.wrappers;

import com.mycompany.gameseeker.utility.Result;
import com.mycompany.gameseeker.utility.Utility;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author johnn
 */
public class G2A {

    private static final String PLATTFORM = "Platform";
    private static final String PRICE = "Price";
    private static final String IMG = "Image";

    public ArrayList<Result> searchResults(String searchQuery) throws IOException {

        ArrayList<Result> results = new ArrayList<>();
        String url = "https://www.g2a.com/it-it/search?category_id=gaming&query=";
        String gameUrl = url + searchQuery.replace(" ", "%20");
        System.out.println(gameUrl);

        try {
            Document doc = Jsoup.connect(gameUrl)
                    .get();
            
            if (doc == null) {
                System.out.println("Doc nul");
                return null;
            }

            Elements listOfResults = doc.select("#app > div > div.content >"
                    + " div > div > section > div > ul > "
                    + "li:nth-child(n)");
            
            if (listOfResults == null) {
                 System.out.println("list null");
                return null;
            }
            int childNum = 1;

            Element titleEl, LinkEl;
            String title, linkToRef;
            for (int i = 0; i < listOfResults.size(); i++) {
                titleEl = doc.selectFirst("#app > div > div.content > div >"
                        + " div > section > div > ul > li:nth-child(" + childNum + ") >"
                        + " div > div > div.Card__body > div.Card__head >"
                        + " div > h3 > a");
                LinkEl = doc.selectFirst("#app > div > div.content > div >"
                        + " div > section > div > ul > li:nth-child(" + childNum + ") >"
                        + " div > div > div.Card__body > div.Card__head >"
                        + " div > h3 > a");

                title = titleEl.text();
                linkToRef = "https://www.g2a.com"+LinkEl.attr("href");
                
                HashMap<String, String> map = getOtherInformation(linkToRef);
                if(map == null) {
                    childNum++;
                    continue;
                }
                
                results.add(new Result(title, map.get(IMG), linkToRef, 
                        map.get(PLATTFORM), 
                        Double.parseDouble(map.get(PRICE))));
                childNum++;
            }
        } catch (HttpStatusException e) {
           if(e.getStatusCode() == 404)
               return null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return results;
    }

    private HashMap<String, String> getOtherInformation(String url) {
        HashMap<String, String> map = new HashMap<>();
        try {
            Document doc = Jsoup.connect(url).get();

            Element plattformEl = doc.selectFirst("#app > div > div.content"
                    + " > div > section > div.product__info "
                    + "> div.product__details > div > div > ul "
                    + "> li:nth-child(2) > div > a > div > strong");
            //System.out.println(plattformEl);

            Element priceEl = doc.selectFirst("#app > div > div.content > "
                    + "div > section > div.product__info > "
                    + "div.product__details > div > div "
                    + "> div.product__details__price > div.product__price"
                    + " > div.price-wrapper > span");
            Element imgEl = doc.selectFirst("img[class='gallery__main-img']");
           
            if(plattformEl == null || priceEl == null)
            {
                return null;
            }
            
            String price = priceEl.text().replace("EUR", "").replace(" ", "");
            //System.out.println(price);
            String imgLink = imgEl.attr("src");
            String platform = plattformEl.text();
            
            if (platform.equals("Blizzard")) {
                platform = "Battle.net";
            }
            
            map.put(PRICE, price);
            map.put(PLATTFORM, platform);
            map.put(IMG, imgLink);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
