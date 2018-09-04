/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gameseeker.mediator;

import com.mongodb.DBCollection;
import com.mycompany.gameseeker.dbResurces.DBResurces;
import com.mycompany.gameseeker.task.*;
import com.mycompany.gameseeker.mongoDB.Result;
import com.mycompany.gameseeker.utility.Utility;
import com.mycompany.gameseeker.wrappers.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.commons.text.similarity.LevenshteinDetailedDistance;
import org.apache.commons.text.similarity.LevenshteinResults;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author johnn
 */
public class Mediator {

    private ArrayList<Result> igResults;
    private ArrayList<Result> g2aResults;
    private ArrayList<Result> youTubeResults;
    private ArrayList<Result> everyeyeResults;
    private Result amazonResult;
    private DBResurces db;
    private Datastore ds;
    private ArrayList<Result> resultsMatch;

    private static ExecutorService exec = Executors.newCachedThreadPool();

    public Mediator() {
        igResults = new ArrayList<>();
        g2aResults = new ArrayList<>();
        youTubeResults = new ArrayList<>();
        everyeyeResults = new ArrayList<>();
        resultsMatch = new ArrayList<>();
        db = new DBResurces("com.mycompany.gameseeker.mongoDB",
                "GameSeekerDB");
        ds = db.getDatastore();
    }

    private void getDataFromSites(String searchQuery) {

        getDataFromAmazon(searchQuery);
        getDataFromEveryeye(searchQuery);
        getDataFromInstantGaming(searchQuery);
        getDataFromYouTube(searchQuery);
        getDataFromG2A(searchQuery);

    }

