package wrappers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import utility.Result;

public class Test {
	
	public static void main(String[] args) throws IOException {
//		Amazon amz = new Amazon();
//		Result minResult = amz.searchResult("dark souls");
//		if(minResult != null)
//		{
//		System.out.println(minResult.getTitle() +"\n"+minResult.getImgUrl() +"\n"+minResult.getLinkRef() +"\n"+
//				minResult.getPlattformTitle() +"\n"+minResult.getPrice()+"\n"+minResult.getAvailability()+"\n"
//				+minResult.getVendor()+"\n");
//		}
//		else
//		{
//			System.out.println("Nessun risultato trovato");
//		}
//		
	
//		Steam steam = new Steam();
//		 ArrayList<Result> results = steam.searchResul("Counter Strike");
//		 for (int i = 0; i < results.size(); i++) {
//			
//			System.out.println(results.get(i).steamToString());
//		}
		
		
//		InstantGaming ig = new InstantGaming();
//		 ArrayList<Result> results = ig.searchResults("Airport Simulator");
//		 
//		 for (int i = 0; i < results.size(); i++) {
//			
//			System.out.println(results.get(i).instantGamingToString());
//		
		
		YouTube yt = new YouTube();
		HashMap<String, String> map = yt.searchResults("Dark Souls");
		System.out.println(map.get(YouTube.GP));
		System.out.println(map.get(YouTube.VD));
		

	}

}
