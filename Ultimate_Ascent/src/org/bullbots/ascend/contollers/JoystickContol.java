/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bullbots.ascend.contollers;

import edu.wpi.first.wpilibj.Joystick;
/**
 *
 * @author Admin
 */
public class JoystickContol {
    Joystick joystick;
    
    public JoystickContol(int port){
        joystick = new Joystick(port);
    }

}
