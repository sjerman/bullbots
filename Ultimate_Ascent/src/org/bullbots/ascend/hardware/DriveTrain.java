/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bullbots.ascend.hardware;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 *
 * @author Admin
 */
public class DriveTrain {
    
    private  DriveJaguar left;
    private DriveJaguar right;
    
    public DriveTrain(){
        
        right = new DriveJaguar(3, .05, .02, .5); //jag number, and P, I, and D
        left = new DriveJaguar(6, .05, .02, .5); //jag number, and P, I, and D
        

    }
    
    public void drive(double forwardSpeed, double turnSpeed){
        double leftValue = forwardSpeed + turnSpeed;
        double rightValue = forwardSpeed - turnSpeed;
        System.out.println("Setting speed:"+leftValue);
        if(right.getSetSpeed() != leftValue || left.getSetSpeed() != rightValue){
            left.set(leftValue);
            right.set(rightValue);
            //System.out.println("its working");
        }
        
    }
}
