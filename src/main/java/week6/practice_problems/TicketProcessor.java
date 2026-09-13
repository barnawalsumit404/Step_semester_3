package week6.practice_problems;

public class TicketProcessor {
    public static String classifyGeneration(EventTicket ticket) {
        if (ticket instanceof PremiumWorkshopTicket) return "Multilevel descendant (3 generations deep)";
        if (ticket instanceof WorkshopTicket) return "Two-level descendant (2 generations deep)";
        if (ticket instanceof HackathonTicket) return "Hierarchical sibling (independent branch)";
        return "Standard Event Ticket";
    }

    public static double getTotalBalanceDue(EventTicket[] tickets) {
        if (tickets == null) return 0.0;
        double sum = 0.0;
        for (EventTicket t : tickets) {
            if (t == null) continue;
            sum += t.getBalanceDue();
        }
        return sum;
    }

    public static String batchPrint(EventTicket[] tickets) {
        StringBuilder sb = new StringBuilder();
        for (EventTicket t : tickets) {
            t.printTicket();
            sb.append(t.getClass().getSimpleName()).append(" | Balance: ").append(t.getBalanceDue());
            if (t instanceof WorkshopTicket) {
                WorkshopTicket w = (WorkshopTicket) t;
                sb.append(" [Track via downcast: ").append(w.getTrack()).append("]");
            }
            sb.append(" | ");
        }
        return sb.toString();
    }

    public static String processNightlySettlement(EventTicket[] tickets) {
        if (tickets == null) return "0 processed | 0 null skipped | 0 group | 0 individual";
        int processed = 0, nullSkipped = 0, group = 0, individual = 0;
        for (EventTicket t : tickets) {
            if (t == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if (t instanceof GroupTicket) group++; else individual++;
        }
        return processed + " processed | " + nullSkipped + " null skipped | " + group + " group | " + individual + " individual";
    }
}
