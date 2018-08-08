package wrappers;

import java.util.ArrayList;

import utility.Result;

public class Test {
	
	public static void main(String[] args) {
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
		
		
		InstantGaming ig = new InstantGaming();
		 ArrayList<Result> results = ig.searchResults("Airport Simulator");
		 
		 for (int i = 0; i < results.size(); i++) {
			
			System.out.println(results.get(i).instantGamingToString());
		}
		
	}

}
