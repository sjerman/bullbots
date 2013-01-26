package org.bullbots.ascend;


import edu.wpi.first.wpilibj.IterativeRobot;
import org.bullbots.ascend.controllers.JoystickControl;
import org.bullbots.ascend.hardware.DriveTrain;


public class Main extends IterativeRobot {
    
    DriveTrain driveTrain;
    JoystickControl joystick;
    JoystickControl gamepad;
    
    public void robotInit() {
        joystick = new JoystickControl(1);
        gamepad = new JoystickControl(2);
        
        driveTrain = new DriveTrain();
    }

    
    public void autonomousPeriodic() {

    }

    
    public void teleopPeriodic() {
        driveTrain.drive(joystick.getYAxis(), joystick.getXAxis());
    }
    
}
