package org.bullbots.ascend.hardware;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 *
 * @author Admin
 */
public class ClimberSlave {
    
    private CANJaguar jag;
    private ClimberLord lord;
    
    public ClimberSlave(int number, ClimberLord lord){
        try{
            jag = new CANJaguar(number, CANJaguar.ControlMode.kVoltage);
            jag.configMaxOutputVoltage(12);
            jag.enableControl();
            this.lord = lord;
            
        }
        catch(CANTimeoutException ex){
            System.out.println("Problem initializing jags");
            ex.printStackTrace();
        }
    }
    
    public void update(){
        try {
            jag.setX(lord.getVoltage());
            
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
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
    
    public void setJagControlMode(CANJaguar.ControlMode mode){
        try{
            jag.changeControlMode((mode));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public double getCurrent(){
        try{
            return jag.getOutputCurrent();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return 424242;
    }
    
    public double getVoltage(){
        try{
            return jag.getOutputVoltage();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return 424242;
    }
}