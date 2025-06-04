package HWSystem.Devices.WirelessIO;

import HWSystem.Devices.Device;

public abstract class WirelessIO extends Device {

    protected int devID;

    public int getDevID() {
        return devID;
    }
    
    public abstract void sendData(String data);
    public abstract String recvData();

    @Override
    public String getDevType(){
        return "WirelessIO";
    }
}
