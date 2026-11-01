package week11.practice_problems;

public class Problem5_ReorderLinkedList {
    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    static void reorderList(Node head) {
        if (head == null || head.next == null) {
            return;
        }

        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node secondHalf = slow.next;
        slow.next = null;
        secondHalf = reverse(secondHalf);

        Node firstHalf = head;
        while (secondHalf != null) {
            Node firstNext = firstHalf.next;
            Node secondNext = secondHalf.next;
            firstHalf.next = secondHalf;
            secondHalf.next = firstNext;
            firstHalf = firstNext;
            secondHalf = secondNext;
        }
    }

    private static Node reverse(Node head) {
        Node previous = null;
        while (head != null) {
            Node next = head.next;
            head.next = previous;
            previous = head;
            head = next;
        }
        return previous;
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
        Node even = list(1, 2, 3, 4);
        reorderList(even);
        print(even);

        Node odd = list(1, 2, 3, 4, 5);
        reorderList(odd);
        print(odd);
    }
}
