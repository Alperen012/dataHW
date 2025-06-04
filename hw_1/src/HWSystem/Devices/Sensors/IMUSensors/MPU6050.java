package HWSystem.Devices.Sensors.IMUSensors;

import HWSystem.Devices.State;
import HWSystem.Protocols.Protocol;

public class MPU6050 extends IMUSensor{

    public MPU6050(Protocol protocol,int port_id, int dev_id){

        if(protocol.getProtocolName().equals("I2C") ){
            super.protocol = protocol;
        }else{
            throw new IllegalArgumentException("Invalid Protocol");
        }

        super.state = State.OFF;
        super.portID = port_id;
        super.devID = dev_id;
    }


    @Override
    public Float getAccel(){
        if(this.state == State.OFF){
            throw new IllegalStateException(String.format("%s is OFF", getName()));
        }
        protocol.read();
        return (float) (Math.random() * 10);
    }
    
    @Override
    public Float getRot(){
        if(this.state == State.OFF){
            throw new IllegalStateException(String.format("%s is OFF", getName()));
        }
        protocol.read();
        return (float) (Math.random() * 10);
    }

    @Override
    public String getName(){
        return "MPU6050";
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
