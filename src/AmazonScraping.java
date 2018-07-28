import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AmazonScraping {
	
	public AmazonScraping(){}
	
	public String normalizeSearchQuery(String searchString)
	{
		String normalizedString = searchString.replace(" ", "+")
				.toLowerCase();
		
		return normalizedString;
	}
	
	public String onlyGameUrl(String searchQuery)
	{
		String gameUrl = "https://www.amazon.it/s/ref=sr_nr_n_3?fst=as%3Aoff&rh=n%3A412603031%2Cn%3A13900044031%2Ck%3A"
						+searchQuery+"&keywords="+searchQuery+"&ie";
		System.out.println(gameUrl);
		return gameUrl;
	}
	
	public void searchResult(String queryString)
	{
		try {
			Document doc = Jsoup.connect(onlyGameUrl(normalizeSearchQuery(queryString))).get();
			Elements listOfResult = doc.select("li[id^=result_]");
			System.out.println(listOfResult.size());
			for(int i = 0; i < listOfResult.size(); i++)
			{
				Element imgEl = listOfResult.select("#result_"+i+"> div > div > div >"
								+ "div.a-fixed-left-grid-col.a-col-left > div > div > a > img").first();
				String imgUrl = imgEl.attr("src");
				System.out.println(imgUrl);
				
			}
		
		} 
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
