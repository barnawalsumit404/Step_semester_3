package week12.assignment_problems;

import java.util.Scanner;

public class Problem1_DeliveryRouteLoopDetector {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static String detectLoop(Node head) {
        Node slow = head;
        Node fast = head;
        do {
            if (fast == null || fast.next == null) {
                return "Loop: no";
            }
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast);

        int length = 1;
        for (Node node = slow.next; node != slow; node = node.next) {
            length++;
        }

        Node loopStart = head;
        while (loopStart != slow) {
            loopStart = loopStart.next;
            slow = slow.next;
        }
        return "Loop: yes, starts at stop " + loopStart.data + ", length " + length;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        Node head = null;
        Node tail = null;
        Node loopTarget = null;
        int position = scanner.nextInt();
        for (int i = 0; i < count; i++) {
            Node node = new Node(scanner.nextInt());
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
            if (i == position) {
                loopTarget = node;
            }
        }
        if (tail != null && loopTarget != null) {
            tail.next = loopTarget;
        }
        System.out.println(detectLoop(head));
        scanner.close();
    }
}
