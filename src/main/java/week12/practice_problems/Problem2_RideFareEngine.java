package week12.practice_problems;

import java.util.Locale;
import java.util.Scanner;

public class Problem2_RideFareEngine {
    static abstract class Ride {
        protected final double distance;

        Ride(double distance) {
            this.distance = distance;
        }

        abstract double calculateFare();

        abstract String type();
    }

    static class Auto extends Ride {
        Auto(double distance) {
            super(distance);
        }

        @Override
        double calculateFare() {
            return 30 + 15 * distance;
        }

        @Override
        String type() {
            return "Auto";
        }
    }

    static class Cab extends Ride {
        private final double surge;

        Cab(double distance, double surge) {
            super(distance);
            this.surge = surge;
        }

        @Override
        double calculateFare() {
            return (50 + 20 * distance) * surge;
        }

        @Override
        String type() {
            return "Cab";
        }
    }

    static class Bike extends Ride {
        private final int waitingMinutes;

        Bike(double distance, int waitingMinutes) {
            super(distance);
            this.waitingMinutes = waitingMinutes;
        }

        @Override
        double calculateFare() {
            return 20 + 10 * distance + 2 * waitingMinutes;
        }

        @Override
        String type() {
            return "Bike";
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);
        int rideCount = scanner.nextInt();
        Ride[] rides = new Ride[rideCount];

        for (int i = 0; i < rideCount; i++) {
            String rideType = scanner.next();
            double distance = scanner.nextDouble();
            if (rideType.equals("Auto")) {
                rides[i] = new Auto(distance);
            } else if (rideType.equals("Cab")) {
                rides[i] = new Cab(distance, scanner.nextDouble());
            } else if (rideType.equals("Bike")) {
                rides[i] = new Bike(distance, scanner.nextInt());
            } else {
                throw new IllegalArgumentException("Unknown ride type: " + rideType);
            }
        }

        double total = 0;
        for (Ride ride : rides) {
            double fare = ride.calculateFare();
            total += fare;
            System.out.println(ride.type() + ": " + format(fare));
        }
        System.out.println("Total: " + format(total));
        scanner.close();
    }

    private static String format(double value) {
        return value == Math.rint(value) ? String.valueOf((long) value) : String.valueOf(value);
    }
}
