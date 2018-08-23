/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gameseeker.task;

import com.mycompany.gameseeker.utility.Result;
import com.mycompany.gameseeker.wrappers.Everyeye;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 *
 * @author johnn
 */
public class EveryeyeTask implements Callable<ArrayList<Result>>{
    
    private String searchString;
    private Everyeye ev;

    public EveryeyeTask(String searchString) {
        this.searchString = searchString;
        ev = new Everyeye();
    }

    @Override
    public ArrayList<Result> call() throws Exception {
        System.out.println("Sono qui");
     return ev.searchResults(searchString);
    }
    
    
    
}
