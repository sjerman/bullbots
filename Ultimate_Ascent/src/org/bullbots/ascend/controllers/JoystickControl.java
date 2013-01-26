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
    
    public JoystickControl(int port){
        joystick = new Joystick(port);
    }

    public double getYAxis(){
        return joystick.getRawAxis(1);
    }
    
    public double getXAxis(){
        return joystick.getRawAxis(2);
    }
    
    public double getThrottle(){
        return joystick.getRawAxis(3);
    }
    
    public boolean getButton(int button){
        return joystick.getRawButton(button);
    }
}
