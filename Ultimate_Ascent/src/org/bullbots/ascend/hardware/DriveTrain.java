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
    
    double leftValue;
    double rightValue;
    
    DriveJaguar left;
    DriveJaguar right;
    
    public DriveTrain(){
        
        left = new DriveJaguar(7);
        right = new DriveJaguar(3);

    }
    
    public void drive(double forwardSpeed, double turnSpeed){
        leftValue = forwardSpeed + turnSpeed;
        rightValue = forwardSpeed - turnSpeed;
        if(left.getSetSpeed() != leftValue || right.getSetSpeed() != rightValue){
            left.set(leftValue);
            right.set(rightValue);
        }
        
    }
}
