package org.bullbots.ascend.hardware;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Victor;

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
    
    private Victor vic = new Victor(0); // Need to configure the port!!!
    
    private final double vicSpeed = .5;
    private final int spinTime = 300;
    private boolean firstTime = true;
    
    public boolean slot1Full()
    {
        if(bottomSwitch.get())
        {
            return true;
        }
        
        return false;
    }
    
    public boolean slot2Full()
    {
        if(topSwitch.get())
        {
            return true;
        }
        
        return false;
    }
    
    public synchronized void spinWheel()
    {
        vic.set(vicSpeed);
    }
    
    public synchronized void stopWheel()
    {
        vic.set(0);
    }
    
}
