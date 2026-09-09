package week4.practice_problems;

public class NightlyFleetReconciliationEngine {
    static {
        System.out.println("Daily fleet reconciliation engine initialized.");
    }

    public static class BusTicketAccount {
        private final String bookingId;
        private final double ticketFare;

        public BusTicketAccount(String bookingId, double ticketFare) {
            if (bookingId == null || bookingId.trim().isEmpty()) {
                throw new IllegalArgumentException("Booking ID cannot be empty.");
            }
            if (ticketFare < 0) {
                throw new IllegalArgumentException("Ticket fare cannot be negative.");
            }

            this.bookingId = bookingId.trim();
            this.ticketFare = ticketFare;
        }

        public BusTicketAccount(String bookingId) {
            this(bookingId, 0.0);
        }

        public final double calculatePenalty(int minutesLate) {
            if (minutesLate < 0) {
                throw new IllegalArgumentException("Minutes late cannot be negative.");
            }
            if (minutesLate == 0 || ticketFare == 0) {
                return 0.0;
            }
            return ticketFare * 0.01 * minutesLate;
        }

        public String getBookingId() {
            return bookingId;
        }

        public double getTicketFare() {
            return ticketFare;
        }
    }

    public static class SleeperBusTicketAccount extends BusTicketAccount {
        public SleeperBusTicketAccount(String bookingId, double ticketFare) {
            super(bookingId, ticketFare);
        }

        public SleeperBusTicketAccount(String bookingId) {
            super(bookingId);
        }
    }

    public static void processAccount(BusTicketAccount account, double amount, int minutesLate) {
        if (account == null) {
            return;
        }

        double penalty;
        if (account instanceof SleeperBusTicketAccount) {
            penalty = account.calculatePenalty(minutesLate) * 1.10;
            System.out.println("Sleeper processed: " + account.getBookingId() + " | penalty = " + penalty);
        } else {
            penalty = account.calculatePenalty(minutesLate);
            System.out.println("Regular processed: " + account.getBookingId() + " | penalty = " + penalty);
        }

        double settledAmount = amount + penalty;
        System.out.println("Settled amount: " + settledAmount);
    }

    public static void processBatch(BusTicketAccount[] accounts, double[] amounts, int[] minutesLateArray) {
        if (accounts == null || amounts == null || minutesLateArray == null) {
            System.out.println("Invalid batch input; nothing processed.");
            return;
        }

        int limit = Math.min(accounts.length, Math.min(amounts.length, minutesLateArray.length));
        int processed = 0;
        int nullSkipped = 0;
        int sleeper = 0;
        int regular = 0;
        double totalPenalty = 0.0;

        for (int i = 0; i < limit; i++) {
            BusTicketAccount account = accounts[i];
            if (account == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (account instanceof SleeperBusTicketAccount) {
                sleeper++;
            } else {
                regular++;
            }

            double penalty = (account instanceof SleeperBusTicketAccount)
                    ? account.calculatePenalty(minutesLateArray[i]) * 1.10
                    : account.calculatePenalty(minutesLateArray[i]);
            totalPenalty += penalty;
        }

        System.out.println(processed + " processed | " + nullSkipped + " null skipped | " + sleeper + " sleeper | " + regular + " regular | grand total penalties = " + totalPenalty);
    }

    public static void main(String[] args) {
        BusTicketAccount[] accounts = {
                new SleeperBusTicketAccount("BK001", 2000),
                null,
                new BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {1200, 900, 700};
        int[] minutesLateArray = {10, 5, 0};

        processBatch(accounts, amounts, minutesLateArray);
    }
}
