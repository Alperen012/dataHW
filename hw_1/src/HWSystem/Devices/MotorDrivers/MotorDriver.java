package HWSystem.Devices.MotorDrivers;

import HWSystem.Devices.Device;

public abstract class MotorDriver extends Device {

    protected int devID;

    public int getDevID() {
        return devID;
    }

    protected int speed;

    public abstract void setMotorSpeed(int speed);

    @Override
    public String getDevType() {
        return "MotorDriver";
    }

}
