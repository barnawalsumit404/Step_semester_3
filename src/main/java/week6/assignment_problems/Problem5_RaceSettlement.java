class RaceSettlementEntry {
    private static int bibCounter = 1000;
    private final String entryCode;
    protected final double entryFee;
    protected double amountPaid = 0.0;

    public RaceSettlementEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().isEmpty() || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid bib number");
        }
        this.entryFee = entryFee;
        bibCounter++;
        this.entryCode = "BIB-" + bibCounter;
    }

    public void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;
        }
    }

    public void pay(double amount, String mode) {
        System.out.println("Paying via " + mode);
        pay(amount);
    }

    public double getBalanceDue() {
        return entryFee - amountPaid;
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
}

class RelaySettlementEntry extends RaceSettlementEntry {
    private final int teamSize;

    public RelaySettlementEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }
}

public class Problem5_RaceSettlement {
    public static String settleNight(RaceSettlementEntry[] entries) {
        if (entries == null) {
            return "0 processed | 0 null skipped | 0 relay | 0 individual";
        }

        int processed = 0;
        int nullSkipped = 0;
        int relay = 0;
        int individual = 0;

        for (RaceSettlementEntry entry : entries) {
            if (entry == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (entry instanceof RelaySettlementEntry) {
                relay++;
            } else {
                individual++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + relay + " relay | " + individual + " individual";
    }

    public static void main(String[] args) {
        RaceSettlementEntry r = new RaceSettlementEntry("BIB2001", 80);
        r.pay(10, "UPI");
        System.out.println(RaceSettlementEntry.isValidDiscountCode("M123A"));
        System.out.println(RaceSettlementEntry.isValidDiscountCode("M12A"));
        System.out.println(RaceSettlementEntry.isValidDiscountCode("X123A"));

        RaceSettlementEntry relay = new RelaySettlementEntry("BIB4001", 300, 4);
        System.out.println(settleNight(new RaceSettlementEntry[]{relay, null, r}));
        System.out.println(RaceSettlementEntry.getBibCounter());
    }
}
