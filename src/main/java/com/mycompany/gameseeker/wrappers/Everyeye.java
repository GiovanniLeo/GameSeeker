/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gameseeker.wrappers;

import com.mycompany.gameseeker.utility.Utility;
import com.mycompany.gameseeker.mongoDB.Result;
import java.util.ArrayList;
import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author johnn
 */
public class Everyeye {

    private static final String PUB = "Publisher";
    private static final String REW = "Review";

    public ArrayList<Result> searchResults(String searchQuery) {
          
        ArrayList<Result> results = new ArrayList<>();
        String url = "https://www.everyeye.it/ricerca/?q=";
        
        if (searchQuery.toLowerCase().contains(Utility.DS)) {
            searchQuery = Utility.clearSpecialCharacterWithDigits(searchQuery);
            searchQuery = Utility.checkRomanNumber(searchQuery);
        }
        
        String gameUrl = url + Utility.normalizeSearchQuery(searchQuery, false) + "&recensioni=1";
        System.out.println(gameUrl);
        int childNum = 1;

        try {
            Document doc = Jsoup.connect(gameUrl).get();
            Elements listOfResult = doc.select("#bodybg > main > div > "
                    + "div.cont-pagina-ricerca > div.parte-sinistra > div "
                    + "> div.contenuti > article:nth-child(n)");
            Element titleEl, linkToRefEl, valutationEl;
            String title, linkToRef, rewiew;
            double valutation;
            for (int i = 0; i < listOfResult.size(); i++) {
               
                titleEl = doc.selectFirst("#bodybg > main > div >"
                        + " div.cont-pagina-ricerca > div.parte-sinistra >"
                        + " div > div.contenuti > article:nth-child(" + childNum + ") > div > "
                        + "a > h2");

                linkToRefEl = doc.selectFirst("#bodybg > main > div >"
                        + " div.cont-pagina-ricerca > div.parte-sinistra >"
                        + " div > div.contenuti > article:nth-child(" + childNum + ") > div > "
                        + "a");
                valutationEl = doc.selectFirst("#bodybg > main > div "
                        + "> div.cont-pagina-ricerca > div.parte-sinistra "
                        + "> div > div.contenuti > article:nth-child(" + childNum + ")"
                        + " div[class='ico-voto']");
               
                if (valutationEl == null) {
                    childNum++;
                    continue;
                }

                title = titleEl.text();
                linkToRef = linkToRefEl.attr("href");
                valutation = Double.parseDouble(valutationEl.text());
                HashMap<String, String> map = getOtherInformation(linkToRef);
                rewiew = map.get(REW);
                String publisher = map.get(PUB);
                results.add(new Result(title, rewiew, publisher));
                childNum++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

    private HashMap<String, String> getOtherInformation(String linToRef) {
        HashMap<String, String> map = new HashMap<>();
        String rewiew, publisher;
        try {
            Document doc = Jsoup.connect(linToRef).get();
            Element rewviewEl = doc.selectFirst("#inread > div:nth-child(3)");
            Element publisherEl = doc.selectFirst("#inread >"
                    + " div.reorder-articolo > div.acura_rece > span > "
                    + "a > span");
            rewiew = rewviewEl.text();
            publisher = publisherEl.text();
            map.put(REW, rewiew);
            map.put(PUB, publisher);

        } catch (Exception e) {
        }
        return map;
    }
}
