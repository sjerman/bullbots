/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bullbots.ascend.hardware;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 *
 * @author Admin
 */
public class DriveJaguar {
    CANJaguar jag;
    
    double P = .05;
    double I = .02;
    double D = .5;
    
    public DriveJaguar(int number){
        try{
            jag = new CANJaguar(number);
            jag.setPID(P, I, D);
            jag.setSpeedReference(CANJaguar.SpeedReference.kQuadEncoder);
        }
        catch(CANTimeoutException ex){
            System.out.println("Problem initializing jags");
            ex.printStackTrace();
        }
    
    }
    
    public void set(double value){
        try {
            jag.setX(value);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public double getSetSpeed(){
        try {
            return jag.getX();
        } catch (CANTimeoutException ex) {
            System.out.println("Problem getting speed");
            ex.printStackTrace();
        }
        return 3.14159265358979323846264332;
    }
}
