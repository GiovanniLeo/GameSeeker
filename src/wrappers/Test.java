package wrappers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import distanceComputation.Levenshtein;
import task.InstantGamingTask;
import task.SteamTask;
import utility.Result;

public class Test {
	
	private static ExecutorService exec = Executors.newCachedThreadPool();
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
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

		long start = System.currentTimeMillis();
//		Steam steam = new Steam();
//		ArrayList<Result> results = steam.searchResul("Dark Souls");
//
//		InstantGaming ig = new InstantGaming();
//		ArrayList<Result> results2 = ig.searchResults("Dark Souls");
		
		ArrayList<Result> results,results2;
		
		Future<ArrayList<Result>> f1 = exec.submit(new SteamTask("Dark souls"));
		Future<ArrayList<Result>> f2 = exec.submit(new InstantGamingTask("Dark souls"));
		
		results=f1.get();
		results2 = f2.get();

		for (int i = 0; i < results.size(); i++) {
			for (int j = 0; j < results2.size(); j++) {

				int distance = Levenshtein.levenshteinDistance(results.get(i).getTitle(), results2.get(j).getTitle());
				double minP = 0;
				String link;
				if(distance <= 18)
				{
					if(results.get(i).getPrice() < results2.get(j).getPrice())
					{
						minP = results.get(i).getPrice();
						link =  results.get(i).getLinkRef();
					}
					else
					{
						minP = results2.get(j).getPrice();
						link =  results2.get(j).getLinkRef();
					}
					
					int goodRewiews = results.get(i).getFeedback();
					if(results2.get(j).getFeedback() > 0)
					{
						goodRewiews+=results2.get(j).getFeedback();
						goodRewiews = goodRewiews/2;
					}
					

					System.out.println("{\n"+results.get(i).getTitle()+"\n"+
							results2.get(j).getTitle()+"\n"+"distance->"+distance+"\nMinP->"+minP+"\nLink->"+link+"\nRewiew->"+goodRewiews+"\n}");


				}

			}
		}
		System.out.println("-------------------------------------------");
		for (int i = 0; i < results.size(); i++) {
			for (int j = 0; j < results2.size(); j++) {
				int distance = Levenshtein.levenshteinDistance(results.get(i).getTitle(), results2.get(j).getTitle());
				System.out.println("{\n"+results.get(i).getTitle()+"\n"+
						results2.get(j).getTitle()+"\n"+"distance->"+distance+"\n}");
			}
		}



		YouTube yt = new YouTube();
		HashMap<String, String> map = yt.searchResults("Dark Souls");
		System.out.println(map.get(YouTube.GP));
		System.out.println(map.get(YouTube.VD));
		
		long end = System.currentTimeMillis();
		double total = (end-start)/1000;
		System.out.println("Tempo totale in secondi:"+total);




	}
}
