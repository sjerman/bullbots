package org.bullbots.ascend.hardware;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Servo;

/**
 * The wheels the launch the frisbees.
 * 
 * @author Clay Kuznia
 * @version February 7, 2013
 */
public class Cannon implements Runnable
{
    private CANJaguar jag1;
    private CANJaguar jag2;
    
    private Servo leftServo = new Servo(0); // Need to configure the port!!!
    private Servo rightServo = new Servo(1); // Need to configure the port!!!
    
    private final double P = 0.0;
    private final double I = 0.0;
    private final double D = 0.0;
    
    private final int motorSpeed = 1;
    private final int tolerance = 3;
    private final int spinTime = 1000;
    
    private boolean upToSpeed = false;
    private boolean firstTime = true;
    
    public Cannon()
    {
        configure();
    }
    
    private void configure()
    {
        leftServo.set(0);
        rightServo.set(0);
        
        try
        {
            jag1 = new CANJaguar(0, CANJaguar.ControlMode.kVoltage); // NEED TO CONFIGURE JAG #!!!!!
            jag1.setPID(P, I, D);
            jag1.configMaxOutputVoltage(12); // Configure Voltage
            jag1.enableControl();
            
            jag2 = new CANJaguar(1, CANJaguar.ControlMode.kVoltage); // NEED TO CONFIGURE JAG #!!!!!
            jag2.setPID(P, I, D);
            jag2.configMaxOutputVoltage(12); // Configure Voltage
            jag2.enableControl();
        }
        
        catch(Exception e)
        {
            System.out.println("\n\n\tERROR OCCURED WHILE CONFIGURING JAGS\n\n");
            e.printStackTrace();
        }
    }
    
    public void fire()
    {
        try
        {
            while(!upToSpeed)
            {
                jag1.setX(motorSpeed); // CONFIGURE SPEED
                jag2.setX(motorSpeed); // CONFIGURE SPEED
                
                if(jag1.getX() >= motorSpeed && jag2.getX() >= motorSpeed)
                {
                    upToSpeed = true;
                }
            }
            
            setServoPosition(180);
        }
        
        catch(Exception e){}
    }
    
    public void stopWheels()
    {
        try
        {
            jag1.setX(0);
            jag2.setX(0);
        }
        
        catch(Exception e){}
    }
    
    public void setServoPosition(double position)
    {
        leftServo.set(position);
        rightServo.set(position);
    }
    
    public boolean servosReady()
    {
        if(leftServo.get() <= tolerance && leftServo.get() >= -tolerance &&
                rightServo.get() <= tolerance && rightServo.get() >= -tolerance)
        {
            return true;
        }
        
        return false;
    }
    
    public void run()
    {
        System.out.println("RUN CALLED ======= CANNON");
        if(!firstTime)
        {
            try
            {
                Thread.sleep(spinTime);
                stopWheels();
            }

            catch(Exception e){}
        }
        
        else
        {
            firstTime = false;
        }
    }
}
