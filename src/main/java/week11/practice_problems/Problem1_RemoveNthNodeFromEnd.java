package week11.practice_problems;

public class Problem1_RemoveNthNodeFromEnd {
    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    static Node removeNthFromEnd(Node head, int n) {
        Node dummy = new Node(0);
        dummy.next = head;
        Node fast = dummy;
        Node slow = dummy;

        for (int step = 0; step < n; step++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    static Node list(int... values) {
        Node dummy = new Node(0);
        Node tail = dummy;
        for (int value : values) {
            tail.next = new Node(value);
            tail = tail.next;
        }
        return dummy.next;
    }

    static void print(Node head) {
        while (head != null) {
            System.out.print(head.value + (head.next == null ? "\n" : " -> "));
            head = head.next;
        }
    }

    public static void main(String[] args) {
        print(removeNthFromEnd(list(1, 2, 3, 4, 5), 2));
        print(removeNthFromEnd(list(1), 1));
    }
}
