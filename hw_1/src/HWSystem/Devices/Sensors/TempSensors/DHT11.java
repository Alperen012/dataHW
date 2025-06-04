package HWSystem.Devices.Sensors.TempSensors;

import HWSystem.Devices.State;
import HWSystem.Protocols.Protocol;

public class DHT11 extends TempSensor{
    public DHT11(Protocol protocol, int portID, int devID) {
        super.protocol = protocol;

        super.state = State.OFF;
        super.portID = portID;
        super.devID = devID;
    }

    @Override
    public Float getTemp(){
        if(this.state == State.OFF){
            throw new IllegalStateException(String.format("%s is OFF", getName()));
        }
        protocol.read();
        return (float) (Math.random() * 40);
    }

    @Override
    public String getName(){
        return "DHT11";
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
