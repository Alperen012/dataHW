package HWSystem.Devices.Sensors.IMUSensors;

import HWSystem.Devices.Sensors.Sensor;
import HWSystem.Devices.State;

public abstract class IMUSensor extends Sensor{
    public abstract Float getAccel();
    public abstract Float getRot();

    @Override
    public String getSensType(){
        return "IMU Sensor";
    }

    @Override
    public String data2String(){
        if(this.state == State.OFF){
            throw new IllegalStateException(String.format("%s is OFF", getName()));
        }
        return String.format("Accel: %.2f, Rot: %.2f", getAccel(), getRot());
    }
    
}
