package week5.practice_problems;

import java.util.Arrays;

public class PatientVitals {
    private double[] readings;

    public PatientVitals(double[] initialReadings) {
        this.readings = new double[0];
        if (initialReadings != null) {
            for (double reading : initialReadings) {
                recordReading(reading);
            }
        }
    }

    public void recordReading(double reading) {
        if (reading <= 0 || reading > 45) {
            return;
        }

        double[] updated = Arrays.copyOf(readings, readings.length + 1);
        updated[updated.length - 1] = reading;
        readings = updated;
    }

    public double getAverage() {
        if (readings.length == 0) {
            return 0.0;
        }

        double total = 0;
        for (double reading : readings) {
            total += reading;
        }

        return total / readings.length;
    }

    public double[] getAllReadings() {
        return Arrays.copyOf(readings, readings.length);
    }

    public static void main(String[] args) {
        PatientVitals v = new PatientVitals(new double[]{36.5, -2, 37.1});
        System.out.println(Arrays.toString(v.getAllReadings()));

        double[] copy = v.getAllReadings();
        copy[0] = 999;
        System.out.println(v.getAllReadings()[0]);
    }
}
