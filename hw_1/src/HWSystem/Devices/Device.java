package HWSystem.Devices;

import HWSystem.Protocols.Protocol;

public abstract class Device {
    protected Protocol protocol; // the protocol used to communicate with the device
    protected State state; // on or off
    protected int portID; // the port ID
    protected int devID; // the device ID
    
    public abstract void turnOn(); // turn on the device
    public abstract void turnOff(); // turn off the device

    public abstract String getName(); // get the name of the device

    public abstract String getDevType(); // get the type of the device

    public State getState() {// get the state of the device
        return state;
    }

    public Protocol getProtocol() { // get the protocol used to communicate with the device
        return protocol;
    }
}
