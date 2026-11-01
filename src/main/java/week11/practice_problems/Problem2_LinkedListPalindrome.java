package week11.practice_problems;

public class Problem2_LinkedListPalindrome {
    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    static boolean isPalindrome(Node head) {
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
        Node first = head;
        Node second = secondHalf;
        boolean palindrome = true;
        while (second != null) {
            if (first.value != second.value) {
                palindrome = false;
                break;
            }
            first = first.next;
            second = second.next;
        }
        slow.next = reverse(secondHalf);
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

    static Node list(int... values) {
        Node dummy = new Node(0);
        Node tail = dummy;
        for (int value : values) {
            tail.next = new Node(value);
            tail = tail.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(list(1, 2, 2, 1)));
        System.out.println(isPalindrome(list(1, 2, 3)));
    }
}
