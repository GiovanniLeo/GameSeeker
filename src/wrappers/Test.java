package wrappers;

import utility.Result;

public class Test {
	
	public static void main(String[] args) {
		AmazonScraping amz = new AmazonScraping();
		Result minResult = amz.searchResult("dark souls");
		if(minResult != null)
		{
		System.out.println(minResult.getTitle() +"\n"+minResult.getImgUrl() +"\n"+minResult.getLinkRef() +"\n"+
				minResult.getPlattformTitle() +"\n"+minResult.getPrice()+"\n"+minResult.getAvailability()+"\n");
		}
		else
		{
			System.out.println("Nessun risultato trovato");
		}
		
	
		
	}

}