    private void getDataFromInstantGaming(String searchQuery) {
        Future<ArrayList<Result>> fInstantGaming = exec
                .submit(new InstantGamingTask(searchQuery));
        try {
            igResults = fInstantGaming.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getDataFromAmazon(String searchQuery) {
        Future<Result> fAmazon = exec
                .submit(new AmazonTask(searchQuery));
        try {
            amazonResult = fAmazon.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getDataFromYouTube(String searchQuery) {
        Future<ArrayList<Result>> fYouTube = exec
                .submit(new YouTubeTask(searchQuery));
        try {
            youTubeResults = fYouTube.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getDataFromG2A(String searchQuery) {
        Future<ArrayList<Result>> fG2A = exec
                .submit(new G2ATask(searchQuery));
        try {
            g2aResults = fG2A.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getDataFromEveryeye(String searchQuery) {
        Future<ArrayList<Result>> fEveryeye = exec
                .submit(new EveryeyeTask(searchQuery));
        try {
            everyeyeResults = fEveryeye.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectElements(String searchQuery) {

        if (!checkElements(searchQuery)) {
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
                    // System.out.println(loweIgTitle + "\n" + lowerG2ATitle +"\nDistance->"+distance+ "\n-----------");
                    distanceSum += distance;
                    count++;

                }
            }

            average = distanceSum / count;
            System.out.println("Media->" + average);
            double minPrice;
            double priceIg, priceG2A;
            String linkToRef, requisitiMinimi, requisistiConsigliati;
            String descrizione, releaseDate, title, plattform, img;
            int feedback;
            for (int i = 0; i < igResults.size(); i++) {
                for (int j = 0; j < g2aResults.size(); j++) {

                    if (igResults.size() == 0 || g2aResults.size() == 0) {
                        break;
                    }

                    loweIgTitle = igResults.get(i).getTitle().toLowerCase();
                    lowerG2ATitle = g2aResults.get(j).getTitle().toLowerCase();
                    loweIgTitle = Utility.clearSpecialCharacterWithDigits(loweIgTitle);
                    lowerG2ATitle = Utility.clearSpecialCharacterWithDigits(lowerG2ATitle);
                    //  System.out.println(loweIgTitle + "\n" + lowerG2ATitle + "\n-----------");
                    if (lowerG2ATitle.contains(loweIgTitle)) {
                        if (igResults.get(i).getPlattformTitle().equalsIgnoreCase(g2aResults.get(j).getPlattformTitle())) {
                            levDistance = lev.apply(loweIgTitle, lowerG2ATitle);
                            distance = levDistance.getDistance();
                            if (distance < (average + 2)) {

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

                                // System.out.println(igResults.get(i).getPlattformTitle() + "\n" + g2aResults.get(j).getPlattformTitle());
                                //System.out.println(loweIgTitle + "\n" + lowerG2ATitle + "\n-----------");
                                Result res = new Result(title, minPrice, linkToRef, img, plattform,
                                        requisitiMinimi, requisistiConsigliati,
                                        descrizione, feedback, releaseDate);
                                res.setType(Utility.MATCH);
                                resultsMatch.add(res);

                                System.out.println(title + "\n" + minPrice + "\n"
                                        + linkToRef + "\n" + img + "\n" + plattform + "\n"
                                        + requisitiMinimi + "\n" + requisistiConsigliati + "\n"
                                        + descrizione + "\n" + feedback + "\n" + releaseDate + "\n--------------");
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
            System.out.println("Prima di salvare");
            saveAllData();
            System.out.println("Salvati");

        }
        debug();
    }

    public boolean checkElements(String searchQuery) {

        searchQuery = Utility.checkRomanNumber(searchQuery);
        System.out.println(searchQuery);
        searchQuery = searchQuery.trim();
        Query<Result> query = ds.find(Result.class).field("title").
                containsIgnoreCase(searchQuery);

        List<Result> list = query.asList();
        List<Result> dbResults = new ArrayList<>();

        if (list.size() > 0) {

            dbResults = retriveDataFromDB(searchQuery, Utility.MATCH).asList();

            if (dbResults.size() > 0) {
                resultsMatch = (ArrayList) dbResults;
            } else {
                getDataFromSites(searchQuery);
            }

            dbResults = retriveDataFromDB(searchQuery, Utility.YT).asList();

            if (dbResults.size() > 0) {
                youTubeResults = (ArrayList) dbResults;
            } else {
                getDataFromYouTube(searchQuery);
            }

            dbResults = retriveDataFromDB(searchQuery, Utility.AMAZ).asList();

            if (dbResults.size() > 0) {
                amazonResult = dbResults.get(0);
            } else {
                getDataFromAmazon(searchQuery);
            }

            dbResults = retriveDataFromDB(searchQuery, Utility.EVE).asList();

            if (dbResults.size() > 0) {
                everyeyeResults = (ArrayList) dbResults;
            } else {
                getDataFromEveryeye(searchQuery);
            }

            dbResults = retriveDataFromDB(searchQuery, Utility.IG).asList();

            if (dbResults.size() > 0) {
                igResults = (ArrayList) dbResults;
            } else {
                getDataFromInstantGaming(searchQuery);
            }

            dbResults = retriveDataFromDB(searchQuery, Utility.G2A).asList();

            if (dbResults.size() > 0) {
                g2aResults = (ArrayList) dbResults;
            } else {
                getDataFromG2A(searchQuery);
            }
           // debug();
            //resultsMatch = (ArrayList) list;
            return true;
        }
        return false;
    }

    private void saveAllData() {
        for (int i = 0; i < resultsMatch.size(); i++) {
            ds.save(resultsMatch.get(i));
        }

        for (int i = 0; i < youTubeResults.size(); i++) {
            ds.save(youTubeResults.get(i));
        }

        for (int i = 0; i < igResults.size(); i++) {
            ds.save(igResults.get(i));
        }

        for (int i = 0; i < g2aResults.size(); i++) {
            ds.save(g2aResults.get(i));
        }

        for (int i = 0; i < everyeyeResults.size(); i++) {
            ds.save(everyeyeResults.get(i));
        }

        if (amazonResult != null) {
            ds.save(amazonResult);
        }

    }

    private Query retriveDataFromDB(String searchQuery, String type) {
        searchQuery = searchQuery.trim();
        Query<Result> query = ds.find(Result.class)
                .field("title").containsIgnoreCase(searchQuery)
                .field("type").equalIgnoreCase(type);
        return query;
    }

    public ArrayList<Result> getIgResults() {
        return igResults;
    }

    public ArrayList<Result> getG2aResults() {
        return g2aResults;
    }

    public ArrayList<Result> getYouTubeResults() {
        return youTubeResults;
    }

    public ArrayList<Result> getEveryeyeResults() {
        return everyeyeResults;
    }

    public Result getAmazonResult() {
        return amazonResult;
    }

    public ArrayList<Result> getResultsMatch() {
        return resultsMatch;
    }
    

    private void debug() {
        for (int i = 0; i < resultsMatch.size(); i++) {
            System.out.println(resultsMatch.get(i).getTitle() + "--->" + resultsMatch.get(i).getType());
        }

        for (int i = 0; i < igResults.size(); i++) {
            System.out.println(igResults.get(i).getTitle() + "--->" + igResults.get(i).getType());
        }
        if (g2aResults != null) {
            
            for (int i = 0; i < g2aResults.size(); i++) {
                System.out.println(g2aResults.get(i).getTitle() + "--->" + g2aResults.get(i).getType());
            }
        }

        for (int i = 0; i < everyeyeResults.size(); i++) {
            System.out.println(everyeyeResults.get(i).getTitle() + "--->" + everyeyeResults.get(i).getType());
        }

        for (int i = 0; i < youTubeResults.size(); i++) {
            System.out.println(youTubeResults.get(i).getTitle() + "--->" + youTubeResults.get(i).getType());
        }

        if (amazonResult != null) {
            System.out.println(amazonResult.getTitle() + "--->" + amazonResult.getType());
        }

    }

}
