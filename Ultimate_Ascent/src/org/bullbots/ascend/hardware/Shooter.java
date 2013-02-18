package org.bullbots.ascend.hardware;

/**
 *
 * @author Clay Kuznia
 */
public class Shooter
{
    private Cannon cannon = new Cannon();
    private Hopper hopper = new Hopper();
    
    public Shooter()
    {
        try{
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    
    public void shoot()
    {
        if(hopper.slot1Full())
        {
            cannon.fire();
        }
        
        else
        {
            cannon.setServoPosition(0);
            
            if(hopper.slot2Full() && cannon.servosReady())
            {
                hopper.spinWheel();
            }
            
            else
            {
                hopper.stopWheel();
            }
        }
        
        if(!hopper.slot1Full() && !hopper.slot2Full() && cannon.servosReady())
        {
            cannon.stopWheels();
        }
    }
    
    public Cannon getCannon()
    {
        return cannon;
    }
    
    public Hopper getHopper()
    {
        return hopper;
    }
}
