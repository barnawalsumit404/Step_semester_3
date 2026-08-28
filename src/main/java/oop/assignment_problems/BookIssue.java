package oop.assigment_problems;

public class BookIssue {

    String title;
    String borrowerName;
    int daysOverdue;

    // Constructor
    BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    // Calculates the fine for this particular book
    double fineAmount() {
        if (daysOverdue > 0) {
            return daysOverdue * 5;
        }
        return 0;
    }

    // Checks whether this particular book is severely overdue
    boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    /*
     * totalFineCollected is static because it calculates the total fine
     * for multiple BookIssue objects, so it belongs to the class as a whole.
     * fineAmount is not static because it calculates the fine for one
     * particular BookIssue object.
     */
    static double totalFineCollected(BookIssue[] issues) {
        double total = 0;

        for (BookIssue issue : issues) {
            total += issue.fineAmount();
        }

        return total;
    }

    public static void main(String[] args) {

        BookIssue[] issues = {
                new BookIssue("Clean Code", "Alice", 18),
                new BookIssue("Effective Java", "Bob", 5),
                new BookIssue("Refactoring", "Charlie", 0),
                new BookIssue("DSA Handbook", "David", 21),
                new BookIssue("Design Patterns", "Eve", 9)
        };

        for (BookIssue issue : issues) {
            if (issue.isSeverelyOverdue()) {
                System.out.println(issue.title + " - "
                        + issue.daysOverdue + " days - Severely overdue");
            } else {
                System.out.println(issue.title + " - "
                        + issue.daysOverdue + " days - OK");
            }
        }

        double totalFine = BookIssue.totalFineCollected(issues);

        System.out.println("Total fine collected: Rs " + totalFine);
    }
}