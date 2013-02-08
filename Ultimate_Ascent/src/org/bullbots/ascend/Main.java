package org.bullbots.ascend;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PIDController;
import org.bullbots.ascend.controllers.JoystickControl;
import org.bullbots.ascend.controllers.TrackingController;
import org.bullbots.ascend.hardware.DriveTrain;

public class Main extends IterativeRobot {
    
    JoystickControl joystick;
    JoystickControl gamepad;
    
    DriveTrain driveTrain;
    TrackingController trackingController;
    PIDController pidController;
    
    private final double DAMP = 300;
    
    /* Original PID's 
     * P = .005
     * I = .001
     * D = 0
     */
    
    
    private final double P = 0.0023;
    private final double I = 0.05;
    private final double D = .4;
    
    public void robotInit() {
        joystick = new JoystickControl(1);
        //gamepad = new JoystickControl(2);
        trackingController = new TrackingController(joystick); // Input for tracking PID
        
        driveTrain = new DriveTrain(); // Output for tracking PID
        pidController = new PIDController(P, I, D, trackingController, driveTrain);
        pidController.setSetpoint(0);
        pidController.setContinuous(true);
        pidController.setOutputRange(-1, 1);
        pidController.setAbsoluteTolerance(3);
        pidController.enable();
    }
    
    public void autonomousPeriodic() {

    }

    public void teleopPeriodic() {
        try{
            pidController.enable();
            
            if(joystick.getButton(2)){
                trackingController.trackGoal();
                driveTrain.turnPID();
                System.out.println("PID VALUE" + pidController.get());
            }
            
            else{
                driveTrain.driveVoltage(joystick.getYAxis(), joystick.getXAxis());
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
