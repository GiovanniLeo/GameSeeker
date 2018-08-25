/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gameseeker.task;

import com.mycompany.gameseeker.utility.Result;
import com.mycompany.gameseeker.wrappers.Amazon;
import java.util.concurrent.Callable;

/**
 *
 * @author johnn
 */
public class AmazonTask implements Callable<Result>{
    private String searchString;
    private Amazon amazon;

    public AmazonTask(String searchString) {
        this.searchString = searchString;
        amazon = new Amazon();
    }
    
    
    @Override
    public Result call() throws Exception {
        return amazon.searchResult(searchString);
    }
    
}
