package com.mycompany.gameseeker.wrappers;

import com.mycompany.gameseeker.mongoDB.Result;
import com.mycompany.gameseeker.utility.Utility;
import java.util.ArrayList;
import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class YouTube {

    public static final String GP = "Gameplay";
    public static final String VD = "Video";

    /**
     * Retituisce il video pi� visto e il gmaeplay pi� visto
     *
     * @param searchQuery
     * @return
     */
    public  ArrayList<Result> searchResults(String searchQuery) {
        String url = "https://www.youtube.com/results?search_query=";
        String gameUrl = url + Utility.normalizeSearchQuery(searchQuery, false) + "&sp=CAMSAhABQgQIABIA";
        ArrayList<Result> results = new ArrayList<>();
  
       
        try {

            Document doc = Jsoup.connect(gameUrl)
                    .userAgent("Mozilla/5.0")
                    .get();

            Element mostViewVideo = doc.selectFirst(".yt-lockup-title > a[title]:not([href*=&list=])");

            if (mostViewVideo != null) {
                String mostViewVideoLink = "http://www.youtube.com" + mostViewVideo.attr("href");
                Result res = new Result(Utility.checkRomanNumber(searchQuery) +" "+ VD, mostViewVideoLink);
                res.setType(Utility.YT);
                results.add(res);
                

            }

            gameUrl = url + Utility.normalizeSearchQuery(searchQuery + " walkthrough", false)
                    + "&sp=CAMSAhABQgQIABIA";

            doc = Jsoup.connect(gameUrl)
                    .userAgent("Mozilla/5.0")
                    .get();

            Element mostViewGameplay = doc.selectFirst(".yt-lockup-title > a[title]:not([href*=&list=])");

            if (mostViewGameplay != null) {
                String mostViewGameplayLink = "http://www.youtube.com" + mostViewGameplay.attr("href");
                Result res = new Result(Utility.checkRomanNumber(searchQuery) +" "+ GP, mostViewGameplayLink);
                res.setType(Utility.YT);
                results.add(res);
              
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

}
