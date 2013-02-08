package org.bullbots.ascend.hardware;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 *
 * @author Admin
 */
public class DriveJaguar {
    
    private  CANJaguar jag;
    
//    double P = .05;
//    double I = .02;
//    double D = .5;
    
    public DriveJaguar(int number, double P, double I, double D){
        try{
            jag = new CANJaguar(number, CANJaguar.ControlMode.kSpeed);
            jag.setPID(P, I, D);
            jag.setSpeedReference(CANJaguar.SpeedReference.kQuadEncoder);
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
        return 42000;
    }
}