package week12.assignment_problems;

import java.util.Scanner;

public class Problem2_ExamScriptBatches {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node reverseInGroups(Node head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        Node previousGroupTail = null;
        Node groupHead = head;
        while (groupHead != null) {
            Node groupTail = groupHead;
            for (int count = 1; count < k && groupTail != null; count++) {
                groupTail = groupTail.next;
            }
            if (groupTail == null) {
                break;
            }

            Node nextGroup = groupTail.next;
            Node previous = nextGroup;
            Node current = groupHead;
            while (current != nextGroup) {
                Node next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }

            if (previousGroupTail == null) {
                head = previous;
            } else {
                previousGroupTail.next = previous;
            }
            previousGroupTail = groupHead;
            groupHead = nextGroup;
        }
        return head;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int k = scanner.nextInt();
        Node head = null;
        Node tail = null;
        for (int i = 0; i < count; i++) {
            Node node = new Node(scanner.nextInt());
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
        }
        print(reverseInGroups(head, k));
        scanner.close();
    }

    private static void print(Node head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }
}
