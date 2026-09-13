// Problem 3: Late-Registration Penalty Override & Audit Trail
public class Problem3_LateFeeUtils {
    // Apply a late fee to a workshop ticket and return a defensive copy of the history
    public static double[] applyLateFeeAndGetHistory(week6.practice_problems.WorkshopTicket w, double amount) {
        if (w == null) return new double[0];
        w.applyLateFee(amount);
        return w.getLateFeeHistory();
    }
}
