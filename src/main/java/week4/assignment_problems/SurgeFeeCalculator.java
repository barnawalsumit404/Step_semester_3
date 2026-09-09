package week4.assignment_problems;

public final class SurgeFeeCalculator {
    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        if (minimumSurgePercent < 0) {
            throw new IllegalArgumentException("Minimum surge percent cannot be negative.");
        }
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0) {
            throw new IllegalArgumentException("Order value cannot be negative.");
        }
        if (delayMinutes < 0) {
            throw new IllegalArgumentException("Delay minutes cannot be negative.");
        }
        if (delayMinutes == 0 || orderValue == 0) {
            return 0.0;
        }

        double tieredFee = 0.0;
        int remaining = delayMinutes;

        int firstFive = Math.min(remaining, 5);
        tieredFee += firstFive * orderValue * 0.005;
        remaining -= firstFive;

        int nextTen = Math.min(remaining, 10);
        tieredFee += nextTen * orderValue * 0.01;
        remaining -= nextTen;

        tieredFee += remaining * orderValue * 0.02;

        double floorFee = orderValue * (minimumSurgePercent / 100.0);
        return Math.max(tieredFee, floorFee);
    }

    public static void main(String[] args) {
        SurgeFeeCalculator calculator = new SurgeFeeCalculator(1.0);

        System.out.println("0 min: Rs " + calculator.calculateSurgeFee(500, 0));
        System.out.println("1 min: Rs " + calculator.calculateSurgeFee(500, 1));
        System.out.println("16 min: Rs " + calculator.calculateSurgeFee(500, 16));
    }
}
