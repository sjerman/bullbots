/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bullbots.ascend.controllers;

import edu.wpi.first.wpilibj.image.*;
import org.bullbots.ascend.hardware.Camera;

/**
 *
 * @author Loid Sherwood
 */
public class TrackingController {
    
    Camera camera;
    ColorImage image;
    BinaryImage filteredImage;
    CriteriaCollection criteriaCollection = new CriteriaCollection();
    ParticleAnalysisReport[] particleReport;
    JoystickControl joystick;
    
    private boolean pressed = false;
    
    private final int GREEN_LOW = 198;
    private final int GREEN_HIGH = 255;
    
    public TrackingController(JoystickControl joystick){
        try{
            this.joystick = joystick;
            camera = new Camera();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void trackGoal(){
        image = camera.getCameraImage();
        
        try
        {
            filteredImage = image.thresholdRGB(0, 0, GREEN_LOW, GREEN_HIGH, 0, 0);
            filteredImage = filteredImage.convexHull(true);
            filteredImage = filteredImage.removeSmallObjects(false, 4);
            filteredImage.particleFilter(criteriaCollection);
            particleReport = filteredImage.getOrderedParticleAnalysisReports();
            
            if(!pressed && joystick.getButton(9)){
                filteredImage.write("FilteredImage.png");
                pressed = true;
            } 
            else{
                pressed = false;
            }
            
            filteredImage.free();
            image.free();
        }
        
        catch(Exception e){}
    }
}