/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gameseeker.mediator;

import com.mycompany.gameseeker.wrappers.*;

/**
 *
 * @author johnn
 */
public class Mediator {
    
    private Steam steam;
    private InstantGaming instantGaming;
    private Amazon amazon;
    private YouTube youTube;
    
    
    public Mediator()
    {
        steam = new Steam();
        instantGaming = new InstantGaming();
        amazon = new Amazon();
        youTube = new YouTube();
    }
    
    
}
