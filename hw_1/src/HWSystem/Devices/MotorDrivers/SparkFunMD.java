package HWSystem.Devices.MotorDrivers;

import HWSystem.Devices.State;
import HWSystem.Protocols.Protocol;

public class SparkFunMD extends MotorDriver{

    public SparkFunMD(Protocol protocol, int portID, int devID) {
        super.protocol = protocol;

        super.state = State.OFF;
        super.portID = portID;
        super.devID = devID;    
    }

    @Override
    public String getName(){
        return "SparkFunMD";
    }

    @Override
    public void turnOn(){
        super.state = State.ON;
        super.protocol.write(String.format("%s is ON", getName())); 
    }
    
    @Override
    public void turnOff(){
        super.state = State.OFF;
        super.protocol.write(String.format("%s is OFF", getName())); 
    }

    @Override
    public void setMotorSpeed(int speed) {
        if(this.state == State.OFF){
            throw new IllegalStateException(String.format("%s is OFF", getName()));
        }
        super.speed = speed;
        super.protocol.write(String.format("%s speed set to %d", getName(), speed));
    }
    
}
