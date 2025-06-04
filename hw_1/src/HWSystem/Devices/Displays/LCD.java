package HWSystem.Devices.Displays;

import HWSystem.Devices.State;
import HWSystem.Protocols.Protocol;

public class LCD extends Display{
    public LCD(Protocol protocol, int portID, int devID) {
        super.protocol = protocol;

        super.state = State.OFF;
        super.portID = portID;
        super.devID = devID;
    }

    @Override
    public void printData(String data){
        if(this.state == State.OFF){
            throw new IllegalStateException(String.format("%s is OFF", getName()));
        }
        super.protocol.write(String.format("%s: %s",getName(), data));
    }

    @Override
    public String getName(){
        return "LCD";
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
    
}
