package HWSystem;

import HWSystem.Devices.Device;
import HWSystem.Devices.Displays.Display;
import HWSystem.Devices.Displays.LCD;
import HWSystem.Devices.Displays.OLED;
import HWSystem.Devices.MotorDrivers.MotorDriver;
import HWSystem.Devices.MotorDrivers.PCA9685;
import HWSystem.Devices.MotorDrivers.SparkFunMD;
import HWSystem.Devices.Sensors.IMUSensors.GY951;
import HWSystem.Devices.Sensors.IMUSensors.MPU6050;
import HWSystem.Devices.Sensors.Sensor;
import HWSystem.Devices.Sensors.TempSensors.BMP280;
import HWSystem.Devices.Sensors.TempSensors.DHT11;
import HWSystem.Devices.State;
import HWSystem.Devices.WirelessIO.Bluetooth;
import HWSystem.Devices.WirelessIO.Wifi;
import HWSystem.Devices.WirelessIO.WirelessIO;
import HWSystem.Protocols.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class HWSystem{
    
    private ArrayList<Protocol> ports;
    private ArrayList<Sensor> sensors;
    private ArrayList<Display> displays;
    private ArrayList<WirelessIO> wirelessAdapters;
    private ArrayList<MotorDriver> motorDrivers;
    
    private int maxSensors;
    private int maxDisplays;
    private int maxWirelessAdapters;
    private int maxMotorDrivers;

    public HWSystem(String configFile){
        ports = new ArrayList<>();
        sensors = new ArrayList<>();
        displays = new ArrayList<>();
        wirelessAdapters = new ArrayList<>();
        motorDrivers = new ArrayList<>();
        
        loadConfiguration(configFile);
    }

    public void loadConfiguration(String configFile){
        try{

            File file = new File(configFile);
            Scanner scanner = new Scanner(file);
            
            String line = scanner.nextLine();
            line = line.split(":")[1].trim();
            String[] tokens = line.split(",");

            for (String token : tokens) {
                switch (token) {
                    case "SPI":
                        ports.add(new SPI());
                        break;
                    case "UART":
                        ports.add(new UART());
                        break;
                    case "I2C":
                        ports.add(new I2C());
                        break;
                    case "OneWire":
                        ports.add(new OneWire());
                        break;
                    default:
                        break;
                }
            }

            line = scanner.nextLine();
            maxSensors = Integer.parseInt(line.split(":")[1].trim());

            line = scanner.nextLine();
            maxDisplays = Integer.parseInt(line.split(":")[1].trim());

            line = scanner.nextLine();
            maxWirelessAdapters = Integer.parseInt(line.split(":")[1].trim());

            line = scanner.nextLine();
            maxMotorDrivers = Integer.parseInt(line.split(":")[1].trim());

            scanner.close();

        }
        catch(Exception e){
            System.out.println("Error loading configuration file");
        }
    }
    
    private Device findDeviceByPort(int portId) {
        for (Sensor sensor : sensors) {
            if (getDevicePort(sensor) == portId) {
                return sensor;
            }
        }
        
        for (Display display : displays) {
            if (getDevicePort(display) == portId) {
                return display;
            }
        }
        
        for (WirelessIO wireless : wirelessAdapters) {
            if (getDevicePort(wireless) == portId) {
                return wireless;
            }
        }
        
        for (MotorDriver motor : motorDrivers) {
            if (getDevicePort(motor) == portId) {
                return motor;
            }
        }
        
        return null;
    }

    private int getDevicePort(Device device) {
        for (int i = 0; i < ports.size(); i++) {
            if (device.getProtocol() == ports.get(i)) {
                return i;
            }
        }
        return -1;
    }

    private int getDeviceID(Device device) {

        if (device instanceof Sensor sensor) {
            for (int i = 0; i < sensors.size(); i++) {
                if (sensors.get(i) == device) {
                    return (sensor).getDevID();
                }
            }
        }
        else if (device instanceof Display display) {
            for (int i = 0; i < displays.size(); i++) {
                if (displays.get(i) == device) {
                    return (display).getDevID();
                }
            }
        }
        else if (device instanceof WirelessIO wireless) {
            for (int i = 0; i < wirelessAdapters.size(); i++) {
                if (wirelessAdapters.get(i) == device) {
                    return (wireless).getDevID();
                }
            }
        }
        else if (device instanceof MotorDriver motor) {
            for (int i = 0; i < motorDrivers.size(); i++) {
                if (motorDrivers.get(i) == device) {
                    return (motor).getDevID();
                }
            }
        }
        
        return -1;
    }

    
    
    public Boolean addDevice(String devName, int portID, int devID) {
        // Check if port exists
        if (portID < 0 || portID >= ports.size()) {
            System.out.println("Invalid port ID.");
            return false;
        }
        
        // Check if port is already occupied
        if (isPortOccupied(portID)) {
            System.out.println("Port is already occupied.");
            return false;
        }
        
        
        switch (devName) {
            case "DHT11":
                if (sensors.size() >= maxSensors) {
                    System.out.println("Maximum number of sensors reached.");
                    return false;
                }

                if(devID>=maxSensors){
                    System.out.println("Invalid sensor ID");
                    return false;
                }
                
                if (checkDeviceIDExists(devID, sensors)) {
                    System.out.println("Sensor ID already exists.");
                    return false;
                }
                
                if (!(ports.get(portID) instanceof OneWire)) {
                    System.out.println("Incompatible protocol for DHT11.");
                    return false;
                }
                
                sensors.add(new DHT11(ports.get(portID),portID, devID));
                break;
                
            case "BME280":
                if (sensors.size() >= maxSensors) {
                    System.out.println("Maximum number of sensors reached.");
                    return false;
                }

                if(devID>=maxSensors){
                    System.out.println("Invalid sensor ID");
                    return false;
                }
                
                if (checkDeviceIDExists(devID, sensors)) {
                    System.out.println("Sensor ID already exists.");
                    return false;
                }
                
                if (!(ports.get(portID) instanceof I2C) && !(ports.get(portID) instanceof SPI)) {
                    System.out.println("Incompatible protocol for BME280.");
                    return false;
                }
                
                sensors.add(new BMP280(ports.get(portID),portID, devID));
                break;
                
            case "MPU6050":
                if (sensors.size() >= maxSensors) {
                    System.out.println("Maximum number of sensors reached.");
                    return false;
                }

                if(devID>=maxSensors){
                    System.out.println("Invalid sensor ID");
                    return false;
                }
                
                if (checkDeviceIDExists(devID, sensors)) {
                    System.out.println("Sensor ID already exists.");
                    return false;
                }
                
                if (!(ports.get(portID) instanceof I2C)) {
                    System.out.println("Incompatible protocol for MPU6050.");
                    return false;
                }
                
                sensors.add(new MPU6050(ports.get(portID),portID, devID));
                break;
                
            case "GY951":
                if (sensors.size() >= maxSensors) {
                    System.out.println("Maximum number of sensors reached.");
                    return false;
                }

                if(devID>=maxSensors){
                    System.out.println("Invalid sensor ID");
                    return false;
                }
                
                if (checkDeviceIDExists(devID, sensors)) {
                    System.out.println("Sensor ID already exists.");
                    return false;
                }
                
                if (!(ports.get(portID) instanceof SPI) && !(ports.get(portID) instanceof UART)) {
                    System.out.println("Incompatible protocol for GY951.");
                    return false;
                }
                
                sensors.add(new GY951(ports.get(portID),portID, devID));
                break;
                
            case "LCD":
                if (displays.size() >= maxDisplays) {
                    System.out.println("Maximum number of displays reached.");
                    return false;
                }

                if(devID>=maxDisplays){
                    System.out.println("Invalid display ID");
                    return false;
                }
                
                if (checkDeviceIDExists(devID, displays)) {
                    System.out.println("Display ID already exists.");
                    return false;
                }
                
                if (!(ports.get(devID) instanceof I2C)) {
                    System.out.println("Incompatible protocol for LCD.");
                    return false;
                }
                
                displays.add(new LCD(ports.get(portID),portID, devID));
                break;
                
            case "OLED":
                if (displays.size() >= maxDisplays) {
                    System.out.println("Maximum number of displays reached.");
                    return false;
                }

                if(devID>=maxDisplays){
                    System.out.println("Invalid display ID");
                    return false;
                }
                
                if (checkDeviceIDExists(devID, displays)) {
                    System.out.println("Display ID already exists.");
                    return false;
                }
                
                if (!(ports.get(portID) instanceof SPI)) {
                    System.out.println("Incompatible protocol for OLED.");
                    return false;
                }
                
                displays.add(new OLED(ports.get(portID),portID, devID));
                break;
                
            case "Bluetooth":
                if (wirelessAdapters.size() >= maxWirelessAdapters) {
                    System.out.println("Maximum number of wireless modules reached.");
                    return false;
                }

                if(devID>=maxWirelessAdapters){
                    System.out.println("Invalid wireless module ID");
                    return false;
                }
                
                if (checkDeviceIDExists(devID, wirelessAdapters)) {
                    System.out.println("Wireless module ID already exists.");
                    return false;
                }
                
                if (!(ports.get(portID) instanceof UART)) {
                    System.out.println("Incompatible protocol for Bluetooth.");
                    return false;
                }
                
                
                wirelessAdapters.add(new Bluetooth(ports.get(portID),portID, devID));
                break;
                
            case "Wifi":
                if (wirelessAdapters.size() >= maxWirelessAdapters) {
                    System.out.println("Maximum number of wireless modules reached.");
                    return false;
                }

                if(devID>=maxWirelessAdapters){
                    System.out.println("Invalid wireless module ID");
                    return false;
                }
                
                if (checkDeviceIDExists(devID, wirelessAdapters)) {
                    System.out.println("Wireless module ID already exists.");
                    return false;
                }
                
                if (!(ports.get(devID) instanceof SPI) && !(ports.get(devID) instanceof UART)) {
                    System.out.println("Incompatible protocol for Wifi.");
                    return false;
                }
                
                
                wirelessAdapters.add(new Wifi(ports.get(portID),portID, devID));
                break;
                
            case "PCA9685":
                if (motorDrivers.size() >= maxMotorDrivers) {
                    System.out.println("Maximum number of motor drivers reached.");
                    return false;
                }

                if(devID>=maxMotorDrivers){
                    System.out.println("Invalid motor driver ID");
                    return false;
                }
                
                if (checkDeviceIDExists(devID, motorDrivers)) {
                    System.out.println("Motor driver ID already exists.");
                    return false;
                }
                
                if (!(ports.get(portID) instanceof I2C)) {
                    System.out.println("Incompatible protocol for PCA9685.");
                    return false;
                }
                
                motorDrivers.add(new PCA9685(ports.get(portID),portID, devID));
                break;
                
            case "SparkFunMD":
                if (motorDrivers.size() >= maxMotorDrivers) {
                    System.out.println("Maximum number of motor drivers reached.");
                    return false;
                }

                if(devID>=maxMotorDrivers){
                    System.out.println("Invalid motor driver ID");
                    return false;
                }
                
                if (checkDeviceIDExists(devID, motorDrivers)) {
                    System.out.println("Motor driver ID already exists.");
                    return false;
                }
                
                if (!(ports.get(portID) instanceof SPI)) {
                    System.out.println("Incompatible protocol for SparkFunMD.");
                    return false;
                }
                
                motorDrivers.add(new SparkFunMD(ports.get(portID),portID, devID));
                break;
                
            default:
                System.out.println("Unknown device type: " + devName);
                return false;
        }
        return true;
    }

    public Boolean rmDev(int portID){

        Device device = findDeviceByPort(portID);

        if(device != null){
            if(device.getState() == State.ON){
                throw new IllegalArgumentException("Device is ON. Turn it off before removing it.");
            }
            if(device instanceof Sensor sensor){
                sensors.remove(sensor);
            }
            else if(device instanceof Display display){
                displays.remove(display);
            }
            else if(device instanceof WirelessIO wireless){
                wirelessAdapters.remove(wireless);
            }
            else if(device instanceof MotorDriver motorDriver){
                motorDrivers.remove(motorDriver);
            }
        }

        return true;
    }

    public void turnOn(int portID){
        Device device = findDeviceByPort(portID);
        if(device != null){
            device.turnOn();
            return;
        }
        throw new IllegalArgumentException("Device not found");
    }

    public void turnOff(int portID){
        Device device = findDeviceByPort(portID);
        if(device != null){
            device.turnOff();
            return;
        }
        throw new IllegalArgumentException("Device not found");
    }

    public void listDevsByType(String devType){
        //Device\\ID\\Port\\ProtocolType
        //<devName> <devID> <portID> <Protocol>
        //System.out.printf("Listing all devices:");

        for (int i = 0; i < ports.size(); i++) {
            Device device = findDeviceByPort(i);
            if(device == null){
                continue;
            }

            if(device.getDevType().equalsIgnoreCase(devType)){
                //System.out.println("Port: " + device.getProtocol().getProtocolName());
                if(device instanceof MPU6050 mpu6050){
                    System.out.println(String.format("%s %d %d %s", mpu6050.getName(), mpu6050.getDevID(), getDevicePort(mpu6050), mpu6050.getProtocol().getProtocolName()));
                }
                else if(device instanceof GY951 gy951){
                    System.out.println(String.format("%s %d %d %s", gy951.getName(),  gy951.getDevID(), getDevicePort(gy951), gy951.getProtocol().getProtocolName()));
                }
                else if(device instanceof DHT11 dht11){
                    System.out.println(String.format("%s %d %d %s", dht11.getName(), dht11.getDevID(), getDevicePort(dht11), dht11.getProtocol().getProtocolName()));
                }
                else if(device instanceof BMP280 bmp280){
                    System.out.println(String.format("%s %d %d %s", bmp280.getName(), bmp280.getDevID(), getDevicePort(bmp280), bmp280.getProtocol().getProtocolName()));
                }
                else if(device instanceof LCD lcd){
                    System.out.println(String.format("%s %d %d %s", lcd.getName(), lcd.getDevID(), getDevicePort(lcd), lcd.getProtocol().getProtocolName()));
                }
                else if(device instanceof OLED oled){
                    System.out.println(String.format("%s %d %d %s", oled.getName(), oled.getDevID(), getDevicePort(oled), oled.getProtocol().getProtocolName()));
                }
                else if(device instanceof Bluetooth bluetooth){
                    System.out.println(String.format("%s %d %d %s", bluetooth.getName(), bluetooth.getDevID(), getDevicePort(bluetooth), bluetooth.getProtocol().getProtocolName()));
                }
                else if(device instanceof Wifi wifi){
                    System.out.println(String.format("%s %d %d %s", wifi.getName(), wifi.getDevID(), getDevicePort(wifi), wifi.getProtocol().getProtocolName()));
                }
                else if(device instanceof PCA9685 pca9685){
                    System.out.println(String.format("%s %d %d %s", pca9685.getName(), pca9685.getDevID(), getDevicePort(pca9685), pca9685.getProtocol().getProtocolName()));
                }
                else if(device instanceof SparkFunMD sparkFunMD){
                    System.out.println(String.format("%s %d %d %s", sparkFunMD.getName(), sparkFunMD.getDevID(), getDevicePort(sparkFunMD), sparkFunMD.getProtocol().getProtocolName()));
                }
            }
        }
    }

    public void listPorts(){
        //<portID> <protocolType> <occupied/empty> <devName> <devType> <devID> <State>

        for (int i = 0; i < ports.size(); i++) {
            Device device = findDeviceByPort(i);
            if(device != null){
                System.out.println(String.format("%d %s occupied %s %s dev_id: %d state: %s", i, ports.get(i).getProtocolName(), device.getName(), device.getDevType(), i, device.getState()));
            }
            else{
                System.out.println(String.format("%d %s empty", i, ports.get(i).getProtocolName()));
            }
        }
    }

    public void readSensor(int devId){
        for (Sensor sensor : sensors) {
            if (sensor.getDevID() == devId) {
                String data = sensor.data2String();

                System.out.printf("%s %s: %s\n", sensor.getName(), sensor.getDevType(),data);
                return;
            }
        }
        throw new IllegalArgumentException("Sensor not found");
    }

    public void printDisplay(int devId, String data){
        for (Display display : displays) {
            if (display.getDevID() == devId) {
                display.printData(data);
                return;
            }
        }
        throw new IllegalArgumentException("Display not found");
    }

    public void readWireless(int devId){
        for (WirelessIO wireless : wirelessAdapters) {
            if (wireless.getDevID() == devId) {
                String data = wireless.recvData();

                System.out.printf("%s %s: %s\n", wireless.getName(), wireless.getDevType(),data);
                return;
            }
        }
        throw new IllegalArgumentException("Wireless adapter not found");
    }

    public void writeWireless(int devId, String data){
        for (WirelessIO wireless : wirelessAdapters) {
            if (wireless.getDevID() == devId) {
                wireless.sendData(data);
                return;
            }
        }
        throw new IllegalArgumentException("Wireless adapter not found");
    }

    
    public void setMotorSpeed(int devId, int speed){
        for (MotorDriver motor : motorDrivers) {
            if (motor.getDevID() == devId) {
                motor.setMotorSpeed(speed);
                return;
            }
        }
        throw new IllegalArgumentException("Motor driver not found"); 
    }

    private boolean isPortOccupied(int portID) {
        for (Sensor sensor : sensors) {
            if (getDevicePort(sensor) == portID) {
                return true;
            }
        }
        
        for (Display display : displays) {
            if (getDevicePort(display) == portID) {
                return true;
            }
        }
        
        for (WirelessIO wireless : wirelessAdapters) {
            if (getDevicePort(wireless) == portID) {
                return true;
            }
        }
        
        for (MotorDriver motor : motorDrivers) {
            if (getDevicePort(motor) == portID) {
                return true;
            }
        }
        
        return false;
    }

    private boolean checkDeviceIDExists(int devID, ArrayList<? extends Device> deviceList) {
        for (Device device : deviceList) {
            if (getDeviceID(device) == devID) {
                return true;
            }
        }
        
        return false;
    }

    
}
