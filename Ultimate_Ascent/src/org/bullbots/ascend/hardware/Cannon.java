package org.bullbots.ascend.hardware;

import edu.wpi.first.wpilibj.CANJaguar;

/**
 * The wheels the launch the frisbees.
 * 
 * @author Clay Kuznia
 * @version February 7, 2013
 */
public class Cannon
{
    private CANJaguar jag1;
    private CANJaguar jag2;
    
    private final double P = 0.0;
    private final double I = 0.0;
    private final double D = 0.0;
    
    public Cannon()
    {
        configureJags();
    }
    
    private void configureJags()
    {
        try
        {
            jag1 = new CANJaguar(0, CANJaguar.ControlMode.kVoltage); // NEED TO CONFIGURE JAG #!!!!!
            jag1.setPID(P, I, D);
            jag1.configMaxOutputVoltage(1); // CONFIGURE THIS NUMBER... VOLTAGE IS UNKNOWN!!!
            jag1.enableControl();
            
            jag2 = new CANJaguar(1, CANJaguar.ControlMode.kVoltage); // NEED TO CONFIGURE JAG #!!!!!
            jag2.setPID(P, I, D);
            jag2.configMaxOutputVoltage(1); // CONFIGURE THIS NUMBER... VOLTAGE IS UNKNOWN!!!
            jag2.enableControl();
        }
        
        catch(Exception e)
        {
            System.out.println("\n\n\tERROR OCCURED WHILE CONFIGURING JAGS\n\n");
            e.printStackTrace();
        }
    }
}
