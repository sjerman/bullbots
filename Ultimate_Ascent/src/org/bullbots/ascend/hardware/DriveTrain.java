package org.bullbots.ascend.hardware;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 *
 * @author Admin
 */
public class DriveTrain{
    
    class TrackingPidOutput implements PIDOutput{
        DriveTrain controller;
        public TrackingPidOutput(DriveTrain controller) {
            this.controller = controller;
            System.out.println("creating tracking pid output class, should only be called once.");
        }

        public void pidWrite(double d) {
            //System.out.println("TURN PID UPDATING:  " + d);
            controller.setTrackingPidValue(d);
        }
    }

    class DepthPidOutput implements PIDOutput{
        DriveTrain controller;
        public DepthPidOutput(DriveTrain controller) {
            this.controller = controller;
        }
        public void pidWrite(double d) {
            //System.out.println(d + " inches");
            controller.setDepthPidValue(d);
        }
    }
    
    private DriveJaguar left;
    private DriveJaguar right;
    
    private final int SPEED_FACTOR = 150;
    private final int VOLTAGE_FACTOR = 1;
    
    private double trackingPIDValue = 0;
    private double depthPIDValue = 0;
    
    public DriveTrain(){
        
        right = new DriveJaguar(3, .05, .02, .5); //jag number, and P, I, and D
        left = new DriveJaguar(6, .05, .02, .5); //jag number, and P, I, and D
        
        //700, 5 , 0

    }
    
    public void driveSpeed(double forwardSpeed, double turnSpeed){
        if(!(left.getJagControlMode() == CANJaguar.ControlMode.kSpeed && right.getJagControlMode() == CANJaguar.ControlMode.kSpeed)){
            left.setJagControlMode(CANJaguar.ControlMode.kSpeed);
            right.setJagControlMode(CANJaguar.ControlMode.kSpeed);
        }
        double leftValue = -(forwardSpeed - turnSpeed) * SPEED_FACTOR;
        double rightValue = (forwardSpeed + turnSpeed) * SPEED_FACTOR;
        
        if(right.getSetSpeed() != leftValue || left.getSetSpeed() != rightValue){
            left.set(leftValue);
            right.set(rightValue);
        }
        
    }
    
    public void drivePosition(double leftRotation, double rightRotation){
        if(!(left.getJagControlMode() == CANJaguar.ControlMode.kPosition && right.getJagControlMode() == CANJaguar.ControlMode.kPosition)){
            left.setJagControlMode(CANJaguar.ControlMode.kPosition);
            right.setJagControlMode(CANJaguar.ControlMode.kPosition);
        }
        
        double leftValue = -(leftRotation - leftRotation);
        double rightValue = (rightRotation + rightRotation) ;
        
        left.set(leftValue);
        right.set(rightValue);
    }
    
    public void driveVoltage(double forwardVoltage, double turnVoltage){
        if(!(left.getJagControlMode() == CANJaguar.ControlMode.kPercentVbus && right.getJagControlMode() == CANJaguar.ControlMode.kPercentVbus)){
            left.setJagControlMode(CANJaguar.ControlMode.kPercentVbus);
            right.setJagControlMode(CANJaguar.ControlMode.kPercentVbus);
        }
        
        double leftValue = -(forwardVoltage - turnVoltage) * VOLTAGE_FACTOR;
        double rightValue = (forwardVoltage + turnVoltage) * VOLTAGE_FACTOR;
        //System.out.println("leftValue: " + leftValue);
        //System.out.println("rightValue: " + rightValue);
        //System.out.println("turnVoltage: " + turnVoltage);
        System.out.println(left.getCurrent());
        left.set(leftValue);
        right.set(rightValue);
        System.out.println(left.getSetSpeed());
        //System.out.println(left.getCurrent() + " , " + right.getCurrent());
    }
    
    public void turnPID(){
        driveVoltage(0, trackingPIDValue);
        //System.out.println("TRACKING PID VALUE: " + trackingPIDValue);
    }
    
    public void forwardPID()
    {
        System.out.println("depthPIDValue: " + depthPIDValue);
        driveSpeed(depthPIDValue, 0);
    }
    
    public boolean isDoneTurning(){
        if(Math.abs(trackingPIDValue) < .1){
            return true;
        }
        return false;
        
    }
    
    public boolean isDoneForward(){
        if(Math.abs(depthPIDValue) < .1){
            return true;
        }
        return false;
        
    }

    public void setTrackingPidValue(double d) {
        trackingPIDValue = d;
    }
    
    public void setDepthPidValue(double d)
    {
        System.out.println("SETTING DEPTH VALUE: " + depthPIDValue);
        depthPIDValue = d;
    }
    
    public TrackingPidOutput getTrackingPidOutput()
    {
        System.out.println("Should only happen once");
        return new TrackingPidOutput(this);
    }
    
    public DepthPidOutput getDepthPidOutput()
    {   
        
        return new DepthPidOutput(this);
    }
}
