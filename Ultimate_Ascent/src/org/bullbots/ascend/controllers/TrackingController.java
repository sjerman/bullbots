/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bullbots.ascend.controllers;

import org.bullbots.ascend.hardware.Camera;

/**
 *
 * @author Loid Sherwood
 */
public class TrackingController {
    
    Camera camera;
    
    public TrackingController(){
        try{
            camera = new Camera();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void trackGoal(){
        camera.getCameraImage();
        
    }
}