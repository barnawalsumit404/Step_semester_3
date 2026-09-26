public class Question5_TheCampusCanteenSmartCard {
    interface PricingPlan {
        double applyDiscount(double amount);
    }

    static class DayScholarPlan implements PricingPlan {
        public double applyDiscount(double amount) {
            return amount;
        }
    }

    static class HostellerPlan implements PricingPlan {
        public double applyDiscount(double amount) {
            return amount * 0.90;
        }
    }

    static class StaffPlan implements PricingPlan {
        public double applyDiscount(double amount) {
            return amount * 0.80;
        }
    }

    static class SmartCard {
        private final String cardId;
        private final PricingPlan plan;
        private double balance;
        private boolean blocked;

        public SmartCard(String cardId, PricingPlan plan, double balance) {
            this.cardId = cardId;
            this.plan = plan;
            this.balance = balance;
        }

        public void topUp(double amount) {
            if (blocked) {
                System.out.println("Top-up rejected: card is blocked.");
                return;
            }
            if (amount >= 100 && amount <= 5000 && balance + amount <= 5000) {
                balance += amount;
                System.out.println("C-2045 topped up with ₹" + amount + ". Balance: ₹" + balance);
            } else {
                System.out.println("Top-up rejected.");
            }
        }

        public void purchase(String itemName, double price) {
            if (blocked) {
                System.out.println("Purchase failed: card is blocked.");
                return;
            }

            double payable = plan.applyDiscount(price);
            if (payable > balance) {
                System.out.println("Purchase failed: Insufficient balance.");
                return;
            }

            balance -= payable;
            System.out.println(itemName + " purchased for ₹" + payable + ". Balance: ₹" + balance);
        }

        public void refund(String itemName, double amount) {
            if (blocked) {
                System.out.println("Refund rejected: card is blocked.");
                return;
            }
            balance += amount;
            System.out.println("Refund of ₹" + amount + " for " + itemName + " processed. Balance: ₹" + balance);
        }

        public void block() {
            blocked = true;
        }

        public void unBlock() {
            blocked = false;
        }
    }

    public static void main(String[] args) {
        SmartCard card = new SmartCard("C-2045", new HostellerPlan(), 0);
        card.topUp(500);
        card.purchase("Veg Thali", 120);
        card.purchase("Cold Coffee", 60);
        card.purchase("Burger", 400);
        card.refund("Veg Thali", 108);
    }
}
