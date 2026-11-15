package week12.assignment_problems;

import java.util.Scanner;

public class Problem4_LibraryReturnsByDueDay {
    static class Node {
        String title;
        int dueDay;
        Node next;

        Node(String title, int dueDay) {
            this.title = title;
            this.dueDay = dueDay;
        }
    }

    static Node sortByDueDay(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node right = slow.next;
        slow.next = null;
        return merge(sortByDueDay(head), sortByDueDay(right));
    }

    private static Node merge(Node left, Node right) {
        Node dummy = new Node("", 0);
        Node tail = dummy;
        while (left != null && right != null) {
            if (left.dueDay <= right.dueDay) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }
        tail.next = left == null ? right : left;
        return dummy.next;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        Node head = null;
        Node tail = null;
        for (int i = 0; i < count; i++) {
            String[] book = scanner.next().split(":");
            Node node = new Node(book[0], Integer.parseInt(book[1]));
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
        }
        print(sortByDueDay(head));
        scanner.close();
    }

    private static void print(Node head) {
        while (head != null) {
            System.out.print(head.title + ":" + head.dueDay + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }
}
