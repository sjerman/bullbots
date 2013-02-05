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
    BinaryImage thresholdImage;
    BinaryImage convexImage;
    BinaryImage removeSmallObjectImage;
    
    
    
    
    
    
    CriteriaCollection criteriaCollection = new CriteriaCollection();
    ParticleAnalysisReport[] particleReport;
    JoystickControl joystick;
    
    ParticleAnalysisReport highestRectangle;
    
    private boolean pressed = false;
    
    private final int GREEN_LOW = 230;
    private final int GREEN_HIGH = 255;
    
    private final double RECTANGULARITY = .9;
    
    private double dif = 0;
    
    public TrackingController(JoystickControl joystick){
        try{
            this.joystick = joystick;
            camera = new Camera();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public double getDif(){
        return dif;
    }
    
    public void trackGoal(){
        

        try
        {
            
            image = camera.getCameraImage();
            
            if(image != null){
                thresholdImage = image.thresholdRGB(0, 255, GREEN_LOW, GREEN_HIGH, 0, 255);
                convexImage = thresholdImage.convexHull(true);
                removeSmallObjectImage = convexImage.removeSmallObjects(false, 4);
                removeSmallObjectImage.particleFilter(criteriaCollection);
                particleReport = removeSmallObjectImage.getOrderedParticleAnalysisReports();


                for(int i = 0; i  < particleReport.length; i++){
                    if(i == 0){
                        highestRectangle = particleReport[0];
                        for(int k = 0; k < particleReport.length; k++){
                            
                            if(particleReport[k].particleArea/(particleReport[k].boundingRectHeight*particleReport[k].boundingRectWidth) >= RECTANGULARITY){
                                highestRectangle = particleReport[k];
                                k = particleReport.length;
                                i = k + 1;
                            }
                        } 
                    }
                    else if(highestRectangle.center_mass_y < particleReport[i].center_mass_y && particleReport[i].particleArea/(particleReport[i].boundingRectHeight*particleReport[i].boundingRectWidth) >= RECTANGULARITY){
                        highestRectangle = particleReport[i];
                    }
                }

                if(highestRectangle != null){
                   dif = -((image.getWidth()/2) - highestRectangle.center_mass_x);  
                   //System.out.println(highestRectangle.center_mass_x);
                   System.out.println("DIFF: " + dif);
                }




                if(joystick.getButton(1)){
                    if(!pressed){
                        removeSmallObjectImage.write("/FilteredImage.png");
                        System.out.println("Writing image");
                    }
                    pressed = true;
                } 
                else{
                    pressed = false;
                }

                //System.out.println("Freeing images");
                
                image.free();
                thresholdImage.free();
                convexImage.free();
                removeSmallObjectImage.free();
                
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
       
    }
}