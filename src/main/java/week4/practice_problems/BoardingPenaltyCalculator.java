package week4.practice_problems;

public final class BoardingPenaltyCalculator {
    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        if (minimumPenaltyPercent < 0) {
            throw new IllegalArgumentException("Minimum penalty percent cannot be negative.");
        }
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(double ticketFare, int minutesLate) {
        if (ticketFare < 0) {
            throw new IllegalArgumentException("Ticket fare cannot be negative.");
        }
        if (minutesLate < 0) {
            throw new IllegalArgumentException("Minutes late cannot be negative.");
        }
        if (minutesLate == 0 || ticketFare == 0) {
            return 0.0;
        }

        double tieredPenalty = 0.0;
        int remaining = minutesLate;

        int firstFive = Math.min(remaining, 5);
        tieredPenalty += firstFive * ticketFare * 0.005;
        remaining -= firstFive;

        int nextTen = Math.min(remaining, 10);
        tieredPenalty += nextTen * ticketFare * 0.01;
        remaining -= nextTen;

        tieredPenalty += remaining * ticketFare * 0.02;

        double floorPenalty = ticketFare * (minimumPenaltyPercent / 100.0);
        return Math.max(tieredPenalty, floorPenalty);
    }

    public static void main(String[] args) {
        BoardingPenaltyCalculator calculator = new BoardingPenaltyCalculator(1.0);

        System.out.println("0 min: Rs " + calculator.calculatePenalty(1000, 0));
        System.out.println("1 min: Rs " + calculator.calculatePenalty(1000, 1));
        System.out.println("16 min: Rs " + calculator.calculatePenalty(1000, 16));
    }
}
