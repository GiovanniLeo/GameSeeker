package com.mycompany.gameseeker.wrappers;


import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import com.mycompany.gameseeker.utility.*;

public class YouTube {

	public static final String GP = "Gameplay";
	public static final String VD = "Video";
	
	/**
	 * Retituisce il video pi� visto e il gmaeplay pi� visto
	 * @param searchQuery
	 * @return
	 */
	public HashMap<String, String> searchResults(String searchQuery)
	{
		String url = "https://www.youtube.com/results?search_query=";
		String gameUrl = url + Utility.normalizeSearchQuery(searchQuery, false)+"&sp=CAMSAhABQgQIABIA";
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
			
			gameUrl = url + Utility.normalizeSearchQuery(searchQuery+" gameplay", false)
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
