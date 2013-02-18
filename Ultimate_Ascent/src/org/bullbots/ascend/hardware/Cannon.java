package org.bullbots.ascend.hardware;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Victor;

/**
 * The wheels the launch the frisbees.
 * 
 * @author Clay Kuznia
 * @version February 7, 2013
 */
public class Cannon
{
    private Victor vic1;
    private Victor vic2;
    
    private Servo leftServo;
    private Servo rightServo;
        
    private final int motorSpeed = 1;
    private final int tolerance = 3;
    
    public Cannon()
    {
        leftServo.set(0);
        rightServo.set(0);
        
        
        leftServo = new Servo(0);//NEED TO PUT ACTUAL SLOT HERE
        rightServo = new Servo(1); //NEED TO PUT ACTUAL SLOT HERE
        vic1 = new Victor(1); //NEED TO PUT ACTUAL SLOT HERE
        vic2 = new Victor(2); //NEED TO PUT ACTUAL SLOT HERE
    }
    
    
    
    public void fire(){

        setServoPosition(180);
        
    }
    
    public void chargeCannon(){
        vic1.set(motorSpeed); 
        vic2.set(motorSpeed);
    }
    
    public void stopWheels()
    {
        vic1.set(0);
        vic2.set(0);
    }
    
    public void setServoPosition(double position)
    {
        leftServo.set(position);
        rightServo.set(position);
    }
    
    public boolean servosReady()
    {
        if(Math.abs(leftServo.get()) <= tolerance && Math.abs(rightServo.get()) <= tolerance)
        {
            return true;
        }
        
        return false;
    }
}
