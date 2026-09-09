package week4.assignment_problems;

public class DeliverySlot {
    private final String orderId;
    private final String timeSlot;

    public DeliverySlot(String orderId, String timeSlot) {
        if (orderId == null || orderId.trim().isEmpty()) {
            throw new IllegalArgumentException("Order ID cannot be empty.");
        }
        this.orderId = orderId.trim();
        this.timeSlot = (timeSlot == null || timeSlot.trim().isEmpty()) ? "ASAP" : timeSlot.trim();
    }

    public DeliverySlot(String orderId) {
        this(orderId, "ASAP");
    }

    public boolean isPeakHour() {
        return "12:00-13:00".equals(timeSlot)
                || "13:00-14:00".equals(timeSlot)
                || "19:00-20:00".equals(timeSlot)
                || "20:00-21:00".equals(timeSlot);
    }

    public static void main(String[] args) {
        System.out.println(new DeliverySlot("ORD101", "13:00-14:00").isPeakHour());
        System.out.println(new DeliverySlot("ORD102").isPeakHour());
    }
}
