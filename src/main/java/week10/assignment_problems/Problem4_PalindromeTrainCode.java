package week10.assignment_problems;

public class Problem4_PalindromeTrainCode {
    public static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node secondHalf = reverse(slow.next);
        Node comparison = secondHalf;
        Node firstHalf = head;
        boolean palindrome = true;
        while (comparison != null) {
            if (firstHalf.data != comparison.data) {
                palindrome = false;
                break;
            }
            firstHalf = firstHalf.next;
            comparison = comparison.next;
        }

        slow.next = reverse(secondHalf);
        return palindrome;
    }

    private static Node reverse(Node head) {
        Node previous = null;
        Node current = head;
        while (current != null) {
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(list(1, 2, 3, 2, 1)));
        System.out.println(isPalindrome(list(1, 2, 3, 2)));
        System.out.println(isPalindrome(list(1, 2, 2, 1)));
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
}
