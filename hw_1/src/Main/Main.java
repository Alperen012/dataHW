package Main;

import HWSystem.HWSystem;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String configFile = "config.txt";
        HWSystem hwSystem = new HWSystem(configFile);
        
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        System.out.println("\nHardware System Command Interface");        
        while (running) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim();
            String[] parts = input.split("\\s+");
            
            if (parts.length == 0) continue;
            
            String command = parts[0];
            
            try {
                switch (command) {
                    case "addDev":
                        if (parts.length != 4) {
                            System.out.println("Usage: addDev <device> <port> <id>");
                            break;
                        }
                        String device = parts[1];
                        int port = Integer.parseInt(parts[2]);
                        int id = Integer.parseInt(parts[3]);
                        if (hwSystem.addDevice(device, port, id)) {
                            System.out.println(device + " added to port " + port + " with ID " + id);
                        } else {
                            System.out.println("Failed to add device");
                        }
                        break;
                        
                    case "rmDev":
                        if (parts.length != 2) {
                            System.out.println("Usage: rmDev <port>");
                            break;
                        }
                        int rmPort = Integer.parseInt(parts[1]);
                        if (hwSystem.rmDev(rmPort)) {
                            System.out.println("Device removed from port " + rmPort);
                        } else {
                            System.out.println("Failed to remove device");
                        }
                        break;
                        
                    case "list":
                        if(parts.length==2 && parts[1].equals("ports")){
                            System.out.println("Listing all ports:");
                            hwSystem.listPorts();
                            break;
                        }
                        if (parts.length != 2) {
                            System.out.println("Usage: list <devType>");
                            break;
                        }
                        String devType = parts[1];
                        System.out.println("Listing all " + devType + "s:");
                        System.out.println("Device\\ID\\Port\\ProtocolType");
                        hwSystem.listDevsByType(devType);
                        break;
                        
                        
                    case "turnON":
                        if (parts.length != 2) {
                            System.out.println("Usage: turnON <port>");
                            break;
                        }
                        int onPort = Integer.parseInt(parts[1]);
                        hwSystem.turnOn(onPort);
                        break;
                        
                    case "turnOFF":
                        if (parts.length != 2) {
                            System.out.println("Usage: turnOFF <port>");
                            break;
                        }
                        int offPort = Integer.parseInt(parts[1]);
                        System.out.println("Turning off device at port " + offPort);
                        hwSystem.turnOff(offPort);
                        break;
                        
                    case "readSensor":
                        if (parts.length != 2) {
                            System.out.println("Usage: readSensor <device_id>");
                            break;
                        }
                        int sensorId = Integer.parseInt(parts[1]);
                        hwSystem.readSensor(sensorId);
                        break;
                        
                    case "printDisplay":
                        if (parts.length < 3) {
                            System.out.println("Usage: printDisplay <device_id> <text>");
                            break;
                        }
                        int displayId = Integer.parseInt(parts[1]);
                        StringBuilder text = new StringBuilder();
                        for (int i = 2; i < parts.length; i++) {
                            text.append(parts[i]).append(" ");
                        }
                        hwSystem.printDisplay(displayId, text.toString().trim());
                        break;
                        
                    case "readWireless":
                        if (parts.length != 2) {
                            System.out.println("Usage: readWireless <device_id>");
                            break;
                        }
                        int wirelessReadId = Integer.parseInt(parts[1]);
                        hwSystem.readWireless(wirelessReadId);
                        break;
                        
                    case "writeWireless":
                        if (parts.length < 3) {
                            System.out.println("Usage: writeWireless <device_id> <data>");
                            break;
                        }
                        int wirelessWriteId = Integer.parseInt(parts[1]);
                        StringBuilder data = new StringBuilder();
                        for (int i = 2; i < parts.length; i++) {
                            data.append(parts[i]).append(" ");
                        }
                        hwSystem.writeWireless(wirelessWriteId, data.toString().trim());
                        break;
                        
                    case "setMotorSpeed":
                        if (parts.length != 3) {
                            System.out.println("Usage: setMotorSpeed <device_id> <speed>");
                            break;
                        }
                        int motorId = Integer.parseInt(parts[1]);
                        int speed = Integer.parseInt(parts[2]);
                        hwSystem.setMotorSpeed(motorId, speed);
                        break;
                        
                    case "exit":
                        running = false;
                        System.out.println("Exiting Hardware System...");
                        break;
                        
                    default:
                        System.out.println("Unknown command.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid number format");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        scanner.close();
        System.out.println("Hardware System terminated.");
    }
}