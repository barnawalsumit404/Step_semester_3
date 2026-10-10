package week10.assignment_problems;

public class Problem3_MergingTicketCounters {
    public static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static Node mergeSorted(Node a, Node b) {
        Node dummy = new Node(0);
        Node tail = dummy;

        while (a != null && b != null) {
            if (a.data <= b.data) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }

        tail.next = a != null ? a : b;
        return dummy.next;
    }

    public static void main(String[] args) {
        Node first = list(3, 8, 15);
        Node second = list(5, 8, 12, 20);
        print(mergeSorted(first, second));
        print(mergeSorted(null, list(4)));
    }

    private static Node list(int... values) {
        Node dummy = new Node(0);
        Node tail = dummy;
        for (int value : values) {
            tail.next = new Node(value);
            tail = tail.next;
        }
        return dummy.next;
    }

    private static void print(Node head) {
        while (head != null) {
            System.out.print(head.data);
            head = head.next;
            if (head != null) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
}
