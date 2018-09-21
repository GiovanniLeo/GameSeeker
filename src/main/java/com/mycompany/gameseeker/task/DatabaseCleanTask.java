/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gameseeker.task;

import com.mycompany.gameseeker.mongoDB.Result;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author johnn
 */
public class DatabaseCleanTask implements Runnable{
    
    private Datastore ds;

    public DatabaseCleanTask(Datastore ds) {
        this.ds = ds;
    }

   
    
    

    @Override
    public void run() {
       Query<Result> allResult = ds.createQuery(Result.class);
       ds.delete(allResult);
        System.out.println("Clean OK");
    }
    
}
