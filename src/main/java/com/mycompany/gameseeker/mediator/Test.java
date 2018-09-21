/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gameseeker.mediator;

import com.mycompany.gameseeker.mongoDB.Result;

/**
 *
 * @author johnn
 */
public class Test {
    public static void main(String[] args) {
        Mediator md = new Mediator();
        md.selectElements("Dark Souls");
        //md.checkElements("Dark Souls III");
       /* Result res = md.getG2aResults().get(0);
        System.out.println("----------------------------------------");
        System.out.println(res.getTitle());
        Result res1 = md.getElementByIdFromDB(res.getId()+"");
        System.out.println(res1.getTitle());*/
        
        
    }
}
