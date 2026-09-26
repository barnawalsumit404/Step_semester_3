public class Question5_FoodOrderPaymentSystem {
    interface IPaymentMethod {
        boolean pay(double amount);
    }

    static class CreditCardPayment implements IPaymentMethod {
        public boolean pay(double amount) {
            System.out.println("Payment via Credit Card successful.");
            return true;
        }
    }

    static class DigitalWalletPayment implements IPaymentMethod {
        public boolean pay(double amount) {
            System.out.println("Payment via Digital Wallet failed.");
            return false;
        }
    }

    static class FoodItem {
        private final String name;
        private final double price;

        public FoodItem(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }

    static class LineItem {
        private final FoodItem item;
        private final int quantity;

        public LineItem(FoodItem item, int quantity) {
            this.item = item;
            this.quantity = quantity;
        }

        public double getTotalPrice() {
            return item.getPrice() * quantity;
        }
    }

    static class Order {
        private final LineItem[] items = new LineItem[20];
        private int count = 0;
        private String status = "Pending Payment";

        public void addItem(LineItem item) {
            items[count++] = item;
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public double getTotalAmount() {
            double total = 0;
            for (int i = 0; i < count; i++) {
                total += items[i].getTotalPrice();
            }
            return total;
        }

        public boolean makePayment(IPaymentMethod paymentMethod) {
            if (paymentMethod.pay(getTotalAmount())) {
                status = "Paid";
                System.out.println("Order status: Paid");
                return true;
            }
            status = "Pending Payment";
            System.out.println("Order status: Pending Payment");
            return false;
        }
    }

    public static void main(String[] args) {
        Order order1 = new Order();
        order1.addItem(new LineItem(new FoodItem("Pizza", 120), 2));
        order1.addItem(new LineItem(new FoodItem("Soda", 40), 1));

        if (order1.isEmpty()) {
            System.out.println("Cannot place order: Order must contain at least one item.");
        } else {
            System.out.println("Order created. Added Pizza (Qty 2), Soda (Qty 1).");
            order1.makePayment(new CreditCardPayment());
        }

        Order order2 = new Order();
        order2.addItem(new LineItem(new FoodItem("Burger", 150), 1));
        order2.makePayment(new DigitalWalletPayment());
    }
}
