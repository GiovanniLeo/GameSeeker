/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gameseeker.listener;

import com.mongodb.MongoClient;
import com.mycompany.gameseeker.dbResurces.DBResurces;
import com.mycompany.gameseeker.mongoDB.Result;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author johnn
 */
@WebListener
public class BackgroundTask implements ServletContextListener{
private ScheduledExecutorService scheduledExecutorService;
private Datastore ds;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                 DBResurces db = new DBResurces("com.mycompany.gameseeker.mongoDB",
                "GameSeekerDB");
               ds = db.getDatastore();
               Query<Result> allResult = ds.createQuery(Result.class);
               ds.delete(allResult);
            }
        }, 6, 6, TimeUnit.HOURS);
        
    }
   

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        scheduledExecutorService.shutdownNow();
    }
    
}
