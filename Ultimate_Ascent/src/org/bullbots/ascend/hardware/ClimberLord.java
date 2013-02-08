package org.bullbots.ascend.hardware;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 *
 * @author Admin
 */
public class ClimberLord {
    
    private  CANJaguar jag;
    
//    double P = .05;
//    double I = .02;
//    double D = .5;
    
    public ClimberLord(int number, double P, double I, double D){
        try{
            jag = new CANJaguar(number, CANJaguar.ControlMode.kPosition);
            jag.setPID(P, I, D);
            jag.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
            jag.configEncoderCodesPerRev(360);
            jag.configMaxOutputVoltage(12);
            jag.enableControl();
            
        }
        catch(CANTimeoutException ex){
            System.out.println("Problem initializing jags");
            ex.printStackTrace();
        }
    }
    
    public void set(double value){
        try {
            jag.setX(value);
            //System.out.println("setting to: " + value);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public double getSetPos(){
        try {
            return jag.getX();
        } catch (CANTimeoutException ex) {
            System.out.println("Problem getting position");
//            ex.printStackTrace();
        }
        return 0.0;
    }
    
    public double getActPos(){
        try{
            return jag.getPosition();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return 394203;
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