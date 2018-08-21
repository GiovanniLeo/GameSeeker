package com.mycompany.gameseeker.wrappers;
import java.io.IOException;
import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.mycompany.gameseeker.utility.*;


public class Amazon {
	public static final String AMAZON = "Amazon";
	private static final String AVAILABILITY = "Availability";
	private static final String VENDOR = "Vendor";
	public Amazon(){}
	

	
	private String onlyGameUrl(String searchQuery)
	{
		String gameUrl = "https://www.amazon.it/s/ref=sr_nr_n_3?fst=as%3Aoff&rh=n%3A412603031%2Cn%3A13900044031%2Ck%3A"
						+searchQuery+"&page=1&keywords="+searchQuery+"&ie";
		return gameUrl;
	}
	
	public Result searchResult(String queryString)
	{
		Result minResult = null;
		try {
			String url = onlyGameUrl(Utility.normalizeSearchQuery(queryString, true));
			Document doc = Jsoup.connect(url).get();
			
			if(doc == null)
			{
				return null;
			}
			Elements listOfResult = doc.select("li[id^=result_]");
			
			Element imgEl,titleEl,plattform,priceEl;
			String title,imgUrl,linkRef,plattformTitle;
			String[] priceString;
			double price;
			Result temp;
			
			for(int i = 0; i < listOfResult.size(); i++)
			{
				 imgEl = listOfResult.select("#result_"+i+"> div > div > div >"
								+ "div.a-fixed-left-grid-col.a-col-left > div > div > a > img").first();
				 titleEl = listOfResult.select("#result_"+i+"> div > div > div >"
									+ " div.a-fixed-left-grid-col.a-col-right > div.a-row.a-spacing-small > "
									+ "div:nth-child(1) > a > h2").first();
				 plattform = listOfResult.select("#result_"+i+" > div > div > div > div.a-fixed-left-grid-col.a-col-right"
									+ " > div:nth-child(2) > div.a-column.a-span7 > table > tbody >"
									+ " tr.a-spacing-none.s-table-twister-row-no-border.s-table-twister-row > "
									+ "td:nth-child(1) > div:nth-child(1) > a").first();
				 priceEl = listOfResult.select("#result_"+i+" > div > div > div > div.a-fixed-left-grid-col.a-col-right "
				 		+ "> div:nth-child(2) > div.a-column.a-span7 > div:nth-child(2) > a >"
				 		+ " span.a-size-base.a-color-price.s-price.a-text-bold").first();
				
				if(plattform == null) //Vuol dire che sono in una forma tabellare 
				{
					plattform = listOfResult.select("#result_"+i+" > div > div > div >"
							+ " div.a-fixed-left-grid-col.a-col-right > div:nth-child(2) >"
							+ " div.a-column.a-span7 > div:nth-child(1) > a").first();
					
				}
				
				if(priceEl == null)
				{
					
					priceEl = listOfResult.select("#result_"+i+" > div > div > div > div.a-fixed-left-grid-col.a-col-right "
							+ "> div:nth-child(2) > div.a-column.a-span7 > table > tbody >"
							+ " tr.a-spacing-none.s-table-twister-row-no-border.s-table-twister-row > td:nth-child(2) >"
							+ " div > a > span.a-size-base.a-color-price.s-price.a-text-bold").first();
				}
				
				if(plattform == null || priceEl == null) continue;
				
				
				 title = titleEl.text();
				 imgUrl = imgEl.attr("src");
				 linkRef = plattform.attr("href");
				 plattformTitle = plattform.attr("title");
				 priceString = priceEl.text().split(" ");
				 price = Double.parseDouble(priceString[1].replace(",", "."));
				 
		
				if(plattformTitle.equalsIgnoreCase("pc") && title.toLowerCase().contains(queryString))
				{
					 temp = new Result(title, imgUrl, linkRef, plattformTitle, price);
				
					 if(i == 0)
					  {
						  minResult = temp;
					  }
					 else
					 {
						  if(minResult.getPrice() > temp.getPrice())
						  {
							  minResult = temp;
						  }
					 }
				}
				
			} //end for
			

		
		} 
		catch (IOException e) {
			
			e.printStackTrace();
		}
		
		HashMap<String, String> otherResult = getOtherInfomation(minResult);
		
		minResult.setAvailability(otherResult.get(AVAILABILITY));
		minResult.setVendor(otherResult.get(VENDOR));
		
		
		return minResult;
	}
	
	private HashMap<String, String> getOtherInfomation(Result result)
	{
		HashMap<String, String> otherInfo = new HashMap<>();
		try {
			Document doc = Jsoup.connect(result.getLinkRef()).get();
			Element avilEl = doc.select("#availability > span").first();
			Element vendorEl = doc.select("#merchant-info").first();
			otherInfo.put(AVAILABILITY, avilEl.text());
			otherInfo.put(VENDOR, vendorEl.text());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return otherInfo;
		
	}

}
