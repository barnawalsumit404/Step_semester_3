package week5.assignment_problems;

import java.util.Arrays;

public final class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("memberId cannot be empty.");
        }
        if (bookIds == null) {
            throw new IllegalArgumentException("bookIds cannot be null.");
        }

        for (String id : bookIds) {
            if (id == null || !id.matches("BK-\\d{3}")) {
                throw new IllegalArgumentException("Invalid book id: " + id);
            }
        }

        this.memberId = memberId.trim();
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
    }

    public String[] getBookIds() {
        return Arrays.copyOf(bookIds, bookIds.length);
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        if (index < 0 || index >= bookIds.length) {
            throw new IllegalArgumentException("Index out of range.");
        }
        if (newId == null || !newId.matches("BK-\\d{3}")) {
            throw new IllegalArgumentException("Invalid replacement book id.");
        }

        String[] updated = Arrays.copyOf(bookIds, bookIds.length);
        updated[index] = newId;
        return new LoanReceipt(this.memberId, updated);
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        if (receipts == null) {
            return "0 processed | 0 null skipped | 0 reference-only | 0 regular";
        }

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceipt receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + referenceOnly + " reference-only | " + regular + " regular";
    }

    public static void main(String[] args) {
        try {
            new LoanReceipt("LIB-8841", new String[]{"BK-100", "bad"});
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        LoanReceipt r = new LoanReceipt("LIB-8841", new String[]{"BK-100", "BK-101"});
        String[] ids = r.getBookIds();
        ids[0] = "HACKED";
        System.out.println(Arrays.toString(r.getBookIds()));

        System.out.println(processNightlyCirculation(new LoanReceipt[] {
                new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
                null,
                new LoanReceipt("LIB-002", new String[]{"BK-201"})
        }));
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }
}
