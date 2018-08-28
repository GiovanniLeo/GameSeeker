/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gameseeker.dbResurces;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author johnn
 */
public class DBResurces {
    private Morphia morphia;
    private String packageToMap;
    private String DbName;
    private Datastore datastore;
    
    public DBResurces(String packageToMap,String DbName)
    {
        this.packageToMap = packageToMap;
        this.DbName = DbName;
        morphia = new Morphia();
        datastore = morphia.createDatastore(new MongoClient(), DbName);
    }

    public Morphia getMorphia() {
        return morphia;
    }

    public void setMorphia(Morphia morphia) {
        this.morphia = morphia;
    }

    public String getPackageToMap() {
        return packageToMap;
    }

    public void setPackageToMap(String packageToMap) {
        this.packageToMap = packageToMap;
    }

    public String getDbName() {
        return DbName;
    }

    public void setDbName(String DbName) {
        this.DbName = DbName;
    }

    public Datastore getDatastore() {
        return datastore;
    }

    public void setDatastore(Datastore datastore) {
        this.datastore = datastore;
    }

 
    
    
}
