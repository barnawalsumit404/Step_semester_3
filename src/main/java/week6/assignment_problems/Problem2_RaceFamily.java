class RaceEntryBase {
    private static int bibCounter = 1000;
    private final String entryCode;
    protected final double entryFee;
    protected double amountPaid = 0.0;

    public RaceEntryBase(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().isEmpty() || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid bib number");
        }
        this.entryFee = entryFee;
        bibCounter++;
        this.entryCode = "BIB-" + bibCounter;
    }

    public double getBalanceDue() {
        return entryFee - amountPaid;
    }

    public String getEntryCode() {
        return entryCode;
    }

    public void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;
        }
    }

    public void announce() {
        System.out.println("Race Entry | Bib: " + getEntryCode() + " | Balance: " + getBalanceDue());
    }
}

class RunnerEntry extends RaceEntryBase {
    private final String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    @Override
    public void announce() {
        System.out.println("Runner Entry | Bib: " + getEntryCode() + " | Category: " + category + " | Balance: " + getBalanceDue());
    }
}

class EliteRunnerEntry extends RunnerEntry {
    private final double sponsorBonus;

    public EliteRunnerEntry(String bibNumber, double entryFee, String category, double sponsorBonus) {
        super(bibNumber, entryFee, category);
        this.sponsorBonus = sponsorBonus;
    }

    @Override
    public void announce() {
        System.out.println("Elite Runner | Bib: " + getEntryCode() + " | Category: " + category + " | Sponsor Bonus: " + sponsorBonus + " | Balance: " + getBalanceDue());
    }
}

class RelayTeamEntry extends RaceEntryBase {
    private final int teamSize;

    public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    @Override
    public void announce() {
        System.out.println("Relay Team | Bib: " + getEntryCode() + " | Team Size: " + teamSize + " | Balance: " + getBalanceDue());
    }
}

public class Problem2_RaceFamily {
    public static String classifyGeneration(RaceEntryBase entry) {
        if (entry instanceof EliteRunnerEntry) {
            return "Multilevel descendant (3 generations deep)";
        } else if (entry instanceof RunnerEntry) {
            return "Two-level descendant (2 generations deep)";
        } else if (entry instanceof RelayTeamEntry) {
            return "Hierarchical sibling (independent branch)";
        }
        return "Standard Race Entry";
    }

    public static double getTotalBalanceDue(RaceEntryBase[] entries) {
        if (entries == null) {
            return 0.0;
        }

        double total = 0.0;
        for (RaceEntryBase entry : entries) {
            if (entry != null) {
                total += entry.getBalanceDue();
            }
        }
        return total;
    }

    public static void main(String[] args) {
        RunnerEntry runner = new RunnerEntry("BIB2001", 80, "Open 10K");
        EliteRunnerEntry elite = new EliteRunnerEntry("BIB3001", 150, "Elite Full Marathon", 500);
        RelayTeamEntry relay = new RelayTeamEntry("BIB4001", 300, 4);

        runner.announce();
        elite.announce();
        relay.announce();

        System.out.println(classifyGeneration(elite));
        System.out.println(classifyGeneration(relay));
        System.out.println(getTotalBalanceDue(new RaceEntryBase[]{runner, elite, relay}));
    }
}
