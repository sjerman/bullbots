/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bullbots.ascend.controllers;

import edu.wpi.first.wpilibj.Joystick;
/**
 *
 * @author Admin
 */
public class JoystickControl {
    Joystick joystick;
    double DEADBAND = .05;
    
    public JoystickControl(int port){
        joystick = new Joystick(port);
        
    }

    public double getYAxis(){
        double value = joystick.getRawAxis(1);
        if(Math.abs(value) > DEADBAND){
            return value;
        }
        return 0;

    }
    
    public double getXAxis(){
        double value = joystick.getRawAxis(2);
        if(Math.abs(value) > DEADBAND){
            return value;
        }
        return 0;
    }
    
    public double getThrottle(){
        return joystick.getRawAxis(3);
    }
    
    public boolean getButton(int button){
        return joystick.getRawButton(button);
    }
}
