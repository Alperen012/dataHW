package HWSystem.Devices.WirelessIO;

import HWSystem.Devices.State;
import HWSystem.Protocols.Protocol;

public class Bluetooth extends WirelessIO{
    public Bluetooth(Protocol protocol, int portID, int devID) {
        super.protocol = protocol;

        super.state = State.OFF;
        super.portID = portID;
        super.devID = devID;
    }

    @Override
    public void sendData(String data){
        if(this.state == State.OFF){
            throw new IllegalStateException(String.format("%s is OFF", getName()));
        }
        super.protocol.write(String.format("%s: %s",getName(), data));
    }

    @Override
    public String recvData(){
        if(this.state == State.OFF){
            throw new IllegalStateException(String.format("%s is OFF", getName()));
        }
        return super.protocol.read();
    }

    @Override
    public String getName(){
        return "Bluetooth";
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
