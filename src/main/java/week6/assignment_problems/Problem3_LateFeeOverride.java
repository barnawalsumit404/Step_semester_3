class RaceEntryLateBase {
    protected final double entryFee;
    protected double amountPaid = 0.0;
    private double[] lateFees = new double[0];

    public RaceEntryLateBase(double entryFee) {
        this.entryFee = entryFee;
    }

    protected void applyLateFee(double amount) {
        if (amount > 0) {
            double[] copy = new double[lateFees.length + 1];
            System.arraycopy(lateFees, 0, copy, 0, lateFees.length);
            copy[copy.length - 1] = amount;
            lateFees = copy;
        }
    }

    public double getBalanceDue() {
        double totalLate = 0.0;
        for (double fee : lateFees) {
            totalLate += fee;
        }
        return entryFee - amountPaid + totalLate;
    }

    public double[] getLateFeeHistory() {
        return lateFees.clone();
    }

    public void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;
        }
    }
}

class RunnerEntryLate extends RaceEntryLateBase {
    public RunnerEntryLate(double entryFee) {
        super(entryFee);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class Problem3_LateFeeOverride {
    public static void main(String[] args) {
        RunnerEntryLate r = new RunnerEntryLate(80);
        r.pay(30);
        r.applyLateFee(20);
        System.out.println(r.getBalanceDue());

        double[] history = r.getLateFeeHistory();
        System.out.println(history[0]);
        history[0] = 999;
        System.out.println(r.getLateFeeHistory()[0]);
    }
}
