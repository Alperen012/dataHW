package HWSystem.Devices.Displays;

import HWSystem.Devices.Device;

public abstract class Display extends Device {

    protected int devID;

    public int getDevID() {
        return devID;
    }
    
    public abstract void printData(String data);

    @Override
    public String getDevType(){
        return "Display";
    }
}
