package week11.practice_problems;

public class Problem3_AddTwoNumbers {
    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    static Node addNumbers(Node a, Node b) {
        Node dummy = new Node(0);
        Node tail = dummy;
        int carry = 0;

        while (a != null || b != null || carry != 0) {
            int sum = carry;
            if (a != null) {
                sum += a.value;
                a = a.next;
            }
            if (b != null) {
                sum += b.value;
                b = b.next;
            }
            tail.next = new Node(sum % 10);
            tail = tail.next;
            carry = sum / 10;
        }
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
        print(addNumbers(list(2, 4, 3), list(5, 6, 4)));
        print(addNumbers(list(9, 9), list(1)));
    }
}
