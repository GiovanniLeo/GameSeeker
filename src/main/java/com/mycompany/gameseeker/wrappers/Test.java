package com.mycompany.gameseeker.wrappers;

import com.mycompany.gameseeker.mongoDB.Result;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.mycompany.gameseeker.task.InstantGamingTask;
import com.mycompany.gameseeker.task.*;
import org.apache.commons.text.similarity.CosineDistance;
import org.apache.commons.text.similarity.JaccardSimilarity;
import org.apache.commons.text.similarity.LevenshteinDetailedDistance;
import org.apache.commons.text.similarity.LevenshteinResults;

public class Test {
	
	private static ExecutorService exec = Executors.newCachedThreadPool();
  
        
        
        
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
	/*			Amazon amz = new Amazon();
				Result minResult;
                                 minResult = amz.searchResult("Dark souls III");
				if(minResult != null)
				{
				System.out.println(minResult.getTitle() +"\n"+minResult.getImgUrl() +"\n"+minResult.getLinkRef() +"\n"+
						minResult.getPlattformTitle() +"\n"+minResult.getPrice()+"\n"+minResult.getAvailability()+"\n"
						+minResult.getVendor()+"\n");
				}
				else
				{
					System.out.println("Nessun risultato trovato");
				}*/
		

		long start = System.currentTimeMillis();
//		Steam steam = new Steam();
//		ArrayList<Result> results = steam.searchResul("Dark Souls");
//             &platform=272&platform=1&platform=2&platform=9242
		/*InstantGaming ig = new InstantGaming();
		ArrayList<Result> results2 = ig.searchResults("fddfgdfgdgd");*/
		
		//ArrayList<Result> results,results2;
		G2A g2a = new G2A();/*
                ArrayList<Result> results3 = g2a.searchResults("Overwatch");
                for(int i = 0; i < results3.size(); i++)
                {
                    System.out.println(results3.get(i).getTitle()+"\n"+results3.get(i).getPlattformTitle());
                }*/
		//Future<ArrayList<Result>> f1 = exec.submit(new SteamTask("Dark souls"));
		//Future<ArrayList<Result>> f2 = exec.submit(new InstantGamingTask("The witcher"));
		
		//results=f1.get();
		//results2 = f2.get();
/*
		for (int i = 0; i < results.size(); i++) {
			for (int j = 0; j < results2.size(); j++) {

				int distance = Levenshtein.levenshteinDistance(results.get(i).getTitle(), results2.get(j).getTitle());
				double minP = 0;
				String link;
				if(distance < 18)
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
		}*/
               /* LevenshteinDetailedDistance cd = new LevenshteinDetailedDistance();
		System.out.println("-------------------------------------------");
                int divivsion=0,sum = 1;
                ArrayList<Integer> array = new ArrayList<>();
                System.out.println("Qui"+results.size());
		for (int i = 0; i < results.size(); i++) {
                    System.out.println("Primo for");
			for (int j = 0; j < results2.size(); j++) {
                            System.out.println("Titolo 1--->"+results.get(i).getTitle()+
                            "\nTitolo 2--->"+results2.get(j).getTitle());
				//int distance = Levenshtein.levenshteinDistance(results.get(i).getTitle(), results2.get(j).getTitle());
                                LevenshteinResults resultDistance = cd.apply(results.get(i).getTitle(), results2.get(j).getTitle());
                                int distance = resultDistance.getDistance();
				System.out.println("{\n"+results.get(i).getTitle()+"\n"+
						results2.get(j).getTitle()+"\n"+"distance->"+distance+"\n}");
                                array.add(distance);
                                divivsion+=distance;
                                sum++;
			}
		}
                
                double media = divivsion/sum;
                double sumOfSquare=0;
                for(int i = 0; i< array.size();i++)
                {
                    double partial = array.get(i) - media;
                    double square = partial*partial;
                    sumOfSquare+=square;
                    
                }
                System.out.println("Media -->"+divivsion/sum);
                System.out.println("Varianza -->"+sumOfSquare/sum);

               */
		YouTube yt = new YouTube();
		ArrayList<Result>map = yt.searchResults("Dark Souls");
		System.out.println(map.get(0).getTitle());
		System.out.println(map.get(1).getTitle());
		/*
		long end = System.currentTimeMillis();
		double total = (end-start)/1000;
		System.out.println("Tempo totale in secondi:"+total);*/

                
              /*  Everyeye ey = new Everyeye();

                ArrayList<Result> res;
                res = ey.searchResults("Grand Theft Auto V");
                System.out.println(res.size());*/
              
          /*    ArrayList<String> titles = new ArrayList<>();
              titles = g2a.getListOfAllAdditionalComponents();
              
              for (int i = 0; i < titles.size(); i++) {
                  System.out.println(titles.get(i));
            }*/
          
         /* ArrayList<Result> result = new ArrayList<>();
          result = g2a.searchResults("Dark souls");
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i).getTitle());
                System.out.println(result.get(i).getPlattformTitle());
            }*/


	}
}
