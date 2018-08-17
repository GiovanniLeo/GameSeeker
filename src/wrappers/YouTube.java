package wrappers;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import utility.Utitlity;

public class YouTube {

	public static final String GP = "Gameplay";
	public static final String VD = "Video";
	
	/**
	 * Retituisce il video più visto e il gmaeplay più visto
	 * @param searchQuery
	 * @return
	 */
	public HashMap<String, String> searchResults(String searchQuery)
	{
		String url = "https://www.youtube.com/results?search_query=";
		String gameUrl = url + Utitlity.normalizeSearchQuery(searchQuery, false)+"&sp=CAMSAhABQgQIABIA";
		HashMap<String, String> results = new HashMap<>();
		try {
			
			 Document doc = Jsoup.connect(gameUrl)
			            .userAgent("Mozilla/5.0")
			            .get();
			 
			 Element mostViewVideo = doc.selectFirst(".yt-lockup-title > a[title]:not([href*=&list=])");
			 String  mostViewVideoLink = "http://www.youtube.com" + mostViewVideo.attr("href");

			if(mostViewVideo != null )
			{
				results.put(VD, mostViewVideoLink);

			}
			
			gameUrl = url + Utitlity.normalizeSearchQuery(searchQuery+" gameplay", false)
											+"&sp=CAMSAhABQgQIABIA";
			
			 doc = Jsoup.connect(gameUrl)
			            .userAgent("Mozilla/5.0")
			            .get();
			
			 Element mostViewGameplay = doc.selectFirst(".yt-lockup-title > a[title]:not([href*=&list=])");
			 String  mostViewGameplayLink = "http://www.youtube.com" + mostViewGameplay.attr("href");

			if(mostViewGameplay != null)
			{
				results.put(GP, mostViewGameplayLink);
			}

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

}
