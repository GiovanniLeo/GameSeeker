/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gameseeker.wrappers;

import com.mycompany.gameseeker.task.G2AAdditionalComponentTask;
import com.mycompany.gameseeker.task.G2ATask;
import com.mycompany.gameseeker.mongoDB.Result;
import com.mycompany.gameseeker.utility.Utility;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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

    private static ExecutorService exec = Executors.newCachedThreadPool();
    private static final String PLATTFORM = "Platform";
    private static final String PRICE = "Price";
    private static final String IMG = "Image";
    private static final String XBOX = "XBOX LIVE";
    private static final String PSN = "PSN";
    private static final String FEEDBACK = "Feedback";

    public ArrayList<Result> searchResults(String searchQuery) throws IOException {

        ArrayList<Result> results = new ArrayList<>();
        String url = "https://www.g2a.com/it-it/search?category_id=gaming&query=";
        
        if (searchQuery.toLowerCase().contains(Utility.DS)) {
            searchQuery = Utility.clearSpecialCharacterWithDigits(searchQuery);
            searchQuery = Utility.checkRomanNumber(searchQuery);
        }
        
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
                linkToRef = "https://www.g2a.com" + LinkEl.attr("href");

                HashMap<String, String> map = getOtherInformation(linkToRef);
                if (map == null) {
                    childNum++;
                    continue;
                }
                if (searchQuery.toLowerCase().contains(Utility.DS)) {
                    title = Utility.checkRomanNumber(Utility.clearSpecialCharacterWithDigits(title));
                    searchQuery = Utility.checkRomanNumber(searchQuery);
                }

                if (title.toLowerCase().contains(searchQuery.toLowerCase())) {
                    title = Utility.checkRomanNumber(title);
                    Result res = new Result(title, map.get(IMG), linkToRef,
                            map.get(PLATTFORM),
                            Double.parseDouble(map.get(PRICE)));
                    res.setType(Utility.G2A);
                    res.setFeedback(Integer.parseInt(map.get(FEEDBACK)));
                    results.add(res);

                }

                childNum++;
            }
        } catch (HttpStatusException e) {
            if (e.getStatusCode() == 404) {
                return null;
            }
        } catch (Exception e) {
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

            Element regionEl = doc.selectFirst("#app > div > div.content > "
                    + "div > section > div.product__info "
                    + "> div.product__details > div > div > ul "
                    + "> li:nth-child(1) > div > button > div"
                    + " > span:nth-child(2)");

            if (plattformEl == null || priceEl == null || regionEl == null) {
                return null;
            }
            
            String region = regionEl.text();

            if (!region.equalsIgnoreCase("Can be activated in Italy")) {
                return null;
            }
            
            Element feedbackEl = doc.selectFirst("#app > div > div.content >"
                    + " div > section > div.product__info > "
                    + "div.product__details > div > div > "
                    + "div.instant-delivery-wrapper > div > "
                    + "div > section > div.user-info > div >"
                    + " div.star-rating > div > div");
            String width = feedbackEl.attr("style").replaceAll("\\D+","");
            int feedback = Integer.parseInt(width)/20;
            System.out.println(feedback);
            

            String price = priceEl.text().replace("EUR", "").replace(" ", "");
            //System.out.println(price);
            String imgLink = imgEl.attr("src");
            String platform = plattformEl.text();

            if (platform.equals("Blizzard")) {
                platform = "Battle.net";
            }
            if (platform.equals(PSN) || platform.equals(XBOX)) {
                return null;
            }

            map.put(PRICE, price);
            map.put(PLATTFORM, platform);
            map.put(IMG, imgLink);
            map.put(FEEDBACK,feedback+"");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public ArrayList<String> getListOfAllAdditionalComponents() {
        ArrayList<String> results = new ArrayList<>();
        String urlWithPagination = "https://www.g2a.com/it-it/category/points-and-currencies?page=";
        String url = "https://www.g2a.com/it-it/category/points-and-currencies";
        try {

            Document doc = Jsoup.connect(url)
                    .get();
            Elements pages = doc.select("#app > div > div.content >"
                    + " div > div > section > div > "
                    + "nav > ul > li:nth-child(n) > a");

            int pageNum = 1;

            Element titleEl;
            Elements listOfResults;
            for (int i = 0; i < pages.size(); i++) {
                doc = Jsoup.connect(urlWithPagination + pageNum)
                        .get();

                listOfResults = doc.select("#app > div > div.content >"
                        + " div > div > section > div > ul > "
                        + "li:nth-child(n)");
                int childNum = 1;
                for (int j = 0; j < listOfResults.size(); j++) {
                    titleEl = doc.selectFirst("#app > div > div.content > div >"
                            + " div > section > div > ul > li:nth-child(" + childNum + ") >"
                            + " div > div > div.Card__body > div.Card__head >"
                            + " div > h3 > a");

                    results.add(titleEl.text());
                    childNum++;
                }
                pageNum++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

    public ArrayList<Result> getCleanedData(String searchQuery) {
        ArrayList<Result> results = new ArrayList<>();

        try {
            Future<ArrayList<Result>> fG2A = exec
                    .submit(new G2ATask(searchQuery));

            Future<ArrayList<String>> fG2AAdditionalComp = exec
                    .submit(new G2AAdditionalComponentTask());

            ArrayList<Result> resultG2A = fG2A.get();
            ArrayList<String> resultG2AAddition = fG2AAdditionalComp.get();

            for (int i = 0; i < resultG2A.size(); i++) {

                for (int j = 0; j < resultG2AAddition.size(); j++) {

                    if (resultG2A.get(i).getTitle().toLowerCase().contains(resultG2AAddition.get(j).toLowerCase())) {
                        System.out.println(resultG2A.get(i).getTitle());
                        //resultG2A.remove(i);
                    }

                }

            }

            results = resultG2A;
            /*  for (int i = 0; i < results.size(); i++) {
                System.out.println(results.get(i).getTitle());
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            exec.shutdownNow();
        }

        return results;
    }
}
