package com.mycompany.gameseeker.wrappers;

import com.mycompany.gameseeker.utility.Utility;
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
    public HashMap<String, String> searchResults(String searchQuery) {
        String url = "https://www.youtube.com/results?search_query=";
        String gameUrl = url + Utility.normalizeSearchQuery(searchQuery, false) + "&sp=CAMSAhABQgQIABIA";
        HashMap<String, String> results = new HashMap<>();
        try {

            Document doc = Jsoup.connect(gameUrl)
                    .userAgent("Mozilla/5.0")
                    .get();

            Element mostViewVideo = doc.selectFirst(".yt-lockup-title > a[title]:not([href*=&list=])");

            if (mostViewVideo != null) {
                String mostViewVideoLink = "http://www.youtube.com" + mostViewVideo.attr("href");
                results.put(VD, mostViewVideoLink);

            }

            gameUrl = url + Utility.normalizeSearchQuery(searchQuery + " gameplay", false)
                    + "&sp=CAMSAhABQgQIABIA";

            doc = Jsoup.connect(gameUrl)
                    .userAgent("Mozilla/5.0")
                    .get();

            Element mostViewGameplay = doc.selectFirst(".yt-lockup-title > a[title]:not([href*=&list=])");

            if (mostViewGameplay != null) {
                String mostViewGameplayLink = "http://www.youtube.com" + mostViewGameplay.attr("href");
                results.put(GP, mostViewGameplayLink);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

}
