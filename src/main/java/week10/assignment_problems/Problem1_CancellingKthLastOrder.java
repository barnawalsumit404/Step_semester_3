package week10.assignment_problems;

public class Problem1_CancellingKthLastOrder {
    public static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static Node removeKthFromEnd(Node head, int k) {
        Node dummy = new Node(0);
        dummy.next = head;
        Node lead = dummy;
        Node trailing = dummy;

        for (int step = 0; step <= k; step++) {
            lead = lead.next;
        }

        while (lead != null) {
            lead = lead.next;
            trailing = trailing.next;
        }

        trailing.next = trailing.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        Node orders = list(10, 20, 30, 40, 50);
        print(removeKthFromEnd(orders, 2));

        orders = list(10, 20, 30, 40, 50);
        print(removeKthFromEnd(orders, 5));
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
