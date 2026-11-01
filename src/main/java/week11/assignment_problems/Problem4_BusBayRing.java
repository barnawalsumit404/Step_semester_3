package week11.assignment_problems;

public class Problem4_BusBayRing {
    static class BusBay {
        private final int[] buses;
        private int front;
        private int size;

        BusBay(int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException("Capacity must be positive");
            }
            buses = new int[capacity];
        }

        boolean insertFront(int bus) {
            if (isFull()) {
                return false;
            }
            front = (front - 1 + buses.length) % buses.length;
            buses[front] = bus;
            size++;
            return true;
        }

        boolean insertLast(int bus) {
            if (isFull()) {
                return false;
            }
            int rear = (front + size) % buses.length;
            buses[rear] = bus;
            size++;
            return true;
        }

        boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }
            front = (front + 1) % buses.length;
            size--;
            return true;
        }

        boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }
            size--;
            return true;
        }

        int getFront() {
            return isEmpty() ? -1 : buses[front];
        }

        int getRear() {
            return isEmpty() ? -1 : buses[(front + size - 1) % buses.length];
        }

        boolean isEmpty() {
            return size == 0;
        }

        boolean isFull() {
            return size == buses.length;
        }
    }

    public static void main(String[] args) {
        BusBay bay = new BusBay(3);
        System.out.println(bay.insertLast(101));
        System.out.println(bay.insertLast(102));
        System.out.println(bay.insertFront(100));
        System.out.println(bay.insertFront(99));
        System.out.println(bay.getRear());
        System.out.println(bay.isFull());
        System.out.println(bay.deleteLast());
        System.out.println(bay.insertFront(98));
        System.out.println(bay.getFront());
    }
}
