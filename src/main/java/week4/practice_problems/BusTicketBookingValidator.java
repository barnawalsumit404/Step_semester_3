package week4.practice_problems;

import java.util.HashSet;
import java.util.Set;

public class BusTicketBookingValidator {
    private static final Set<String> ACCEPTED_PAIRS = new HashSet<>();

    private final String passengerName;
    private final String destination;
    private boolean checkedIn;

    public BusTicketBookingValidator(String passengerName, String destination) {
        String cleanName = normalize(passengerName);
        String cleanDestination = normalize(destination);

        if (!isMeaningful(cleanName)) {
            throw new IllegalArgumentException("Passenger name is invalid.");
        }
        if (!isMeaningful(cleanDestination)) {
            throw new IllegalArgumentException("Destination is invalid.");
        }

        this.passengerName = cleanName;
        this.destination = cleanDestination;
    }

    private static String normalize(String value) {
        if (value == null) {
            return null;
        }
        return value.trim();
    }

    private static boolean isMeaningful(String value) {
        return value != null && !value.isEmpty() && value.matches(".*[A-Za-z0-9].*");
    }

    public void markCheckedIn() {
        if (checkedIn) {
            throw new IllegalStateException("Ticket already checked in.");
        }
        checkedIn = true;
    }

    public static void processBatch(String[][] rawBookings) {
        if (rawBookings == null) {
            System.out.println("Valid: 0 | Rejected: 0 | Duplicates skipped: 0");
            return;
        }

        int valid = 0;
        int rejected = 0;
        int duplicates = 0;

        for (String[] booking : rawBookings) {
            if (booking == null || booking.length != 2) {
                rejected++;
                continue;
            }

            String passengerName = normalize(booking[0]);
            String destination = normalize(booking[1]);

            if (!isMeaningful(passengerName) || !isMeaningful(destination)) {
                rejected++;
                continue;
            }

            String key = (passengerName + "::" + destination).toLowerCase();

            if (ACCEPTED_PAIRS.contains(key)) {
                duplicates++;
                continue;
            }

            ACCEPTED_PAIRS.add(key);
            valid++;
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected + " | Duplicates skipped: " + duplicates);
    }

    public static void main(String[] args) {
        String[][] rawBookings = {
                {"Divya", "Chennai"},
                {"", "Bangalore"},
                {"Ravi123", "Pune"},
                {"Divya", "Chennai"},
                {" ", " "}
        };

        processBatch(rawBookings);
    }
}
