package org.bullbots.ascend;

import edu.wpi.first.wpilibj.*;
import org.bullbots.ascend.controllers.*;
import org.bullbots.ascend.hardware.*;

public class Main extends IterativeRobot {
    
    JoystickControl joystick;
    JoystickControl gamepad;
    
    DriveTrain driveTrain;
    TrackingController trackingController;
    PIDController pidTrackingController;
    PIDController pidDepthController;
    
    //private Shooter shooter = new Shooter();
    
    private static boolean isClimbing = false;
    
    private final double DAMP = 300;
    
    /* Original PID's 
     * P = .005
     * I = .001
     * D = 0
     */
    
    
    private final double P_TRACKING = 0.00006; //0.0023
    private final double I_TRACKING = 0.5;  //0.05
    private final double D_TRACKING = 1.0; //0.4
    
    private final double P_DEPTH = 0.012;
    private final double I_DEPTH = 0.0;
    private final double D_DEPTH = 0.0;
    
    private final double OPTIMAL_DEPTH = 140;
    
    public void robotInit() {
        joystick = new JoystickControl(1);
        //gamepad = new JoystickControl(2);
        trackingController = new TrackingController(joystick); // Input for tracking PID
        
        driveTrain = new DriveTrain(); // Output for tracking PID
        
        pidTrackingController = new PIDController(P_TRACKING, I_TRACKING, D_TRACKING, trackingController.getTrackingErrorSource(), driveTrain.getTrackingPidOutput());
        pidTrackingController.setSetpoint(0);
        pidTrackingController.setContinuous(true);
        pidTrackingController.setOutputRange(-.5, .5);
        pidTrackingController.setAbsoluteTolerance(3);
        pidTrackingController.enable();
        
        pidDepthController = new PIDController(P_DEPTH, I_DEPTH, D_DEPTH, trackingController.getDepthErrorSource(), driveTrain.getDepthPidOutput());
        pidDepthController.setSetpoint(OPTIMAL_DEPTH);
        pidDepthController.setContinuous(true);
        pidDepthController.setOutputRange(-.7, .7);
        pidDepthController.setAbsoluteTolerance(8);
        pidDepthController.enable();
    }
    
    public void autonomousPeriodic() {

    }

    public void teleopPeriodic() {
        if(!isClimbing){
            try{
                

                if(joystick.getButton(2)){
                    trackingController.trackGoal();
                    driveTrain.turnPID();
                    
//                    if(!driveTrain.isDoneTurning())
//                    {
//                        
//                    }
//                    else{
//                        //driveTrain.forwardPID();
//                    }
                }
                
                 
                else if(joystick.getButton(3))
                {
                    trackingController.trackGoal();
                    driveTrain.forwardPID();
                }
                else{
                    
                    driveTrain.driveVoltage(joystick.getYAxis(), joystick.getXAxis());
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }

            //shooter.tick();
        }
    }
    
    public void isClimbing(boolean value)
    {
        isClimbing = value;
    }
}
