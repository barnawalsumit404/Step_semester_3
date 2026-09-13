package week6.practice_problems;

public class WorkshopTicket extends EventTicket {
    private final String track;

    public WorkshopTicket(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
        this.track = (track == null) ? "" : track.trim();
    }

    @Override
    protected void applyLateFee(double amount) {
        // Double the amount for workshops and delegate to parent
        super.applyLateFee(amount * 2);
    }

    public double[] getLateFeeHistory() {
        return super.getLateFeesCopy();
    }

    @Override
    public void printTicket() {
        System.out.println("Workshop Ticket | Track: " + track + " | Balance Due: " + getBalanceDue());
    }

    public String getTrack() {
        return track;
    }
}

// Premium workshop as a subclass
class PremiumWorkshopTicket extends WorkshopTicket {
    private final double kitFee;

    public PremiumWorkshopTicket(String attendeeId, double basePrice, String track, double kitFee) {
        super(attendeeId, basePrice, track);
        this.kitFee = kitFee;
    }

    @Override
    public void printTicket() {
        System.out.println("Premium Workshop Ticket | Track: " + getTrack() + " | Kit Fee: " + kitFee + " | Balance Due: " + getBalanceDue());
    }
}
