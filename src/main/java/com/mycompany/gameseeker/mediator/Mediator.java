/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gameseeker.mediator;

import com.mycompany.gameseeker.task.*;
import com.mycompany.gameseeker.utility.Result;
import com.mycompany.gameseeker.wrappers.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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

    public void getData(String searchQuery) {

        Future<ArrayList<Result>> fEveryeye = exec
                .submit(new EveryeyeTask(searchQuery));

        Future<ArrayList<Result>> fInstantGaming = exec
                .submit(new InstantGamingTask(searchQuery));

        Future<ArrayList<Result>> fG2A = exec
                .submit(new G2ATask(searchQuery));

        Future<HashMap<String, String>> fYouTube = exec
                .submit(new YouTubeTask(searchQuery));

        try {
            igResults = fInstantGaming.get();
            g2aResults = fG2A.get();
            youTubeResults = fYouTube.get();
            everyeyeResults = fEveryeye.get();
        } catch (Exception e) {
            e.printStackTrace();
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
