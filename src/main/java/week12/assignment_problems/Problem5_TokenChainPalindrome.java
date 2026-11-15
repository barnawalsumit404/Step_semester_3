package week12.assignment_problems;

import java.util.Scanner;

public class Problem5_TokenChainPalindrome {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        Node firstHalfEnd = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            firstHalfEnd = firstHalfEnd.next;
            fast = fast.next.next;
        }

        Node secondHalf = reverse(firstHalfEnd.next);
        Node secondHalfHead = secondHalf;
        Node first = head;
        boolean palindrome = true;
        while (secondHalf != null) {
            if (first.data != secondHalf.data) {
                palindrome = false;
                break;
            }
            first = first.next;
            secondHalf = secondHalf.next;
        }

        firstHalfEnd.next = reverse(secondHalfHead);
        return palindrome;
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
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
        System.out.println("Palindrome: " + isPalindrome(head));
        System.out.println();
        System.out.print("List after check: ");
        print(head);
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
