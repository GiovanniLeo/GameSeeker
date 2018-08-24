/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gameseeker.task;

import com.mycompany.gameseeker.utility.Result;
import com.mycompany.gameseeker.wrappers.G2A;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 *
 * @author johnn
 */
public class G2AAdditionalComponentTask implements Callable<ArrayList<String>>{
    
   ;
    private G2A g2a;

    public G2AAdditionalComponentTask() {
        g2a = new G2A();
        
    }
  
    @Override
    public ArrayList<String> call() throws Exception {
        return g2a.getListOfAllAdditionalComponents();
    }
    
}
