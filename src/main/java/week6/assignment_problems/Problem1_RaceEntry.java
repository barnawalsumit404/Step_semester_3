public class Problem1_RaceEntry {
    private static int bibCounter = 1000;

    private final String entryCode;
    protected final double entryFee;
    protected double amountPaid = 0.0;
    private double[] lateFees = new double[0];

    public Problem1_RaceEntry(String bibNumber, double entryFee) {
        String clean = (bibNumber == null) ? null : bibNumber.trim();
        if (clean == null || clean.isEmpty() || clean.length() < 4) {
            throw new IllegalArgumentException("bibNumber must be at least 4 characters");
        }
        this.entryFee = entryFee;
        synchronized (Problem1_RaceEntry.class) {
            bibCounter++;
            this.entryCode = "BIB-" + bibCounter;
        }
    }

    public String getEntryCode() {
        return entryCode;
    }

    public void pay(double amount) {
        if (amount > 0) {
            this.amountPaid += amount;
        }
    }

    protected void applyLateFee(double amount) {
        if (amount > 0) {
            double[] copy = new double[lateFees.length + 1];
            System.arraycopy(lateFees, 0, copy, 0, lateFees.length);
            copy[copy.length - 1] = amount;
            lateFees = copy;
        }
    }

    public double[] getLateFeeHistory() {
        return lateFees.clone();
    }

    public double getBalanceDue() {
        double totalLate = 0.0;
        for (double fee : lateFees) {
            totalLate += fee;
        }
        return entryFee - amountPaid + totalLate;
    }

    public static boolean isValidDiscountCode(String code) {
        if (code == null || code.length() != 5) {
            return false;
        }
        if (code.charAt(0) != 'M') {
            return false;
        }
        for (int i = 1; i <= 3; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return false;
            }
        }
        return Character.isUpperCase(code.charAt(4));
    }

    public static int getBibCounter() {
        return bibCounter - 1000;
    }

    public static String registerBatch(String[] bibNumbers, double entryFee) {
        if (bibNumbers == null) {
            return "Registered: 0 | Rejected: 0";
        }

        int registered = 0;
        int rejected = 0;

        for (String bib : bibNumbers) {
            try {
                new Problem1_RaceEntry(bib, entryFee);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {
        try {
            new Problem1_RaceEntry("B1", 50);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        Problem1_RaceEntry r = new Problem1_RaceEntry("BIB2001", 80);
        r.pay(30);
        System.out.println(r.getBalanceDue());
        System.out.println(Problem1_RaceEntry.registerBatch(new String[]{"BIB1", "B1", "BIB2"}, 80));
    }
}
