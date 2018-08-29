/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gameseeker.task;

import com.mycompany.gameseeker.mongoDB.Result;
import com.mycompany.gameseeker.wrappers.YouTube;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;

/**
 *
 * @author johnn
 */
public class YouTubeTask implements Callable<ArrayList<Result>> {

    private String searchString;
    private YouTube yt;

    public YouTubeTask(String searchString) {
        yt = new YouTube();
        this.searchString = searchString;
    }
    

    @Override
    public ArrayList<Result> call() throws Exception {
        return yt.searchResults(searchString);
    }

}
