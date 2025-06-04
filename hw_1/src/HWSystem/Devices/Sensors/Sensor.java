package HWSystem.Devices.Sensors;

import HWSystem.Devices.Device;

public abstract class Sensor extends Device{

    protected int devID;
    
    public abstract String getSensType(); // get the type of the sensor

    public abstract String data2String(); 

    @Override
    public String getDevType(){ // get the type of the device
        return "Sensor";
    }

    public int getDevID() {
        return devID;
    }
}
