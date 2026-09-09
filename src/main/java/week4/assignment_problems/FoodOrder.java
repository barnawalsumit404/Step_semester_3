package week4.assignment_problems;

public class FoodOrder {
    private final String studentName;
    private final String dishName;
    private boolean delivered;

    public FoodOrder(String studentName, String dishName) {
        String validStudent = (studentName == null) ? null : studentName.trim();
        String validDish = (dishName == null) ? null : dishName.trim();

        if (validStudent == null || validStudent.isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be blank.");
        }
        if (validDish == null || validDish.isEmpty()) {
            throw new IllegalArgumentException("Dish name cannot be blank.");
        }

        this.studentName = validStudent;
        this.dishName = validDish;
    }

    public void markDelivered() {
        if (delivered) {
            System.out.println("This order was already delivered.");
            return;
        }

        delivered = true;
        System.out.println("Order delivered for " + studentName + ": " + dishName);
    }

    public static void processBatch(String[][] rawOrders) {
        if (rawOrders == null) {
            System.out.println("Valid: 0 | Rejected: 0");
            return;
        }

        int valid = 0;
        int rejected = 0;

        for (String[] order : rawOrders) {
            if (order == null || order.length != 2) {
                rejected++;
                continue;
            }

            try {
                new FoodOrder(order[0], order[1]);
                valid++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }

    public static void main(String[] args) {
        String[][] orders = {
                {"Ravi", "Paneer Butter Masala"},
                {"", "Chole Bhature"},
                {"Meera", " "},
                {"Divya", "Veg Biryani"}
        };

        processBatch(orders);

        FoodOrder order = new FoodOrder("Ravi", "Paneer Butter Masala");
        order.markDelivered();
        order.markDelivered();
    }
}
