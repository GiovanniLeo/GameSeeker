package com.mycompany.gameseeker.wrappers;

import com.mycompany.gameseeker.utility.Utility;
import com.mycompany.gameseeker.mongoDB.Result;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class InstantGaming {

    private static final String AVAILABILITY = "Availability";
    private static final String DESCRIPTION = "Description";
    private static final String FEEDBACK = "Feedback";
    private static final String PRICE = "Price";
    private static final String MIN = "Min requirement";
    private static final String REC = "Rec requirement";
    private static final String RELEASEDATE = "ReleaseDate";
    private static final String PLATTFORM = "Platform";
    public static final String INSTANTGAMING = "InstantGaming";

    public ArrayList<Result> searchResults(String searchQuery) {
        ArrayList<Result> results = new ArrayList<>();
        String url = "https://www.instant-gaming.com/it/ricerca/?"
                + "type%5B%5D=battle.net&type%5B%5D=gog.com&type%5B%5D="
                + "origin&type%5B%5D=rockstar&type%5B%5D=steam&type%5B%5D="
                + "uplay&all_cats=1&min_price=0&max_price=100"
                + "&noprice=1&min_discount=0&max_discount=100&min_reviewsavg=10"
                + "&max_reviewsavg=100&noreviews=1&available_in=IT&gametype=all"
                + "&sort_by=&query=";

        if (searchQuery.toLowerCase().contains(Utility.DS)) {
            searchQuery = Utility.clearSpecialCharacterWithDigits(searchQuery);
            searchQuery = Utility.checkRomanNumber(searchQuery);
        }

        System.out.println("Ig->" + searchQuery);
        String gameUrl = url + Utility.normalizeSearchQuery(searchQuery, false);
        System.out.println(gameUrl);
        try {

            Document doc = Jsoup.connect(gameUrl).get();

            Elements listOfResult = doc.select("#ig-panel-center > div.search-wrapper > div.search > div:nth-child(n)");

            int childNum = 1;

            Element titleEl, imgEl, linkEl;
            String title, imgUrl, linkToRef;
            String onlyOneBase = "#ig-panel-center > div.search-wrapper > div.search > div";
            String baseUrl;

            for (int i = 0; i < listOfResult.size(); i++) {

                if (listOfResult.size() == 1) {
                    baseUrl = onlyOneBase;
                } else {
                    baseUrl = "#ig-panel-center > div.search-wrapper "
                            + "> div.search > div:nth-child(" + childNum + ")";

                }

                titleEl = doc.selectFirst(baseUrl + "> div.name");

                imgEl = doc.selectFirst(baseUrl + " > a > img");

                linkEl = doc.selectFirst(baseUrl + " > a");

                title = titleEl.text();

                imgUrl = imgEl.attr("src");
                linkToRef = linkEl.attr("href");

                if (Utility.clearSpecialCharacter(title.toLowerCase())
                        .contains(Utility.clearSpecialCharacter(searchQuery.toLowerCase()))) {
                    HashMap<String, String> map = getOtherInformation(linkToRef);

                    if (map == null) {
                        childNum++;
                        continue;
                    }
                    // if (map.get(PLATTFORM).toLowerCase().equals(Steam.STEAM.toLowerCase())) {
                    title = Utility.checkRomanNumber(title);
                    Result res = new Result(title, imgUrl, linkToRef, map.get(PLATTFORM),
                            Double.parseDouble(map.get(PRICE)), map.get(AVAILABILITY),
                            map.get(DESCRIPTION), Integer.parseInt(map.get(FEEDBACK)),
                            map.get(RELEASEDATE), map.get(MIN), map.get(REC));
                    res.setType(Utility.IG);
                    results.add(res);
                    //  }

                }

                childNum++;
            }

        } catch (Exception e) {

        }
        return results;
    }

    private HashMap<String, String> getOtherInformation(String linkToRef) {
        HashMap<String, String> map = new HashMap<>();
        try {

            Document doc = Jsoup.connect(linkToRef).get();

            Element availEl = doc.selectFirst("#ig-product-main-panel > div.product > div.infos "
                    + "> div.shadow.mainshadow > div.subinfos > div:nth-child(2)");

            if (availEl == null) {
                return null;
            }

            Element plattformEl = doc.selectFirst("#ig-product-main-panel > div.product > "
                    + "div.infos > div.shadow.mainshadow > div.subinfos > a");

            Element priceEL = doc.selectFirst("#ig-product-main-panel > div.product > "
                    + "div.infos > div.buy > div > div.price");

            Element descriptionEl = doc.selectFirst("#ig-product-desc-content > div.description");

            Element requirementEl = doc.selectFirst("#ig-product-desc-content > div[class^=hardspecs]");

            Element feedbackEl = doc.selectFirst("#ig-panel-center > div >"
                    + " div.information > div.tabs > a.tab.mainshadow.feedbacks"
                    + " > span > span");

            Element releaseDateEl = doc.selectFirst("#ig-product-desc-content > div.release > span");

            String avail = availEl.text();
            String platform = plattformEl.text();
            String description = descriptionEl.text();
            String releaseDate = releaseDateEl.text();
            String price = priceEL.text().replace(",", ".").replace("€", "");

            String width = feedbackEl.attr("style").replaceAll("\\D+","");
            int feedback = Integer.parseInt(width.substring(0,2))/15;
          
           

           
            String requirement = requirementEl.text();

            if (requirement.contains("*I tag e i requisiti sono solo a scopo informativo")) {
                requirement = requirement.replace("*I tag e i requisiti sono solo a scopo informativo", "");
            }

            String[] requisiti = requirement.replace(" ", ",").split("Requisiti,raccomandati*");
            String requisitiMinimi = requisiti[0].replace(",", " ");
            String requisitiConsigliati = "";
            if (requisiti.length > 1) {
                requisitiConsigliati = "Requisiti raccomandati" + requisiti[1].replace(",", " ");
            }

            map.put(AVAILABILITY, avail);
            map.put(DESCRIPTION, description);
            map.put(FEEDBACK, "" + feedback);
            map.put(MIN, requisitiMinimi);
            map.put(REC, requisitiConsigliati);
            map.put(PRICE, price);
            map.put(RELEASEDATE, releaseDate);
            map.put(PLATTFORM, platform);

        } catch (Exception e) {
            // TODO: handle exception
        }

        return map;
    }

}
