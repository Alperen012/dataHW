package HWSystem.Protocols;

public interface Protocol {

    String read(); // read data from the device

    void write(String data); // write data to the device

    String getProtocolName(); // get the name of the protocol
}