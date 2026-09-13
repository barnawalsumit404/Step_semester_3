package week6.practice_problems;

import java.util.Arrays;

public class EventTicket {
    private static int ticketsCounter = 1000;

    private final String ticketId;
    protected final double basePrice;
    protected double amountPaid = 0.0;
    private double[] lateFees = new double[0];

    public EventTicket(String attendeeId, double basePrice) {
        String clean = (attendeeId == null) ? null : attendeeId.trim();
        if (clean == null || clean.isEmpty() || clean.length() < 4) {
            throw new IllegalArgumentException("attendeeId invalid");
        }
        this.basePrice = basePrice;
        synchronized (EventTicket.class) {
            ticketsCounter++;
            this.ticketId = "TCK-" + ticketsCounter;
        }
    }

    // Alternate constructor used in examples that only supplies price
    public EventTicket(double basePrice) {
        this.basePrice = basePrice;
        synchronized (EventTicket.class) {
            ticketsCounter++;
            this.ticketId = "TCK-" + ticketsCounter;
        }
    }

    public String getTicketId() {
        return ticketId;
    }

    public void pay(double amount) {
        if (amount <= 0) return;
        this.amountPaid += amount;
    }

    public void pay(double amount, String mode) {
        System.out.println("Payment mode: " + mode);
        pay(amount);
    }

    protected void applyLateFee(double amount) {
        if (amount <= 0) return;
        double[] copy = Arrays.copyOf(lateFees, lateFees.length + 1);
        copy[copy.length - 1] = amount;
        lateFees = copy;
    }

    protected double[] getLateFeesCopy() {
        return Arrays.copyOf(lateFees, lateFees.length);
    }

    public double getBalanceDue() {
        double sumFees = 0.0;
        for (double f : lateFees) sumFees += f;
        return basePrice - amountPaid + sumFees;
    }

    public void printTicket() {
        System.out.println("Standard Event Ticket | Balance Due: " + getBalanceDue());
    }

    public static boolean isValidPromoCode(String code) {
        if (code == null) return false;
        if (code.length() != 5) return false;
        if (code.charAt(0) != 'F') return false;
        for (int i = 1; i <= 3; i++) {
            if (!Character.isDigit(code.charAt(i))) return false;
        }
        if (!Character.isUpperCase(code.charAt(4))) return false;
        return true;
    }

    public static int getTicketsIssued() {
        return ticketsCounter - 1000;
    }

    public static String registerBatch(String[] attendeeIds, double basePrice) {
        if (attendeeIds == null) return "Registered: 0 | Rejected: 0";
        int reg = 0, rej = 0;
        for (String id : attendeeIds) {
            try {
                new EventTicket(id, basePrice);
                reg++;
            } catch (IllegalArgumentException e) {
                rej++;
            }
        }
        return "Registered: " + reg + " | Rejected: " + rej;
    }
}

// HackathonTicket: independent branch extending EventTicket
class HackathonTicket extends EventTicket {
    private final String teamName;

    public HackathonTicket(String attendeeId, double basePrice, String teamName) {
        super(attendeeId, basePrice);
        this.teamName = (teamName == null) ? "" : teamName.trim();
    }

    @Override
    public void printTicket() {
        System.out.println("Hackathon Ticket | Team: " + teamName + " | Balance Due: " + getBalanceDue());
    }
}

// GroupTicket used in nightly settlement
class GroupTicket extends EventTicket {
    private final int groupSize;

    public GroupTicket(double basePrice, int groupSize) {
        super(basePrice * groupSize);
        this.groupSize = groupSize;
    }

    @Override
    public void printTicket() {
        System.out.println("Group Ticket | Size: " + groupSize + " | Balance Due: " + getBalanceDue());
    }
}
