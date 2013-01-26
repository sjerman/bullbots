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
    
    int P = 1;
    int I = 1;
    int D = 1;
    
    public DriveJaguar(int number){
        try{
            jag = new CANJaguar(number, CANJaguar.ControlMode.kSpeed);
            jag.setPID(P, I, D);
            jag.setSpeedReference(CANJaguar.SpeedReference.kQuadEncoder);
        }
        catch(CANTimeoutException ex){
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
}
