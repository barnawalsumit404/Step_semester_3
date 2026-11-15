package week12.practice_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem1_OrderWalkthrough {
    static class Order {
        private final List<LineItem> items = new ArrayList<>();

        static class LineItem {
            private final String productName;
            private final int quantity;
            private final double unitPrice;

            LineItem(String productName, int quantity, double unitPrice) {
                this.productName = productName;
                this.quantity = quantity;
                this.unitPrice = unitPrice;
            }

            double total() {
                return quantity * unitPrice;
            }
        }

        class Cursor {
            private int index;

            boolean hasNext() {
                return index < items.size();
            }

            LineItem next() {
                if (!hasNext()) {
                    throw new IllegalStateException("No more line items");
                }
                return items.get(index++);
            }

            void reset() {
                index = 0;
            }
        }

        void addItem(String productName, int quantity, double unitPrice) {
            items.add(new LineItem(productName, quantity, unitPrice));
        }

        Cursor cursor() {
            return new Cursor();
        }

        double total() {
            double orderTotal = 0;
            for (LineItem item : items) {
                orderTotal += item.total();
            }
            return orderTotal;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int itemCount = scanner.nextInt();
        Order order = new Order();
        for (int i = 0; i < itemCount; i++) {
            order.addItem(scanner.next(), scanner.nextInt(), scanner.nextDouble());
        }

        Order.Cursor cursor = order.cursor();
        while (cursor.hasNext()) {
            Order.LineItem item = cursor.next();
            System.out.println(item.productName + " " + format(item.total()));
        }
        System.out.println("Total " + format(order.total()));
        System.out.println("cursor: inner (reads the order's array)");
        System.out.println("line item: static nested (needs no order)");
        scanner.close();
    }

    private static String format(double value) {
        return value == Math.rint(value) ? String.valueOf((long) value) : String.valueOf(value);
    }
}
