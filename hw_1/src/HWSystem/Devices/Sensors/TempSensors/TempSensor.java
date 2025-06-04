package HWSystem.Devices.Sensors.TempSensors;

import HWSystem.Devices.Sensors.Sensor;

public abstract class TempSensor extends Sensor{
    public abstract Float getTemp();

    @Override
    public String getSensType(){
        return "Temrature Sensor";
    }

    @Override
    public String data2String(){
        return String.format("Temp: %.2fC", getTemp());
    }
    
}
