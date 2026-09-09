package week4.assignment_problems;

import java.util.Arrays;

public class Canteen implements Comparable<Canteen> {
    private final String canteenCode;
    private final String canteenName;
    private final int trustScore;

    public Canteen(String canteenCode, String canteenName, int trustScore) {
        if (canteenCode == null || canteenCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Canteen code cannot be empty.");
        }
        if (canteenName == null || canteenName.trim().isEmpty()) {
            throw new IllegalArgumentException("Canteen name cannot be empty.");
        }

        this.canteenCode = canteenCode.trim();
        this.canteenName = canteenName.trim();
        this.trustScore = trustScore;
    }

    public Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, 3);
    }

    @Override
    public int compareTo(Canteen other) {
        if (other == null) {
            return 1;
        }

        int scoreCompare = Integer.compare(other.trustScore, this.trustScore);
        if (scoreCompare != 0) {
            return scoreCompare;
        }

        int codeCompare = this.canteenCode.compareToIgnoreCase(other.canteenCode);
        if (codeCompare != 0) {
            return codeCompare;
        }

        return Integer.compare(this.canteenName.length(), other.canteenName.length());
    }

    public static Canteen[] rankCanteens(Canteen[] canteens) {
        if (canteens == null) {
            return new Canteen[0];
        }

        Canteen[] sorted = Arrays.copyOf(canteens, canteens.length);
        for (int i = 1; i < sorted.length; i++) {
            Canteen key = sorted[i];
            int j = i - 1;

            while (j >= 0 && sorted[j].compareTo(key) > 0) {
                sorted[j + 1] = sorted[j];
                j--;
            }

            sorted[j + 1] = key;
        }

        return sorted;
    }

    public String getCanteenCode() {
        return canteenCode;
    }

    public static void main(String[] args) {
        Canteen[] canteens = {
                new Canteen("HB3-C", "Spice Junction", 3),
                new Canteen("hb1-c", "Grand Mess", 5),
                new Canteen("HB2-C", "Southern Treats")
        };

        Canteen[] ranked = rankCanteens(canteens);
        for (Canteen canteen : ranked) {
            System.out.println(canteen.getCanteenCode());
        }
    }
}
