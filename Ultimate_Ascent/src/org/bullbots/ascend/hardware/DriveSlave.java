package org.bullbots.ascend.hardware;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 *
 * @author Admin
 */
public class DriveSlave {
    
    private CANJaguar jag;
    private DriveLord lord;
  
    public DriveSlave(int number, DriveLord lord){
        try{
           this.lord = lord;
           jag = new CANJaguar(number, CANJaguar.ControlMode.kPercentVbus);
           jag.configMaxOutputVoltage(12);
           jag.enableControl();
            
        }
        catch(CANTimeoutException ex){
            System.out.println("Problem initializing jags");
            ex.printStackTrace();
        }
    }
    
    public void update(){
        try {
            jag.setX(lord.getVoltage());
            System.out.println("updating" + lord.getVoltage());
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public void set(double value){
        try{
            jag.setX(value);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
            
    
    }
    
    public double getSetSpeed(){
        try {
            return jag.getX();
        } catch (CANTimeoutException ex) {
            System.out.println("Problem getting speed");
//            ex.printStackTrace();
        }
        return 0.0;
    }
    
    public CANJaguar.ControlMode getJagControlMode(){
        try{
            return jag.getControlMode();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public double getCurrent(){
        try{
            return jag.getOutputCurrent();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return 42000;
    }
}