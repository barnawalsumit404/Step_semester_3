import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question3_TheSmartLabControlPanel {
    interface Capability {
        String getName();
        boolean isValid(double value);
        String apply(double value);
    }

    static class PowerCapability implements Capability {
        @Override
        public String getName() {
            return "Power";
        }

        @Override
        public boolean isValid(double value) {
            return value == 0 || value == 1;
        }

        @Override
        public String apply(double value) {
            if (!isValid(value)) {
                return "Rejected: invalid power state";
            }
            return "ON";
        }
    }

    static class BrightnessCapability implements Capability {
        @Override
        public String getName() {
            return "Brightness";
        }

        @Override
        public boolean isValid(double value) {
            return value >= 0 && value <= 100;
        }

        @Override
        public String apply(double value) {
            if (!isValid(value)) {
                return "Rejected: brightness must be between 0 and 100";
            }
            return "brightness set to " + value + "%";
        }
    }

    static class TemperatureCapability implements Capability {
        @Override
        public String getName() {
            return "Temperature";
        }

        @Override
        public boolean isValid(double value) {
            return value >= 16 && value <= 30;
        }

        @Override
        public String apply(double value) {
            if (!isValid(value)) {
                return "Rejected: temperature must be between 16°C and 30°C";
            }
            return "temperature set to " + value + "°C";
        }
    }

    static class Device {
        private final String name;
        private final Map<String, Capability> capabilities = new HashMap<>();

        public Device(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void addCapability(Capability capability) {
            capabilities.put(capability.getName(), capability);
        }

        public boolean hasCapability(String name) {
            return capabilities.containsKey(name);
        }

        public String applyCapability(String capabilityName, double value) {
            Capability capability = capabilities.get(capabilityName);
            if (capability == null) {
                return "Device does not support " + capabilityName;
            }
            return capability.apply(value);
        }
    }

    static class SceneStep {
        private final String capabilityName;
        private final double value;

        public SceneStep(String capabilityName, double value) {
            this.capabilityName = capabilityName;
            this.value = value;
        }

        public String getCapabilityName() {
            return capabilityName;
        }

        public double getValue() {
            return value;
        }
    }

    static class Scene {
        private final String name;
        private final List<SceneStep> steps = new ArrayList<>();

        public Scene(String name) {
            this.name = name;
        }

        public void addStep(String capabilityName, double value) {
            steps.add(new SceneStep(capabilityName, value));
        }

        public void execute(List<Device> devices) {
            int actions = 0;
            for (SceneStep step : steps) {
                for (Device device : devices) {
                    if (device.hasCapability(step.getCapabilityName())) {
                        String result = device.applyCapability(step.getCapabilityName(), step.getValue());
                        if (!result.startsWith("Rejected")) {
                            actions++;
                        }
                        System.out.println(device.getName() + ": " + result);
                    }
                }
            }
            System.out.println("Scene '" + name + "' completed: " + actions + " actions applied.");
        }
    }

    public static void main(String[] args) {
        Device labAc = new Device("Lab AC");
        labAc.addCapability(new PowerCapability());
        labAc.addCapability(new TemperatureCapability());

        Device ceilingLights = new Device("Ceiling Lights");
        ceilingLights.addCapability(new PowerCapability());
        ceilingLights.addCapability(new BrightnessCapability());

        Device projector = new Device("Projector");
        projector.addCapability(new PowerCapability());

        Scene lectureMode = new Scene("Lecture Mode");
        lectureMode.addStep("Power", 1);
        lectureMode.addStep("Brightness", 40);
        lectureMode.addStep("Temperature", 24);

        lectureMode.execute(List.of(labAc, ceilingLights, projector));

        System.out.println(labAc.applyCapability("Temperature", 12));

        projector.addCapability(new BrightnessCapability());
        System.out.println(projector.applyCapability("Brightness", 70));
    }
}
