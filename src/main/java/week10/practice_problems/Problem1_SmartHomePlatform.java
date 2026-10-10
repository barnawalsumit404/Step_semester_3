package week10.practice_problems;

import java.util.HashMap;
import java.util.Map;

public class Problem1_SmartHomePlatform {
    interface Device {
        String getId();
        boolean supports(String capability);
        String execute(String command, String argument);
    }

    static class Light implements Device {
        private final String id;
        private boolean on;

        Light(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        boolean isOn() {
            return on;
        }

        public boolean supports(String capability) {
            return "DIM".equals(capability) || "SCHEDULE".equals(capability);
        }

        public String execute(String command, String argument) {
            if ("ON".equals(command)) {
                on = true;
                return id + " is ON";
            }
            if ("OFF".equals(command)) {
                on = false;
                return id + " is OFF";
            }
            if ("DIM".equals(command)) {
                return id + " dimmed to " + argument;
            }
            return id + " scheduled " + argument;
        }
    }

    static class Fan implements Device {
        private final String id;
        private boolean on;

        Fan(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        boolean isOn() {
            return on;
        }

        public boolean supports(String capability) {
            return "SCHEDULE".equals(capability);
        }

        public String execute(String command, String argument) {
            if ("ON".equals(command)) {
                on = true;
                return id + " is ON";
            }
            if ("OFF".equals(command)) {
                on = false;
                return id + " is OFF";
            }
            return id + " scheduled " + argument;
        }
    }

    static class Plug implements Device {
        private final String id;
        private final int energy;
        private boolean on;

        Plug(String id, int energy) {
            this.id = id;
            this.energy = energy;
        }

        public String getId() {
            return id;
        }

        boolean isOn() {
            return on;
        }

        public boolean supports(String capability) {
            return "ENERGY".equals(capability);
        }

        public String execute(String command, String argument) {
            if ("ON".equals(command)) {
                on = true;
                return id + " is ON";
            }
            if ("OFF".equals(command)) {
                on = false;
                return id + " is OFF";
            }
            return id + " energy " + energy + " kWh";
        }
    }

    static class SmartHome {
        private final Map<String, Device> devices = new HashMap<>();

        void addDevice(Device device) {
            devices.put(device.getId(), device);
        }

        String process(String command, String id, String argument) {
            Device device = devices.get(id);
            if (device == null) {
                return id + " rejected: device not found";
            }
            if (!"ON".equals(command) && !"OFF".equals(command) && !device.supports(command)) {
                return id + " rejected: " + command + " unsupported";
            }
            return device.execute(command, argument);
        }
    }

    public static void main(String[] args) {
        SmartHome home = new SmartHome();
        home.addDevice(new Light("L1"));
        home.addDevice(new Fan("F1"));
        home.addDevice(new Plug("P1", 12));

        System.out.println(home.process("ON", "L1", null));
        System.out.println(home.process("DIM", "L1", "40"));
        System.out.println(home.process("DIM", "F1", "30"));
        System.out.println(home.process("SCHEDULE", "F1", "22:00"));
        System.out.println(home.process("ENERGY", "P1", null));
        System.out.println(home.process("ENERGY", "L1", null));
    }
}
