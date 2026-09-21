class RaceEntryAnnouncerBase {
    protected final String bibNumber;
    protected final double entryFee;
    protected double amountPaid = 0.0;

    public RaceEntryAnnouncerBase(String bibNumber, double entryFee) {
        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
    }

    public double getBalanceDue() {
        return entryFee - amountPaid;
    }

    public void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;
        }
    }

    public void announce() {
        System.out.println("Runner Entry | Bib: " + bibNumber + " | Balance: " + getBalanceDue());
    }
}

class RelayTeamAnnouncerEntry extends RaceEntryAnnouncerBase {
    private final int teamSize;

    public RelayTeamAnnouncerEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    @Override
    public void announce() {
        System.out.println("Relay Team | Bib: " + bibNumber + " | Team Size: " + teamSize + " | Balance: " + getBalanceDue());
    }

    public int getTeamSize() {
        return teamSize;
    }
}

public class Problem4_RaceAnnouncer {
    public static String announceAll(RaceEntryAnnouncerBase[] entries) {
        StringBuilder sb = new StringBuilder();

        for (RaceEntryAnnouncerBase entry : entries) {
            if (entry == null) {
                continue;
            }

            sb.append(entry.getClass().getSimpleName()).append(" | Balance: ").append(entry.getBalanceDue()).append(" | ");
            if (entry instanceof RelayTeamAnnouncerEntry) {
                RelayTeamAnnouncerEntry relay = (RelayTeamAnnouncerEntry) entry;
                sb.append("[Team size via downcast: ").append(relay.getTeamSize()).append("] | ");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        RaceEntryAnnouncerBase runner = new RaceEntryAnnouncerBase("BIB2001", 80) {
            @Override
            public void announce() {
                System.out.println("Runner Entry | Bib: BIB2001 | Category: Open 10K | Balance: 90.0");
            }
        };

        RaceEntryAnnouncerBase relay = new RelayTeamAnnouncerEntry("BIB4001", 300, 4);
        System.out.println(announceAll(new RaceEntryAnnouncerBase[]{runner, relay}));
    }
}
