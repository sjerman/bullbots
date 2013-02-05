package org.bullbots.ascend;


import edu.wpi.first.wpilibj.IterativeRobot;
import org.bullbots.ascend.controllers.JoystickControl;
import org.bullbots.ascend.controllers.TrackingController;
import org.bullbots.ascend.hardware.DriveTrain;


public class Main extends IterativeRobot {
    
    DriveTrain driveTrain;
    JoystickControl joystick;
    JoystickControl gamepad;
    TrackingController trackingController;
    
    private final double DAMP = 300;
    
    public void robotInit() {
        joystick = new JoystickControl(1);
        //gamepad = new JoystickControl(2);
        trackingController = new TrackingController(joystick);
        
        
        
        driveTrain = new DriveTrain();
    }

    
    public void autonomousPeriodic() {

    }

    
    public void teleopPeriodic() {
        try{
            
            
            if(joystick.getButton(2)){
                trackingController.trackGoal();
                System.out.println(trackingController.getDif());
                if(Math.abs(trackingController.getDif()) > 5){
                    driveTrain.driveVoltage(0,trackingController.getDif()/DAMP);
                }
                else{
                    driveTrain.driveVoltage(0,0);
                }
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
