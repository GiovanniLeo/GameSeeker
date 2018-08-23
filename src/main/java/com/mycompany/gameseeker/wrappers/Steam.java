package com.mycompany.gameseeker.wrappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.mycompany.gameseeker.utility.*;

public class Steam 
{
	public static final String STEAM = "Steam";
	private static final String IMG = "ImgLink";
	private static final String DESCRIPTION = "Description";
	private static final String FEEDBACK = "Feedback";
	private static final String PRICE = "Price";
	private static final String DEVELOPER = "Developer";
	private static final String RELEASEDATE = "ReleaseDate";
	private static final String CATEGORY = "Category";
	
	
	
	public ArrayList<Result> searchResul(String searchQuery)
	{
		String url = "https://store.steampowered.com/search/?term=";
		String gameUrl = url+Utility.normalizeSearchQuery(searchQuery, false);
		ArrayList<Result> results = new ArrayList<>();
		try {
			
			Document doc = Jsoup.connect(gameUrl).get();
			System.out.println(gameUrl);
			
			if(doc == null)
			{
				return null; 
			}
			//Aggiungendo la n prende tutti i child
			Elements listOfResult = doc.select("#search_result_container > div:nth-child(2) > a:nth-child(n)");
			System.out.println(listOfResult.size());
			
			Element linkEl,titleEl,priceEl;
			String linkToRef,title;
			int childNum = 2;
			for(int i = 0; i < listOfResult.size(); i++)
			{
				
				
				linkEl = doc.selectFirst("#search_result_container > div:nth-child(2) > a:nth-child("+childNum+")");
				titleEl = doc.selectFirst("#search_result_container > div:nth-child(2) > a:nth-child("+childNum+")"
							+ "> div.responsive_search_name_combined > div.col.search_name.ellipsis > span");
                                priceEl = doc.selectFirst("#search_result_container > div:nth-child(2) > a:nth-child("+childNum+") > "
                                        + "div.responsive_search_name_combined > div.col.search_price_discount_combined.responsive_secondrow "
                                        + "> div.col.search_price.responsive_secondrow");
				
                               
                                if(priceEl == null) continue;
                                
				linkToRef = linkEl.attr("href");
                                System.out.println(linkToRef);
				title =titleEl.text();
                              
                                String price = priceEl.text();
                                if(price != null || price != "")
                                {
                                    price = price.replace("€", "").replace(",", ".");
                                }
                                else continue;
                                
		
				HashMap<String, String> map  = new HashMap<>();
				
				
				if(Utility.clearSpecialCharacter(title).toLowerCase()
						.contains(Utility.clearSpecialCharacter(searchQuery).toLowerCase()))
				{
					                               
					map = getOtherInfo(linkToRef);
				}
				
                                
				//if(map.get(PRICE) != null)
				//{
                                    
					results.add(new Result(title, map.get(IMG), linkToRef, Double.parseDouble(price), map.get(DESCRIPTION),
							map.get(DESCRIPTION), Integer.parseInt(map.get(FEEDBACK)), map.get(RELEASEDATE), map.get(DEVELOPER)));
				//}
				

				childNum++;
				
			}
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return results;
	}
	
	private HashMap<String, String> getOtherInfo(String url)
	{
            
		HashMap<String, String> map = new HashMap<>();
		
		try {
			Document doc = Jsoup.connect(url)
                                .userAgent("Mozilla/5.0")
                                .maxBodySize(0)
                                .timeout(600000)
			        .get();
			 
                        System.out.println("Connection ok");
			Element imgEl = doc.selectFirst("#game_highlights > div.rightcol > div > div.game_header_image_ctn > img");
			Element descriptionEl = doc.selectFirst("#game_highlights > div.rightcol > div > div.game_description_snippet");
			Element numOfRewiew = doc.selectFirst("#game_highlights > div.rightcol > div > div.glance_ctn_responsive_left >"
							+ " div > div:nth-child(2) > div.summary.column "
							+ "> span.responsive_hidden"); 
                        
			Element feedbackEl = doc.selectFirst("#game_highlights > div.rightcol > div > div.glance_ctn_responsive_left "
							+ "> div > div:nth-child(2) > div.summary.column > "
							+ "span.nonresponsive_hidden.responsive_reviewdesc");
			
			/*Element priceEl = doc.selectFirst("#game_area_purchase > div:nth-child(1) >"
							+ " div > div.game_purchase_action > div >"
							+ " div.game_purchase_price.price");*/
			
			
			Element developerEl = doc.selectFirst("#developers_list > a");
			
			Element catagoryEl = doc.selectFirst("body > div.responsive_page_frame.with_header > div.responsive_page_content"
								+ " > div.responsive_page_template_content >"
								+ " div.game_page_background.game > div.page_content_ctn > "
								+ "div.page_title_area.game_title_area.page_content"
								+ " > div.breadcrumbs > div.blockbg > a:nth-child(2)");
			
			Element relaseDateEl = doc.selectFirst("#game_highlights > div.rightcol > div > "
								+ "div.glance_ctn_responsive_left >"
                                                                + " div > div.release_date > div.date");
			
			
			/*if(priceEl == null)
			{
            
				priceEl = doc.selectFirst("#game_area_purchase > div:nth-child(2) > div > div.game_purchase_action"
						+ " > div:nth-child(2) > div.game_purchase_price.price");
			}
                        else if(priceEl == null) {}*/
			String feedbackText = feedbackEl.text();
			String imgUrl = imgEl.attr("src");
			String description = descriptionEl.text();
			String cleanNumOrReview = numOfRewiew.text().replace(",","").replace("(","").replace(")", "");
			int totalReview = Integer.parseInt(cleanNumOrReview);
			Pattern pattern = Pattern.compile("[0-9]*%");
			Matcher matcher = pattern.matcher(feedbackText);
			
			int percentage,goodReview = 0;
			if (matcher.find())
			{
			   percentage = Integer.parseInt( matcher.group(0).replace("%", ""));
			   goodReview = (totalReview * percentage)/100;
			}
			
			//String price = priceEl.text().replace("€", "").replace(",", ".");
			String relaseDate = relaseDateEl.text();
			String developer = developerEl.text();
			String category = catagoryEl.text();
			
			map.put(DESCRIPTION, description);
			map.put(FEEDBACK, ""+goodReview);
			map.put(IMG, imgUrl);
			//map.put(PRICE, price);
			map.put(CATEGORY, category);
			map.put(DEVELOPER, developer);
			map.put(RELEASEDATE, relaseDate);
	
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
