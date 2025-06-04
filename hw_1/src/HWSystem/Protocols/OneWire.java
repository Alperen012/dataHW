package HWSystem.Protocols;

public class OneWire implements Protocol {
    

    @Override
    public String read() {
        return String.format("%s: Reading", getProtocolName());
    }

    @Override
    public void write(String data) {
        System.out.printf("%s: Writing \" %s \"", getProtocolName(), data);
    }

    @Override
    public String getProtocolName() {
        return "OneWire";
    }
}
