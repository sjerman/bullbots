/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bullbots.ascend.hardware;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.image.ColorImage;

public class Camera{

    private static AxisCamera cam; 
    private ColorImage img;
    
    
    
    public Camera(){
        try{
            cam = AxisCamera.getInstance("10.18.91.11");
            cam.writeMaxFPS(15);
            cam.writeResolution(AxisCamera.ResolutionT.k320x240);
            cam.writeCompression(30);
            cam.writeWhiteBalance(AxisCamera.WhiteBalanceT.automatic);
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
    public ColorImage getCameraImage(){
        try{
            if(cam.freshImage()){
                return cam.getImage();
            }
            else{
                return img;
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public void setFPS(int frames){
        cam.writeMaxFPS(frames);
    }
    
    public boolean hasImage(){
        return (cam != null);
    }
}