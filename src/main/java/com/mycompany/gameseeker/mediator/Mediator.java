/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gameseeker.mediator;

import com.mycompany.gameseeker.task.*;
import com.mycompany.gameseeker.utility.Result;
import com.mycompany.gameseeker.utility.Utility;
import com.mycompany.gameseeker.wrappers.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.commons.text.similarity.LevenshteinDetailedDistance;
import org.apache.commons.text.similarity.LevenshteinResults;

/**
 *
 * @author johnn
 */
public class Mediator {

    private ArrayList<Result> igResults;
    private ArrayList<Result> g2aResults;
    private HashMap<String, String> youTubeResults;
    private ArrayList<Result> everyeyeResults;
    private Result amazonResult;

    private static ExecutorService exec = Executors.newCachedThreadPool();

    public Mediator() {
        igResults = new ArrayList<>();
        g2aResults = new ArrayList<>();
        youTubeResults = new HashMap<>();
        everyeyeResults = new ArrayList<>();
    }

    private void getDataFromSites(String searchQuery) {

        Future<ArrayList<Result>> fEveryeye = exec
                .submit(new EveryeyeTask(searchQuery));

        Future<ArrayList<Result>> fInstantGaming = exec
                .submit(new InstantGamingTask(searchQuery));

        Future<ArrayList<Result>> fG2A = exec
                .submit(new G2ATask(searchQuery));

        Future<HashMap<String, String>> fYouTube = exec
                .submit(new YouTubeTask(searchQuery));
        Future<Result> fAmazon = exec
                .submit(new AmazonTask(searchQuery));

        try {
            igResults = fInstantGaming.get();
            g2aResults = fG2A.get();
            youTubeResults = fYouTube.get();
            everyeyeResults = fEveryeye.get();
            amazonResult = fAmazon.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void selectElements(String searchQuery) {
        getDataFromSites(searchQuery);

        String loweIgTitle, lowerG2ATitle;
        int average = 0, distanceSum = 0, count = 1, distance;
        LevenshteinDetailedDistance lev = new LevenshteinDetailedDistance();
        LevenshteinResults levDistance;

        //System.out.println(igResults.size()+" "+g2aResults.size());
        for (int i = 0; i < igResults.size(); i++) {
            for (int j = 0; j < g2aResults.size(); j++) {

                loweIgTitle = igResults.get(i).getTitle().toLowerCase();
                lowerG2ATitle = g2aResults.get(j).getTitle().toLowerCase();
                levDistance = lev.apply(loweIgTitle, lowerG2ATitle);
                distance = levDistance.getDistance();
                //System.out.println(loweIgTitle + "\n" + lowerG2ATitle +"\nDistance->"+distance+ "\n-----------");
                distanceSum += distance;
                count++;

            }
        }

        average = distanceSum / count;
        System.out.println("Media->" + average);
        double minPrice;
        double priceIg, priceG2A;
        String linkToRef,requisitiMinimi,requisistiConsigliati;
        String descrizione,releaseDate,title,plattform,img;
        int feedback;
        for (int i = 0; i < igResults.size(); i++) {
            for (int j = 0; j < g2aResults.size(); j++) {

                loweIgTitle = igResults.get(i).getTitle().toLowerCase();
                lowerG2ATitle = g2aResults.get(j).getTitle().toLowerCase();
                loweIgTitle = Utility.clearSpecialCharacterWithDigits(loweIgTitle);
                lowerG2ATitle = Utility.clearSpecialCharacterWithDigits(lowerG2ATitle);
                //  System.out.println(loweIgTitle + "\n" + lowerG2ATitle + "\n-----------");
                if (lowerG2ATitle.contains(loweIgTitle)) {
                    if (igResults.get(i).getPlattformTitle().equalsIgnoreCase(g2aResults.get(j).getPlattformTitle())) {
                        levDistance = lev.apply(loweIgTitle, lowerG2ATitle);
                        distance = levDistance.getDistance();
                        if (distance < average) {

                            priceIg = igResults.get(i).getPrice();
                            priceG2A = g2aResults.get(j).getPrice();

                            if (priceIg < priceG2A) {

                                minPrice = priceIg;
                                linkToRef = igResults.get(i).getLinkRef();
                            } else {
                                minPrice = priceG2A;
                                linkToRef = g2aResults.get(j).getLinkRef();
                            }
                            
                            img = igResults.get(i).getImgUrl();
                            plattform = igResults.get(i).getPlattformTitle();
                            title = igResults.get(i).getTitle();
                            requisitiMinimi = igResults.get(i).getRequisitiMinimi();
                            requisistiConsigliati = igResults.get(i).getRequisitiConsigliati();
                            descrizione = igResults.get(i).getDescription();
                            feedback = igResults.get(i).getFeedback();
                            releaseDate = igResults.get(i).getReleaseDate();
                            
                            System.out.println(igResults.get(i).getPlattformTitle() + "\n" + g2aResults.get(j).getPlattformTitle());
                            System.out.println(loweIgTitle + "\n" + lowerG2ATitle + "\n-----------");
                            
                            igResults.remove(i);
                            g2aResults.remove(j);
                        }
                    }

                    if (loweIgTitle.contains(lowerG2ATitle)) {
                        if (igResults.get(i).getPlattformTitle().equalsIgnoreCase(g2aResults.get(j).getPlattformTitle())) {
                            // levDistance = lev.apply(loweIgTitle, lowerG2ATitle);
                            // distance = levDistance.getDistance();
                            // if (distance < average) {
                            //  System.out.println(igResults.get(i).getPlattformTitle() + "\n" + g2aResults.get(j).getPlattformTitle());
                            //System.out.println(loweIgTitle + "\n" + lowerG2ATitle + "\n-----------");
                            // }
                        }
                    }

                }
            }
            System.out.println("No match");
        }
    }

    public ArrayList<Result> getIgResults() {
        return igResults;
    }

    public ArrayList<Result> getG2aResults() {
        return g2aResults;
    }

    public HashMap<String, String> getYouTubeResults() {
        return youTubeResults;
    }

    public ArrayList<Result> getEveryeyeResults() {
        return everyeyeResults;
    }

    public Result getAmazonResult() {
        return amazonResult;
    }

}
