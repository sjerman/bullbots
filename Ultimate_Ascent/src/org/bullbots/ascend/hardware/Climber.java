/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bullbots.ascend.hardware;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;

/**
 *
 * @author Loid Sherwood
 */
public class Climber {
    
    ClimberLord master;
    ClimberSlave slave;
    
    DigitalInput bottomSwitch;
    
    Servo brake;
    
    private final int P = 1;
    private final int I = 1;
    private final int D = 1;
    
    private final double CURRENT_THRESHOLD = 40;
    
    public Climber(){
        master = new ClimberLord(2, P, I, D); // NEED TO TUNE PIDS!!
        slave = new ClimberSlave(5, master); 
        
        brake = new Servo(3); //NEED TO CONFIRM SLOT NUMBER
        bottomSwitch = new DigitalInput(4); // NEED TO CONFIRM SLOT NUMBER
    }
    
    public void setPosition(double pos){
        master.set(pos);
        slave.update();
    }
    
    public void setBrake(boolean shouldBrake){
        if(shouldBrake){
            brake.setAngle(180);
        }
        else{
            brake.setAngle(0);
        }
    }
    
    public boolean isLifting(){
        if(master.getCurrent() > 40 || slave.getCurrent() > 40){
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean isDown(){
        return bottomSwitch.get();
    }
    
    public double getPosition(){
        return master.getActPos();
    }
    
}
