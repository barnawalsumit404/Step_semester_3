package week4.practice_problems;

import java.util.Arrays;

public class FareSplitter {
    private final String tripId;
    private final double totalFare;
    private final int passengerCount;
    private final double[] breakdown;

    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (tripId == null || tripId.trim().isEmpty()) {
            throw new IllegalArgumentException("Trip ID cannot be empty.");
        }
        if (totalFare < 0) {
            throw new IllegalArgumentException("Total fare cannot be negative.");
        }
        if (passengerCount <= 0) {
            throw new IllegalArgumentException("Passenger count must be positive.");
        }

        this.tripId = tripId.trim();
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
        this.breakdown = buildBreakdown();
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0.0, 2);
    }

    private double[] buildBreakdown() {
        double[] shares = new double[passengerCount];
        long totalCents = Math.round(totalFare * 100);
        long base = totalCents / passengerCount;
        long remainder = totalCents % passengerCount;

        for (int i = 0; i < passengerCount; i++) {
            long shareInCents = base + (i >= passengerCount - remainder ? 1 : 0);
            shares[i] = shareInCents / 100.0;
        }

        return shares;
    }

    public double[] fareBreakdown() {
        return Arrays.copyOf(breakdown, breakdown.length);
    }

    public boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }

    public static void main(String[] args) {
        FareSplitter first = new FareSplitter("TRIP001", 100000, 3);
        System.out.println(Arrays.toString(first.fareBreakdown()));

        FareSplitter provisional = new FareSplitter("TRIP003");
        System.out.println(Arrays.toString(provisional.fareBreakdown()));
    }
}
