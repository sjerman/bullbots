package org.bullbots.ascend.hardware;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * The loading system for the robot.
 * 
 * @author Clay Kuznia
 * @version February 7, 2013
 */
public class Hopper
{
    private DigitalInput topSwitch = new DigitalInput(0); // Need to configure the port!!!
    private DigitalInput bottomSwitch = new DigitalInput(1); // Need to configure the port!!!
    
    //private SOMEWEIRDMOTORTHINGCLASS lkdsjfldj;        FIND OUT WHAT THIS IS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    
    public Hopper()
    {
        
    }
    
    public boolean canFire()
    {
        if(bottomSwitch.get())
        {
            return true;
        }
        
        return false;
    }
    
    public boolean hasExtraFrisbee()
    {
        if(topSwitch.get())
        {
            return true;
        }
        
        return false;
    }
}
