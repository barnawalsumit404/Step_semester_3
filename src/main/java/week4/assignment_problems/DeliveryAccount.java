package week4.assignment_problems;

public class DeliveryAccount {
    static {
        System.out.println("Delivery system initialized.");
    }

    private final String studentId;
    private final double orderValue;

    public DeliveryAccount(String studentId, double orderValue) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be empty.");
        }
        if (orderValue < 0) {
            throw new IllegalArgumentException("Order value cannot be negative.");
        }

        this.studentId = studentId.trim();
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    public final double calculateSurgeFee(int delayMinutes) {
        if (delayMinutes < 0) {
            throw new IllegalArgumentException("Delay minutes cannot be negative.");
        }
        if (delayMinutes == 0 || orderValue == 0) {
            return 0.0;
        }
        return orderValue * 0.01 * delayMinutes;
    }

    public String getStudentId() {
        return studentId;
    }

    public double getOrderValue() {
        return orderValue;
    }

    public static void processAccount(DeliveryAccount account, double amount, int delayMinutes) {
        if (account == null) {
            return;
        }

        double fee = account instanceof PremiumDeliveryAccount
                ? account.calculateSurgeFee(delayMinutes) * 1.10
                : account.calculateSurgeFee(delayMinutes);

        System.out.println("Processed: " + account.getStudentId() + " | surge fee = " + fee);
    }

    public static void processBatch(DeliveryAccount[] accounts, double[] amounts, int[] delayMinutesArray) {
        if (accounts == null || amounts == null || delayMinutesArray == null) {
            System.out.println("Invalid batch input; nothing processed.");
            return;
        }

        int limit = Math.min(accounts.length, Math.min(amounts.length, delayMinutesArray.length));
        int processed = 0;
        int nullSkipped = 0;
        int premium = 0;
        int regular = 0;
        double grandTotal = 0.0;

        for (int i = 0; i < limit; i++) {
            DeliveryAccount account = accounts[i];
            if (account == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (account instanceof PremiumDeliveryAccount) {
                premium++;
            } else {
                regular++;
            }

            double fee = account instanceof PremiumDeliveryAccount
                    ? account.calculateSurgeFee(delayMinutesArray[i]) * 1.10
                    : account.calculateSurgeFee(delayMinutesArray[i]);

            grandTotal += fee;
        }

        System.out.println(processed + " processed | " + nullSkipped + " null skipped | " + premium + " premium | " + regular + " regular | grand total surge fees = " + grandTotal);
    }

    public static void main(String[] args) {
        DeliveryAccount[] accounts = {
                new PremiumDeliveryAccount("STU001", 500),
                null,
                new DeliveryAccount("STU002", 300)
        };

        double[] amounts = {500, 400, 300};
        int[] delayMinutesArray = {10, 5, 0};

        processBatch(accounts, amounts, delayMinutesArray);
    }
}

class PremiumDeliveryAccount extends DeliveryAccount {
    public PremiumDeliveryAccount(String studentId, double orderValue) {
        super(studentId, orderValue);
    }

    public PremiumDeliveryAccount(String studentId) {
        super(studentId);
    }
}
